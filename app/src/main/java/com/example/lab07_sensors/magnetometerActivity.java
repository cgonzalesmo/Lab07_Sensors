package com.example.lab07_sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class magnetometerActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor magnetometer;
    private TextView xValue;
    private TextView yValue;
    private TextView zValue;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //recuperar los valores actuales del magnetómetro para cada eje
        float current_xValue = sensorEvent.values[0];
        float current_yValue = sensorEvent.values[1];
        float current_zValue = sensorEvent.values[2];

        //mostrar cada valor en su textView correspondiente
        xValue.setText(getResources().getString(R.string.magnetometer_x_value, current_xValue));
        yValue.setText(getResources().getString(R.string.magnetometer_y_value, current_yValue));
        zValue.setText(getResources().getString(R.string.magnetometer_z_value, current_zValue));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

        //magnetómetro no informa cambios de precisión
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnetometer);

        //recuperar widgets
        xValue = findViewById(R.id.xValue);
        yValue = findViewById(R.id.yValue);
        zValue = findViewById(R.id.zValue);

        //definir instances
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    //registrar el listener una vez que comience la actividad
    @Override
    protected void onStart() {
        super.onStart();

        if(magnetometer != null) {

            sensorManager.registerListener(this, magnetometer, sensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    //detenga el sensor cuando la actividad se detenga para reducir el uso de la batería
    @Override
    protected void onStop() {
        super.onStop();

        sensorManager.unregisterListener(this);
    }
}