package com.example.eggtimer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    SeekBar timerSeekBar;
    Button btn,btn2;
    ImageView imageView;
    int min = 10000;
    int max = 600000;
    MediaPlayer mediaPlayer;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.seekBar);
        btn = findViewById(R.id.btnStart);
        btn2 = findViewById(R.id.btnReset);
        btn2.setVisibility(View.INVISIBLE);
        btn.setVisibility(View.INVISIBLE);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        imageView.setImageResource(R.drawable.egg1);
        timerSeekBar.setMax(max);
        timerSeekBar.setMin(min);
        timerSeekBar.setProgress(min);
        textView.setVisibility(View.INVISIBLE);
        btn.setText("Start Timer");
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.alarm);



       timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser)
                {   btn.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    Long minutes = Long.valueOf(progress/1000/60);
                    Long seconds = Long.valueOf( progress/1000%60);
                    String timeLeft = ""+minutes+ " : " + seconds;

                    textView.setText(timeLeft);



                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void startTimer(final long time)
    {
        new CountDownTimer(time,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {
                Long minutes = millisUntilFinished/1000/60;
                Long seconds = millisUntilFinished/1000%60;
                String timeLeft = ""+minutes+ " : " + seconds;

                textView.setText(timeLeft);

            }

            @Override
            public void onFinish() {


                imageView.setImageResource(R.drawable.egg2);
                Toast.makeText(getApplicationContext(),"the timer is complete",Toast.LENGTH_SHORT).show();
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
                btn.setVisibility(View.INVISIBLE);
                btn2.setVisibility(View.VISIBLE);




            }
        }.start();
    }

    public void Startt(View view)
    {
            startTimer(timerSeekBar.getProgress());
            timerSeekBar.setEnabled(false);
    }

    public void reset(View view)
    {   mediaPlayer.stop();
        imageView.setImageResource(R.drawable.egg1);
        btn2.setVisibility(View.INVISIBLE);
        btn.setVisibility(View.VISIBLE);
        timerSeekBar.setProgress(min);

    }
}