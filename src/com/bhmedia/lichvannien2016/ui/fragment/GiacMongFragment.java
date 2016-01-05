package com.bhmedia.lichvannien2016.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.adapter.GiaiMongAdapter;
import com.bhmedia.lichvannien2016.ui.database.DataNew1DataBaseHelper;
import com.bhmedia.lichvannien2016.ui.model.GiaiMong;
import com.google.android.gms.ads.AdView;

public class GiacMongFragment extends BaseFragment implements
		View.OnClickListener {
	Context context;
	// Wheel scrolled flag
	public static String thongtin;
	private AdView mAdView;
	public static String idchd = "";
	private ListView lvgiaimonga;
	TextView tva, tvb, tvc, tvd, tve, tvf, tvg, tvh, tvi, tvj, tvk, tvl, tvm,
			tvn, tvo, tvp, tvq, tvr, tvs, tvt, tvu, tvv, tvw, tvx, tvy, tvz;
	LinearLayout llchucai, lledittext;
	EditText edttimkiem;

	@Override
	protected int getLayoutId() {

		return R.layout.activity_giaimong;
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
		llchucai = (LinearLayout) findViewById(R.id.llchucai);
		lledittext = (LinearLayout) findViewById(R.id.lledittext);
		edttimkiem = (EditText) findViewById(R.id.edttimkiem);
		lvgiaimonga = (ListView) findViewById(R.id.lvgiaimonga);
		int width2 = getActivity().getResources().getDisplayMetrics().widthPixels;
		int height2 = getActivity().getResources().getDisplayMetrics().heightPixels;
		LayoutParams params2 = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);

		llchucai.setLayoutParams(params2);
		llchucai.getLayoutParams().width = width2;
		llchucai.getLayoutParams().height = height2 * 2 / 28;

		llchucai.setLayoutParams(params2);
		llchucai.getLayoutParams().width = width2 / 10;
		llchucai.getLayoutParams().height = height2 * 26 / 28;

		lvgiaimonga.setLayoutParams(params2);
		lvgiaimonga.getLayoutParams().width = width2 * 9 / 10;
		lvgiaimonga.getLayoutParams().height = height2 * 26 / 28;
		DataNew1DataBaseHelper db = new DataNew1DataBaseHelper(getActivity());
		final List<GiaiMong> lia = db.getAllLabelgma(getActivity());
		GiaiMongAdapter adapa = new GiaiMongAdapter(getActivity(),
				R.layout.item_giaimong, lia);
		lvgiaimonga.setAdapter(adapa);
		tva = (TextView) findViewById(R.id.tva);
		tvb = (TextView) findViewById(R.id.tvb);
		tvc = (TextView) findViewById(R.id.tvc);
		tvd = (TextView) findViewById(R.id.tvd);
		tve = (TextView) findViewById(R.id.tve);
		tvf = (TextView) findViewById(R.id.tvf);
		tvg = (TextView) findViewById(R.id.tvg);
		tvh = (TextView) findViewById(R.id.tvh);
		tvi = (TextView) findViewById(R.id.tvi);
		tvj = (TextView) findViewById(R.id.tvj);
		tvk = (TextView) findViewById(R.id.tvk);
		tvl = (TextView) findViewById(R.id.tvl);
		tvm = (TextView) findViewById(R.id.tvm);
		tvn = (TextView) findViewById(R.id.tvn);
		tvo = (TextView) findViewById(R.id.tvo);
		tvp = (TextView) findViewById(R.id.tvp);
		tvq = (TextView) findViewById(R.id.tvq);
		tvr = (TextView) findViewById(R.id.tvr);
		tvs = (TextView) findViewById(R.id.tvs);
		tvt = (TextView) findViewById(R.id.tvt);
		tvu = (TextView) findViewById(R.id.tvu);
		tvv = (TextView) findViewById(R.id.tvv);
		tvw = (TextView) findViewById(R.id.tvw);
		tvx = (TextView) findViewById(R.id.tvx);
		tvy = (TextView) findViewById(R.id.tvy);
		tvz = (TextView) findViewById(R.id.tvz);
		// Set lại kích thước cho view
		int width = getActivity().getResources().getDisplayMetrics().widthPixels;
		int height = getActivity().getResources().getDisplayMetrics().heightPixels;
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);

		tva.setLayoutParams(params);
		tva.getLayoutParams().width = width / 16;
		tva.getLayoutParams().height = height / 28;

		tvb.setLayoutParams(params);
		tvb.getLayoutParams().width = width / 16;
		tvb.getLayoutParams().height = height / 28;

		tvc.setLayoutParams(params);
		tvc.getLayoutParams().width = width / 16;
		tvc.getLayoutParams().height = height / 28;

		tvd.setLayoutParams(params);
		tvd.getLayoutParams().width = width / 16;
		tvd.getLayoutParams().height = height / 28;

		tve.setLayoutParams(params);
		tve.getLayoutParams().width = width / 16;
		tve.getLayoutParams().height = height / 28;

		tvf.setLayoutParams(params);
		tvf.getLayoutParams().width = width / 16;
		tvf.getLayoutParams().height = height / 28;

		tvg.setLayoutParams(params);
		tvg.getLayoutParams().width = width / 16;
		tvg.getLayoutParams().height = height / 28;

		tvh.setLayoutParams(params);
		tvh.getLayoutParams().width = width / 16;
		tvh.getLayoutParams().height = height / 28;

		tvi.setLayoutParams(params);
		tvi.getLayoutParams().width = width / 16;
		tvi.getLayoutParams().height = height / 28;

		tvj.setLayoutParams(params);
		tvj.getLayoutParams().width = width / 16;
		tvj.getLayoutParams().height = height / 28;

		tvk.setLayoutParams(params);
		tvk.getLayoutParams().width = width / 16;
		tvk.getLayoutParams().height = height / 28;

		tvl.setLayoutParams(params);
		tvl.getLayoutParams().width = width / 16;
		tvl.getLayoutParams().height = height / 28;

		tvm.setLayoutParams(params);
		tvm.getLayoutParams().width = width / 16;
		tvm.getLayoutParams().height = height / 28;

		tvn.setLayoutParams(params);
		tvn.getLayoutParams().width = width / 16;
		tvn.getLayoutParams().height = height / 28;

		tvo.setLayoutParams(params);
		tvo.getLayoutParams().width = width / 16;
		tvo.getLayoutParams().height = height / 28;

		tvp.setLayoutParams(params);
		tvp.getLayoutParams().width = width / 16;
		tvp.getLayoutParams().height = height / 28;

		tvq.setLayoutParams(params);
		tvq.getLayoutParams().width = width / 16;
		tvq.getLayoutParams().height = height / 28;

		tvr.setLayoutParams(params);
		tvr.getLayoutParams().width = width / 16;
		tvr.getLayoutParams().height = height / 28;

		tvs.setLayoutParams(params);
		tvs.getLayoutParams().width = width / 16;
		tvs.getLayoutParams().height = height / 28;

		tvt.setLayoutParams(params);
		tvt.getLayoutParams().width = width / 16;
		tvt.getLayoutParams().height = height / 28;

		tvu.setLayoutParams(params);
		tvu.getLayoutParams().width = width / 16;
		tvu.getLayoutParams().height = height / 28;

		tvv.setLayoutParams(params);
		tvv.getLayoutParams().width = width / 16;
		tvv.getLayoutParams().height = height / 28;

		tvw.setLayoutParams(params);
		tvw.getLayoutParams().width = width / 16;
		tvw.getLayoutParams().height = height / 28;

		tvx.setLayoutParams(params);
		tvx.getLayoutParams().width = width / 16;
		tvx.getLayoutParams().height = height / 28;

		tvy.setLayoutParams(params);
		tvy.getLayoutParams().width = width / 16;
		tvy.getLayoutParams().height = height / 28;

		tvz.setLayoutParams(params);
		tvz.getLayoutParams().width = width / 16;
		tvz.getLayoutParams().height = height / 28;

		// Close set lại kích thước cho view
		tva.setOnClickListener(this);
		tvb.setOnClickListener(this);
		tvc.setOnClickListener(this);
		tvd.setOnClickListener(this);
		tve.setOnClickListener(this);
		tvf.setOnClickListener(this);
		tvg.setOnClickListener(this);
		tvh.setOnClickListener(this);
		tvi.setOnClickListener(this);
		tvj.setOnClickListener(this);
		tvk.setOnClickListener(this);
		tvl.setOnClickListener(this);
		tvm.setOnClickListener(this);
		tvn.setOnClickListener(this);
		tvo.setOnClickListener(this);
		tvp.setOnClickListener(this);
		tvq.setOnClickListener(this);
		tvr.setOnClickListener(this);
		tvs.setOnClickListener(this);
		tvt.setOnClickListener(this);
		tvu.setOnClickListener(this);
		tvv.setOnClickListener(this);
		tvw.setOnClickListener(this);
		tvx.setOnClickListener(this);
		tvy.setOnClickListener(this);
		tvz.setOnClickListener(this);
		//Set edit edittext
		edttimkiem.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				DataNew1DataBaseHelper db = new DataNew1DataBaseHelper(getActivity());
				final List<GiaiMong> lia = db.getAllLabelgmtk(getActivity(), edttimkiem.getText().toString());
				GiaiMongAdapter adapa = new GiaiMongAdapter(getActivity(),
						R.layout.item_giaimong, lia);
				lvgiaimonga.setAdapter(adapa);
			}
		});
		//Close set edittext
		lvgiaimonga.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
//				String youtContentStr = String.valueOf(Html
//						.fromHtml("<![CDATA[<body style=\"text-align:justify;color:#222222; \">"
//								+ lia.get(position).getContent() + "</body>]]>"));
				LinearLayout layout2 = new LinearLayout(getActivity());
				AlertDialog.Builder builder2 = new AlertDialog.Builder(layout2
						.getContext());
//				builder2.setTitle(lia.get(position).getTitle());
				builder2.setMessage(Html.fromHtml(lia.get(position).getContent()));
				builder2.setPositiveButton("Share",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// -----------------------Send có lựa
								// chọn------------------//
								Intent emailIntent = new Intent();
								emailIntent.setAction(Intent.ACTION_SEND);
								String shareBody = String.valueOf(Html
										.fromHtml(lia.get(position)
												.getContent()))
										+ "\n"
										+ "Download Link: https://play.google.com/store/apps/details?id="
										+ getActivity().getPackageName()
										+ "&hl=en";
								;
								String subject = "Dream Interpretation - BHMedia Android";
								emailIntent.putExtra(Intent.EXTRA_TEXT,
										shareBody);
								emailIntent.putExtra(Intent.EXTRA_SUBJECT,
										subject);
								emailIntent.setType("message/rfc822");
								PackageManager pm = getActivity()
										.getPackageManager();
								Intent sendIntent = new Intent(
										Intent.ACTION_SEND);
								sendIntent.setType("text/plain");
								Intent openInChooser = Intent.createChooser(
										emailIntent, "Share with");

								List<ResolveInfo> resInfo = pm
										.queryIntentActivities(sendIntent, 0);
								List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
								for (int i = 0; i < resInfo.size(); i++) {
									// Extract the label, append it, and
									// repackage it in a
									// LabeledIntent
									ResolveInfo ri = resInfo.get(i);
									String packageName = ri.activityInfo.packageName;
									if (packageName.contains("android.email")) {
										emailIntent.setPackage(packageName);
									} else if (packageName.contains("twitter")
											|| packageName.contains("facebook")
											|| packageName.contains("mms")
											|| packageName
													.contains("android.gm")) {
										Intent intent = new Intent();
										intent.setComponent(new ComponentName(
												packageName,
												ri.activityInfo.name));
										intent.setAction(Intent.ACTION_SEND);
										intent.setType("text/plain");
										if (packageName.contains("twitter")) {
											intent.putExtra(
													Intent.EXTRA_TEXT,
													"Download Link: https://play.google.com/store/apps/details?id="
															+ getActivity()
																	.getPackageName()
															+ "&hl=en");
										} else if (packageName
												.contains("facebook")) {
											// Warning: Facebook IGNORES our
											// text. They say
											// "These fields are intended for users to express themselves. Pre-filling these fields erodes the authenticity of the user voice."
											// One workaround is to use the
											// Facebook SDK to post,
											// but that doesn't allow the user
											// to choose how they
											// want to share. We can also make a
											// custom landing
											// page, and the link
											// will show the <meta content
											// ="..."> text from that
											// page with our link in Facebook.
											intent.putExtra(
													Intent.EXTRA_TEXT,
													"Download Link: https://play.google.com/store/apps/details?id="
															+ getActivity()
																	.getPackageName()
															+ "&hl=en");
										} else if (packageName.contains("mms")) {
											intent.putExtra(
													Intent.EXTRA_TEXT,
													"Download Link: https://play.google.com/store/apps/details?id="
															+ getActivity()
																	.getPackageName()
															+ "&hl=en");
										} else if (packageName
												.contains("android.gm")) {
											intent.putExtra(Intent.EXTRA_TEXT,
													shareBody);
											intent.putExtra(
													Intent.EXTRA_SUBJECT,
													subject);
											intent.setType("message/rfc822");
										}

										intentList.add(new LabeledIntent(
												intent, packageName, ri
														.loadLabel(pm), ri.icon));
									}
								}
								// convert intentList to array
								LabeledIntent[] extraIntents = intentList
										.toArray(new LabeledIntent[intentList
												.size()]);

								openInChooser.putExtra(
										Intent.EXTRA_INITIAL_INTENTS,
										extraIntents);
								getActivity().startActivity(openInChooser);
								// -----------------------Close send có lựa
								// chọn------------//
							}
						});
				builder2.setNegativeButton("Close",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
				builder2.create().show();
			}

		});
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tva:
			lvgiaimonga.setSelection(0);
			break;

		case R.id.tvb:
			lvgiaimonga.setSelection(212);
			break;

		case R.id.tvc:
			lvgiaimonga.setSelection(485);
			break;

		case R.id.tvd:
			lvgiaimonga.setSelection(812);
			break;

		case R.id.tve:
			lvgiaimonga.setSelection(956);
			break;

		case R.id.tvf:
			lvgiaimonga.setSelection(1044);
			break;

		case R.id.tvg:
			lvgiaimonga.setSelection(1178);
			break;

		case R.id.tvh:
			lvgiaimonga.setSelection(1283);
			break;

		case R.id.tvi:
			lvgiaimonga.setSelection(1426);
			break;

		case R.id.tvj:
			lvgiaimonga.setSelection(1484);
			break;

		case R.id.tvk:
			lvgiaimonga.setSelection(1533);
			break;

		case R.id.tvl:
			lvgiaimonga.setSelection(1565);
			break;

		case R.id.tvm:
			lvgiaimonga.setSelection(1686);
			break;

		case R.id.tvn:
			lvgiaimonga.setSelection(1866);
			break;

		case R.id.tvo:
			lvgiaimonga.setSelection(1918);
			break;

		case R.id.tvp:
			lvgiaimonga.setSelection(1967);
			break;

		case R.id.tvq:
			lvgiaimonga.setSelection(2208);
			break;

		case R.id.tvr:
			lvgiaimonga.setSelection(2227);
			break;

		case R.id.tvs:
			lvgiaimonga.setSelection(2364);
			break;

		case R.id.tvt:
			lvgiaimonga.setSelection(2740);
			break;

		case R.id.tvu:
			lvgiaimonga.setSelection(2926);
			break;

		case R.id.tvv:
			lvgiaimonga.setSelection(2952);
			break;

		case R.id.tvw:
			lvgiaimonga.setSelection(3006);
			break;

		case R.id.tvx:
			lvgiaimonga.setSelection(3132);
			break;

		case R.id.tvy:
			lvgiaimonga.setSelection(3135);
			break;

		case R.id.tvz:
			lvgiaimonga.setSelection(3156);
			break;

		default:
			break;
		}
	}
}
