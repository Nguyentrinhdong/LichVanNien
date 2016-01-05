package com.bhmedia.lichvannien2016.ui.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bhmedia.frame.utils.RootSupport;
import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.adapter.MenuAdapter;
import com.bhmedia.lichvannien2016.ui.database.DBSuKienHelper;
import com.bhmedia.lichvannien2016.ui.fragment.CTNFragment;
import com.bhmedia.lichvannien2016.ui.fragment.ConGiapFragment;
import com.bhmedia.lichvannien2016.ui.fragment.CungHoangDao2Fragment;
import com.bhmedia.lichvannien2016.ui.fragment.CungHoangDaoFragment;
import com.bhmedia.lichvannien2016.ui.fragment.DemNgayFragment;
import com.bhmedia.lichvannien2016.ui.fragment.DoiNgayFragment;
import com.bhmedia.lichvannien2016.ui.fragment.GiacMongFragment;
import com.bhmedia.lichvannien2016.ui.fragment.LichNgayFragment;
import com.bhmedia.lichvannien2016.ui.fragment.LichThangFragment;
import com.bhmedia.lichvannien2016.ui.fragment.NhipSinhHocFragment;
import com.bhmedia.lichvannien2016.ui.fragment.NotRuoiFragment;
import com.bhmedia.lichvannien2016.ui.fragment.SuKienFragment;
import com.bhmedia.lichvannien2016.ui.http.request.RequestApi;
import com.bhmedia.lichvannien2016.ui.http.response.GetWeatherResponse;
import com.bhmedia.lichvannien2016.ui.model.Menu;
import com.bhmedia.lichvannien2016.ui.service.LocationService;
import com.bhmedia.lichvannien2016.ui.service.NotificationService;
import com.bhmedia.lichvannien2016.ui.utility.FragmentUtils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

@SuppressLint("NewApi")
public class MainActivity extends BaseActivity implements View.OnClickListener {
	public static MainActivity me;
	RelativeLayout rootadView;

	private long lastBackPressTime;
	private ImageView rb_home;
	public TextView tv_thu_menu, tv_ngay_menu, tv_thang_menu;
	public TextView tv_ngay_sukien_menu, tv_ngay_doingay_menu,
			tv_ngay_demngay_menu;
	public String weekDay = "";
	public String preTitle = null;
	public String preTitle1 = null;

	public ListView lv_menu;
	LocationService appLocationService;
	MyAsyncTask myAsyncTask;
	public static List<GetWeatherResponse.Data.CurrentCondition> listCurrentCondition;
	public static List<GetWeatherResponse.Data.Weather> listWeather;
	public static List<GetWeatherResponse.Data.Request> listRequest;

	Calendar calendar;
	int day, month, year;
	private PendingIntent pendingIntent;
	private AdView mAdView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSlidingMenu().setMode(SlidingMenu.LEFT);

		// getSlidingMenu().setSlidingEnabled(false);
		// getSlidingMenu().setSecondaryMenu(R.layout.layout_menu_left);

		me = MainActivity.this;

		initUI();
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
	}

	private void initUI() {
		setupAds();

		calendar = Calendar.getInstance();
		day = calendar.get(Calendar.DAY_OF_MONTH);
		month = calendar.get(Calendar.MONTH) + 1;
		year = calendar.get(Calendar.YEAR);

		// getSlidingMenu().setSlidingEnabled(false);
		getSlidingMenu().setSecondaryMenu(R.layout.layout_menu_left);

		Display display = getWindowManager().getDefaultDisplay();
		getSlidingMenu().setBehindOffset(
				display.getWidth() - (display.getWidth() * 2 / 3));

		startService(new Intent(MainActivity.this, NotificationService.class));

		tv_ngay_menu = (TextView) findViewById(R.id.tv_ngay_menu);
		tv_ngay_sukien_menu = (TextView) findViewById(R.id.tv_ngay_sukien_menu);
		tv_ngay_doingay_menu = (TextView) findViewById(R.id.tv_ngay_doingay_menu);
		tv_ngay_demngay_menu = (TextView) findViewById(R.id.tv_ngay_demngay_menu);
		tv_thu_menu = (TextView) findViewById(R.id.tv_thu_menu);
		tv_thang_menu = (TextView) findViewById(R.id.tv_thang_menu);
		lv_menu = (ListView) findViewById(R.id.lv_menu);

		ArrayList<Menu> list = new ArrayList<Menu>();
		list.add(new Menu(R.drawable.icon_lichngay_action, "Daily View", 1));
		list.add(new Menu(R.drawable.icon_lichthang_action, "Monthly View", 2));
		list.add(new Menu(R.drawable.tuantrang, "Moon Phase", 3));
		list.add(new Menu(R.drawable.chiemtinh, "Astrology Calendar", 4));
		list.add(new Menu(R.drawable.icon_sukien_action, "Events", 5));
		list.add(new Menu(R.drawable.icon_doingay_action, "Calendar Converter", 6));
		list.add(new Menu(R.drawable.icon_demngay_action, "Counting Days", 7));
		list.add(new Menu(R.drawable.congtru, "Add/Subtract Day", 8));
		list.add(new Menu(R.drawable.giacmo, "Dream Interpretation", 9));
		list.add(new Menu(R.drawable.nsh, "Biorhythm", 10));
		list.add(new Menu(R.drawable.xemtuong, "Appearance Astrology", 11));
		list.add(new Menu(R.drawable.gieoque, "I Ching Divination", 12));
		list.add(new Menu(R.drawable.notruoi, "Mole Reading", 13));
		list.add(new Menu(R.drawable.phuongtay, "Western Astrology", 14));
		list.add(new Menu(R.drawable.phuongdong, "Eastern Astrology", 15));
		list.add(new Menu(R.drawable.caidat, "Settings", 16));
		MenuAdapter adapter = new MenuAdapter(this, list);
		lv_menu.setAdapter(adapter);
		adapter.notifyDataSetChanged();

		lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (AdmobManager.getInstance(MainActivity.me).allowShowQCFull
						&& AdmobManager.getInstance(MainActivity.me).adFull
								.isLoaded()) {
					AdmobManager
							.getInstance(MainActivity.me)
							.showQuangCaoFull_Option(parent, view, position, id);
					return;
				}

				oItemClick(parent, view, position, id);
			}
		});

		rb_home = (ImageView) findViewById(R.id.rb_home);

		rb_home.setOnClickListener(this);
		Calendar c = Calendar.getInstance();
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

		changeThu(dayOfWeek);
		onInitializeView();

		updateLocation();

		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				Date dateCurr = new Date();
				Calendar calendar1 = Calendar.getInstance();
				calendar1.setTime(dateCurr);
				int timeShow = calendar1.get(Calendar.HOUR_OF_DAY) * 60
						+ calendar1.get(Calendar.MINUTE);
				DBSuKienHelper suKienHelper = new DBSuKienHelper(
						MainActivity.this);
				boolean showDialog = suKienHelper.getCheckShowEvent(
						calendar1.get(Calendar.DAY_OF_MONTH),
						calendar1.get(Calendar.MONTH) + 1,
						calendar1.get(Calendar.YEAR), timeShow);
				suKienHelper.close();
				if (showDialog == true) {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							AlertDialog alertDialog = new AlertDialog.Builder(
									MainActivity.this)
									.setMessage("Bạn có sự kiện vào hôm nay!")
									.setNegativeButton(
											"Đóng",
											new DialogInterface.OnClickListener() {
												public void onClick(
														DialogInterface dialog,
														int whichButton) {
													dialog.cancel();
												}
											}).create();
							alertDialog.show();
						}
					});

				}
			}
		};

		timer = new Timer();
		int delay = 1 * 60 * 1000;
		timer.scheduleAtFixedRate(timerTask, 0, delay);

	}

	public void oItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Menu menu = (Menu) parent.getItemAtPosition(position);
		switch (menu.getId()) {
		case 1:
			// Lich ngay
			toggle();
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					openFragment(LichNgayFragment.class, false); 
				}
			}, 200);
			// openFragment(LichNgayFragment.class, false);
			cancelAsyncTask();
			toggle();
			tv_thu_menu.setText(weekDay);
			tv_ngay_menu.setVisibility(View.VISIBLE);
			tv_ngay_sukien_menu.setVisibility(View.GONE);
			tv_ngay_doingay_menu.setVisibility(View.GONE);
			tv_ngay_demngay_menu.setVisibility(View.GONE);
			tv_thang_menu.setVisibility(View.GONE);
			break;
		case 2:
			// Lich thang
			toggle();
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					openFragment(LichThangFragment.class, false);
				}
			}, 200);
			// openFragment(LichThangFragment.class, false);
			cancelAsyncTask();
			toggle();
			tv_thu_menu.setText("Monthly View");
			tv_ngay_menu.setVisibility(View.GONE);
			tv_ngay_sukien_menu.setVisibility(View.GONE);
			tv_ngay_doingay_menu.setVisibility(View.GONE);
			tv_ngay_demngay_menu.setVisibility(View.GONE);
			tv_thang_menu.setVisibility(View.VISIBLE);
			break;
		case 3:
			// Xem tuan trang
			break;
		case 4:
			// Xem chiem tinh
			toggle();
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					openFragment(CungHoangDaoFragment.class, false); 
				}
			}, 200);
			// openFragment(LichThangFragment.class, false);
			cancelAsyncTask();
			toggle();
			tv_thu_menu.setText("Astrology Calendar");
			tv_ngay_menu.setVisibility(View.GONE);
			tv_ngay_sukien_menu.setVisibility(View.GONE);
			tv_ngay_doingay_menu.setVisibility(View.GONE);
			tv_ngay_demngay_menu.setVisibility(View.GONE);
			tv_thang_menu.setVisibility(View.GONE);
			break;
		case 5:
			toggle();
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					openFragment(SuKienFragment.class, false);
				}
			}, 200);
			// openFragment(SuKienFragment.class, false);
			cancelAsyncTask();
			tv_thu_menu.setText("Events");
			tv_ngay_sukien_menu.setVisibility(View.VISIBLE);
			tv_ngay_doingay_menu.setVisibility(View.GONE);
			tv_ngay_demngay_menu.setVisibility(View.GONE);
			tv_thang_menu.setVisibility(View.GONE);
			tv_ngay_menu.setVisibility(View.GONE);
			toggle();
			break;
		case 6:
			toggle();
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					openFragment(DoiNgayFragment.class, false);
				}
			}, 200);
			// openFragment(DoiNgayFragment.class, false);
			cancelAsyncTask();
			tv_thu_menu.setText("Calendar Converter");
			tv_ngay_menu.setVisibility(View.GONE);
			tv_ngay_sukien_menu.setVisibility(View.GONE);
			tv_ngay_doingay_menu.setVisibility(View.VISIBLE);
			tv_ngay_demngay_menu.setVisibility(View.GONE);
			tv_thang_menu.setVisibility(View.GONE);
			toggle();
			break;
		case 7:
			toggle();
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					openFragment(DemNgayFragment.class, false);
				}
			}, 200);
//			openFragment(DemNgayFragment.class, false);
			cancelAsyncTask();
			toggle();
			tv_thu_menu.setText("Counting Days");
			tv_ngay_menu.setVisibility(View.GONE);
			tv_thang_menu.setVisibility(View.GONE);
			tv_ngay_sukien_menu.setVisibility(View.GONE);
			tv_ngay_doingay_menu.setVisibility(View.GONE);
			tv_ngay_demngay_menu.setVisibility(View.VISIBLE);
			break;
		case 8:
			// Cong tru ngay
			toggle();
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					openFragment(CTNFragment.class, false);
				}
			}, 200);
			cancelAsyncTask();
			toggle();
			tv_thu_menu.setText("Add/Subtract Day");
			tv_ngay_menu.setVisibility(View.GONE);
			tv_thang_menu.setVisibility(View.GONE);
			tv_ngay_sukien_menu.setVisibility(View.GONE);
			tv_ngay_doingay_menu.setVisibility(View.GONE);
			tv_ngay_demngay_menu.setVisibility(View.GONE);
			break;
		case 9:
			// Giai ma giac mo
//			Intent it=new Intent(MainActivity.this,ListScroll.class);
//			startActivity(it);
			toggle();
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					openFragment(GiacMongFragment.class, false); 
				}
			}, 200);
			// openFragment(LichThangFragment.class, false);
			cancelAsyncTask();
			toggle();
			tv_thu_menu.setText("Dream Interpretation");
			tv_ngay_menu.setVisibility(View.GONE);
			tv_ngay_sukien_menu.setVisibility(View.GONE);
			tv_ngay_doingay_menu.setVisibility(View.GONE);
			tv_ngay_demngay_menu.setVisibility(View.GONE);
			tv_thang_menu.setVisibility(View.GONE);
			break;
		case 10:
			// Nhip sinh hoc
			toggle();
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					openFragment(NhipSinhHocFragment.class, false); 
				}
			}, 200);
			cancelAsyncTask();
			toggle();
			tv_thu_menu.setText("Circadian Rhythm");
			tv_ngay_menu.setVisibility(View.GONE);
			tv_ngay_sukien_menu.setVisibility(View.GONE);
			tv_ngay_doingay_menu.setVisibility(View.GONE);
			tv_ngay_demngay_menu.setVisibility(View.GONE);
			tv_thang_menu.setVisibility(View.GONE);
			break;
		case 11:
			// Xem tuong
			Uri uri = Uri
					.parse("https://play.google.com/store/apps/details?id=com.bhmedia.facereader&hl=en");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
			break;
		case 12:
			// Gieo que
//			Intent it=new Intent(MainActivity.this, LacDienThoaiActivity.class);
//			startActivity(it);
			toggle();
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					openFragment(LacDienThoaiActivity.class, false); 
				}
			}, 200);
			// openFragment(LichThangFragment.class, false);
			cancelAsyncTask();
			toggle();
			tv_thu_menu.setText("Western Astrology");
			tv_ngay_menu.setVisibility(View.GONE);
			tv_ngay_sukien_menu.setVisibility(View.GONE);
			tv_ngay_doingay_menu.setVisibility(View.GONE);
			tv_ngay_demngay_menu.setVisibility(View.GONE);
			tv_thang_menu.setVisibility(View.GONE);
			break;
		case 13:
			// Not ruoi
			toggle();
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					openFragment(NotRuoiFragment.class, false); 
				}
			}, 200);
			cancelAsyncTask();
			toggle();
			tv_thu_menu.setText("Dream Interpretation");
			tv_ngay_menu.setVisibility(View.GONE);
			tv_ngay_sukien_menu.setVisibility(View.GONE);
			tv_ngay_doingay_menu.setVisibility(View.GONE);
			tv_ngay_demngay_menu.setVisibility(View.GONE);
			tv_thang_menu.setVisibility(View.GONE);
			break;
		case 14:
			// Phuong tay
			toggle();
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					openFragment(CungHoangDao2Fragment.class, false); // your
																		// fragment
																		// transactions
																		// go
																		// here
				}
			}, 200);
			// openFragment(LichThangFragment.class, false);
			cancelAsyncTask();
			toggle();
			tv_thu_menu.setText("Western Astrology");
			tv_ngay_menu.setVisibility(View.GONE);
			tv_ngay_sukien_menu.setVisibility(View.GONE);
			tv_ngay_doingay_menu.setVisibility(View.GONE);
			tv_ngay_demngay_menu.setVisibility(View.GONE);
			tv_thang_menu.setVisibility(View.GONE);
			break;
		case 15:
			// Phuong dong
			toggle();
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					openFragment(ConGiapFragment.class, false); // your fragment
																// transactions
																// go here
				}
			}, 200);
			// openFragment(LichThangFragment.class, false);
			cancelAsyncTask();
			toggle();
			tv_thu_menu.setText("Eastern Astrology");
			tv_ngay_menu.setVisibility(View.GONE);
			tv_ngay_sukien_menu.setVisibility(View.GONE);
			tv_ngay_doingay_menu.setVisibility(View.GONE);
			tv_ngay_demngay_menu.setVisibility(View.GONE);
			tv_thang_menu.setVisibility(View.GONE);
			break;
		case 16:
			// Cai dat
			break;
		}
	}

	private Timer timer;
	private android.os.Handler mHandler;

	private Runnable updateWeather = new Runnable() {
		@Override
		public void run() {
			if (LichNgayFragment.tv_noInformation == null)
				return;
			LichNgayFragment.tv_noInformation.setVisibility(View.VISIBLE);
			LichNgayFragment.ll_weather.setVisibility(View.GONE);
			LichNgayFragment.tv_diadiem.setVisibility(View.GONE);

			updateLocation();
		}
	};

	private void updateLocation() {
		appLocationService = new LocationService(this);
		Location nwLocation = appLocationService
				.getLocation(LocationManager.NETWORK_PROVIDER);

		if (nwLocation != null) {
			updateStatusWeather(nwLocation);

		} else {
			Location gpsLocation = appLocationService
					.getLocation(LocationManager.GPS_PROVIDER);

			if (gpsLocation != null) {
				updateStatusWeather(gpsLocation);
			} else {

				if (mHandler == null)
					mHandler = new android.os.Handler();
				mHandler.removeCallbacks(updateWeather);
				mHandler.postDelayed(updateWeather, 30 * 1000);
			}
		}
	}

	public void changeThu(int dayOfWeek) {
		if (Calendar.MONDAY == dayOfWeek) {
			weekDay = "Monday";
		} else if (Calendar.TUESDAY == dayOfWeek) {
			weekDay = "Tuesday";
		} else if (Calendar.WEDNESDAY == dayOfWeek) {
			weekDay = "Wednesday";
		} else if (Calendar.THURSDAY == dayOfWeek) {
			weekDay = "Thursday";
		} else if (Calendar.FRIDAY == dayOfWeek) {
			weekDay = "Friday";
		} else if (Calendar.SATURDAY == dayOfWeek) {
			weekDay = "Saturday";
		} else if (Calendar.SUNDAY == dayOfWeek) {
			weekDay = "Sunday";
		}

		tv_thu_menu.setText(weekDay);
	}

	private void setupAds() {
		// BH.getInstance(this).init(this,
		// (RelativeLayout) findViewById(R.id.parent), null,
		// getString(R.string.app_name), "relax", "bottom",
		// "/35883025/bha_728a", 30, 4);
		// BH.getInstance(this).onCreate();
		AdmobManager.getInstance(this).init();
	}

	@Override
	protected void onDestroy() {
		if (timer != null)
			timer.cancel();
		super.onDestroy();
		// BH.getInstance(this).onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public void onBackPressed() {
		FragmentManager manager = getSupportFragmentManager();
		long currentTimeMillis = System.currentTimeMillis();
		if (manager.getBackStackEntryCount() == 0) {
			boolean shouldExit = ((currentTimeMillis - lastBackPressTime) / 1000) < 3000;
			if (shouldExit) {
				super.onBackPressed();
			} else {
				Toast.makeText(this, "Press back again to exit",
						Toast.LENGTH_LONG).show();
			}
		} else {
			// If there are back-stack entries, leave the FragmentActivity
			// implementation take care of them.
			// super.onBackPressed();
			manager.popBackStack();

			if (preTitle1 != null) {
				tv_thu_menu.setText(preTitle1);
				preTitle1 = null;
			} else if (preTitle != null) {
				tv_thu_menu.setText(preTitle);
				if (preTitle.equalsIgnoreCase(weekDay)) {
					tv_ngay_menu.setVisibility(View.VISIBLE);
				} else {
					tv_ngay_menu.setVisibility(View.GONE);

				}
				preTitle = null;
			}
		}
		lastBackPressTime = currentTimeMillis;
	}

	public void updatePreTitle() {
		preTitle = tv_thu_menu.getText().toString();
		tv_ngay_menu.setVisibility(View.VISIBLE);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
	}

	private void onInitializeView() {
		openFragment(LichNgayFragment.class, false);
	}

	public void openFragment(Class<? extends Fragment> fragmentClazz) {
		openFragment(fragmentClazz, null, true);
	}

	public void openFragment(Class<? extends Fragment> fragmentClazz,
			boolean addBackStack) {
		openFragment(fragmentClazz, null, addBackStack);
	}

	public void openFragment(Class<? extends Fragment> fragmentClazz,
			Bundle args) {
		openFragment(fragmentClazz, args, true);
	}

	public void openFragment(Class<? extends Fragment> fragmentClazz,
			Bundle args, boolean addBackStack) {
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		String tag = fragmentClazz.getName();

		try {
			if (!isFragmentAdded(tag)) {
				Fragment fragment = null;

				fragment = fragmentClazz.newInstance();
				if (args != null) {
					fragment.setArguments(args);
				}

				Fragment fragCurr = FragmentUtils.getCurrentFragment(this);
				if (fragCurr != null)
					transaction.hide(fragCurr);
				transaction.add(R.id.frameContent, fragment, tag);
				if (addBackStack) {
					transaction.addToBackStack(tag);
				}
				transaction.commit();

			} else {
				showFragment(tag, transaction);
			}

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	private Boolean isFragmentAdded(String tag) {
		FragmentManager manager = getSupportFragmentManager();
		List<Fragment> fragmentList = manager.getFragments();
		if (fragmentList != null) {
			for (int i = fragmentList.size() - 1; i > -1; i--) {
				Fragment fragment = fragmentList.get(i);
				if (fragment != null
						&& fragment.getClass().getName().equals(tag)
						&& !fragment.isRemoving())
					return true;
			}
		}
		return false;
	}

	private void showFragment(String tag, FragmentTransaction transaction) {
		FragmentManager manager = getSupportFragmentManager();
		List<Fragment> fragmentList = manager.getFragments();
		if (fragmentList != null)
			for (Fragment fragment : fragmentList) {
				if (fragment != null) {
					if (fragment.getClass().getName().equals(tag)) {
						transaction.show(fragment);
					} else {
						transaction.hide(fragment);
					}
				}
			}
		transaction.commit();
	}

	@Override
	public void onClick(View v) {
		if (v.getId() != R.id.rb_home) {
			if (AdmobManager.getInstance(MainActivity.me).allowShowQCFull
					&& AdmobManager.getInstance(MainActivity.me).adFull
							.isLoaded()) {
				AdmobManager.getInstance(MainActivity.me).showQuangCaoFull(v);
				return;
			}
		}

		switch (v.getId()) {
		case R.id.rb_home:
			// onResume();
			toggle();
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					toggle(); // your fragment transactions go here
				}
			}, 200);
			toggle();
			break;
		}
	}

	public void cancelAsyncTask() {
		if (myAsyncTask != null) {
			myAsyncTask.cancel(true);
		}
	}

	private class MyAsyncTask extends AsyncTask<Location, Void, Void> {

		@Override
		protected Void doInBackground(Location... params) {

			final double latitude = params[0].getLatitude();
			final double longitude = params[0].getLongitude();
			final String latlong = latitude + "," + longitude + "";

			final Geocoder myLocation = new Geocoder(MainActivity.this,
					Locale.getDefault());
			loadDataAPI("1fb4bcfe9e152330111305", "8bcb0015fc200346120401",
					"json", latlong, "yes", "7", "yes", "isDayTime,utcDateTime");
			try {
				List<Address> myList = myLocation.getFromLocation(latitude,
						longitude, 1);
				if (myList != null && myList.size() > 0) {
					final Address address = myList.get(0);
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							if (LichNgayFragment.tv_diadiem != null) {
								LichNgayFragment.tv_diadiem.setText(address
										.getAddressLine(address
												.getMaxAddressLineIndex() - 1));
								LichNgayFragment.tv_diadiem
										.setVisibility(View.VISIBLE);
							}
						}
					});

				} else {
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}
			return null;
		}
	}

	private void updateStatusWeather(Location location) {
		cancelAsyncTask();

		myAsyncTask = new MyAsyncTask();
		myAsyncTask.execute(location);
	}

	@Override
	public void onStop() {
		super.onStop();
		cancelAsyncTask();
	}

	public static void loadDataAPI(String key, String feedkey, String format,
			String q, String includeLocation, String num_of_days, String fx,
			String extra) {

		RequestApi.getInstance().weather(key, feedkey, format, q,
				includeLocation, num_of_days, fx, extra,
				new Callback<GetWeatherResponse>() {

					@Override
					public void success(GetWeatherResponse getWeatherResponse,
							Response response) {

						if (getWeatherResponse == null) {
							LichNgayFragment.tv_noInformation
									.setVisibility(View.VISIBLE);
							LichNgayFragment.ll_weather
									.setVisibility(View.GONE);
							LichNgayFragment.tv_diadiem
									.setVisibility(View.GONE);
							return;
						}

						if (LichNgayFragment.tv_diadiem == null) {
							return;
						}
						listCurrentCondition = getWeatherResponse.getData()
								.getCurrentConditionList();
						listWeather = getWeatherResponse.getData()
								.getWeatherList();
						listRequest = getWeatherResponse.getData()
								.getRequestList();
						String str1 = listCurrentCondition.get(0)
								.getWeatherCode();
						String status1 = listCurrentCondition.get(0)
								.getIsdaytime();
						switch (status1) {
						case "yes":
							switch (str1) {
							case "113":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_113);
								break;
							case "116":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_116);
								break;
							case "119":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_119);
								break;
							case "122":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_122);
								break;
							case "143":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_143);
								break;
							case "176":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_176);
								break;
							case "179":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_179);
								break;
							case "182":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_182);
								break;
							case "185":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_185);
								break;
							case "200":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_200);
								break;
							case "227":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_227);
								break;
							case "230":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_230);
								break;
							case "248":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_248);
								break;
							case "260":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_260);
								break;
							case "263":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_263);
								break;
							case "266":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_266);
								break;
							case "281":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_281);
								break;
							case "284":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_284);
								break;
							case "293":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_293);
								break;
							case "296":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_296);
								break;
							case "299":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_299);
								break;
							case "302":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_302);
								break;
							case "305":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_305);
								break;
							case "308":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_308);
								break;
							case "311":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_311);
								break;
							case "314":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_314);
								break;
							case "317":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_317);
								break;
							case "320":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_320);
								break;
							case "323":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_323);
								break;
							case "326":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_326);
								break;
							case "329":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_329);
								break;
							case "332":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_332);
								break;
							case "335":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_335);
								break;
							case "338":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_338);
								break;
							case "350":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_350);
								break;
							case "353":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_353);
								break;
							case "356":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_356);
								break;
							case "359":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_359);
								break;
							case "362":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_362);
								break;
							case "365":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_365);
								break;
							case "368":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_368);
								break;
							case "371":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_371);
								break;
							case "374":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_374);
								break;
							case "377":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_377);
								break;
							case "386":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_386);
								break;
							case "389":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_389);
								break;
							case "392":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_392);
								break;
							case "395":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.day_sm_395);
								break;
							}
							break;
						case "no":
							switch (str1) {
							case "113":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_113);
								break;
							case "116":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_116);
								break;
							case "119":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_119);
								break;
							case "122":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_122);
								break;
							case "143":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_143);
								break;
							case "176":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_176);
								break;
							case "179":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_179);
								break;
							case "182":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_182);
								break;
							case "185":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_185);
								break;
							case "200":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_200);
								break;
							case "227":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_227);
								break;
							case "230":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_230);
								break;
							case "248":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_248);
								break;
							case "260":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_260);
								break;
							case "263":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_263);
								break;
							case "266":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_266);
								break;
							case "281":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_281);
								break;
							case "284":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_284);
								break;
							case "293":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_293);
								break;
							case "296":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_296);
								break;
							case "299":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_299);
								break;
							case "302":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_302);
								break;
							case "305":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_305);
								break;
							case "308":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_308);
								break;
							case "311":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_311);
								break;
							case "314":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_314);
								break;
							case "317":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_317);
								break;
							case "320":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_320);
								break;
							case "323":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_323);
								break;
							case "326":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_326);
								break;
							case "329":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_329);
								break;
							case "332":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_332);
								break;
							case "335":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_335);
								break;
							case "338":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_338);
								break;
							case "350":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_350);
								break;
							case "353":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_353);
								break;
							case "356":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_356);
								break;
							case "359":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_359);
								break;
							case "362":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_362);
								break;
							case "365":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_365);
								break;
							case "368":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_368);
								break;
							case "371":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_371);
								break;
							case "374":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_374);
								break;
							case "377":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_377);
								break;
							case "386":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_386);
								break;
							case "389":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_389);
								break;
							case "392":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_392);
								break;
							case "395":
								LichNgayFragment.iv_thoitiet
										.setImageResource(R.drawable.night_sm_395);
								break;
							}
							break;
						}
						final String doF = listCurrentCondition.get(0)
								.getTempF();
						final int changeDoF = (int) ((Integer.parseInt(doF) - 32) / 1.8);
						LichNgayFragment.tv_nhietdo.setText(changeDoF + "°C"
								+ "");
					}

					@Override
					public void failure(RetrofitError error) {
						if (LichNgayFragment.tv_diadiem == null) {
							return;
						}

						LichNgayFragment.tv_noInformation
								.setVisibility(View.VISIBLE);
						LichNgayFragment.ll_weather.setVisibility(View.GONE);
					}
				});
	}

}
