package com.bhmedia.lichvannien2016.ui.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.meetme.android.horizontallistview.HorizontalListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.activity.ChiTietNgayActivity;
import com.bhmedia.lichvannien2016.ui.activity.MainActivity;
import com.bhmedia.lichvannien2016.ui.activity.MyApp;
import com.bhmedia.lichvannien2016.ui.activity.SuaSuKienActivity;
import com.bhmedia.lichvannien2016.ui.activity.ThemSuKienActivity;
import com.bhmedia.lichvannien2016.ui.adapter.CustomSuKienHorizontalAdapter;
import com.bhmedia.lichvannien2016.ui.adapter.SuKienListAdapter;
import com.bhmedia.lichvannien2016.ui.database.DBSuKienHelper;
import com.bhmedia.lichvannien2016.ui.model.Sukien;
import com.google.android.gms.analytics.HitBuilders;

/**
 * Created by Van on 1/14/2015.
 */
public class SuKienFragment extends BaseFragment implements
		View.OnClickListener {
	Context context;
	ImageView iv_nextMonth, iv_backMonth;
	TextView tv_thangNamSukien;
	ListView lv_suKien;
	SuKienListAdapter adapter;
	HorizontalListView mListView;

	int day, month, year;
	TextView tv_sukien_thanhcong;
	TextView tv_chitietngay_sukien, tv_themsukien_sukien;
	CustomSuKienHorizontalAdapter suKienHorizontalAdapter;
	Calendar calendar;

	@Override
	protected int getLayoutId() {
		return R.layout.layout_fragment_sukien;
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
		MyApp.tracker().setScreenName("Su Kien Android");
		MyApp.tracker().send(new HitBuilders.AppViewBuilder().build());

		mListView = (HorizontalListView) findViewById(R.id.hsv_suKien);

		calendar = Calendar.getInstance();
		day = calendar.get(Calendar.DAY_OF_MONTH);
		month = calendar.get(Calendar.MONTH) + 1;
		year = calendar.get(Calendar.YEAR);

		suKienHorizontalAdapter = new CustomSuKienHorizontalAdapter(
				getActivity(), month, year);
		mListView.setAdapter(suKienHorizontalAdapter);
		tv_chitietngay_sukien = (TextView) findViewById(R.id.tv_chitietngay_sukien);
		tv_themsukien_sukien = (TextView) findViewById(R.id.tv_themsukien_sukien);
		tv_chitietngay_sukien.setOnClickListener(this);
		tv_themsukien_sukien.setOnClickListener(this);

		iv_backMonth = (ImageView) findViewById(R.id.iv_backMonth);
		iv_nextMonth = (ImageView) findViewById(R.id.iv_nextMonth);

		tv_thangNamSukien = (TextView) findViewById(R.id.tv_thangNamSuKien);
		tv_sukien_thanhcong = (TextView) findViewById(R.id.tv_sukien_thanhcong);
		switch (month) {
		case 1:
			tv_thangNamSukien.setText("January " + year);
			break;
		case 2:
			tv_thangNamSukien.setText("February " + year);
			break;
		case 3:
			tv_thangNamSukien.setText("March " + year);
			break;
		case 4:
			tv_thangNamSukien.setText("April " + year);
			break;
		case 5:
			tv_thangNamSukien.setText("May " + year);
			break;
		case 6:
			tv_thangNamSukien.setText("June " + year);
			break;
		case 7:
			tv_thangNamSukien.setText("July " + year);
			break;
		case 8:
			tv_thangNamSukien.setText("August " + year);
			break;
		case 9:
			tv_thangNamSukien.setText("September " + year);
			break;
		case 10:
			tv_thangNamSukien.setText("October " + year);
			break;
		case 11:
			tv_thangNamSukien.setText("November " + year);
			break;
		case 12:
			tv_thangNamSukien.setText("December " + year);
			break;

		default:
			break;
		}
		iv_nextMonth.setOnClickListener(this);
		iv_backMonth.setOnClickListener(this);
		lv_suKien = (ListView) findViewById(R.id.lv_suKien);

		suKienHorizontalAdapter.changeBackground(day - 1);
		lv_suKien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Sukien sukien = (Sukien) parent.getItemAtPosition(position);
				Intent intent = new Intent(getActivity(),
						SuaSuKienActivity.class);
				Bundle mBundle = new Bundle();
				mBundle.putString("ID", sukien.getId());
				mBundle.putString("TENSUKIEN", sukien.getSukien());
				mBundle.putString("DIADIEM", sukien.getDiadiem());
				mBundle.putString("CHITIETSUKIEN", sukien.getChitiet());
				mBundle.putString("DAY_BEGIN", sukien.getDay_begin() + "");
				mBundle.putString("MONTH_BEGIN", sukien.getMonth_begin() + "");
				mBundle.putString("YEAR_BEGIN", sukien.getYear_begin() + "");
				mBundle.putString("HOUR_BEGIN", sukien.getHour_begin() + "");
				mBundle.putString("MINUTE_BEGIN", sukien.getMinute_begin() + "");
				mBundle.putString("DAY_END", sukien.getDay_end() + "");
				mBundle.putString("MONTH_END", sukien.getMonth_end() + "");
				mBundle.putString("YEAR_END", sukien.getYear_end() + "");
				mBundle.putString("HOUR_END", sukien.getHour_end() + "");
				mBundle.putString("MINUTE_END", sukien.getMinute_end() + "");
				mBundle.putString("SPINNER", sukien.getSpinner() + "");
				intent.putExtra("Bundle", mBundle);
				startActivity(intent);
				// showDialogListener.showDialog(position)
			}

		});

		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				day = (int) parent.getItemAtPosition(position);
				suKienHorizontalAdapter.changeBackground(position);
				DBSuKienHelper suKienHelper = new DBSuKienHelper(getActivity());
				ArrayList<Sukien> list = suKienHelper.getAllSuKienByTime(day,
						month, year);
				if (list != null && list.size() > 0) {
					adapter = new SuKienListAdapter(context, list,
							removeListener);
					lv_suKien.setAdapter(adapter);
					adapter.notifyDataSetChanged();
					if (lv_suKien != null) {
						tv_sukien_thanhcong.setVisibility(View.GONE);
					}
				} else {
					adapter = new SuKienListAdapter(context, list,
							removeListener);
					lv_suKien.setAdapter(adapter);
					adapter.notifyDataSetChanged();
					tv_sukien_thanhcong.setVisibility(View.VISIBLE);
				}
			}
		});

		((MainActivity) getActivity()).tv_ngay_sukien_menu
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Date dateCurr = new Date();
						calendar = Calendar.getInstance(Locale.getDefault());
						calendar.setTime(dateCurr);
						day = calendar.get(Calendar.DAY_OF_MONTH);
						month = calendar.get(Calendar.MONTH) + 1;
						year = calendar.get(Calendar.YEAR);
						setListViewAdapterToDate(month, year);
						suKienHorizontalAdapter.changeBackground(day - 1);
					}
				});
	}

	@Override
	public void onResume() {
		super.onResume();
		suKienHorizontalAdapter.notifyDataSetChanged();
		DBSuKienHelper suKienHelper = new DBSuKienHelper(getActivity());
		ArrayList<Sukien> list = suKienHelper.getAllSuKienByTime(day, month,
				year);
		adapter = new SuKienListAdapter(context, list, removeListener);
		lv_suKien.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		if (lv_suKien != null && list.size() > 0) {
			tv_sukien_thanhcong.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_chitietngay_sukien:
			Intent intent = new Intent(getActivity(), ChiTietNgayActivity.class);
			Bundle mBundle = new Bundle();
			mBundle.putInt("NGAY", day);
			mBundle.putInt("THANG", month);
			mBundle.putInt("NAM", year);
			intent.putExtra("Bundle", mBundle);
			startActivity(intent);
			break;
		case R.id.tv_themsukien_sukien:
			Intent intent1 = new Intent(getActivity(), ThemSuKienActivity.class);
			Bundle mBundle1 = new Bundle();
			mBundle1.putInt("NGAY_SUKIEN", day);
			mBundle1.putInt("THANG_SUKIEN", month);
			mBundle1.putInt("NAM_SUKIEN", year);
			intent1.putExtra("Bundle", mBundle1);
			startActivity(intent1);
			break;
		case R.id.iv_backMonth:
			if (month <= 1) {
				month = 12;
				year--;
			} else {
				month--;
			}
			setListViewAdapterToDate(month, year);
			suKienHorizontalAdapter.changeBackground(day - 1);
			break;
		case R.id.iv_nextMonth:
			if (month > 11) {
				month = 1;
				year++;
			} else {
				month++;
			}
			setListViewAdapterToDate(month, year);
			suKienHorizontalAdapter.changeBackground(day - 1);
			break;
		}
	}

	private void setListViewAdapterToDate(int month, int year) {
		calendar.set(year, month - 1, calendar.get(Calendar.DAY_OF_MONTH));
		switch (month) {
		case 1:
			tv_thangNamSukien.setText("January "+ year);
			break;
		case 2:
			tv_thangNamSukien.setText("February "+ year);
			break;
		case 3:
			tv_thangNamSukien.setText("March "+ year);
			break;
		case 4:
			tv_thangNamSukien.setText("April "+ year);
			break;
		case 5:
			tv_thangNamSukien.setText("May "+ year);
			break;
		case 6:
			tv_thangNamSukien.setText("June "+ year);
			break;
		case 7:
			tv_thangNamSukien.setText("July "+ year);
			break;
		case 8:
			tv_thangNamSukien.setText("August "+ year);
			break;
		case 9:
			tv_thangNamSukien.setText("September "+ year);
			break;
		case 10:
			tv_thangNamSukien.setText("October "+ year);
			break;
		case 11:
			tv_thangNamSukien.setText("November "+ year);
			break;
		case 12:
			tv_thangNamSukien.setText("December "+ year);
			break;

		default:
			break;
		}
		suKienHorizontalAdapter = new CustomSuKienHorizontalAdapter(context,
				month, year);
		adapter.notifyDataSetChanged();
		mListView.setAdapter(suKienHorizontalAdapter);
		suKienHorizontalAdapter.notifyDataSetChanged();
	}

	private RemoveListener removeListener = new RemoveListener() {
		@Override
		public void showDialog(int position, String id) {
			MyAlertDialogFragment frag = MyAlertDialogFragment.newInstance(
					R.string.title, position, id);
			frag.init(SuKienFragment.this);
			frag.show(getFragmentManager(), "dialog");
		}
	};

	private ShowDialogListener showDialogListener = new ShowDialogListener() {
		@Override
		public void showDialog(int position) {
			CustomDialogFragment frag = CustomDialogFragment.newInstance(
					"Thông Tin Sự Kiện", position);
			frag.init(SuKienFragment.this);
			frag.show(getFragmentManager(), "dialog");
		}
	};

	public interface RemoveListener {
		public void showDialog(int position, String id);
	}

	public interface ShowDialogListener {
		public void showDialog(int position);
	}

	public static class MyAlertDialogFragment extends DialogFragment {
		SuKienFragment suKienFragment;

		public static MyAlertDialogFragment newInstance(int title,
				int position, String id) {
			MyAlertDialogFragment frag = new MyAlertDialogFragment();
			Bundle args = new Bundle();
			args.putInt("title", title);
			args.putInt("pos", position);
			args.putString("id", id);
			frag.setArguments(args);
			return frag;
		}

		public void init(SuKienFragment suKienFragment) {
			this.suKienFragment = suKienFragment;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			int title = getArguments().getInt("title");
			final int postion = getArguments().getInt("pos");
			final String id = getArguments().getString("id");
			return new AlertDialog.Builder(getActivity())
					.setTitle(title)
					.setMessage("Bạn có chắc chắn muốn xóa sự kiện này?")
					.setPositiveButton("Đồng ý",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									suKienFragment.removeListView(postion);
									if (suKienFragment.lv_suKien == null) {
										suKienFragment.tv_sukien_thanhcong
												.setVisibility(View.VISIBLE);
									}
									DBSuKienHelper suKienHelper = new DBSuKienHelper(
											getActivity());
									suKienHelper.deleteSuKien(Integer
											.parseInt(id));
								}
							})
					.setNegativeButton("Hủy",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									dialog.cancel();
								}
							}).create();

		}

	}

	public void removeListView(int positon) {
		adapter.removeItems(positon);
	}

	@SuppressLint("NewApi")
	public static class CustomDialogFragment extends DialogFragment {

		SuKienFragment suKienFragment;
		TextView tv_nameSuKien, tv_diaDiemSuKien, tv_chiTietSuKien,
				tv_thoiGianSuKien;
		Button btn_cancel;

		public static CustomDialogFragment newInstance(String title,
				int position) {
			CustomDialogFragment frag = new CustomDialogFragment();
			Bundle args = new Bundle();
			args.putString("title", title);
			args.putInt("pos", position);
			frag.setArguments(args);
			return frag;
		}

		public void init(SuKienFragment suKienFragment) {
			this.suKienFragment = suKienFragment;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.layout_dialog_sukien,
					container);
			tv_nameSuKien = (TextView) view.findViewById(R.id.tv_nameSuKien);
			tv_diaDiemSuKien = (TextView) view
					.findViewById(R.id.tv_diaDiemSuKien);
			tv_chiTietSuKien = (TextView) view
					.findViewById(R.id.tv_chiTietSuKien);
			tv_thoiGianSuKien = (TextView) view
					.findViewById(R.id.tv_thoiGianSuKien);
			btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
			btn_cancel.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					getDialog().dismiss();
				}
			});

			String title = "Sự kiện";
			if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB_MR1) {
				title = getArguments().getString("title", "Sự kiện");
			}

			getDialog().setTitle(title);

			DBSuKienHelper suKienHelper = new DBSuKienHelper(getActivity());
			ArrayList<Sukien> list = suKienHelper.getAllSuKien();
			for (Sukien sukien : list) {
				tv_nameSuKien.setText("Tên Sự Kiện : " + sukien.getSukien());
				tv_diaDiemSuKien.setText("Địa Điểm : " + sukien.getDiadiem());
				tv_chiTietSuKien.setText("Chi Tiết Sự Kiện : "
						+ sukien.getChitiet());
				tv_thoiGianSuKien.setText("Thời Gian Sự Kiện : Từ Ngày "
						+ sukien.getDay_begin() + "/" + sukien.getMonth_begin()
						+ "/" + sukien.getYear_begin() + " Đến Ngày "
						+ sukien.getDay_end() + "/" + sukien.getMonth_end()
						+ "/" + sukien.getYear_end());
			}
			// Show soft keyboard automatically
			getDialog().getWindow().setSoftInputMode(
					WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
			return view;
		}
	}
}
