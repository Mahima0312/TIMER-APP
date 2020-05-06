package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    CountDownTimer countdowner;
    TextView textview; SeekBar seekBar;
    Boolean counterisactive=false;
    Button goButton;
    public  void resetTimer()
    {
        textview.setText("0:30");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        countdowner.cancel();
        goButton.setText("GO!");
        counterisactive=false;

    }
    public void buttonClicked(View view)
    {
        if(counterisactive)
        {
            resetTimer();

        }else {
            counterisactive = true;
            seekBar.setEnabled(false);
            goButton.setText("STOP!");
            countdowner = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mplayer.start();
                    resetTimer();

                }
            }.start();
        }

    }
    public void updateTimer(int progress)
    {
        int min= progress/60;
        int sec= progress-(min*60);
        String secstring= Integer.toString(sec);
        if(secstring=="0")
            secstring+="0";
        if(sec<=9)
        {
            secstring="0"+sec;
        }
        textview.setText(Integer.toString(min)+":"+secstring);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton= findViewById(R.id.goButton);

        seekBar= findViewById(R.id.seekBar);
        textview= findViewById(R.id.countdownTextView);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
