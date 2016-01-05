package com.bhmedia.lichvannien2016.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bhmedia.lichvannien2016.R;

/**
 * Created by Van on 1/14/2015.
 */
public class YKienPhanHoiFragment extends BaseFragment {
    Context context;
    Button btn_guiPhanHoi;
    EditText et_noiDung;
    TextView tv_device_version;

    @Override
    protected int getLayoutId() {

        return R.layout.layout_fragment_ykienphanhoi;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity.getApplicationContext();
    }

    @Override
    protected void onInitializeView(Bundle savedInstanceState) {
        super.onInitializeView(savedInstanceState);
        tv_device_version = (TextView) findViewById(R.id.tv_device_version);
        et_noiDung = (EditText) findViewById(R.id.et_noiDung);
        btn_guiPhanHoi = (Button) findViewById(R.id.btn_guiPhanHoi);
        btn_guiPhanHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(android.content.Intent.ACTION_SEND);
            /* Fill it with Data */
                email.setType("plain/text");
                email.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"support.ios@bhmedia.vn"});
                // email.putExtra(android.content.Intent.EXTRA_SUBJECT, "Lads to Leaders/Leaderettes Questions and/or Comments");
                email.putExtra(android.content.Intent.EXTRA_TEXT, et_noiDung.getText().toString());
            /* Send it off to the Activity-Chooser */
                startActivity(Intent.createChooser(email, "Send mail..."));
            }
        });
        String str = "Device information(use for support only) Android 2.3.3 upwards " + "\n" +
                "Version App : 1.0";
        tv_device_version.setText(str);
    }
}
