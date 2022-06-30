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

        //establecer una instancia de sensorManager de Android para acceder a los servicios de sensores
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //configurar todas las instancias de botón
        Button accelerometerBtn = findViewById(R.id.accelerometerBtn);
        Button magnetometerBtn = findViewById(R.id.magnetometerBtn);

        //agregue lógica para cada sensor donde el botón esté deshabilitado si ese sensor específico no está


        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {

            //establecer Listener para el botón
            accelerometerBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //agregar el intent que se utiliza para la actividad del sensor si se presiona el botón
                    Intent accelerometerIntent = new Intent(MainActivity.this, accelerometerActivity.class);
                    startActivity(accelerometerIntent);
                }
            });
        } else {

            //botón de desactivación si el sensor no está disponible
            accelerometerBtn.setEnabled(false);
        }


        if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {

            //establecer Listener para el botón
            magnetometerBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //agregar el intent que se utiliza para la actividad del sensor si se presiona el botón
                    Intent magnetometerIntent = new Intent(MainActivity.this, magnetometerActivity.class);
                    startActivity(magnetometerIntent);
                }
            });
        } else {

            //botón de desactivación si el sensor no está disponible
            magnetometerBtn.setEnabled(false);
        }



    }
}