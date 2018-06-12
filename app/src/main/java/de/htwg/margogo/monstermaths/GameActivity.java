package de.htwg.margogo.monstermaths;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.PowerManager;
import android.os.Bundle;
import android.view.Display;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.widget.TextView;
import android.widget.Toast;

import de.htwg.margogo.monstermaths.levels.*;
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
    TextView textViewCurrentOperation2;
    TextView textViewResult2;
    long startTime = 0;

    DataHolderInterface dataHolder;
    Intent intent;
    String id;
    int dt_id;
    String currentOperation = "+";

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

            textViewCurrentOperation2.setText(currentOperation);
            textViewResult2.setText(dataHolder.getExpectedResult().toString());

            if (dataHolder.getLock()) {

                updateScore(seconds);

                dataHolder.setScore(seconds);
                dataHolder.setLock(false);
            } else {
                 // nothing to do here
            }

            timerHandler.postDelayed(this, 500);
        }
    };

    private void updateScore(int score) {
        if (score < dataHolder.getScore() || dataHolder.getScore() == 0) {
            dataHolder.setScore(score);
            Toast.makeText(getApplicationContext(), "New highscore!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "No new highscore!", Toast.LENGTH_SHORT).show();
        }
    }

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

        intent  = getIntent();

        Bundle extra = intent.getExtras();
        if (extra != null) {
            id = intent.getStringExtra("id");
            dt_id = parseInt(id);
        }

        init_data_holder(dt_id);

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

        LayoutParams params2 = new LayoutParams(600,150);
        TextView textViewCurrentOperation = new TextView(this);
        textViewCurrentOperation.setText("Operation: ");
        textViewCurrentOperation.setTextColor(Color.GRAY);
        textViewCurrentOperation.setTextSize(18);
        textViewCurrentOperation.setTypeface(null, Typeface.BOLD);
        textViewCurrentOperation.setLayoutParams(params2);
        textViewCurrentOperation.setX(textViewTimer.getX());
        textViewCurrentOperation.setY(textViewTimer.getY() + 100);

        textViewCurrentOperation2 = new TextView(this);
        textViewCurrentOperation2.setTextColor(Color.GRAY);
        textViewCurrentOperation2.setTextSize(18);
        textViewCurrentOperation2.setTypeface(null, Typeface.BOLD);
        textViewCurrentOperation2.setLayoutParams(params);
        textViewCurrentOperation2.setX(textViewCurrentOperation.getX() + 270);
        textViewCurrentOperation2.setY(textViewCurrentOperation.getY());

        TextView textViewResult = new TextView(this);
        textViewResult.setText("Ergebnis: ");
        textViewResult.setTextColor(Color.GRAY);
        textViewResult.setTextSize(18);
        textViewResult.setTypeface(null, Typeface.BOLD);
        textViewResult.setLayoutParams(params);
        textViewResult.setX(textViewCurrentOperation.getX());
        textViewResult.setY(textViewCurrentOperation.getY() + 100);

        textViewResult2 = new TextView(this);
        textViewResult2.setTextColor(Color.GRAY);
        textViewResult2.setTextSize(18);
        textViewResult2.setTypeface(null, Typeface.BOLD);
        textViewResult2.setLayoutParams(params);
        textViewResult2.setX(textViewResult.getX() + 250);
        textViewResult2.setY(textViewResult.getY());

        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);

        // instantiate our simulation view and set it as the activity's content
        mSimulationView = new SimulationView(this, this);
        mSimulationView.setBackgroundResource(R.drawable.wood);
        mSimulationView.addView(textViewTimer);
        mSimulationView.addView(textViewTimer2);
        mSimulationView.addView(textViewCurrentOperation);
        mSimulationView.addView(textViewCurrentOperation2);
        mSimulationView.addView(textViewResult);
        mSimulationView.addView(textViewResult2);
        setContentView(mSimulationView);

    }

    private void init_data_holder(int id) {

        switch (id) {
            case 1 : dataHolder = DataHolderLevel1.getInstance();
                break;
            case 2: dataHolder = DataHolderLevel2.getInstance();
                break;
            case 3 : dataHolder = DataHolderLevel3.getInstance();
                break;
            case 4: dataHolder = DataHolderLevel4.getInstance();
                break;
            case 5 : dataHolder = DataHolderLevel5.getInstance();
                break;
            case 6: dataHolder = DataHolderLevel6.getInstance();
                break;
            case 7 : dataHolder = DataHolderLevel7.getInstance();
                break;
            case 8: dataHolder = DataHolderLevel8.getInstance();
                break;
            case 9: dataHolder = DataHolderLevel9.getInstance();
                break;
            case 10: dataHolder = DataHolderLevel10.getInstance();
                break;
            case 11: dataHolder = DataHolderLevel11.getInstance();
                break;
            case 12: dataHolder = DataHolderLevel12.getInstance();
                break;
            case 13: dataHolder = DataHolderLevel13.getInstance();
                break;
            case 14: dataHolder = DataHolderLevel14.getInstance();
                break;
            case 15: dataHolder = DataHolderLevel15.getInstance();
                break;
            default: dataHolder = DataHolderLevel1.getInstance();
                break;
        }
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
