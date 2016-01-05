package com.bhmedia.lichvannien2016.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bhmedia.lichvannien2016.R;

/**
 * Created by Van on 6/19/2015.
 */
public class YKienPhanHoiActivity extends Activity implements View.OnClickListener{
    EditText et_noiDung;
    TextView tv_huy,tv_gui,tv_device_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ykienphanhoi);
        tv_device_version = (TextView) findViewById(R.id.tv_device_version);
        tv_huy = (TextView) findViewById(R.id.tv_huy);
        tv_huy.setOnClickListener(this);
        tv_gui = (TextView) findViewById(R.id.tv_gui);
        tv_gui.setOnClickListener(this);
        et_noiDung = (EditText) findViewById(R.id.et_noiDung);
        String str = "Device information(use for support only) " + Build.FINGERPRINT + "\n" +
                "Version App : 1.0";
        tv_device_version.setText(str);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_huy:
                onBackPressed();
                break;
            case R.id.tv_gui:
                /*Intent email = new Intent(Intent.ACTION_SEND);
            *//* Fill it with Data *//*
                email.setType("plain/text");
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"support.ios@bhmendia.vn"});
                //email.putExtra(android.content.Intent.EXTRA_SUBJECT, "Ứng dụng 'Lịch Vạn Niên 2015'");
                email.putExtra(Intent.EXTRA_SUBJECT, "Ứng dụng 'Lịch Vạn Niên 2015' - BHMedia Android");
                email.putExtra(Intent.EXTRA_TEXT, et_noiDung.getText().toString());
            *//* Send it off to the Activity-Chooser *//*
                startActivity(Intent.createChooser(email, "Send mail..s."));*/

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"support.ios@bhmedia.vn"});
                email.putExtra(android.content.Intent.EXTRA_SUBJECT, "Ứng dụng 'Lịch Vạn Niên 2016' - BHMedia Android");
                email.putExtra(android.content.Intent.EXTRA_TEXT, et_noiDung.getText().toString());
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Select Email Client"));
                break;
        }
    }

}
