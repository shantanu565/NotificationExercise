package com.shantanu.example.notificationexercise;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;



import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {
    // it is our worker class,Here we write code for task we want to execute

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    // we have to write our custom method or action here in doWork() method
    public Result doWork() {
        //shownoti function to show notification
        shownoti("Work Manager Demo","Working");
        return Result.SUCCESS;
    }


    private void shownoti(String title,String description){
        //creating instance of Notification Manager
        NotificationManager manager=(NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
      //Checking android device version for creating notification channel.Android version of current device is need to be greaten than Oreo
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            //Creating new channel by passing unique id & name for our notification channel
            NotificationChannel channel=new NotificationChannel("com.shantanu.example.notificationexercise.ID","com.shantanu.example.notificationexercise.workmngerdemo",NotificationManager.IMPORTANCE_HIGH);
           //here we created the channel by using  createNotificationChannel method of NotificationManager class
            manager.createNotificationChannel(channel);
        }
        //Creating element for our notification

        NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext(),"com.shantanu.example.notificationexercise.ID")
                .setContentTitle(title)
                .setContentText(description)
                .setSmallIcon(R.mipmap.ic_launcher);
        manager.notify(1,builder.build());
    }
}
