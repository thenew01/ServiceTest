package com.tql.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    private DownloaderBinder binder = new DownloaderBinder();
    class DownloaderBinder extends Binder {
        public void startDownloada(){
            Toast.makeText(getBaseContext(), "start download", Toast.LENGTH_SHORT).show();
        }
        public int getProgress(){
            Toast.makeText(getBaseContext(), "getProgress", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getBaseContext(), "service started", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getBaseContext(), "service onStartCommand", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getBaseContext(), "service destroyed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        Toast.makeText(getBaseContext(), "service onBind", Toast.LENGTH_SHORT).show();
        return  binder;
    }
}
