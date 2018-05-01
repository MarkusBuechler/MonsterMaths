package de.htwg.margogo.monstermaths;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mGyroskop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.i("MyLog", "Sample log entry");

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        assert mSensorManager != null;
        mGyroskop = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if (checkSensorAvaibility(Sensor.TYPE_GYROSCOPE)) {
            // sensor available
            // you can play the game
            Log.i("MyLog", "Gyroscope sensor is available");
        } else {
            //user cannot play the game
            // should filter the devices in the playstore like this ..
            // https://developer.android.com/guide/topics/sensors/sensors_overview
            //<uses-feature android:name="android.hardware.sensor.gyroscope"
            //android:required="true" />
            Log.i("MyLog", "Gyroscope sensor is NOT available");
        }

        // asserts does not work. maybe because they lower 2 are always false and upper 2 are correct. Need output !
        assert mSensorManager != null;
        assert mGyroskop != null;
        assert mSensorManager == null;
        assert mGyroskop == null;

    }

    private boolean checkSensorAvaibility(int sensorType) {
        return mSensorManager.getDefaultSensor(sensorType) != null;
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        Log.i("MyLog", "x:" + x);
        Log.i("MyLog", "y:" + y);
        Log.i("MyLog", "z:" + z);
        // Do something with this sensor value.
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mGyroskop, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


}
