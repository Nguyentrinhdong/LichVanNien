package com.bhmedia.lichvannien2016.ui.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Vibrator;
import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.activity.MainActivity;
import com.bhmedia.lichvannien2016.ui.database.DBSuKienHelper;
import com.bhmedia.lichvannien2016.ui.database.NgayLeDataBaseHelper;
import com.bhmedia.lichvannien2016.ui.model.Sukien;


/**
 * Created by Van on 6/23/2015.
 */
public class NotificationService extends Service {
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {

		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {

				checkSuKien();
				getNotificationNgayLe();
			}
		};

		timer = new Timer();
		int delay = 1 * 60 * 1000;
		timer.scheduleAtFixedRate(timerTask, 0, delay);
	}

	private Timer timer;

	private void checkSuKien() {
		Date dateCurr = new Date();
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(dateCurr);
		int timeShow = calendar1.get(Calendar.HOUR_OF_DAY) * 60
				+ calendar1.get(Calendar.MINUTE);
		DBSuKienHelper suKienHelper = new DBSuKienHelper(
				NotificationService.this);
		boolean showDialog = suKienHelper.getCheckShowEvent(
				calendar1.get(Calendar.DAY_OF_MONTH),
				calendar1.get(Calendar.MONTH) + 1,
				calendar1.get(Calendar.YEAR), timeShow);

		if (showDialog == true) {
			Sukien sukien = suKienHelper.getSuKienByTime(
					calendar1.get(Calendar.DAY_OF_MONTH),
					calendar1.get(Calendar.MONTH) + 1,
					calendar1.get(Calendar.YEAR), timeShow);

			int day = calendar1.get(Calendar.DAY_OF_MONTH);
			int month = calendar1.get(Calendar.MONTH) + 1;
			int year = calendar1.get(Calendar.YEAR);

			String str = day + "/" + month + "/" + year;
			showNotification(sukien.getSukien(), str);
		}

		suKienHelper.close();
	}

	@SuppressLint("DefaultLocale")
	private void getNotificationNgayLe() {

		Calendar calendar = Calendar.getInstance();

		int minute = calendar.get(Calendar.MINUTE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH) + 1;

		if (hour == 7 && minute == 30) {
			NgayLeDataBaseHelper ngayLeDataBaseHelper = new NgayLeDataBaseHelper(
					this);

			String key = String.format("%02d%02d", day, month);

			System.out.println(key);
			String value = ngayLeDataBaseHelper.showDatabase(this, key);

			ngayLeDataBaseHelper.close();
			if (value != null) {
				String str = day + "/" + month;
				showNotification(value, str);
			}
		}

	}

	private void showNotification(String title, String message) {
		NotificationManager mManager = (NotificationManager) this
				.getApplicationContext().getSystemService(
						this.getApplicationContext().NOTIFICATION_SERVICE);
		Intent intent1 = new Intent(this.getApplicationContext(),
				MainActivity.class);

		Notification notification = new Notification(
				R.drawable.icon_lichvannien, message,
				System.currentTimeMillis());
		intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TOP);

		PendingIntent pendingNotificationIntent = PendingIntent.getActivity(
				this.getApplicationContext(), 0, intent1,
				PendingIntent.FLAG_UPDATE_CURRENT);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.setLatestEventInfo(this.getApplicationContext(), title,
				message, pendingNotificationIntent);

		mManager.notify(0, notification);

		Vibrator v = (Vibrator) NotificationService.this
				.getSystemService(Context.VIBRATOR_SERVICE);
		v.vibrate(500);
	}
}
