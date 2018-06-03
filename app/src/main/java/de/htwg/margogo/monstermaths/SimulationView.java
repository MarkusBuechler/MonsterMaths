package de.htwg.margogo.monstermaths;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import static de.htwg.margogo.monstermaths.MiscUtilities.distance;

/**
 * This is an highly adopted example of using the accelerometer to integrate the device's
 * acceleration to a position using the Verlet method. This is illustrated with
 * a very simple particle system comprised of a few iron balls freely moving on
 * an inclined wooden table. The inclination of the virtual table is controlled
 * by the device's accelerometer.
 * Should be later the prototype for each level
 */
class SimulationView extends FrameLayout implements SensorEventListener {
    // diameter of the balls in meters
    private static final float sBallDiameter = 0.010f;
    private static final float sBallDiameter2 = sBallDiameter * sBallDiameter;

    private GameActivity accelerometerPlayActivity;
    private final int mDstWidth;
    private final int mDstHeight;

    private Sensor mAccelerometer;
    private long mLastT;

    private float mXDpi;
    private float mYDpi;
    private float mMetersToPixelsX;
    private float mMetersToPixelsY;
    private float mXOrigin;
    private float mYOrigin;
    private float mSensorX;
    private float mSensorY;
    private float mHorizontalBound;
    private float mVerticalBound;
    private final ParticleSystem mParticleSystem;
    private float counter = 0;

    int NUM_MONSTERS;
    int NUM_NUMBERS;

    MonsterDataHolder monsterDataHolder[];


    /*
     * Each of our particle holds its previous and current position, its
     * acceleration. for added realism each particle has its own friction
     * coefficient.
     */
    class Particle extends View {
        private float mPosX = (float) Math.random();
        private float mPosY = (float) Math.random();
        private float mVelX;
        private float mVelY;
        private int i;

        public Particle(Context context, int i) {
            super(context); // TODO: should be user later maybe, or use unique lists
            this.i = i;
        }

        public Particle(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public Particle(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        public Particle(Context context, AttributeSet attrs, int defStyleAttr,
                        int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        public void computePhysics(float sx, float sy, float dT) {

            final float ax = -sx/5;
            final float ay = -sy/5;

            mPosX += mVelX * dT + ax * dT * dT / 2;
            mPosY += mVelY * dT + ay * dT * dT / 2;

            mVelX += ax * dT;
            mVelY += ay * dT;
        }

        public void computePhysicsMonster() {

            counter = counter % 180;
            counter++;

            final float dT = 0.01f;

            final float ax = -0.5f/5;
            final float ay = -0/5;

            if (counter > 90) {
                mPosX -= mVelX * dT - ax * dT * dT / 2;
                mVelX -= ax * dT;
            } else {
                mPosX += mVelX * dT + ax * dT * dT / 2;
                mVelX += ax * dT;
            }

            mPosY += mVelY * dT + ay * dT * dT / 2;
            mVelY += ay * dT;

        }

        /*
         * Resolving constraints and collisions with the Verlet integrator
         * can be very simple, we simply need to move a colliding or
         * constrained particle in such way that the constraint is
         * satisfied.
         */
        public void resolveCollisionWithBounds() {
            final float xmax = mHorizontalBound;
            final float ymax = mVerticalBound;
            final float x = mPosX;
            final float y = mPosY;

            if (x > xmax) {
                mPosX = xmax;
                mVelX = 0;
            } else if (x < -xmax) {
                mPosX = -xmax;
                mVelX = 0;
            }
            if (y > ymax) {
                mPosY = ymax;
                mVelY = 0;
            } else if (y < -ymax) {
                mPosY = -ymax;
                mVelY = 0;
            }
        }
    }

    /*
     * A particle system is just a collection of particles
     */
    class ParticleSystem {

        final int NUM_MONSTERS = accelerometerPlayActivity.dataHolder.getNumMonsters();
        final int NUM_NUMBERS = accelerometerPlayActivity.dataHolder.getNumNumbers();
        MonsterDataHolder monsterDataHolder[] = accelerometerPlayActivity.dataHolder.getMonsterDataHolderList();

        private Particle mBall = new Particle(getContext(),1);
        private Particle mGoal = new Particle(getContext(),1);

        private Particle mMonsters[] = new Particle[NUM_MONSTERS];
        private Particle mNumber[] = new Particle[NUM_NUMBERS];

        public boolean lockFinish = false;
        private int goalUnlocked = 0;
        private boolean uniqueLockNumber1 = false;
        private boolean uniqueLockNumber2 = false;


        ParticleSystem() {
            /*
             * Initially our particles have no speed or acceleration
             */

            //monsterDataHolder = accelerometerPlayActivity.dataHolder.getMonsterDataHolderList();

            // Ball has always same start position
            mBall.mPosX = 0f;
            mBall.mPosY = -0.045f;
            mBall.setBackgroundResource(R.drawable.emoji_smile_256);
            mBall.setLayerType(LAYER_TYPE_HARDWARE, null);
            addView(mBall, new ViewGroup.LayoutParams(mDstWidth, mDstHeight));

            for (int i = 0; i < mMonsters.length; i++) {
                mMonsters[i] = new Particle(getContext(),2);
                mMonsters[i].mPosX = monsterDataHolder[i].xPos;
                mMonsters[i].mPosY = monsterDataHolder[i].yPos;
                mMonsters[i].setBackgroundResource(R.drawable.blue_monster_128); // validate getType
                mMonsters[i].setLayerType(LAYER_TYPE_HARDWARE, null);
                addView(mMonsters[i], new ViewGroup.LayoutParams(mDstWidth, mDstHeight));
            }

            // Goal is always same
            mGoal.mPosX = 0.f;
            mGoal.mPosY = 0.045f;
            mGoal.setBackgroundResource(R.drawable.treasure_closed_128);
            mGoal.setLayerType(LAYER_TYPE_HARDWARE, null);
            addView(mGoal, new ViewGroup.LayoutParams(mDstWidth, mDstHeight));


            mNumber[0] = new Particle(getContext(),2);
            mNumber[0].mPosX = 0f;
            mNumber[0].mPosY = -0.015f;
            mNumber[0].setBackgroundResource(R.drawable.one);
            mNumber[0].setLayerType(LAYER_TYPE_HARDWARE, null);
            addView(mNumber[0], new ViewGroup.LayoutParams(mDstWidth, mDstHeight));

            mNumber[1] = new Particle(getContext(),2);
            mNumber[1].mPosX = 0.02f;
            mNumber[1].mPosY = 0.0065f;
            mNumber[1].setBackgroundResource(R.drawable.eigth);
            mNumber[1].setLayerType(LAYER_TYPE_HARDWARE, null);
            addView(mNumber[1], new ViewGroup.LayoutParams(mDstWidth, mDstHeight));


            /*
            for (int i = 0; i < mNumber.length; i++) {
                mNumber[i] = new Particle(getContext(),2);
                mNumber[i].mPosX = monsterDataHolder[i].xPos;
                mNumber[i].mPosY = monsterDataHolder[i].yPos;
                mNumber[i].setBackgroundResource(R.drawable.one); // validate getType
                mNumber[i].setLayerType(LAYER_TYPE_HARDWARE, null);
                addView(mNumber[i], new ViewGroup.LayoutParams(mDstWidth, mDstHeight));
            }
            */

        }


        /*
         * Update the position of each particle in the system using the
         * Verlet integrator.
         */
        private void updatePositions(float sx, float sy, long timestamp) {

            // sx:: links + 5-10, rechts, -5-10
            //sy :: senkrecht nach vorne 10, rückseite vorne -10
            final long t = timestamp;
            if (mLastT != 0) {
                final float dT = (float) (t - mLastT) / 3000.f /** (1.0f / 3000000000.0f)*/;
                    mBall.computePhysics(sx, sy, dT);
                    final int count2 = mMonsters.length;
                    for (int i = 0; i < count2; i++) {
                    Particle ball = mMonsters[i];
                    ball.computePhysicsMonster();
                }
            }
            mLastT = t;
        }



        /*
         * Performs one iteration of the simulation. First updating the
         * position of all the particles and resolving the constraints and
         * collisions.
         */
        public void update(float sx, float sy, long now) {
            // update the system's positions
            updatePositions(sx, sy, now);

            /*
             * Resolve collisions, each particle is tested against every
             * other particle for collision. If a collision is detected the
             * particle is moved away using a virtual spring of infinite
             * stiffness.
             */

            checkFinish();
            //checkCollision();
            //checkNumberCollected();
            mBall.resolveCollisionWithBounds();

            final int count2 = mMonsters.length;
            for (int i = 0; i < count2; i++) {
                Particle curr = mMonsters[i];
                curr.resolveCollisionWithBounds();
            }

        }

        /**
         * Check if player crashed into a monster
         * TODO: Optimize this
         */
        private void checkCollision() {

            // use for loop if multiple monsters
            final int count2 = mMonsters.length;

            Particle ball = mBall;
            final float x1 = ball.mPosX;
            final float y1 = ball.mPosY;

            Particle monster = mMonsters[0];
            final float x2 = monster.mPosX;
            final float y2 = monster.mPosY;

            final double dis = distance(x1,y1,x2,y2);

            if (dis < 0.004)  {
                Toast.makeText(getContext(), "Hey", Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * Check if player touched the treasure
         */
        private void checkFinish() {

            Particle ball = mBall;
            final float x1 = ball.mPosX;
            final float y1 = ball.mPosY;

            Particle treasure = mGoal;
            final float x2 = treasure.mPosX;
            final float y2 = treasure.mPosY;

            final double dis = distance(x1,y1,x2,y2);

            if (dis < 0.004 && goalUnlocked >= 2 && !lockFinish)  {
                mGoal.setBackgroundResource(R.drawable.treasure_open_128);
                //Toast.makeText(getContext(), ""+ goalUnlocked, Toast.LENGTH_SHORT).show();
                lockFinish = true;



                // share data between singleton class !
                DataHolderInterface holder = accelerometerPlayActivity.dataHolder;
                holder.setLock(true);


                // after 1 sec activity is closed
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        accelerometerPlayActivity.finish(); // finished activity, maybe add finish screen before
                    }
                }, 1000);



            }
        }

        /**
         * Check if player collected the correct numbers.
         * TODO: make this abstract !
         */
        private void checkNumberCollected() {

            Particle ball = mBall;
            final float x1 = ball.mPosX;
            final float y1 = ball.mPosY;

            final float x1_2 = mNumber[0].mPosX;
            final float y1_2 = mNumber[0].mPosY;

            final double dis = distance(x1,y1,x1_2,y1_2);

            if (dis < 0.004 && !uniqueLockNumber1)  {
                mNumber[0].setVisibility(INVISIBLE);
                goalUnlocked += 1;
                uniqueLockNumber1 = true;
            }

            final float x2_2 = mNumber[1].mPosX;
            final float y2_2 = mNumber[1].mPosY;

            final double dis2 = distance(x1,y1,x2_2,y2_2);

            if (dis2 < 0.004 && !uniqueLockNumber2)  {
                mNumber[1].setVisibility(INVISIBLE);
                goalUnlocked += 1;
                uniqueLockNumber2 = true;
            }
        }

        public int getParticleCount2() {
            return monsterDataHolder.length;
        }

        public float getPosX2(int i) {
            return monsterDataHolder[i].getxPos();
        }

        public float getPosY2(int i) {
            return monsterDataHolder[i].getyPos();
        }

        public int getParticleCount3() {
            return mNumber.length;
        }

        public float getPosX3(int i) {
            return mNumber[i].mPosX;
        }

        public float getPosY3(int i) {
            return mNumber[i].mPosY;
        }

    }

    public void startSimulation() {
        /*
         * It is not necessary to get accelerometer events at a very high
         * rate, by using a slower rate (SENSOR_DELAY_UI), we get an
         * automatic low-pass filter, which "extracts" the gravity component
         * of the acceleration. As an added benefit, we use less power and
         * CPU resources.
         */
        accelerometerPlayActivity.mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    public void stopSimulation() {
        accelerometerPlayActivity.mSensorManager.unregisterListener(this);
    }

    public SimulationView(GameActivity accelerometerPlayActivity, Context context) {
        super(context);
        this.accelerometerPlayActivity = accelerometerPlayActivity;
        mAccelerometer = accelerometerPlayActivity.mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        DisplayMetrics metrics = new DisplayMetrics();
        accelerometerPlayActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mXDpi = metrics.xdpi;
        mYDpi = metrics.ydpi;
        mMetersToPixelsX = mXDpi / 0.0254f;
        mMetersToPixelsY = mYDpi / 0.0254f;

        // rescale the ball so it's about 0.5 cm on screen
        mDstWidth = (int) (sBallDiameter * mMetersToPixelsX + 0.5f);
        mDstHeight = (int) (sBallDiameter * mMetersToPixelsY + 0.5f);
        mParticleSystem = new ParticleSystem();

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inDither = true;
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // compute the origin of the screen relative to the origin of
        // the bitmap
        mXOrigin = (w - mDstWidth) * 0.5f;
        mYOrigin = (h - mDstHeight) * 0.5f;
        mHorizontalBound = ((w / mMetersToPixelsX - sBallDiameter) * 0.5f);
        mVerticalBound = ((h / mMetersToPixelsY - sBallDiameter) * 0.5f);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
            return;
        /*
         * record the accelerometer data, the event's timestamp as well as
         * the current time. The latter is needed so we can calculate the
         * "present" time during rendering. In this application, we need to
         * take into account how the screen is rotated with respect to the
         * sensors (which always return data in a coordinate space aligned
         * to with the screen in its native orientation).
         */

        switch (accelerometerPlayActivity.mDisplay.getRotation()) {
            case Surface.ROTATION_0:
                mSensorX = event.values[0];
                mSensorY = event.values[1];
                break;
            case Surface.ROTATION_90:
                mSensorX = -event.values[1];
                mSensorY = event.values[0];
                break;
            case Surface.ROTATION_180:
                mSensorX = -event.values[0];
                mSensorY = -event.values[1];
                break;
            case Surface.ROTATION_270:
                mSensorX = event.values[1];
                mSensorY = -event.values[0];
                break;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /*
         * Compute the new position of our object, based on accelerometer
         * data and present time.
         */
        final ParticleSystem particleSystem = mParticleSystem;
        final long now = System.currentTimeMillis();
        final float sx = mSensorX;
        final float sy = mSensorY;

        particleSystem.update(sx, sy, now);

        final float xc = mXOrigin;
        final float yc = mYOrigin;
        final float xs = mMetersToPixelsX;
        final float ys = mMetersToPixelsY;

        /*
         * Player
         * We transform the canvas so that the coordinate system matches
         * the sensors coordinate system with the origin in the center
         * of the screen and the unit is the meter.
         */
        final float x = xc + particleSystem.mBall.mPosX * xs;
        final float y = yc - particleSystem.mBall.mPosY * ys;
        particleSystem.mBall.setTranslationX(x);
        particleSystem.mBall.setTranslationY(y);

        /*
         * MonsterS
         * We transform the canvas so that the coordinate system matches
         * the sensors coordinate system with the origin in the center
         * of the screen and the unit is the meter.
         */
        final int count2 = particleSystem.getParticleCount2();
        for (int i = 0; i < count2; i++) {

            final float x2 = xc + particleSystem.getPosX2(i) * xs;
            final float y2 = yc - particleSystem.getPosY2(i) * ys;
            particleSystem.mMonsters[i].setTranslationX(x2);
            particleSystem.mMonsters[i].setTranslationY(y2);
        }

        /*
         * Treasure
         * We transform the canvas so that the coordinate system matches
         * the sensors coordinate system with the origin in the center
         * of the screen and the unit is the meter.
         */
        final float x3 = xc + particleSystem.mGoal.mPosX * xs;
        final float y3 = yc - particleSystem.mGoal.mPosY * ys;
        particleSystem.mGoal.setTranslationX(x3);
        particleSystem.mGoal.setTranslationY(y3);

        /*
         * NumberS
         * We transform the canvas so that the coordinate system matches
         * the sensors coordinate system with the origin in the center
         * of the screen and the unit is the meter.
         */
        final int count3 = particleSystem.getParticleCount3();
        for (int i = 0; i < count3; i++) {

            final float x4 = xc + particleSystem.getPosX3(i) * xs;
            final float y4 = yc - particleSystem.getPosY3(i) * ys;
            particleSystem.mNumber[i].setTranslationX(x4);
            particleSystem.mNumber[i].setTranslationY(y4);
        }

        // and make sure to redraw asap
        invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
