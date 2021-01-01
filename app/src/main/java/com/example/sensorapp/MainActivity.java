package com.example.sensorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;

import static com.example.sensorapp.App.CHANNEL_ID;


public class MainActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener {

    private TextView LightSensorXvalue;

    private TextView PorximitySensorXvalue;
    private TextView PorximitySensorYvalue;
    private TextView PorximitySensorZvalue;

    private TextView AccelerometerXvalue;
    private TextView AccelerometerYvalue;
    private TextView AccelerometerZvalue;

    private TextView gyroscopeXvalue;
    private TextView gyroscopeYvalue;
    private TextView gyroscopeZvalue;

    private SensorManager sensorManager;
    private Sensor sensor1, sensor2, sensor3, sensor4;

    private NotificationManagerCompat notificationManager;

    double x = .0,y = 0.0, z = 0.0, k= 0.0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = NotificationManagerCompat.from(this);

        //showNotification();

        LightSensorXvalue = findViewById(R.id.lightSensorXId);


        PorximitySensorXvalue = findViewById(R.id.proximitySenorXId);
        PorximitySensorYvalue = findViewById(R.id.proximitySenorYId);
        PorximitySensorZvalue = findViewById(R.id.proximitySenorZId);

        AccelerometerXvalue = findViewById(R.id.AccelerometerXId);
        AccelerometerYvalue = findViewById(R.id.AccelerometerYId);
        AccelerometerZvalue = findViewById(R.id.AccelerometerZId);

        gyroscopeXvalue = findViewById(R.id.gyroscopeXId);
        gyroscopeYvalue = findViewById(R.id.gyroscopeYId);
        gyroscopeZvalue = findViewById(R.id.gyroscopeZId);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor1 = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensor2 = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensor3 = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensor4 = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        //CardView  button for next activity
        CardView LightSensorBtn = findViewById(R.id.lightSensorBtn);
        CardView ProximitySensorBtn = findViewById(R.id.proximitySenorBtn);
        CardView AccelerometerSensorBtn = findViewById(R.id.accelerometerSensorBtn);
        CardView GyroscopeSensorBtn = findViewById(R.id.gyroscopeSensorBtn);


        LightSensorBtn.setOnClickListener(this);
        ProximitySensorBtn.setOnClickListener(this);
        AccelerometerSensorBtn.setOnClickListener(this);
        GyroscopeSensorBtn.setOnClickListener(this);

//        showNotification(x, y, z, k);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {


        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            LightSensorXvalue.setText(String.valueOf(event.values[0]));
            x= event.values[0];
//            showNotification();


        }
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            PorximitySensorXvalue.setText(String.valueOf(event.values[0]));
            PorximitySensorYvalue.setText(String.valueOf(event.values[1]));
            PorximitySensorZvalue.setText(String.valueOf(event.values[2]));

            y= event.values[0];

//            showNotification();
        }

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            AccelerometerXvalue.setText(String.valueOf(event.values[0]));
            AccelerometerYvalue.setText(String.valueOf(event.values[1]));
            AccelerometerZvalue.setText(String.valueOf(event.values[2]));

            z= event.values[0];

//            showNotification();

        }

        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            gyroscopeXvalue.setText(String.valueOf(event.values[0]));
            gyroscopeYvalue.setText(String.valueOf(event.values[1]));
            gyroscopeZvalue.setText(String.valueOf(event.values[2]));

            k= event.values[0];
        }

        showNotification(x, y, z, k);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        sensorManager.registerListener(this, sensor1, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensor2, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensor3, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensor4, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }


    public void showNotification( double a , double b, double c, double d) {

          RemoteViews collapsedView = new RemoteViews(getPackageName(),
                 R.layout.notification_collasped);
          RemoteViews expandedView = new RemoteViews(getPackageName(),
                R.layout.notification_expanded);
        Intent clickIntent = new Intent(this, NotificationReceiver.class);
//        PendingIntent clickPendingIntent = PendingIntent.getBroadcast(this,
//                0, clickIntent, 0);
        collapsedView.setTextViewText(R.id.text_view_collapsed_1, "Sensor Values");

        collapsedView.setTextViewText(R.id.text_view_collapsed_2, "L_Sensor: " +" "+ a +" "+ "P_Sensor:" + " "+b);
        collapsedView.setTextViewText(R.id.text_view_collapsed_3, "A_Sensor:" + " "+c +" "+ "G_Sensor:" + " "+ d);
        expandedView.setImageViewResource(R.id.image_view_expanded, R.drawable.ic_android);
//        expandedView.setOnClickPendingIntent(R.id.image_view_expanded, clickPendingIntent);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_android)
                .setCustomContentView(collapsedView)
//                .setCustomBigContentView(expandedView)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .build();
        notificationManager.notify(1, notification);
    }


  //Button Click
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lightSensorBtn:
                startActivity(new Intent(MainActivity.this, LightSensorActivity.class));
                break;

            case R.id.proximitySenorBtn:
                startActivity(new Intent(MainActivity.this, ProximitySensorActivity.class));
                break;

            case R.id.accelerometerSensorBtn:
                startActivity(new Intent(MainActivity.this, AccelerometerSensorActivity.class));
                break;

            case R.id.gyroscopeSensorBtn:
                startActivity(new Intent(MainActivity.this, GyroscopeSensorActivity.class));
                break;

            default:
                break;
        }
    }
}