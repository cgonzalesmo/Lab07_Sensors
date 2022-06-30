package com.example.lab07_sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class accelerometerActivity extends AppCompatActivity implements SensorEventListener {

    //establecer instancias para sensorManager, acelerómetro y textViews
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private TextView xValue;
    private TextView yValue;
    private TextView zValue;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //obtener los valores actuales del acelerómetro para cada eje
        float current_xValue = sensorEvent.values[0];
        float current_yValue = sensorEvent.values[1];
        float current_zValue = sensorEvent.values[2];

        //mostrar los valores actuales del acelerómetro para cada eje en los widgets de textview
        xValue.setText(getResources().getString(R.string.accelerometer_x_value, current_xValue));
        yValue.setText(getResources().getString(R.string.accelerometer_y_value, current_yValue));
        zValue.setText(getResources().getString(R.string.accelerometer_z_value, current_zValue));
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //el acelerómetro no informa cambios de precisión
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        //recuperar widgets
        xValue = findViewById(R.id.xValue);
        yValue = findViewById(R.id.yValue);
        zValue = findViewById(R.id.zValue);

        //definir instances
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    //registrar al listener una vez que comience la actividad
    @Override
    protected void onStart() {
        super.onStart();

        if(accelerometer != null) {

            sensorManager.registerListener(this, accelerometer, sensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    //detenga el sensor cuando la actividad se detenga para reducir el uso de la batería
    @Override
    protected void onStop() {
        super.onStop();

        sensorManager.unregisterListener(this);
    }
}