package com.example.ostamatii42ipzlab03;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    int seconds = 0;
    boolean isRunning = false;

    private boolean wasRunning = false;

    private TextView textTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runTimer();
        textTimer = findViewById(R.id.textTimer);
        if (savedInstanceState != null)
        {
            seconds = savedInstanceState.getInt("seconds");
            isRunning = savedInstanceState.getBoolean("isRunning");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
    }


    public void onClickStartTimer(View view)
    {
        isRunning  = true;
    }
    public void onClickPauseTimer(View view)
    {
        isRunning  = false;
    }
    public void onClickResetTimer(View view)
    {
        isRunning  = false;
        seconds = 0;
      // textTimer.setText(getString(R.string.init_time));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("isRunning",  isRunning);
        outState.putBoolean("wasRunning", wasRunning);
        outState.putInt("seconds", seconds);

    }

    public void runTimer()
    {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(isRunning)
                    seconds++;

                handler.postDelayed(this,1000);

                int hours = seconds / 3600;
                int minutes = seconds % 3600 / 60 ;
                int sec = seconds % 60;

                String time = String.format("%d:%02d:%02d", hours, minutes, sec);
                textTimer.setText(time);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRunning = wasRunning;
    }

    @Override
    protected void onPause() {
        super.onPause();

        wasRunning = isRunning;
        isRunning = false;
    }
}
