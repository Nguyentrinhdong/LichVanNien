package com.bhmedia.lichvannien2016.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.activity.MyApp;
import com.bhmedia.lichvannien2016.ui.activity.YKienPhanHoiActivity;
import com.google.android.gms.analytics.HitBuilders;


/**
 * Created by Van on 1/14/2015.
 */
public class ThongTinUngDungFragment extends BaseFragment implements View.OnClickListener{

    TextView tv_email,tv_developer;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_thongtinungdung;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    protected void onInitializeView(Bundle savedInstanceState) {
        super.onInitializeView(savedInstanceState);
        MyApp.tracker().setScreenName("Thong Tin Ung Dung Android");
		MyApp.tracker().send(new HitBuilders.AppViewBuilder().build());

        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_developer = (TextView) findViewById(R.id.tv_developer);
        tv_email.setOnClickListener(this);
        tv_developer.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_email:
                startActivity(new Intent(getActivity(), YKienPhanHoiActivity.class));
                break;
            case R.id.tv_developer:
                Uri uri = Uri.parse("http://www.bhmedia.vn");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
        }
    }
}
