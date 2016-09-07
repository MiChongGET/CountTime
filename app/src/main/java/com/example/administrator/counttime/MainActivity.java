package com.example.administrator.counttime;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputet;
    private Button getTime,starTime,stopTime;
    private TextView time;
    int i=0;
    private Timer timer = null;
    private TimerTask timerTask  =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        inputet = (EditText) findViewById(R.id.editText);
        getTime = (Button) findViewById(R.id.button);
        starTime = (Button) findViewById(R.id.button2);
        stopTime = (Button) findViewById(R.id.button3);
        time = (TextView) findViewById(R.id.textView);
        getTime.setOnClickListener(this);
        starTime.setOnClickListener(this);
        stopTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.button:
                time.setText(inputet.getText().toString());
                i =Integer.parseInt(inputet.getText().toString());
                break;
            case R.id.button2:
                starTime();
                break;
            case R.id.button3:
                stopTime();
                break;

        }

    }

    private android.os.Handler  mhaHandler = new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
        time.setText(msg.arg1+"");
            starTime();
        };

    };

    public void starTime() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                i--;
                Message message = mhaHandler.obtainMessage();
                message.arg1=i;
                mhaHandler.sendMessage(message);
            }
        };
        timer.schedule(timerTask,1000);
    }

    public void stopTime(){
    timer.cancel();
    }
}
