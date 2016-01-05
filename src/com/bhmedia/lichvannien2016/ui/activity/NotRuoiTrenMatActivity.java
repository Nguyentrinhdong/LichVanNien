package com.bhmedia.lichvannien2016.ui.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
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
 * Created by Van on 4/13/2015.
 */
public class NotRuoiTrenMatActivity extends Activity implements
		View.OnClickListener {
	ImageView iv_notruoitm_back;
	TextView tv_title_detail_notruoitm, tv_title;
	RadioButton radioButton1, radioButton2, radioButton3, radioButton4,
			radioButton5_1, radioButton6_1, radioButton7_1, radioButton8,
			radioButton9, radioButton10, radioButton11_1, radioButton12_1,
			radioButton13_1, radioButton14, radioButton15_1, radioButton16,
			radioButton17_1, radioButton18_1, radioButton19_1, radioButton20,
			radioButton21_1, radioButton22_1, radioButton23_1, radioButton24_1,
			radioButton25_1, radioButton5_2, radioButton6_2, radioButton7_2,
			radioButton11_2, radioButton12_2, radioButton13_2, radioButton15_2,
			radioButton15_3, radioButton17_2, radioButton18_2, radioButton19_2,
			radioButton21_2, radioButton22_2, radioButton23_2, radioButton24_2,
			radioButton25_2;
	ArrayList<NotRuoi> list;
	WebView wv_ketqua_vantrinhnam;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notruoi_trenmat);
		tv_title = (TextView) findViewById(R.id.tv_thu_menu);
		tv_title.setText("Nốt ruồi trên mặt");
		iv_notruoitm_back = (ImageView) findViewById(R.id.rb_home);
		tv_title_detail_notruoitm = (TextView) findViewById(R.id.tv_title_detail_notruoitm);
		wv_ketqua_vantrinhnam = (WebView) findViewById(R.id.wv_vanKhan);
		// Typeface font= Typeface.createFromAsset(this.getAssets(),
		// "fonts/UVNBaiSau_R_0.TTF");
		// tv_detail_notruoitm.setTypeface(font);
		// tv_title_detail_notruoitm.setTypeface(font);
		// tv_title.setTypeface(font);
		iv_notruoitm_back.setOnClickListener(this);
		DataNew2DataBaseHelper boiNotRuoiDataBaseHelper = new DataNew2DataBaseHelper(
				this);
		list = boiNotRuoiDataBaseHelper.getAllLabelnrface(this);
		boiNotRuoiDataBaseHelper.closeDataBase();
		radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
		radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
		radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
		radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
		radioButton5_1 = (RadioButton) findViewById(R.id.radioButton5_1);
		radioButton6_1 = (RadioButton) findViewById(R.id.radioButton6_1);
		radioButton7_1 = (RadioButton) findViewById(R.id.radioButton7_1);
		radioButton8 = (RadioButton) findViewById(R.id.radioButton8);
		radioButton9 = (RadioButton) findViewById(R.id.radioButton9);
		radioButton10 = (RadioButton) findViewById(R.id.radioButton10);
		radioButton11_1 = (RadioButton) findViewById(R.id.radioButton11_1);
		radioButton12_1 = (RadioButton) findViewById(R.id.radioButton12_1);
		radioButton13_1 = (RadioButton) findViewById(R.id.radioButton13_1);
		radioButton14 = (RadioButton) findViewById(R.id.radioButton14);
		radioButton15_1 = (RadioButton) findViewById(R.id.radioButton15_1);
		radioButton16 = (RadioButton) findViewById(R.id.radioButton16);
		radioButton17_1 = (RadioButton) findViewById(R.id.radioButton17_1);
		radioButton18_1 = (RadioButton) findViewById(R.id.radioButton18_1);
		radioButton19_1 = (RadioButton) findViewById(R.id.radioButton19_1);
		radioButton20 = (RadioButton) findViewById(R.id.radioButton20);
		radioButton21_1 = (RadioButton) findViewById(R.id.radioButton21_1);
		radioButton22_1 = (RadioButton) findViewById(R.id.radioButton22_1);
		radioButton23_1 = (RadioButton) findViewById(R.id.radioButton23_1);
		radioButton24_1 = (RadioButton) findViewById(R.id.radioButton24_1);
		radioButton25_1 = (RadioButton) findViewById(R.id.radioButton25_1);
		radioButton5_2 = (RadioButton) findViewById(R.id.radioButton5_2);
		radioButton6_2 = (RadioButton) findViewById(R.id.radioButton6_2);
		radioButton7_2 = (RadioButton) findViewById(R.id.radioButton7_2);
		radioButton11_2 = (RadioButton) findViewById(R.id.radioButton11_2);
		radioButton12_2 = (RadioButton) findViewById(R.id.radioButton12_2);
		radioButton13_2 = (RadioButton) findViewById(R.id.radioButton13_2);
		radioButton15_2 = (RadioButton) findViewById(R.id.radioButton15_2);
		radioButton15_3 = (RadioButton) findViewById(R.id.radioButton15_3);
		radioButton17_2 = (RadioButton) findViewById(R.id.radioButton17_2);
		radioButton18_2 = (RadioButton) findViewById(R.id.radioButton18_2);
		radioButton19_2 = (RadioButton) findViewById(R.id.radioButton19_2);
		radioButton21_2 = (RadioButton) findViewById(R.id.radioButton21_2);
		radioButton22_2 = (RadioButton) findViewById(R.id.radioButton22_2);
		radioButton23_2 = (RadioButton) findViewById(R.id.radioButton23_2);
		radioButton24_2 = (RadioButton) findViewById(R.id.radioButton24_2);
		radioButton25_2 = (RadioButton) findViewById(R.id.radioButton25_2);
		radioButton1.setOnClickListener(listener);
		radioButton2.setOnClickListener(listener);
		radioButton3.setOnClickListener(listener);
		radioButton4.setOnClickListener(listener);
		radioButton5_1.setOnClickListener(listener);
		radioButton6_1.setOnClickListener(listener);
		radioButton7_1.setOnClickListener(listener);
		radioButton8.setOnClickListener(listener);
		radioButton9.setOnClickListener(listener);
		radioButton10.setOnClickListener(listener);
		radioButton11_1.setOnClickListener(listener);
		radioButton12_1.setOnClickListener(listener);
		radioButton13_1.setOnClickListener(listener);
		radioButton14.setOnClickListener(listener);
		radioButton15_1.setOnClickListener(listener);
		radioButton16.setOnClickListener(listener);
		radioButton17_1.setOnClickListener(listener);
		radioButton18_1.setOnClickListener(listener);
		radioButton19_1.setOnClickListener(listener);
		radioButton20.setOnClickListener(listener);
		radioButton21_1.setOnClickListener(listener);
		radioButton22_1.setOnClickListener(listener);
		radioButton23_1.setOnClickListener(listener);
		radioButton24_1.setOnClickListener(listener);
		radioButton25_1.setOnClickListener(listener);
		radioButton5_2.setOnClickListener(listener);
		radioButton6_2.setOnClickListener(listener);
		radioButton7_2.setOnClickListener(listener);
		radioButton11_2.setOnClickListener(listener);
		radioButton12_2.setOnClickListener(listener);
		radioButton13_2.setOnClickListener(listener);
		radioButton15_2.setOnClickListener(listener);
		radioButton15_3.setOnClickListener(listener);
		radioButton17_2.setOnClickListener(listener);
		radioButton18_2.setOnClickListener(listener);
		radioButton19_2.setOnClickListener(listener);
		radioButton21_2.setOnClickListener(listener);
		radioButton22_2.setOnClickListener(listener);
		radioButton23_2.setOnClickListener(listener);
		radioButton24_2.setOnClickListener(listener);
		radioButton25_2.setOnClickListener(listener);

		clearBackGround();
	}

	private void clearBackGround() {
		radioButton1.setBackgroundResource(R.drawable.nr);
		radioButton2.setBackgroundResource(R.drawable.nr);
		radioButton3.setBackgroundResource(R.drawable.nr);
		radioButton4.setBackgroundResource(R.drawable.nr);
		radioButton5_1.setBackgroundResource(R.drawable.nr);
		radioButton6_1.setBackgroundResource(R.drawable.nr);
		radioButton7_1.setBackgroundResource(R.drawable.nr);
		radioButton8.setBackgroundResource(R.drawable.nr);
		radioButton9.setBackgroundResource(R.drawable.nr);
		radioButton10.setBackgroundResource(R.drawable.nr);
		radioButton11_1.setBackgroundResource(R.drawable.nr);
		radioButton12_1.setBackgroundResource(R.drawable.nr);
		radioButton13_1.setBackgroundResource(R.drawable.nr);
		radioButton14.setBackgroundResource(R.drawable.nr);
		radioButton15_1.setBackgroundResource(R.drawable.nr);
		radioButton16.setBackgroundResource(R.drawable.nr);
		radioButton17_1.setBackgroundResource(R.drawable.nr);
		radioButton18_1.setBackgroundResource(R.drawable.nr);
		radioButton19_1.setBackgroundResource(R.drawable.nr);
		radioButton20.setBackgroundResource(R.drawable.nr);
		radioButton21_1.setBackgroundResource(R.drawable.nr);
		radioButton22_1.setBackgroundResource(R.drawable.nr);
		radioButton23_1.setBackgroundResource(R.drawable.nr);
		radioButton24_1.setBackgroundResource(R.drawable.nr);
		radioButton25_1.setBackgroundResource(R.drawable.nr);
		radioButton5_2.setBackgroundResource(R.drawable.nr);
		radioButton6_2.setBackgroundResource(R.drawable.nr);
		radioButton7_2.setBackgroundResource(R.drawable.nr);
		radioButton11_2.setBackgroundResource(R.drawable.nr);
		radioButton12_2.setBackgroundResource(R.drawable.nr);
		radioButton13_2.setBackgroundResource(R.drawable.nr);
		radioButton15_2.setBackgroundResource(R.drawable.nr);
		radioButton15_3.setBackgroundResource(R.drawable.nr);
		radioButton17_2.setBackgroundResource(R.drawable.nr);
		radioButton18_2.setBackgroundResource(R.drawable.nr);
		radioButton19_2.setBackgroundResource(R.drawable.nr);
		radioButton21_2.setBackgroundResource(R.drawable.nr);
		radioButton22_2.setBackgroundResource(R.drawable.nr);
		radioButton23_2.setBackgroundResource(R.drawable.nr);
		radioButton24_2.setBackgroundResource(R.drawable.nr);
		radioButton25_2.setBackgroundResource(R.drawable.nr);
	}

	private View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			clearBackGround();
			((RadioButton) v).setBackgroundResource(R.drawable.nr_active);
			switch (v.getId()) {
			case R.id.radioButton1:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(0).getContent());
				break;
			case R.id.radioButton2:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(1)
						.getContent());
				break;
			case R.id.radioButton3:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(2)
						.getContent());
				break;
			case R.id.radioButton4:
				findWebview(list.get(3)
						.getContent());
				break;
			case R.id.radioButton5_1:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(4)
						.getContent());
				break;
			case R.id.radioButton5_2:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(4)
						.getContent());
				break;
			case R.id.radioButton6_1:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(5)
						.getContent());
				break;
			case R.id.radioButton6_2:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(5)
						.getContent());
				break;
			case R.id.radioButton7_1:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(6)
						.getContent());
				break;
			case R.id.radioButton7_2:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(6)
						.getContent());
				break;
			case R.id.radioButton8:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(7)
						.getContent());
				break;
			case R.id.radioButton9:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(8)
						.getContent());
				break;
			case R.id.radioButton10:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(9)
						.getContent());
				break;
			case R.id.radioButton11_1:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(10)
						.getContent());
				break;
			case R.id.radioButton11_2:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(10)
						.getContent());
				break;
			case R.id.radioButton12_1:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(11)
						.getContent());
				break;
			case R.id.radioButton12_2:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(11)
						.getContent());
				break;
			case R.id.radioButton13_1:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(12)
						.getContent());
				break;
			case R.id.radioButton13_2:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(12)
						.getContent());
				break;
			case R.id.radioButton14:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(13)
						.getContent());
				break;
			case R.id.radioButton15_1:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(14)
						.getContent());
				break;
			case R.id.radioButton15_2:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(14)
						.getContent());
				break;
			case R.id.radioButton15_3:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(14)
						.getContent());
				break;
			case R.id.radioButton16:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(15)
						.getContent());
				break;
			case R.id.radioButton17_1:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(16)
						.getContent());
				break;
			case R.id.radioButton17_2:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(16)
						.getContent());
				break;
			case R.id.radioButton18_1:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(17)
						.getContent());
				break;
			case R.id.radioButton18_2:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(17)
						.getContent());
				break;
			case R.id.radioButton19_1:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(18)
						.getContent());
				break;
			case R.id.radioButton19_2:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(18)
						.getContent());
				break;
			case R.id.radioButton20:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(19)
						.getContent());
				break;
			case R.id.radioButton21_1:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(20)
						.getContent());
				break;
			case R.id.radioButton21_2:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(20)
						.getContent());
				break;
			case R.id.radioButton22_1:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(21)
						.getContent());
				break;
			case R.id.radioButton22_2:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(21)
						.getContent());
				break;
			case R.id.radioButton23_1:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(22)
						.getContent());
				break;
			case R.id.radioButton23_2:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(22)
						.getContent());
				break;
			case R.id.radioButton24_1:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(23)
						.getContent());
				break;
			case R.id.radioButton24_2:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(23)
						.getContent());
				break;
			case R.id.radioButton25_1:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(24)
						.getContent());
				break;
			case R.id.radioButton25_2:
				tv_title_detail_notruoitm.setVisibility(View.GONE);
				findWebview(list.get(24)
						.getContent());
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
