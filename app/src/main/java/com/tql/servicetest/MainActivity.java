package com.tql.servicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    private Button start;
    private Button stop;
    private Button bind_service;
    private Button unbind_service;

    private MyService.DownloaderBinder downloaderBinder;

    private Button start_intent_service;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloaderBinder = (MyService.DownloaderBinder)service;
            downloaderBinder.startDownloada();
            downloaderBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button)findViewById(R.id.start);
        stop = (Button)findViewById(R.id.stop);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);

        bind_service = (Button)findViewById(R.id.bind_service);
        unbind_service = (Button)findViewById(R.id.unbind_service);
        bind_service.setOnClickListener(this);
        unbind_service.setOnClickListener(this);

        start_intent_service = (Button)findViewById(R.id.start_intent_service);
        start_intent_service.setOnClickListener(this);

        /*Intent intent = new Intent(this, MainActivity.class);
        Intent[] intents = {intent};
        PendingIntent pendingIntent = PendingIntent.getActivities(MainActivity.this, 0, intents, 0 );
        Notification notification = new NotificationCompat.Builder(this)
                .setContentInfo("info")
                .setContentTitle("title")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource( getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)
                .build();
        //startForeground(1, notification);
        startForegroundService(intent);*/
    }
    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.start:
                Intent intent = new Intent(this, MyService.class);
                startService(intent);
                break;
            case R.id.stop:
                Intent intent1 = new Intent(this, MyService.class);
                stopService(intent1);
                break;
            case R.id.bind_service:
                Intent intent2 = new Intent(this, MyService.class);
                bindService(intent2, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(connection);
            case R.id.start_intent_service:
                Intent intent3 = new Intent(this, MyIntentService.class);
                startService(intent3);
                break;
        }
    }
}

