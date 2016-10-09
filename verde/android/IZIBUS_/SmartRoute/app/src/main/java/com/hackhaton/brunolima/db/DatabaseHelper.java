package com.hackhaton.brunolima.db;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper{
	private static final String DATABASE_NAME = "d8db";
	private static final int DATABASE_VERSION = 1;
	private static final ArrayList<String> DATABASE_TABLES = new ArrayList<String>();
	private Context context;
	
	public DatabaseHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		DATABASE_TABLES.add("CREATE TABLE d8_searchconf ("
				+ "_id integer PRIMARY KEY AUTOINCREMENT,"
				+ "lookingfor integer NOT NULL DEFAULT 0,"
				+ "age_from integer NOT NULL DEFAULT 18,"
				+ "age_to integer NOT NULL DEFAULT 100,"
				+ "distance integer NOT NULL DEFAULT 1000,"
				+ "advance_filter integer NOT NULL DEFAULT 0,"
				+ "ad_height_from integer NOT NULL DEFAULT 60," 
				+ "ad_height_to integer NOT NULL DEFAULT 250,"
				+ "ad_weight_from integer NOT NULL DEFAULT 40,"
				+ "ad_weight_to integer NOT NULL DEFAULT 300,"
				+ "ad_ethnicity_all integer NOT NULL DEFAULT 1,"				
				+ "ad_ethnicity_asian integer NOT NULL DEFAULT 0,"
				+ "ad_ethnicity_black integer NOT NULL DEFAULT 0,"
				+ "ad_ethnicity_latin integer NOT NULL DEFAULT 0,"
				+ "ad_ethnicity_arabic integer NOT NULL DEFAULT 0,"
				+ "ad_ethnicity_brown integer NOT NULL DEFAULT 0,"
				+ "ad_ethnicity_native_american integer NOT NULL DEFAULT 0,"
				+ "ad_ethnicity_south_asian integer NOT NULL DEFAULT 0,"
				+ "ad_ethnicity_white integer NOT NULL DEFAULT 0,"
				+ "ad_city integer NOT NULL DEFAULT 0,"
				+ "ad_cities text"
				+ ");");
		
		DATABASE_TABLES.add("CREATE TABLE d8_appconf ("
				+ "_id integer PRIMARY KEY AUTOINCREMENT,"
				+ "available integer NOT NULL DEFAULT 1,"
				+ "metric_system integer NOT NULL DEFAULT 0,"
				+ "notification_new_message integer NOT NULL DEFAULT 1,"
				+ "notification_new_connection integer NOT NULL DEFAULT 1,"
				+ "sound_new_message integer NOT NULL DEFAULT 1,"
				+ "sound_new_connection integer NOT NULL DEFAULT 1"
				+ ");");
		
		for (int i = 0; i < DATABASE_TABLES.size(); i++) {
			db.execSQL(DATABASE_TABLES.get(i).toString());
		}
		Toast.makeText(context, "DB CRIADA", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		//db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME+ ";");
		//onCreate(db);
	}

}



