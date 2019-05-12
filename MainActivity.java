package com.example.lab5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import android.os.Message;

import org.w3c.dom.Text;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import android.widget.Button;
import android.widget.ProgressBar;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Handler mHandler;
    ProgressDialog mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                notificationDialog();
                break;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void notificationDialog() {
        mHandler=new Handler();
        mProgressBar= new ProgressDialog(MainActivity.this);
        mProgressBar.setMax(100);
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressBar.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    final int currentProgressCount = i;
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //Update the value background thread to UI thread
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(currentProgressCount);
                        }
                    });
                }
            }
        }).start();
    }
}