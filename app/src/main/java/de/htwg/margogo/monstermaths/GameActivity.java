package de.htwg.margogo.monstermaths;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Locale;

import de.htwg.margogo.monstermaths.database.AppDatabase;
import de.htwg.margogo.monstermaths.database.Highscore;
import de.htwg.margogo.monstermaths.levels.DataHolderInterface;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel1;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel10;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel11;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel12;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel13;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel14;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel15;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel16;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel17;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel18;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel19;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel2;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel20;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel3;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel4;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel5;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel6;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel7;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel8;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel9;

import static java.lang.Integer.parseInt;

/**
 * This activity handles the game activity for one level.
 *
 * @see SensorManager
 * @see SensorEvent
 * @see Sensor
 */
public class GameActivity extends Activity {

    private SimulationView mSimulationView;
    protected SensorManager mSensorManager;
    protected Display mDisplay;

    AppDatabase db;

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

            textViewTimer2.setText(String.format(Locale.getDefault(), "%d:%02d", minutes, seconds));

            textViewCurrentOperation2.setText(currentOperation);
            textViewResult2.setText(dataHolder.getExpectedResult().toString());

            if (dataHolder.getLock()) {

                updateScore(seconds);
                dataHolder.setLock(false);

            }

            timerHandler.postDelayed(this, 500);
        }
    };

    private void updateScore(final int score) {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                db.highscoreDao().insertHighscore(new Highscore(dataHolder.getId(), score));
                return null;
            }
        }.execute();

    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Get an instance of the SensorManager
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Get an instance of the WindowManager
        WindowManager mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mDisplay = mWindowManager.getDefaultDisplay();

        intent  = getIntent();

        db = Room.databaseBuilder(GameActivity.this,
                AppDatabase.class, "database-name-2").build();

        Bundle extra = intent.getExtras();
        if (extra != null) {
            id = intent.getStringExtra("id");
            dt_id = parseInt(id);
        }

        init_data_holder(dt_id);

        LayoutParams params = new LayoutParams(300,150);
        TextView textViewTimer = new TextView(this);
        textViewTimer.setText(getString(R.string.Time));
        textViewTimer.setTextColor(Color.BLACK);
        textViewTimer.setTextSize(18);
        textViewTimer.setTypeface(null, Typeface.BOLD);
        textViewTimer.setLayoutParams(params);
        textViewTimer.setX(textViewTimer.getX() + 50);
        textViewTimer.setY(textViewTimer.getY() + 50);

        textViewTimer2 = new TextView(this);
        textViewTimer2.setTextColor(Color.BLACK);
        textViewTimer2.setTextSize(18);
        textViewTimer2.setTypeface(null, Typeface.BOLD);
        textViewTimer2.setLayoutParams(params);
        textViewTimer2.setX(textViewTimer.getX() + 120);
        textViewTimer2.setY(textViewTimer.getY());

        LayoutParams params2 = new LayoutParams(600,150);
        TextView textViewCurrentOperation = new TextView(this);
        textViewCurrentOperation.setText(getString(R.string.Operation));
        textViewCurrentOperation.setTextColor(Color.BLACK);
        textViewCurrentOperation.setTextSize(18);
        textViewCurrentOperation.setTypeface(null, Typeface.BOLD);
        textViewCurrentOperation.setLayoutParams(params2);
        textViewCurrentOperation.setX(textViewTimer.getX());
        textViewCurrentOperation.setY(textViewTimer.getY() + 100);

        textViewCurrentOperation2 = new TextView(this);
        textViewCurrentOperation2.setTextColor(Color.BLACK);
        textViewCurrentOperation2.setTextSize(18);
        textViewCurrentOperation2.setTypeface(null, Typeface.BOLD);
        textViewCurrentOperation2.setLayoutParams(params);
        textViewCurrentOperation2.setX(textViewCurrentOperation.getX() + 270);
        textViewCurrentOperation2.setY(textViewCurrentOperation.getY());

        TextView textViewResult = new TextView(this);
        textViewResult.setText(R.string.Result);
        textViewResult.setTextColor(Color.BLACK);
        textViewResult.setTextSize(18);
        textViewResult.setTypeface(null, Typeface.BOLD);
        textViewResult.setLayoutParams(params);
        textViewResult.setX(textViewCurrentOperation.getX());
        textViewResult.setY(textViewCurrentOperation.getY() + 100);

        textViewResult2 = new TextView(this);
        textViewResult2.setTextColor(Color.BLACK);
        textViewResult2.setTextSize(18);
        textViewResult2.setTypeface(null, Typeface.BOLD);
        textViewResult2.setLayoutParams(params);
        textViewResult2.setX(textViewResult.getX() + 250);
        textViewResult2.setY(textViewResult.getY());

        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);

        // instantiate our simulation view and set it as the activity's content
        mSimulationView = new SimulationView(this, this);
        mSimulationView.setBackgroundResource(R.drawable.background);
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
            case 16: dataHolder = DataHolderLevel16.getInstance();
                break;
            case 17: dataHolder = DataHolderLevel17.getInstance();
                break;
            case 18: dataHolder = DataHolderLevel18.getInstance();
                break;
            case 19: dataHolder = DataHolderLevel19.getInstance();
                break;
            case 20: dataHolder = DataHolderLevel20.getInstance();
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
