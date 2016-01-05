package com.bhmedia.lichvannien2016.ui.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bhmedia.lichvannien2016.ui.model.Category;
import com.bhmedia.lichvannien2016.ui.model.GiaiMong;

public class DataNew1DataBaseHelper extends SQLiteOpenHelper {
	private SQLiteDatabase myDataBase;
	private final Context myContext;
	private static final String DATABASE_NAME = "datanew1.sqlite";
	public static String DATABASE_PATH;
	public static final int DATABASE_VERSION = 1;

	// Constructor
	public DataNew1DataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.myContext = context;

		DATABASE_PATH = "/data/data/" + context.getPackageName()
				+ "/databases/";

		try {
			createDatabase();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create a empty database on the system
	public void createDatabase() throws IOException {
		boolean dbExist = checkDataBase();

		if (dbExist) {
			Log.v("DB Exists", "db exists");
			// By calling this method here onUpgrade will be called on a
			// writeable database, but only if the version number has been
			// bumped
			// onUpgrade(myDataBase, DATABASE_VERSION_old, DATABASE_VERSION);
		}

		boolean dbExist1 = checkDataBase();
		if (!dbExist1) {
			this.getReadableDatabase();
			try {
				this.close();
				copyDataBase();
				openDatabase();
			} catch (IOException e) {
				throw new Error("Error copying database");
			}
		}
	}

	// Check database already exist or not
	private boolean checkDataBase() {
		boolean checkDB = false;
		try {
			String myPath = DATABASE_PATH + DATABASE_NAME;
			File dbfile = new File(myPath);
			checkDB = dbfile.exists();
		} catch (SQLiteException e) {
		}
		return checkDB;
	}

	// Copies your database from your local assets-folder to the just created
	// empty database in the system folder
	private void copyDataBase() throws IOException {
		String outFileName = DATABASE_PATH + DATABASE_NAME;
		OutputStream myOutput = new FileOutputStream(outFileName);
		InputStream myInput = myContext.getAssets().open(DATABASE_NAME);

		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}
		myInput.close();
		myOutput.flush();
		myOutput.close();
	}

	// delete database
	public void db_delete() {
		File file = new File(DATABASE_PATH + DATABASE_NAME);
		if (file.exists()) {
			file.delete();
			System.out.println("delete database file.");
		}
	}

	// Open database
	public void openDatabase() throws SQLException {
		String myPath = DATABASE_PATH + DATABASE_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READWRITE);
	}

	public synchronized void closeDataBase() throws SQLException {
		if (myDataBase != null)
			myDataBase.close();
		super.close();
	}

	public void onCreate(SQLiteDatabase db) {
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (newVersion > oldVersion) {
			Log.v("Database Upgrade", "Database version higher than old.");
			db_delete();
		}
	}
	
	public Category getAllLabelct(Context context,String month, String title) {
		Category ca = new Category();

		// Select All Query
		String selectQuery = "SELECT  * FROM chiemtinh where month='" + month + "' and title='"+title+"'";
		SQLiteDatabase sqLiteDatabase = getReadableDatabase();
		Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				String content = cursor.getString(3);
				ca.setCate(content);
			} while (cursor.moveToNext());
		}

		// closing connection
		cursor.close();

		// returning lables
		return ca;
	}
	
	@SuppressWarnings("null")
	public List<String> getAllLabelgm(Context context) {
		List<String> listcate = null;

		// Select All Query
		String selectQuery = "SELECT  * FROM giaimong";
		SQLiteDatabase sqLiteDatabase = getReadableDatabase();
		Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				String id=cursor.getString(0);
				String cate=cursor.getString(1);
				String title=cursor.getString(2);
				String content = cursor.getString(3);
				listcate.add(title);
			} while (cursor.moveToNext());
		}

		// closing connection
		cursor.close();

		// returning lables
		return listcate;
	}
	
	public ArrayList<GiaiMong> getAllLabelgma(Context context) {
		ArrayList<GiaiMong> listcate=new ArrayList<GiaiMong>();

		// Select All Query
		String selectQuery = "SELECT  * FROM giaimong";
		SQLiteDatabase sqLiteDatabase = getReadableDatabase();
		Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				String id=cursor.getString(0);
				String cate=cursor.getString(1);
				String title=cursor.getString(2);
				String content = cursor.getString(3);
				GiaiMong gm=new GiaiMong();
				gm.setId(id);
				gm.setCate(cate);
				gm.setTitle(title);
				gm.setContent(content);
				listcate.add(gm);
			} while (cursor.moveToNext());
		}

		// closing connection
		cursor.close();

		// returning lables
		return listcate;
	}
	
	public ArrayList<GiaiMong> getAllLabelgmtk(Context context, String chuoi) {
		ArrayList<GiaiMong> listcate=new ArrayList<GiaiMong>();

		// Select All Query
		String selectQuery = "SELECT  * FROM giaimong where title like '%"+chuoi+"%'";
		SQLiteDatabase sqLiteDatabase = getReadableDatabase();
		Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				String id=cursor.getString(0);
				String cate=cursor.getString(1);
				String title=cursor.getString(2);
				String content = cursor.getString(3);
				GiaiMong gm=new GiaiMong();
				gm.setId(id);
				gm.setCate(cate);
				gm.setTitle(title);
				gm.setContent(content);
				listcate.add(gm);
			} while (cursor.moveToNext());
		}

		// closing connection
		cursor.close();

		// returning lables
		return listcate;
	}
	
	public Category getAllLabelgq(Context context,String id) {
		Category ca = new Category();

		// Select All Query
		String selectQuery = "SELECT  * FROM queboi where id='" + id + "'";
		SQLiteDatabase sqLiteDatabase = getReadableDatabase();
		Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				String content = cursor.getString(2);
				ca.setCate(content);
			} while (cursor.moveToNext());
		}

		// closing connection
		cursor.close();

		// returning lables
		return ca;
	}
}
