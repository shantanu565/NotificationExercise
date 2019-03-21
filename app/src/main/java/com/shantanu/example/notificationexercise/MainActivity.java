package com.shantanu.example.notificationexercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button btnNoti,btnQuestwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //here we are creating instance of OneTimeWorkRequest for creating request which will run only once & passing myWorker.class which contains the code of work we want to do
        final OneTimeWorkRequest workRequest=new OneTimeWorkRequest.Builder(MyWorker.class).build();

        imageView=(ImageView)findViewById(R.id.imgview);

        //Press this for Ques 2 (Notification channels)
        btnQuestwo=(Button)findViewById(R.id.btn_questwo);
        btnQuestwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent q2=new Intent(MainActivity.this,ChannelExample.class);
                startActivity(q2);
            }
        });


        btnNoti=(Button)findViewById(R.id.btn_noti);
        btnNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //submitting our workrequest to WorkManager(It manages & schedule the work)
                WorkManager.getInstance().enqueue(workRequest);

            }
        });
    }
}
