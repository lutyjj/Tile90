package com.lutyjj.tile90;

import android.content.ContentResolver;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radio_90hz:
                if (checked)
                    setState(0);
                break;
            case R.id.radio_60hz:
                if (checked)
                    setState(1);
                break;
            case R.id.radio_auto:
                if (checked)
                    setState(2);
                break;
        }
    }


    public String getState() {
        ContentResolver cr = getContentResolver();
        return Settings.Global.getString(cr, "oneplus_screen_refresh_rate");
    }

    private void setState(int state) {
        ContentResolver cr = getContentResolver();
        Settings.Global.putInt(cr, "oneplus_screen_refresh_rate", state);
    }
}
