package com.bhmedia.lichvannien2016.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.activity.MainActivity;
import com.bhmedia.lichvannien2016.ui.activity.MyApp;
import com.bhmedia.lichvannien2016.ui.activity.VanKhanChildActivity;
import com.bhmedia.lichvannien2016.ui.adapter.VanKhanAdapter;
import com.bhmedia.lichvannien2016.ui.database.DataBaseHelper;
import com.bhmedia.lichvannien2016.ui.model.VanKhan;
import com.google.android.gms.analytics.HitBuilders;


/**
 * Created by Van on 1/14/2015.
 */
public class VanKhanFragment extends BaseFragment {
    Context context;
    VanKhanAdapter adapter;
    ListView lv_vanKhan;

    @Override
    protected int getLayoutId() {

        return R.layout.layout_fragment_vankhan;
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
        
        MyApp.tracker().setScreenName("Van Khan Android");
		MyApp.tracker().send(new HitBuilders.AppViewBuilder().build());

		
        lv_vanKhan = (ListView) findViewById(R.id.lv_vanKhan);
        final DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        final ArrayList<VanKhan> listVanKhan = dataBaseHelper.showDatabase(getActivity());
        adapter = new VanKhanAdapter(getActivity(), listVanKhan);
        lv_vanKhan.setAdapter(adapter);
        lv_vanKhan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VanKhan vanKhan = (VanKhan) parent.getItemAtPosition(position);
                Bundle mBundle = new Bundle();
                Intent intent = new Intent(getActivity(), VanKhanChildActivity.class);
                mBundle.putString("ID", vanKhan.getId());
                mBundle.putString("TITLE", vanKhan.getTitle());
                intent.putExtra("Bundle", mBundle);
                startActivity(intent);
            }
        });
    }
}
