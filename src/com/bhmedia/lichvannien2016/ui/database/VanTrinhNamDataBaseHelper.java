package com.bhmedia.lichvannien2016.ui.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.bhmedia.lichvannien2016.ui.model.VanTrinhNam;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Van on 1/30/2015.
 */
public class VanTrinhNamDataBaseHelper extends SQLiteOpenHelper {
	private SQLiteDatabase myDataBase;
	private final Context myContext;
	private static final String DATABASE_NAME = "vantrinhnam.sqlite";
	public static String DATABASE_PATH;
	public static final int DATABASE_VERSION = 1;

	// Constructor
	public VanTrinhNamDataBaseHelper(Context context) {
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
	
	public VanTrinhNam showVTNam(Context context, String namsinh) {

		try {
			SQLiteDatabase sqLiteDatabase = getReadableDatabase();
			Cursor cursor = sqLiteDatabase.query("vantrinhnam", new String[] {
					"id", "namsinh", "noidung" }, "namsinh ='" + namsinh + "'",
					null, null, null, null);
			VanTrinhNam vanTrinhNam;
			if (cursor.moveToFirst()) {
				vanTrinhNam = new VanTrinhNam(cursor.getInt(0) + "",
						cursor.getInt(1) + "", cursor.getString(2));
				
				cursor.close();
				return vanTrinhNam;
			}

			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			
		}
		
		return null;

	}

	
}
