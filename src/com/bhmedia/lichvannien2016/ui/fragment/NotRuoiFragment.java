package com.bhmedia.lichvannien2016.ui.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.activity.LacDienThoaiActivity;
import com.bhmedia.lichvannien2016.ui.activity.NotRuoiThanSauDanOngActivity;
import com.bhmedia.lichvannien2016.ui.activity.NotRuoiThanSauPhuNuActivity;
import com.bhmedia.lichvannien2016.ui.activity.NotRuoiThanTruocDanOngActivity;
import com.bhmedia.lichvannien2016.ui.activity.NotRuoiThanTruocPhuNuActivity;
import com.bhmedia.lichvannien2016.ui.activity.NotRuoiTrenMatActivity;
import com.bhmedia.lichvannien2016.ui.adapter.ModelAdapter;
import com.bhmedia.lichvannien2016.ui.model.Model;
import com.google.android.gms.ads.AdView;

public class NotRuoiFragment extends BaseFragment implements
		View.OnClickListener {
	Context context;
	ModelAdapter adapter;
	ListView lv_vanKhan;
	Bundle mBundle;
	TextView tv_title;
	ImageView iv_back;
	private AdView mAdView;
	ArrayList<Model> list;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_ts2;
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
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText("Nốt Ruồi");
		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_back.setOnClickListener(this);
		lv_vanKhan = (ListView) findViewById(R.id.lv_vanKhan2);
		list = new ArrayList<Model>();
		list.add(new Model(1, "Moles on face"));
		list.add(new Model(2, "Mole on Male's Front Body"));
		list.add(new Model(3, "Mole on Male's Back Body"));
		list.add(new Model(4, "Mole on Female's Front Body"));
		list.add(new Model(5, "Mole on Female's Back Body"));
		adapter = new ModelAdapter(getActivity(), list);
		lv_vanKhan.setAdapter(adapter);
		lv_vanKhan
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
//						MyApp.sendAnalytic("Kết quả tử vi tướng số");
						Model model = (Model) parent
								.getItemAtPosition(position);
						int key = model.getId();
						switch (key) {
						case 1:
							startActivity(new Intent(getActivity(),
									NotRuoiTrenMatActivity.class));
							break;
						case 2:
							startActivity(new Intent(getActivity(),
									NotRuoiThanTruocDanOngActivity.class));
							break;
						case 3:
							startActivity(new Intent(getActivity(),
									NotRuoiThanSauDanOngActivity.class));
							break;
						case 4:
							startActivity(new Intent(getActivity(),
									NotRuoiThanTruocPhuNuActivity.class));
							break;
						case 5:
							startActivity(new Intent(getActivity(),
									NotRuoiThanSauPhuNuActivity.class));
							break;
						}
					}
				});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tva:
//			lvgiaimonga.setSelection(0);
			break;

		default:
			break;
		}
	}
}
