package de.htwg.margogo.monstermaths;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.PowerManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.widget.TextView;

import static java.lang.Integer.parseInt;

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

    TextView textViewTimer2;
    long startTime = 0;

    DataHolderInterface dataHolder;
    Intent intent;
    String id;
    int bla;



    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    final Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {

            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            textViewTimer2.setText(String.format("%d:%02d", minutes, seconds));

            if (dataHolder.getLock()) {
                dataHolder.setScore(seconds);
                dataHolder.setLock(false);
            } else {
                 // nothing to do here
            }

            timerHandler.postDelayed(this, 500);
        }
    };

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

        /**
        Button myButton = new Button(this);
        myButton.setText("HI");

        LayoutParams params = new LayoutParams(300,150);
        myButton.setLayoutParams(params);
        myButton.setX(myButton.getX() + 50);
        myButton.setY(myButton.getY() + 50);
         **/

        intent  = getIntent();
        Bundle extra = intent.getExtras();
        if (extra != null) {
            id = intent.getStringExtra("id");
            bla = parseInt(id);
        }

        if (bla == 1) {
            dataHolder = DataHolderLevel1.getInstance();
            Log.i("dataholder", "chosen 1");
        } else if (bla == 2){
            dataHolder = DataHolderLevel2.getInstance();
            Log.i("dataholder", "chosen 2");
        }

        Log.i("dataholder", "datatholder is" + dataHolder.toString());

        // TODO: Do the same for expected sum, current sum, current operation ?

        LayoutParams params = new LayoutParams(300,150);
        TextView textViewTimer = new TextView(this);
        textViewTimer.setText("Zeit: ");
        textViewTimer.setTextColor(Color.GRAY);
        textViewTimer.setTextSize(18);
        textViewTimer.setTypeface(null, Typeface.BOLD);
        textViewTimer.setLayoutParams(params);
        textViewTimer.setX(textViewTimer.getX() + 50);
        textViewTimer.setY(textViewTimer.getY() + 50);

        textViewTimer2 = new TextView(this);
        textViewTimer2.setTextColor(Color.GRAY);
        textViewTimer2.setTextSize(18);
        textViewTimer2.setTypeface(null, Typeface.BOLD);
        textViewTimer2.setLayoutParams(params);
        textViewTimer2.setX(textViewTimer.getX() + 120);
        textViewTimer2.setY(textViewTimer.getY());

        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);

        // instantiate our simulation view and set it as the activity's content
        mSimulationView = new SimulationView(this, this);
        mSimulationView.setBackgroundResource(R.drawable.wood);
        mSimulationView.addView(textViewTimer);
        mSimulationView.addView(textViewTimer2);
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

        timerHandler.postDelayed(timerRunnable, 0);

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

        timerHandler.removeCallbacks(timerRunnable);

        // TODO: wake lock crashes
        // and release our wake-lock
       // mWakeLock.release();
    }
}
