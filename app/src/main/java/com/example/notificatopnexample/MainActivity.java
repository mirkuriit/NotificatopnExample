package com.example.notificatopnexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText minutesET, hoursET;
    Button startButton, stopButton ,notificationButton;
    AlarmManager alarmManager;

    Calendar calendar;

    String stamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startServiceButton);
        stopButton = findViewById(R.id.stopServiceButton);
        notificationButton = findViewById(R.id.notification_button);

        minutesET = findViewById(R.id.minutes);
        hoursET = findViewById(R.id.hours);

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        MusicService.class);
                startService(intent);

            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this,
                        MusicService.class));
            }
        });


        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                calendar.set(Calendar.MILLISECOND, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MINUTE, Integer.valueOf(minutesET.getText().toString()));
                calendar.set(Calendar.HOUR, Integer.valueOf(hoursET.getText().toString()));

                long time = calendar.getTimeInMillis();

                Intent intent = new Intent(getApplicationContext(), TimeNotificationService.class);
                PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);




                //alarmManager.setAlarmClock(alarmClockInfo, startTimeNotificationService());
                alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
                Toast.makeText(getApplicationContext(), "OOOOOOO", Toast.LENGTH_SHORT).show();
            }
        });
    }


}