package mmu.edu.my.detectimpacttutorial7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    // 残念
    SensorManager sensorManager;
    private Vibrator vibrator;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),
                SensorManager.SENSOR_DELAY_GAME);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        textView = findViewById(R.id.textView);
        textView.setText("Hello world");
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float accel_x = event.values[0];
        float accel_y = event.values[1];
        float accel_z = event.values[2];
        double magnitude = Math.sqrt(accel_x*accel_x + accel_y*accel_y + accel_z*accel_z);

        //textView.setText("Current magnitude = " +String.valueOf(magnitude)+ ":" +"\nGravity= "+ String.valueOf(SensorManager.GRAVITY_EARTH));
        textView.setText("Current magnitude = " +String.valueOf(magnitude));
        //if(magnitude > SensorManager.GRAVITY_EARTH) {
        if(magnitude > 0) {
            long[] patern = {0, 1000, 500, 2000, 500, 1000};
            vibrator.vibrate(patern,1);
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}