package com.bhmedia.lichvannien2016.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import com.bhmedia.frame.utils.RootSupport;
import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.adapter.TinhThanhAdapter;
import com.bhmedia.lichvannien2016.ui.database.TinhThanhDataBaseHelper;
import com.bhmedia.lichvannien2016.ui.model.TinhThanh;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;


/**
 * Created by Van on 4/21/2015.
 */
public class TinhThanhActivity extends FragmentActivity implements View.OnClickListener {

    TinhThanhAdapter adapter;
    ListView lv_tinhthanh;
    ImageView iv_back;
    private AdView mAdView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinhthanh);
        Bundle extras = new Bundle();
		extras.putString("appname", "lich_van_lien_2016");
		extras.putString("appcat", "productivity");
		extras.putString("jb", "" + RootSupport.isDeviceRooted());

		AdMobExtras adExtra = new AdMobExtras(extras);

		mAdView = (AdView) findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().addNetworkExtras(adExtra)
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("EDA8BB96AFAC4D787093E74267389265")
				.addTestDevice("3D7268CC8E6A7666958991A316D08A88")
				.addTestDevice("7AFC6206AD04A3073968C664E902FF5D")
				.addTestDevice("780C9B0B7E4E49713CFE54EEF2C2F0E7")
				.addTestDevice("48A720DA7ABCF53ECC3A00DEB6983B97").build();
		mAdView.loadAd(adRequest);
        iv_back = (ImageView) findViewById(R.id.iv_back);
//        iv_back.setOnClickListener(this);
        lv_tinhthanh = (ListView) findViewById(R.id.lv_tinhthanh);
        TinhThanhDataBaseHelper dataBaseHelper = new TinhThanhDataBaseHelper(this);
        ArrayList<TinhThanh> listTinhThanh = dataBaseHelper.showDatabaseTinhThanh(this);
        adapter = new TinhThanhAdapter(this, listTinhThanh);
        lv_tinhthanh.setAdapter(adapter);
        lv_tinhthanh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TinhThanh tinhThanh = (TinhThanh) parent.getItemAtPosition(position);
                Intent intent = new Intent(TinhThanhActivity.this, DuBaoThoiTietActivity.class);
                intent.putExtra("NAME", tinhThanh.getName());
                intent.putExtra("LATLONG", tinhThanh.getLatlong());
                setResult(2, intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
            	  this.finish();
                break;
        }
    }
}
