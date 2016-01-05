package com.bhmedia.lichvannien2016.ui.activity;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.fragment.BaseFragment;

public class LacDienThoaiActivity extends BaseFragment implements
		View.OnClickListener, AnimationListener {
	Context context;
	public static int i1;
	private LacDienThoai mShaker;
	TextView tvketqua, tvnhanxet;
	private Animation animation1, animation2;
	Integer[] dsHinh = { R.drawable.xocque0, R.drawable.xocque1,
			R.drawable.xocque2 };
	AnimationDrawable cdXiNgau1, cdXiNgau2, cdXiNgau3;
	ImageView hinhXiNgau1, imgthanhque;
	Random randomXiNgau;
	int giaTriXiNgau1, giaTriXiNgau2, giaTriXiNgau3;
	Timer timer = new Timer();
	Handler handler;
	int tienThuong, kiemtra, id_amthanh;
	SharedPreferences luuTru;
	SoundPool amThanhXiNgau = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
	MediaPlayer nhacnen = new MediaPlayer();
	Callback callback = new Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			RandomXiNgau1();
			return false;
		}
	};

	@Override
	protected int getLayoutId() {
		return R.layout.main;
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

		// MyApp.tracker().setScreenName("Doi Ngay Android");
		// MyApp.tracker().send(new HitBuilders.AppViewBuilder().build());
		tvketqua=(TextView) findViewById(R.id.tvketqua);
		tvketqua.setVisibility(View.INVISIBLE);
		tvnhanxet = (TextView) findViewById(R.id.tvnhanxet);
		tvnhanxet.setText("Shake to get your hexagram");
		Random r = new Random();
		i1 = r.nextInt(64 - 1) + 1;
		hinhXiNgau1 = (ImageView) findViewById(R.id.imgxocque);
		imgthanhque = (ImageView) findViewById(R.id.imgthanhque);
		imgthanhque.setOnClickListener(this);
		int width2 = this.getResources().getDisplayMetrics().widthPixels;
		int height2 = this.getResources().getDisplayMetrics().heightPixels;
		LayoutParams params2 = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params2.setMargins(width2 / 10, 0, width2 / 10,0);
		hinhXiNgau1.setLayoutParams(params2);
		hinhXiNgau1.getLayoutParams().width = width2 * 4 / 5;
		hinhXiNgau1.getLayoutParams().height = height2 * 4 / 5;
		LayoutParams params3 = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params3.setMargins(0, width2 * 2 / 5, 0, 0);
		params3.addRule(RelativeLayout.CENTER_HORIZONTAL);
		imgthanhque.setLayoutParams(params3);
		imgthanhque.getLayoutParams().width = width2 / 5;
		imgthanhque.getLayoutParams().height = height2 / 7;
		imgthanhque.setVisibility(View.INVISIBLE);
		animation1 = AnimationUtils.loadAnimation(getActivity(), R.anim.am_week);
		animation1.setAnimationListener(this);
		id_amthanh = amThanhXiNgau.load(getActivity(), R.raw.xocque, 1);
		handler = new Handler(callback);

		final Vibrator vibe = (Vibrator) getActivity().getSystemService(context.VIBRATOR_SERVICE);

		mShaker = new LacDienThoai(getActivity());
		mShaker.setOnShakeListener(new LacDienThoai.OnShakeListener() {
			public void onShake() {
				tvnhanxet.setVisibility(View.INVISIBLE);
				hinhXiNgau1.setImageResource(R.drawable.hinhdongque);
				cdXiNgau1 = (AnimationDrawable) hinhXiNgau1.getDrawable();
				amThanhXiNgau.play(id_amthanh, 1.0f, 1.0f, 1, 0, 1.0f);
				cdXiNgau1.start();

				tienThuong = 0;
				timer.schedule(new LacXiNgau(), 3200);
				final Handler handler1 = new Handler();
				Timer t1 = new Timer();
				t1.schedule(new TimerTask() {
					public void run() {
						handler1.post(new Runnable() {
							public void run() {
								hinhXiNgau1
										.setImageResource(R.drawable.xocque0);
								imgthanhque.setVisibility(View.VISIBLE);
								imgthanhque.setAnimation(animation1);
								imgthanhque.startAnimation(animation1);
							}
						});
					}
				}, 3200);
				vibe.vibrate(100);
			}
		});
	}


	class LacXiNgau extends TimerTask {

		@Override
		public void run() {
			handler.sendEmptyMessage(0);
		}
	}

	private void RandomXiNgau1() {

		randomXiNgau = new Random();
		int rd = randomXiNgau.nextInt(3);
		switch (rd) {

		case 0:
			hinhXiNgau1.setImageResource(dsHinh[0]);
			giaTriXiNgau1 = rd;
			break;

		case 1:
			hinhXiNgau1.setImageResource(dsHinh[1]);
			giaTriXiNgau1 = rd;
			break;

		case 2:
			hinhXiNgau1.setImageResource(dsHinh[2]);
			giaTriXiNgau1 = rd;
			break;

		}
	}

	@Override
	public void onResume() {
		mShaker.resume();
		super.onResume();
	}

	@Override
	public void onPause() {
		mShaker.pause();
		super.onPause();
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationEnd(Animation animation) {
		mShaker.setOnShakeListener(null);
		hinhXiNgau1.setVisibility(View.GONE);
		tvketqua.setVisibility(View.VISIBLE);
		tvnhanxet.setVisibility(View.VISIBLE);
		tvketqua.setText(String.valueOf(i1));
		tvnhanxet.setText("Hexagram " + String.valueOf(i1)
				+ ", Tap to start your reading");
		LayoutParams params3 = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		int width2 = this.getResources().getDisplayMetrics().widthPixels;
		int height2 = this.getResources().getDisplayMetrics().heightPixels;
		params3.setMargins(width2 / 2 - width2 / 50, (height2/2) / 2, 0,
				0);
		tvketqua.setLayoutParams(params3);
		tvketqua.getLayoutParams().width = width2 * 3 / 5;
	}

	@Override
	public void onAnimationRepeat(Animation animation) {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgthanhque:
			Intent it = new Intent(getActivity(), KetQuaGieoQueActivity.class);
			startActivity(it);
			break;
		}
	}
}
