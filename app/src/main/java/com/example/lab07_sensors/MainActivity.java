package com.example.lab07_sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    OrientationEventListener mOrientationListener;
    private TextView orientationTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orientationTV = findViewById(R.id.orientationValue);

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
        mOrientationListener = new OrientationEventListener(this,
                SensorManager.SENSOR_DELAY_NORMAL) {

            @Override
            public void onOrientationChanged(int orientation) {
                if (orientation == OrientationEventListener.ORIENTATION_UNKNOWN) {
                    return;  //No se detecta ningún ángulo válido cuando el teléfono está en posición horizontal
                }
                //Sólo detecta si hay cuatro cambios de ángulo
                if (orientation > 350 || orientation < 10) { //0度
                    orientation = 0;
                    orientationTV.setText(getResources().getString(R.string.orientation, "Vertical 1"));
                } else if (orientation > 80 && orientation < 100) { //90度
                    orientation = 90;
                    orientationTV.setText(getResources().getString(R.string.orientation, "Horizontal 1"));
                } else if (orientation > 170 && orientation < 190) { //180度
                    orientation = 180;
                    orientationTV.setText(getResources().getString(R.string.orientation, "Vertical 2"));
                } else if (orientation > 260 && orientation < 280) { //270度
                    orientation = 270;
                    orientationTV.setText(getResources().getString(R.string.orientation, "Horizontal 2"));
                } else {
                    return;
                }
                Log.v("DEBUG_TAG","Orientation changed to " + orientation);

            }
        };

        if (mOrientationListener.canDetectOrientation()) {
            Log.v("DEBUG_TAG", "Can detect orientation");
            mOrientationListener.enable();
        } else {
            Log.v("DEBUG_TAG", "Cannot detect orientation");
            mOrientationListener.disable();
        }



    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mOrientationListener.disable();
    }

}