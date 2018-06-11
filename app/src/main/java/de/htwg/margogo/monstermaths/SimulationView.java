package de.htwg.margogo.monstermaths;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import static de.htwg.margogo.monstermaths.MiscUtilities.distance;

/**
 * This is an adopted example of using the accelerometer to integrate the device's
 * acceleration to a position using the Verlet method.
 * The inclination of the virtual table is controlled by the device's accelerometer.
 */
class SimulationView extends FrameLayout implements SensorEventListener {

    private static final float sBallDiameter = 0.010f;
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

        private float counter = 0;

        public Particle(Context context) {
            super(context);
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
        NumberDataHolder numberDataHolder[] = accelerometerPlayActivity.dataHolder.getNumberDataHolderList();

        private Particle myBall = new Particle(getContext());
        private Particle myGoal = new Particle(getContext());

        private Particle myMonsters[] = new Particle[NUM_MONSTERS];
        private Particle myNumbers[] = new Particle[NUM_NUMBERS];

        private boolean lockFinish = false;
        private Boolean[] uniqueNumberLocks = new Boolean[NUM_NUMBERS];
        private int currentResult = 0;


        ParticleSystem() {
            /*
             * Initially our particles have no speed or acceleration
             */

            // Ball has always same start position
            myBall.mPosX = 0f;
            myBall.mPosY = -0.045f;
            myBall.setBackgroundResource(R.drawable.emoji_smile_256);
            myBall.setLayerType(LAYER_TYPE_HARDWARE, null);
            addView(myBall, new ViewGroup.LayoutParams(mDstWidth, mDstHeight));

            // Goal has always same start position
            myGoal.mPosX = 0.f;
            myGoal.mPosY = 0.045f;
            myGoal.setBackgroundResource(R.drawable.treasure_closed_128);
            myGoal.setLayerType(LAYER_TYPE_HARDWARE, null);
            addView(myGoal, new ViewGroup.LayoutParams(mDstWidth, mDstHeight));

            // Loop over numbers
            for (int i = 0; i < myNumbers.length; i++) {
                myNumbers[i] = new Particle(getContext());
                myNumbers[i].mPosX = numberDataHolder[i].xPos;
                myNumbers[i].mPosY = numberDataHolder[i].yPos;
                choosePicture(myNumbers[i], numberDataHolder[i].value);

                myNumbers[i].setLayerType(LAYER_TYPE_HARDWARE, null);
                addView(myNumbers[i], new ViewGroup.LayoutParams(mDstWidth, mDstHeight));
                uniqueNumberLocks[i] = false;
            }

            // Loop over monsters
            for (int i = 0; i < myMonsters.length; i++) {
                myMonsters[i] = new Particle(getContext());
                myMonsters[i].mPosX = monsterDataHolder[i].xPos;
                myMonsters[i].mPosY = monsterDataHolder[i].yPos;
                myMonsters[i].setBackgroundResource(R.drawable.blue_monster_128); // validate getType
                myMonsters[i].setLayerType(LAYER_TYPE_HARDWARE, null);
                addView(myMonsters[i], new ViewGroup.LayoutParams(mDstWidth, mDstHeight));
            }

        }


        /*
         * Update the position of each particle in the system using the
         * Verlet integrator.
         */
        private void updatePositions(float sx, float sy, long timestamp) {

            // sx :: links + 5-10, rechts, -5-10
            // sy :: senkrecht nach vorne 10, rückseite vorne -10
            if (mLastT != 0) {
                final float dT = (float) (timestamp - mLastT) / 3000.f;
                myBall.computePhysics(sx, sy, dT);

                for (Particle monster : myMonsters) {
                    monster.computePhysicsMonster();
                }
            }
            mLastT = timestamp;
        }



        /*
         * Performs one iteration of the simulation. First updating the
         * position of all the particles and resolving the constraints and
         * collisions.
         */
        private void update(float sx, float sy, long now) {

            updatePositions(sx, sy, now);

            checkFinish();
            checkCollision();
            checkNumberCollected();
            myBall.resolveCollisionWithBounds();

            for (Particle monster : myMonsters) {
                monster.resolveCollisionWithBounds();
            }

        }

        /**
         * Check if player crashed into a monster
         */
        private void checkCollision() {

            Particle ball = myBall;
            final float x1_1 = ball.mPosX;
            final float y1_1 = ball.mPosY;

            Boolean catched = false;

            for (Particle monster : myMonsters) {
                final float x1_2 = monster.mPosX;
                final float y1_2 = monster.mPosY;
                final double dis = distance(x1_1,y1_1,x1_2,y1_2);

                if (dis < 0.003 && !catched)  {

                    catched = true;
                    Toast.makeText(getContext(), "Game over!", Toast.LENGTH_SHORT).show();
                    Vibrator v = (Vibrator) accelerometerPlayActivity.getSystemService(Context.VIBRATOR_SERVICE);
                    if (v!= null) v.vibrate(300);

                    // after 1 sec activity is closed
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            accelerometerPlayActivity.finish();
                        }
                    }, 1000);

                }
            }
        }

        /**
         * Check if player touched the treasure
         */
        private void checkFinish() {

            Particle ball = myBall;
            final float x1 = ball.mPosX;
            final float y1 = ball.mPosY;

            Particle treasure = myGoal;
            final float x2 = treasure.mPosX;
            final float y2 = treasure.mPosY;

            final double dis = distance(x1,y1,x2,y2);

            if (dis < 0.004 && !lockFinish && currentResult == accelerometerPlayActivity.dataHolder.getExpectedResult())  {

                myGoal.setBackgroundResource(R.drawable.treasure_open_128);

                lockFinish = true;

                // share data between singleton class !
                accelerometerPlayActivity.dataHolder.setLock(true);

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
         */
        private void checkNumberCollected() {

            Particle ball = myBall;
            final float x1_1 = ball.mPosX;
            final float y1_1 = ball.mPosY;

            for (int i = 0; i < myNumbers.length; i++) {
                final float x1_2 = myNumbers[i].mPosX;
                final float y1_2 = myNumbers[i].mPosY;
                final double dis = distance(x1_1,y1_1,x1_2,y1_2);

                if (dis < 0.004 && !uniqueNumberLocks[i])  {
                    myNumbers[i].setVisibility(INVISIBLE);

                    currentResult += numberDataHolder[i].getValue();
                    uniqueNumberLocks[i] = true;

                }
            }
        }

        private int getMonstersSize() {
            return myMonsters.length;
        }

        private float getMonstersXPos(int i) {
            return myMonsters[i].mPosX;
        }

        private float getMonstersYPos(int i) {
            return myMonsters[i].mPosY;
        }

        // numbern dürften sich nicht bewegen ...
        private int getNumbersSize() {
            return myNumbers.length;
        }

        private float getNumbersXPos(int i) {
            return myNumbers[i].mPosX;
        }

        private float getNumbersYPos(int i) {
            return myNumbers[i].mPosY;
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
        final float x = xc + particleSystem.myBall.mPosX * xs;
        final float y = yc - particleSystem.myBall.mPosY * ys;
        particleSystem.myBall.setTranslationX(x);
        particleSystem.myBall.setTranslationY(y);

        /*
         * MonsterS
         * We transform the canvas so that the coordinate system matches
         * the sensors coordinate system with the origin in the center
         * of the screen and the unit is the meter.
         */
        final int count2 = particleSystem.getMonstersSize();
        for (int i = 0; i < count2; i++) {

            final float x2 = xc + particleSystem.getMonstersXPos(i) * xs;
            final float y2 = yc - particleSystem.getMonstersYPos(i) * ys;
            particleSystem.myMonsters[i].setTranslationX(x2);
            particleSystem.myMonsters[i].setTranslationY(y2);
        }

        /*
         * Treasure
         * We transform the canvas so that the coordinate system matches
         * the sensors coordinate system with the origin in the center
         * of the screen and the unit is the meter.
         */
        final float x3 = xc + particleSystem.myGoal.mPosX * xs;
        final float y3 = yc - particleSystem.myGoal.mPosY * ys;
        particleSystem.myGoal.setTranslationX(x3);
        particleSystem.myGoal.setTranslationY(y3);

        /*
         * NumberS
         * We transform the canvas so that the coordinate system matches
         * the sensors coordinate system with the origin in the center
         * of the screen and the unit is the meter.
         */
        final int count3 = particleSystem.getNumbersSize();
        for (int i = 0; i < count3; i++) {

            final float x4 = xc + particleSystem.getNumbersXPos(i) * xs;
            final float y4 = yc - particleSystem.getNumbersYPos(i) * ys;
            particleSystem.myNumbers[i].setTranslationX(x4);
            particleSystem.myNumbers[i].setTranslationY(y4);
        }

        // and make sure to redraw asap
        invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private void choosePicture(SimulationView.Particle particle, int i) {
        switch (i) {
            case 1: particle.setBackgroundResource(R.drawable.one);
                break;
            case 2: particle.setBackgroundResource(R.drawable.two);
                break;
            case 3: particle.setBackgroundResource(R.drawable.three);
                break;
            case 4: particle.setBackgroundResource(R.drawable.four);
                break;
            case 5: particle.setBackgroundResource(R.drawable.five);
                break;
            case 6: particle.setBackgroundResource(R.drawable.six);
                break;
            case 7: particle.setBackgroundResource(R.drawable.seven);
                break;
            case 8: particle.setBackgroundResource(R.drawable.eigth);
                break;
            case 9: particle.setBackgroundResource(R.drawable.nine);
                break;
            case 0: particle.setBackgroundResource(R.drawable.zero);
                break;
            default: particle.setBackgroundResource(R.drawable.emoji_smile_256);
                break;
        }

    }
}
