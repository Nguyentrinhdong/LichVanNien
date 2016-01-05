package com.bhmedia.lichvannien2016.ui.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.database.DataNew2DataBaseHelper;
import com.bhmedia.lichvannien2016.ui.model.NotRuoi;

/**
 * Created by Van on 4/3/2015.
 */
public class NotRuoiThanSauPhuNuActivity extends FragmentActivity implements
		View.OnClickListener {
	TextView tv_title_detail_notruoitspn, tv_title;
	RadioButton rb_thansauphunu_noruoi37_1, rb_thansauphunu_noruoi37_2,
			rb_thansauphunu_noruoi39_1, rb_thansauphunu_noruoi39_2,
			rb_thansauphunu_noruoi39_3, rb_thansauphunu_noruoi40,
			rb_thansauphunu_noruoi41_1, rb_thansauphunu_noruoi41_2,
			rb_thansauphunu_noruoi49_1, rb_thansauphunu_noruoi49_2,
			rb_thansauphunu_noruoi50_1, rb_thansauphunu_noruoi50_2,
			rb_thansauphunu_noruoi51_1, rb_thansauphunu_noruoi51_2,
			rb_thansauphunu_noruoi52_1, rb_thansauphunu_noruoi52_2,
			rb_thansauphunu_noruoi53_1, rb_thansauphunu_noruoi53_2,
			rb_thansauphunu_noruoi54, rb_thansauphunu_noruoi56_1,
			rb_thansauphunu_noruoi56_2, rb_thansauphunu_noruoi57_1,
			rb_thansauphunu_noruoi57_2, rb_thansauphunu_noruoi58_1,
			rb_thansauphunu_noruoi58_2, rb_thansauphunu_noruoi59_1,
			rb_thansauphunu_noruoi59_2, rb_thansauphunu_noruoi60_1,
			rb_thansauphunu_noruoi60_2, rb_thansauphunu_noruoi61_1,
			rb_thansauphunu_noruoi61_2;
	ImageView iv_detail_notruoithansaupn_back;
	ArrayList<NotRuoi> list;
	WebView wv_ketqua_vantrinhnam;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notruoi_thansauphunu);
		tv_title = (TextView) findViewById(R.id.tv_thu_menu);
		tv_title.setText("Thân sau phụ nữ");
		tv_title_detail_notruoitspn = (TextView) findViewById(R.id.tv_title_detail_notruoitspn);
		wv_ketqua_vantrinhnam = (WebView) findViewById(R.id.wv_vanKhan);
		// Typeface font= Typeface.createFromAsset(this.getAssets(),
		// "fonts/UVNBaiSau_R_0.TTF");
		// tv_title_detail_notruoitspn.setTypeface(font);
		// tv_detail_notruoitspn.setTypeface(font);
		// tv_title.setTypeface(font);
		iv_detail_notruoithansaupn_back = (ImageView) findViewById(R.id.rb_home);
		iv_detail_notruoithansaupn_back.setOnClickListener(this);
		DataNew2DataBaseHelper boiNotRuoiDataBaseHelper = new DataNew2DataBaseHelper(
				this);
		list = boiNotRuoiDataBaseHelper.getAllLabelnrbodyb(this);
		boiNotRuoiDataBaseHelper.closeDataBase();
		rb_thansauphunu_noruoi37_1 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi37_1);
		rb_thansauphunu_noruoi37_2 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi37_2);
		rb_thansauphunu_noruoi39_1 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi39_1);
		rb_thansauphunu_noruoi39_2 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi39_2);
		rb_thansauphunu_noruoi39_3 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi39_3);
		rb_thansauphunu_noruoi40 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi40);
		rb_thansauphunu_noruoi41_1 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi41_1);
		rb_thansauphunu_noruoi41_2 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi41_2);
		rb_thansauphunu_noruoi49_1 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi49_1);
		rb_thansauphunu_noruoi49_2 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi49_2);
		rb_thansauphunu_noruoi50_1 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi50_1);
		rb_thansauphunu_noruoi50_2 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi50_2);
		rb_thansauphunu_noruoi51_1 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi51_1);
		rb_thansauphunu_noruoi51_2 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi51_2);
		rb_thansauphunu_noruoi52_1 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi52_1);
		rb_thansauphunu_noruoi52_2 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi52_2);
		rb_thansauphunu_noruoi53_1 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi53_1);
		rb_thansauphunu_noruoi53_2 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi53_2);
		rb_thansauphunu_noruoi54 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi54);
		rb_thansauphunu_noruoi56_1 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi56_1);
		rb_thansauphunu_noruoi56_2 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi56_2);
		rb_thansauphunu_noruoi57_1 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi57_1);
		rb_thansauphunu_noruoi57_2 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi57_2);
		rb_thansauphunu_noruoi58_1 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi58_1);
		rb_thansauphunu_noruoi58_2 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi58_2);
		rb_thansauphunu_noruoi59_1 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi59_1);
		rb_thansauphunu_noruoi59_2 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi59_2);
		rb_thansauphunu_noruoi60_1 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi60_1);
		rb_thansauphunu_noruoi60_2 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi60_2);
		rb_thansauphunu_noruoi61_1 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi61_1);
		rb_thansauphunu_noruoi61_2 = (RadioButton) findViewById(R.id.rb_thansauphunu_noruoi61_2);

		rb_thansauphunu_noruoi37_1.setOnClickListener(listener); 
		rb_thansauphunu_noruoi37_2.setOnClickListener(listener);
		rb_thansauphunu_noruoi39_1.setOnClickListener(listener); 
		rb_thansauphunu_noruoi39_2.setOnClickListener(listener);
		rb_thansauphunu_noruoi39_3.setOnClickListener(listener); 
		rb_thansauphunu_noruoi40.setOnClickListener(listener);
		rb_thansauphunu_noruoi41_1.setOnClickListener(listener); 
		rb_thansauphunu_noruoi41_2.setOnClickListener(listener);
		rb_thansauphunu_noruoi49_1.setOnClickListener(listener); 
		rb_thansauphunu_noruoi49_2.setOnClickListener(listener);
		rb_thansauphunu_noruoi50_1.setOnClickListener(listener); 
		rb_thansauphunu_noruoi50_2.setOnClickListener(listener);
		rb_thansauphunu_noruoi51_1.setOnClickListener(listener); 
		rb_thansauphunu_noruoi51_2.setOnClickListener(listener);
		rb_thansauphunu_noruoi52_1.setOnClickListener(listener); 
		rb_thansauphunu_noruoi52_2.setOnClickListener(listener);
		rb_thansauphunu_noruoi53_1.setOnClickListener(listener); 
		rb_thansauphunu_noruoi53_2.setOnClickListener(listener);
		rb_thansauphunu_noruoi54.setOnClickListener(listener); 
		rb_thansauphunu_noruoi56_1.setOnClickListener(listener);
		rb_thansauphunu_noruoi56_2.setOnClickListener(listener); 
		rb_thansauphunu_noruoi57_1.setOnClickListener(listener);
		rb_thansauphunu_noruoi57_2.setOnClickListener(listener);
		rb_thansauphunu_noruoi58_1.setOnClickListener(listener);
		rb_thansauphunu_noruoi58_2.setOnClickListener(listener); 
		rb_thansauphunu_noruoi59_1.setOnClickListener(listener);
		rb_thansauphunu_noruoi59_2.setOnClickListener(listener); 
		rb_thansauphunu_noruoi60_1.setOnClickListener(listener);
		rb_thansauphunu_noruoi60_2.setOnClickListener(listener); 
		rb_thansauphunu_noruoi61_1.setOnClickListener(listener);
		rb_thansauphunu_noruoi61_2.setOnClickListener(listener);

		clearBackGround();
	}

	private void clearBackGround() {
		rb_thansauphunu_noruoi37_1.setBackgroundResource(R.drawable.nr); 
		rb_thansauphunu_noruoi37_2.setBackgroundResource(R.drawable.nr);
		rb_thansauphunu_noruoi39_1.setBackgroundResource(R.drawable.nr); 
		rb_thansauphunu_noruoi39_2.setBackgroundResource(R.drawable.nr);
		rb_thansauphunu_noruoi39_3.setBackgroundResource(R.drawable.nr); 
		rb_thansauphunu_noruoi40.setBackgroundResource(R.drawable.nr);
		rb_thansauphunu_noruoi41_1.setBackgroundResource(R.drawable.nr); 
		rb_thansauphunu_noruoi41_2.setBackgroundResource(R.drawable.nr);
		rb_thansauphunu_noruoi49_1.setBackgroundResource(R.drawable.nr); 
		rb_thansauphunu_noruoi49_2.setBackgroundResource(R.drawable.nr);
		rb_thansauphunu_noruoi50_1.setBackgroundResource(R.drawable.nr); 
		rb_thansauphunu_noruoi50_2.setBackgroundResource(R.drawable.nr);
		rb_thansauphunu_noruoi51_1.setBackgroundResource(R.drawable.nr); 
		rb_thansauphunu_noruoi51_2.setBackgroundResource(R.drawable.nr);
		rb_thansauphunu_noruoi52_1.setBackgroundResource(R.drawable.nr); 
		rb_thansauphunu_noruoi52_2.setBackgroundResource(R.drawable.nr);
		rb_thansauphunu_noruoi53_1.setBackgroundResource(R.drawable.nr); 
		rb_thansauphunu_noruoi53_2.setBackgroundResource(R.drawable.nr);
		rb_thansauphunu_noruoi54.setBackgroundResource(R.drawable.nr); 
		rb_thansauphunu_noruoi56_1.setBackgroundResource(R.drawable.nr);
		rb_thansauphunu_noruoi56_2.setBackgroundResource(R.drawable.nr); 
		rb_thansauphunu_noruoi57_1.setBackgroundResource(R.drawable.nr);
		rb_thansauphunu_noruoi57_2.setBackgroundResource(R.drawable.nr);
		rb_thansauphunu_noruoi58_1.setBackgroundResource(R.drawable.nr);
		rb_thansauphunu_noruoi58_2.setBackgroundResource(R.drawable.nr); 
		rb_thansauphunu_noruoi59_1.setBackgroundResource(R.drawable.nr);
		rb_thansauphunu_noruoi59_2.setBackgroundResource(R.drawable.nr); 
		rb_thansauphunu_noruoi60_1.setBackgroundResource(R.drawable.nr);
		rb_thansauphunu_noruoi60_2.setBackgroundResource(R.drawable.nr); 
		rb_thansauphunu_noruoi61_1.setBackgroundResource(R.drawable.nr);
		rb_thansauphunu_noruoi61_2.setBackgroundResource(R.drawable.nr);

	}

	private View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			clearBackGround();
			((RadioButton) v).setBackgroundResource(R.drawable.nr_active);
			switch (v.getId()) {
			case R.id.rb_thansauphunu_noruoi37_1:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(2).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi37_2:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(2).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi39_1:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(4).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi39_2:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(4).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi39_3:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(4).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi40:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(5).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi41_1:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(6).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi41_2:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(6).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi49_1:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(14).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi49_2:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(14).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi50_1:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(15).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi50_2:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(15).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi51_1:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(16).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi51_2:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(16).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi52_1:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(17).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi52_2:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(17).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi53_1:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(18).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi53_2:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(18).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi54:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(19).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi56_1:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(21).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi56_2:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(21).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi57_1:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(22).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi57_2:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(22).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi58_1:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(23).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi58_2:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(23).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi59_1:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(24).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi59_2:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(24).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi60_1:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(25).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi60_2:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(25).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi61_1:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(26).getContent());
				break;
			case R.id.rb_thansauphunu_noruoi61_2:
				tv_title_detail_notruoitspn.setVisibility(View.GONE);
				findWebview(list.get(26).getContent());
				break;
			}
		}
	};

	public void findWebview(String str) {
		String youtContentStr = String
				.valueOf(Html
						.fromHtml("<![CDATA[<body style=\"text-align:justify;color:#222222; \">"
								+ str + "</body>]]>"));
		wv_ketqua_vantrinhnam.getSettings().setJavaScriptEnabled(true);
		wv_ketqua_vantrinhnam.getResources().getDimensionPixelSize(
				R.dimen.text_size_large);
		wv_ketqua_vantrinhnam.loadDataWithBaseURL("file:///android_asset/",
				youtContentStr, "text/html", "utf-8", null);
		// wv_ketqua_vantrinhnam.setBackgroundColor(Color.BLACK);

		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
			wv_ketqua_vantrinhnam.setAlpha(0.7f);
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rb_home:
			onBackPressed();
			break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// BH.getInstance(this).onDestroy();
	}
}
