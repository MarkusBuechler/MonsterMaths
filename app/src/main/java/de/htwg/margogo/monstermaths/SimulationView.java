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
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

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

        public void computePhysicsMonster(float sx, float sy, float dT) {

            counter = counter % 180;
            counter++;

            dT = 0.01f;

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
        static final int NUM_PARTICLES = 1;
        static final int NUM_MONSTERS = 1;
        private Particle mBalls[] = new Particle[NUM_PARTICLES];
        private Particle mMonsters[] = new Particle[NUM_MONSTERS];

        ParticleSystem() {
            /*
             * Initially our particles have no speed or acceleration
             */
            for (int i = 0; i < mBalls.length; i++) {
                mBalls[i] = new Particle(getContext(), 1);
                mBalls[i].setBackgroundResource(R.drawable.emoji_smile_256);
                mBalls[i].setLayerType(LAYER_TYPE_HARDWARE, null);
                addView(mBalls[i], new ViewGroup.LayoutParams(mDstWidth, mDstHeight));
            }

            for (int i = 0; i < mMonsters.length; i++) {
                mMonsters[i] = new Particle(getContext(),2);
                mMonsters[i].mPosX = 0.0275f;
                mMonsters[i].mPosY = -0.03f;
                mMonsters[i].setBackgroundResource(R.drawable.blue_monster_128);
                mMonsters[i].setLayerType(LAYER_TYPE_HARDWARE, null);
                addView(mMonsters[i], new ViewGroup.LayoutParams(mDstWidth, mDstHeight));
            }

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
                    final int count = mBalls.length;
                    for (int i = 0; i < count; i++) {
                        Particle ball = mBalls[i];
                        ball.computePhysics(sx, sy, dT);
                    }
                    final int count2 = mMonsters.length;
                    for (int i = 0; i < count2; i++) {
                    Particle ball = mMonsters[i];
                    ball.computePhysicsMonster(sx, sy, dT);
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
            //updateMonsters(sx, sy, now);
            updatePositions(sx, sy, now);
            //updateMonsters(sx, sy, now);

            // We do no more than a limited number of iterations
            final int NUM_MAX_ITERATIONS = 10;

            /*
             * Resolve collisions, each particle is tested against every
             * other particle for collision. If a collision is detected the
             * particle is moved away using a virtual spring of infinite
             * stiffness.
             */
            boolean more = true;
            final int count = mBalls.length;
            final int count2 = mMonsters.length;
            for (int k = 0; k < NUM_MAX_ITERATIONS && more; k++) {
                more = false;
                for (int i = 0; i < count; i++) {
                    Particle curr = mBalls[i];
                    for (int j = i + 1; j < count; j++) {
                        Particle ball = mBalls[j];
                        float dx = ball.mPosX - curr.mPosX;
                        float dy = ball.mPosY - curr.mPosY;
                        float dd = dx * dx + dy * dy;
                        // Check for collisions
                        if (dd <= sBallDiameter2) {
                            /*
                             * add a little bit of entropy, after nothing is
                             * perfect in the universe.
                             */
                            dx += ((float) Math.random() - 0.5f) * 0.0001f;
                            dy += ((float) Math.random() - 0.5f) * 0.0001f;
                            dd = dx * dx + dy * dy;
                            // simulate the spring
                            final float d = (float) Math.sqrt(dd);
                            final float c = (0.5f * (sBallDiameter - d)) / d;
                            final float effectX = dx * c;
                            final float effectY = dy * c;
                            curr.mPosX -= effectX;
                            curr.mPosY -= effectY;
                            ball.mPosX += effectX;
                            ball.mPosY += effectY;
                            more = true;
                        }
                    }
                    curr.resolveCollisionWithBounds();
                }

                for (int i = 0; i < count2; i++) {
                    Particle curr = mMonsters[i];
                    for (int j = i + 1; j < count2; j++) {
                        Particle ball = mMonsters[j];
                        float dx = ball.mPosX - curr.mPosX;
                        float dy = ball.mPosY - curr.mPosY;
                        float dd = dx * dx + dy * dy;
                        // Check for collisions
                        if (dd <= sBallDiameter2) {
                            /*
                             * add a little bit of entropy, after nothing is
                             * perfect in the universe.
                             */
                            dx += ((float) Math.random() - 0.5f) * 0.0001f;
                            dy += ((float) Math.random() - 0.5f) * 0.0001f;
                            dd = dx * dx + dy * dy;
                            // simulate the spring
                            final float d = (float) Math.sqrt(dd);
                            final float c = (0.5f * (sBallDiameter - d)) / d;
                            final float effectX = dx * c;
                            final float effectY = dy * c;
                            curr.mPosX -= effectX;
                            curr.mPosY -= effectY;
                            ball.mPosX += effectX;
                            ball.mPosY += effectY;
                            more = true;
                        }
                    }
                    curr.resolveCollisionWithBounds();
                }
            }

        }

        public int getParticleCount() {
            return mBalls.length;
        }

        public float getPosX(int i) {
            return mBalls[i].mPosX;
        }

        public float getPosY(int i) {
            return mBalls[i].mPosY;
        }

        public int getParticleCount2() {
            return mMonsters.length;
        }

        public float getPosX2(int i) {
            return mMonsters[i].mPosX;
        }

        public float getPosY2(int i) {
            return mMonsters[i].mPosY;
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
        final int count = particleSystem.getParticleCount();
        for (int i = 0; i < count; i++) {
            /*
             * We transform the canvas so that the coordinate system matches
             * the sensors coordinate system with the origin in the center
             * of the screen and the unit is the meter.
             */
            final float x = xc + particleSystem.getPosX(i) * xs;
            final float y = yc - particleSystem.getPosY(i) * ys;
            particleSystem.mBalls[i].setTranslationX(x);
            particleSystem.mBalls[i].setTranslationY(y);
        }

        final int count2 = particleSystem.getParticleCount2();
        for (int i = 0; i < count2; i++) {
            /*
             * We transform the canvas so that the coordinate system matches
             * the sensors coordinate system with the origin in the center
             * of the screen and the unit is the meter.
             */
            final float x = xc + particleSystem.getPosX2(i) * xs;
            final float y = yc - particleSystem.getPosY2(i) * ys;
            particleSystem.mMonsters[i].setTranslationX(x);
            particleSystem.mMonsters[i].setTranslationY(y);
        }

        // and make sure to redraw asap
        invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
