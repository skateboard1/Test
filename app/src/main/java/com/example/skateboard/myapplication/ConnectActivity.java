package com.example.skateboard.myapplication;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ConnectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        ConnectivityManager manager= (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info=manager.getActiveNetworkInfo();
        if(info!=null && info.isConnected())
        {
            Toast.makeText(ConnectActivity.this, "Connect to the Internet", Toast.LENGTH_SHORT).show();

            if(info.getType()==ConnectivityManager.TYPE_WIFI)
            {
                Toast.makeText(ConnectActivity.this, "wifi mode", Toast.LENGTH_SHORT).show();
            }

            if(info.getType()==ConnectivityManager.TYPE_MOBILE)
            {
                Toast.makeText(ConnectActivity.this, "mobile mode", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
