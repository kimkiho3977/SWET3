package com.example.brightness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subactivity_main);

        textView = findViewById(R.id.TV1);
        SeekBar seekBar = findViewById(R.id.SB1);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                setBrightness(i);
                textView.setText("화면밝기 : " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar SB1) {

            }


            @Override
            public void onStopTrackingTouch(SeekBar SB1) {

            }
        });
    }

    private void setBrightness(int value) {
        if (value < 10) {
            value = 10;
        } else if (value > 100) {
            value = 100;
        }
        WindowManager.LayoutParams params = getWindow().getAttributes();
        //params.screenBrightness = (float) value / 100;
        getWindow().setAttributes(params);
        try {
            setBrightness2(value, params); //얘가 오류나는곳
            //throw new Exception(); // 로그같은게 출력되려면 어떻게? 익셉션출력 어떻게?
        }catch (Exception e){
            System.out.println("예외처리");
            e.printStackTrace();
        }
    }

    private void setBrightness2 (int value, WindowManager.LayoutParams params){
        Log.v("test","setBrightMethod");
        params.screenBrightness = (float) value / 100;
        ContentResolver cResolver = getContentResolver();
        Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, value);
    }
}