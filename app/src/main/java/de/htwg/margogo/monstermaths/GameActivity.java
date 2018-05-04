package de.htwg.margogo.monstermaths;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.PowerManager;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

/**
 * This activity handles the game activity for one level.
 * Should be later the prototype for each level
 *
 * @see SensorManager
 * @see SensorEvent
 * @see Sensor
 */
public class GameActivity extends Activity {

    private SimulationView mSimulationView;
    protected SensorManager mSensorManager;
    private PowerManager mPowerManager;
    private WindowManager mWindowManager;
    protected Display mDisplay;
    private PowerManager.WakeLock mWakeLock;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get an instance of the SensorManager
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Get an instance of the PowerManager
        mPowerManager = (PowerManager) getSystemService(POWER_SERVICE);

        // Get an instance of the WindowManager
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mDisplay = mWindowManager.getDefaultDisplay();

        // Create a bright wake lock
        mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, getClass()
                .getName());

        // instantiate our simulation view and set it as the activity's content
        mSimulationView = new SimulationView(this, this);
        mSimulationView.setBackgroundResource(R.drawable.wood);
        setContentView(mSimulationView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*
         * TODO: Crashes right now.
         * when the activity is resumed, we acquire a wake-lock so that the
         * screen stays on, since the user will likely not be fiddling with the
         * screen or buttons.
         */
        //mWakeLock.acquire();

        // Start the simulation
        mSimulationView.startSimulation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        /*
         * When the activity is paused, we make sure to stop the simulation,
         * release our sensor resources and wake locks
         */

        // Stop the simulation
        mSimulationView.stopSimulation();

        // TODO: wake lock crashes
        // and release our wake-lock
       // mWakeLock.release();
    }
}
