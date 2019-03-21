package com.shantanu.example.notificationexercise;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChannelExample extends AppCompatActivity {
    Button btnStopwatch,btnTimer;
    NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_example);

        //notification manger instance
       manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
       //creating our first custom notification channel named channelStopWatch
        NotificationChannel channelStopWatch= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channelStopWatch = new NotificationChannel("com.shantanu.example.notificationexercise.stopwatchID","com.shantanu.example.notificationexercise.stopwatchName", NotificationManager.IMPORTANCE_HIGH);
            //Enabling viberation when this notification will come
            channelStopWatch.enableVibration(true);
            //channelStopWatch.setSound(uri,RingtoneManager.getActualDefaultRingtoneUri(this,RingtoneManager.TYPE_NOTIFICATION));
            manager.createNotificationChannel(channelStopWatch);

        }

        //creating our second custom notification channel named channelTimer

        NotificationChannel channelTimer= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channelTimer = new NotificationChannel("com.shantanu.example.notificationexercise.timerID","com.shantanu.example.notificationexercise.timerName", NotificationManager.IMPORTANCE_LOW);
            channelTimer.enableLights(true);
            channelTimer.enableVibration(true);
            //channelStopWatch.setSound();
            manager.createNotificationChannel(channelTimer);

        }



        btnStopwatch=(Button)findViewById(R.id.btn_stopwatch);
        btnStopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               notificationStopwatch();
            }
        });
        btnTimer=(Button)findViewById(R.id.btn_timer);
        btnTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               notificationTimer();
            }
        });

    }
    public void notificationStopwatch(){
        NotificationCompat.Builder notiOne= new NotificationCompat.Builder(getApplicationContext(),"com.shantanu.example.notificationexercise.stopwatchID")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Stopwatch Notice")
                .setVibrate(new long[]{1000,1000,1000,1000,1000})
                .setContentText("running");
        Uri alarm=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                notiOne.setSound(alarm);

        manager.notify(1,notiOne.build());

        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            public void run() {
                notificationTimer();
                handler1.postDelayed(this, 60000);
            }
        }, 120000);
    }
    public void notificationTimer(){
        NotificationCompat.Builder notitwo= new NotificationCompat.Builder(getApplicationContext(),"com.shantanu.example.notificationexercise.timerID")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Timer Notice")
                .setContentText("running");
        manager.notify(2,notitwo.build());

        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            public void run() {
                notificationStopwatch();
                handler1.postDelayed(this, 60000);
            }
        }, 60000);
    }
}
