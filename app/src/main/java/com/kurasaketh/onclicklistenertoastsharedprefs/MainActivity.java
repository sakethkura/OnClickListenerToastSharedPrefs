package com.kurasaketh.onclicklistenertoastsharedprefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tleft;
    TextView tright;
    TextView bleft;
    TextView bright;
    SharedPreferences sharedPreferences;
    SeekBar seek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tleft = findViewById(R.id.topleft);
        tright = findViewById(R.id.topright);
        bleft = findViewById(R.id.bottomleft);
        bright = findViewById(R.id.bottomright);
        seek = findViewById(R.id.seek);
        seek.setMax(30);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress+20;
                Log.i("val", "" + progressChangedValue);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                tleft.setTextSize(progressChangedValue);
                tright.setTextSize(progressChangedValue);
                bleft.setTextSize(progressChangedValue);
                bright.setTextSize(progressChangedValue);
            }
        });


        tleft.setOnClickListener(this);
        tright.setOnClickListener(this);
        bleft.setOnClickListener(this);
        bright.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        TextView x =(TextView)v;

        int duration = Toast.LENGTH_SHORT;
        int box = sharedPreferences.getInt("" + x.getId(), 1);

        Context context = getApplicationContext();
        CharSequence text = x.getText() + " Number of Clicks: " + box;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("" + x.getId(), box+1);
        editor.apply();
    }
}
