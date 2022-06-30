package com.example.lab07_sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set instance of Android's sensorManager to access sensor services
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //set up all button instances
        Button accelerometerBtn = findViewById(R.id.accelerometerBtn);
        Button magnetometerBtn = findViewById(R.id.magnetometerBtn);

        //add logic for each sensor where the button is disabled if that specific sensor is


        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {

            //set listener for button
            accelerometerBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //add intent that takes use to the sensor's activity if the button pressed
                    Intent accelerometerIntent = new Intent(MainActivity.this, accelerometerActivity.class);
                    startActivity(accelerometerIntent);
                }
            });
        } else {

            //disable button if sensor is not available
            accelerometerBtn.setEnabled(false);
        }


        if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {

            //set listener for button
            magnetometerBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //add intent that takes use to the sensor's activity if the button pressed
                    Intent magnetometerIntent = new Intent(MainActivity.this, magnetometerActivity.class);
                    startActivity(magnetometerIntent);
                }
            });
        } else {

            //disable button if sensor is not available
            magnetometerBtn.setEnabled(false);
        }



    }
}