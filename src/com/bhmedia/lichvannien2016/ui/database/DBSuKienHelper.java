package com.bhmedia.lichvannien2016.ui.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import com.bhmedia.lichvannien2016.ui.model.Sukien;


/**
 * Created by Van on 2/10/2015.
 */
public class DBSuKienHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "sukien.db";
	public static final String TABLE_NAME = "tblsukien";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_DIADIEM = "diadiem";
	public static final String COLUMN_CHITIET = "chitiet";
	public static final String COLUMN_DAY_BEGIN = "day_begin";
	public static final String COLUMN_MONTH_BEGIN = "month_begin";
	public static final String COLUMN_YEAR_BEGIN = "year_begin";

	public static final String COLUMN_HOUR_BEGIN = "hour_begin";
	public static final String COLUMN_MINUTE_BEGIN = "minute_begin";

	public static final String COLUMN_DAY_END = "day_end";
	public static final String COLUMN_MONTH_END = "month_end";
	public static final String COLUMN_YEAR_END = "year_end";

	public static final String COLUMN_HOUR_END = "hour_end";
	public static final String COLUMN_MINUTE_END = "minute_end";
	public static final String COLUMN_DAY_SHOW_DIALOG = "day_show_dialog";
	public static final String COLUMN_MONTH_SHOW_DIALOG = "month_show_dialog";
	public static final String COLUMN_YEAR_SHOW_DIALOG = "year_show_dialog";
	public static final String COLUMN_SPINNER = "spinner";
	public static final String COLUMN_TIME = "time";
	Context context;

	public DBSuKienHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table tblsukien "
				+ "(id integer primary key, name text,diadiem text,chitiet text, day_begin integer, month_begin integer, year_begin integer, hour_begin integer, minute_begin integer,"
				+ " day_end integer, month_end integer, year_end integer, hour_end integer, minute_end integer,day_show_dialog integer,"
				+ "month_show_dialog integer,year_show_dialog integer,spinner integer,time integer)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS tblsukien");
		onCreate(db);
	}

	public boolean insertSuKien(String name, String diadiem, String chitiet,
			int day_begin, int month_begin, int year_begin, int hour_begin,
			int minute_begin, int day_end, int month_end, int year_end,
			int hour_end, int minute_end, int day_show_dialog,
			int month_show_dialog, int year_show_dialog, int spinner, int time) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", name);
		contentValues.put("diadiem", diadiem);
		contentValues.put("chitiet", chitiet);
		contentValues.put("day_begin", day_begin);
		contentValues.put("month_begin", month_begin);
		contentValues.put("year_begin", year_begin);
		contentValues.put("hour_begin", hour_begin);
		contentValues.put("minute_begin", minute_begin);
		contentValues.put("day_end", day_end);
		contentValues.put("month_end", month_end);
		contentValues.put("year_end", year_end);
		contentValues.put("hour_end", hour_end);
		contentValues.put("minute_end", minute_end);
		contentValues.put("day_show_dialog", day_show_dialog);
		contentValues.put("month_show_dialog", month_show_dialog);
		contentValues.put("year_show_dialog", year_show_dialog);
		contentValues.put("spinner", spinner);
		contentValues.put("time", time);

		db.insert("tblsukien", null, contentValues);
		db.close();
		return true;
	}

	public Cursor getData(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from tblsukien where id=" + id + "",
				null);
		return res;
	}

	public int numberOfRows() {
		SQLiteDatabase db = this.getReadableDatabase();
		int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
		return numRows;
	}

	public boolean updateSuKien(Integer id, String name, String diadiem,
			String chitiet, int day_begin, int month_begin, int year_begin,
			int hour_begin, int minute_begin, int day_end, int month_end,
			int year_end, int hour_end, int minute_end, int day_show_dialog,
			int month_show_dialog, int year_show_dialog, int spinner, int time) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("id", id);
		contentValues.put("name", name);
		contentValues.put("diadiem", diadiem);
		contentValues.put("chitiet", chitiet);
		contentValues.put("day_begin", day_begin);
		contentValues.put("month_begin", month_begin);
		contentValues.put("year_begin", year_begin);
		contentValues.put("hour_begin", hour_begin);
		contentValues.put("minute_begin", minute_begin);
		contentValues.put("day_end", day_end);
		contentValues.put("month_end", month_end);
		contentValues.put("year_end", year_end);
		contentValues.put("hour_end", hour_end);
		contentValues.put("minute_end", minute_end);
		contentValues.put("day_show_dialog", day_show_dialog);
		contentValues.put("month_show_dialog", month_show_dialog);
		contentValues.put("year_show_dialog", year_show_dialog);
		contentValues.put("spinner", spinner);
		contentValues.put("time", time);

		db.update("tblsukien", contentValues, "id = ? ",
				new String[] { Integer.toString(id) });
		db.close();
		return true;
	}

	public Integer deleteSuKien(Integer id) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete("tblsukien", "id = ? ",
				new String[] { Integer.toString(id) });

	}

	public ArrayList<Sukien> getAllSuKien() {
		ArrayList<Sukien> array_list = new ArrayList<Sukien>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from tblsukien", null);
		res.moveToFirst();
		while (res.isAfterLast() == false) {
			array_list.add(new Sukien(res.getString(res
					.getColumnIndex(COLUMN_ID)), res.getString(res
					.getColumnIndex(COLUMN_NAME)), res.getString(res
					.getColumnIndex(COLUMN_DIADIEM)), res.getString(res
					.getColumnIndex(COLUMN_CHITIET)), res.getInt(res
					.getColumnIndex(COLUMN_DAY_BEGIN)), res.getInt(res
					.getColumnIndex(COLUMN_MONTH_BEGIN)), res.getInt(res
					.getColumnIndex(COLUMN_YEAR_BEGIN)), res.getInt(res
					.getColumnIndex(COLUMN_HOUR_BEGIN)), res.getInt(res
					.getColumnIndex(COLUMN_MINUTE_BEGIN)), res.getInt(res
					.getColumnIndex(COLUMN_DAY_END)), res.getInt(res
					.getColumnIndex(COLUMN_MONTH_END)), res.getInt(res
					.getColumnIndex(COLUMN_YEAR_END)), res.getInt(res
					.getColumnIndex(COLUMN_HOUR_END)), res.getInt(res
					.getColumnIndex(COLUMN_MINUTE_END))));
			res.moveToNext();
		}
		res.close();
		db.close();

		return array_list;
	}

	public ArrayList<Sukien> getAllSuKienByTime(int day, int month, int year) {
		ArrayList<Sukien> array_list = new ArrayList<Sukien>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from tblsukien where "
				+ COLUMN_DAY_BEGIN + "<=" + day + " AND " + COLUMN_DAY_END
				+ ">=" + day + " AND " + COLUMN_MONTH_BEGIN + "<=" + month
				+ " AND " + COLUMN_MONTH_END + ">=" + month + " AND "
				+ COLUMN_YEAR_BEGIN + "<=" + year + " AND " + COLUMN_YEAR_END
				+ ">=" + year, null);
		res.moveToFirst();

		while (res.isAfterLast() == false) {
			array_list.add(new Sukien(res.getString(res
					.getColumnIndex(COLUMN_ID)), res.getString(res
					.getColumnIndex(COLUMN_NAME)), res.getString(res
					.getColumnIndex(COLUMN_DIADIEM)), res.getString(res
					.getColumnIndex(COLUMN_CHITIET)), res.getInt(res
					.getColumnIndex(COLUMN_DAY_BEGIN)), res.getInt(res
					.getColumnIndex(COLUMN_MONTH_BEGIN)), res.getInt(res
					.getColumnIndex(COLUMN_YEAR_BEGIN)), res.getInt(res
					.getColumnIndex(COLUMN_HOUR_BEGIN)), res.getInt(res
					.getColumnIndex(COLUMN_MINUTE_BEGIN)), res.getInt(res
					.getColumnIndex(COLUMN_DAY_END)), res.getInt(res
					.getColumnIndex(COLUMN_MONTH_END)), res.getInt(res
					.getColumnIndex(COLUMN_YEAR_END)), res.getInt(res
					.getColumnIndex(COLUMN_HOUR_END)), res.getInt(res
					.getColumnIndex(COLUMN_MINUTE_END)), res.getInt(res
					.getColumnIndex(COLUMN_DAY_SHOW_DIALOG)), res.getInt(res
					.getColumnIndex(COLUMN_MONTH_SHOW_DIALOG)), res.getInt(res
					.getColumnIndex(COLUMN_YEAR_SHOW_DIALOG)), res.getInt(res
					.getColumnIndex(COLUMN_SPINNER)), res.getInt(res
					.getColumnIndex(COLUMN_TIME))));
			res.moveToNext();
		}

		res.close();
		db.close();

		return array_list;
	}

	public Sukien getSuKienByTime(int day_nhacnho, int month_nhacnho,
			int year_nhacnho, int time) {

		try {
			SQLiteDatabase db = this.getReadableDatabase();
			System.out.println(day_nhacnho + "   " + month_nhacnho + "   "
					+ year_nhacnho + "   " + time + "");
			Cursor res = db.rawQuery("select * from tblsukien where "
					+ COLUMN_DAY_SHOW_DIALOG + "=" + day_nhacnho + " AND "
					+ COLUMN_MONTH_SHOW_DIALOG + "=" + month_nhacnho + " AND "
					+ COLUMN_YEAR_SHOW_DIALOG + "=" + year_nhacnho + " AND "
					+ COLUMN_TIME + "=" + time, null);

			if (res.moveToFirst()) {
				Sukien sukien = new Sukien(
						res.getString(res.getColumnIndex(COLUMN_ID)),
						res.getString(res.getColumnIndex(COLUMN_NAME)),
						res.getString(res.getColumnIndex(COLUMN_DIADIEM)),
						res.getString(res.getColumnIndex(COLUMN_CHITIET)),
						res.getInt(res.getColumnIndex(COLUMN_DAY_BEGIN)),
						res.getInt(res.getColumnIndex(COLUMN_MONTH_BEGIN)),
						res.getInt(res.getColumnIndex(COLUMN_YEAR_BEGIN)),
						res.getInt(res.getColumnIndex(COLUMN_HOUR_BEGIN)),
						res.getInt(res.getColumnIndex(COLUMN_MINUTE_BEGIN)),
						res.getInt(res.getColumnIndex(COLUMN_DAY_END)),
						res.getInt(res.getColumnIndex(COLUMN_MONTH_END)),
						res.getInt(res.getColumnIndex(COLUMN_YEAR_END)),
						res.getInt(res.getColumnIndex(COLUMN_HOUR_END)),
						res.getInt(res.getColumnIndex(COLUMN_MINUTE_END)),
						res.getInt(res.getColumnIndex(COLUMN_DAY_SHOW_DIALOG)),
						res.getInt(res.getColumnIndex(COLUMN_MONTH_SHOW_DIALOG)),
						res.getInt(res.getColumnIndex(COLUMN_YEAR_SHOW_DIALOG)),
						res.getInt(res.getColumnIndex(COLUMN_SPINNER)), res
								.getInt(res.getColumnIndex(COLUMN_TIME)));

				res.close();
				db.close();
				return sukien;
			}
		} catch (Exception e) {

		}

		return null;
	}

	public boolean getCheckShowEvent(int day_nhacnho, int month_nhacnho,
			int year_nhacnho, int time) {
		try {
			SQLiteDatabase db = this.getReadableDatabase();
			System.out.println(day_nhacnho + "   " + month_nhacnho + "   "
					+ year_nhacnho + "   " + time + "");
			Cursor res = db.rawQuery("select * from tblsukien where "
					+ COLUMN_DAY_SHOW_DIALOG + "=" + day_nhacnho + " AND "
					+ COLUMN_MONTH_SHOW_DIALOG + "=" + month_nhacnho + " AND "
					+ COLUMN_YEAR_SHOW_DIALOG + "=" + year_nhacnho + " AND "
					+ COLUMN_TIME + "=" + time, null);

			boolean existsEvent = false;
			if (res.moveToFirst())
				existsEvent = true;

			res.close();

			return existsEvent;
		} catch (Exception e) {
			return false;
		}

	}
}