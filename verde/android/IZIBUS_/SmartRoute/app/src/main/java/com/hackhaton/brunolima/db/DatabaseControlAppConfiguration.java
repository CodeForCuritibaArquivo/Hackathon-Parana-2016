package com.hackhaton.brunolima.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class DatabaseControlAppConfiguration {

	//Table name
	private static final String TABLE_NAME = "d8_appconf";

	private static final String KEY_ROWID = "_id";
	private static final String KEY_AVAILABLE = "available";
	private static final String KEY_METRIC_SYSTEM = "metric_system";
	private static final String KEY_NOTIFICATION_NEW_MESSAGE = "notification_new_message";
	private static final String KEY_NOTIFICATION_NEW_CONNECTION = "notification_new_connection";
	private static final String KEY_SOUND_NEW_MESSAGE = "sound_new_message";
	private static final String KEY_SOUND_NEW_CONNECTION = "sound_new_connection";


	private Context context;
	private SQLiteDatabase db;
	private DatabaseHelper dbHelper;

	public DatabaseControlAppConfiguration(Context context){
		this.context = context;
	}

	public DatabaseControlAppConfiguration open() throws SQLiteException{
		dbHelper = new DatabaseHelper(context);
		db = dbHelper.getWritableDatabase();
		return this;	
	}

	public void close(){
		dbHelper.close();	
	}

	public long createDefaultSearchConfiguration(){
		ContentValues values = createContentValues(1, 1, 0, 1, 1, 1, 1);
		return db.insert(TABLE_NAME, null, values);
	}

	public boolean update(int _id, int available, int metricSystem, int notificationNewMessage, int notificationNewConnection,
			int soundNewMessage, int soundNewConnection){

		ContentValues updateValues = createContentValues(_id, available, metricSystem, notificationNewMessage, 
				notificationNewConnection, soundNewMessage, soundNewConnection);

		return db.update(TABLE_NAME, updateValues, KEY_ROWID + " = " + _id, null) > 0;
	}

	public boolean updateFromContent(ContentValues content){

		ContentValues updateValues = createContentValues(content.getAsInteger("KEY_ROWID"), 
				content.getAsInteger("KEY_AVAILABLE"), 
				content.getAsInteger("KEY_METRIC_SYSTEM"), 
				content.getAsInteger("KEY_NOTIFICATION_NEW_MESSAGE"),
				content.getAsInteger("KEY_NOTIFICATION_NEW_CONNECTION"),
				content.getAsInteger("KEY_SOUND_NEW_MESSAGE"),
				content.getAsInteger("KEY_SOUND_NEW_CONNECTION"));

		return db.update(TABLE_NAME, updateValues, KEY_ROWID + " = " + content.getAsInteger("KEY_ROWID"), null) > 0;
	}

	public void drop(){
		db.execSQL("DROP TABLE IF EXISTS d8_appconf;");
	}

	public ContentValues getAppConfiguration(){
		Cursor c;
		c = db.rawQuery("select * from "+TABLE_NAME+" where _id = ?", new String[]{"1"});
		c.moveToFirst();

		ContentValues values = new ContentValues();

		values.put("KEY_ROWID", c.getInt(c.getColumnIndex(KEY_ROWID)));
		values.put("KEY_AVAILABLE", c.getInt(c.getColumnIndex(KEY_AVAILABLE)));
		values.put("KEY_METRIC_SYSTEM", c.getInt(c.getColumnIndex(KEY_METRIC_SYSTEM)));
		values.put("KEY_NOTIFICATION_NEW_MESSAGE", c.getInt(c.getColumnIndex(KEY_NOTIFICATION_NEW_MESSAGE)));
		values.put("KEY_NOTIFICATION_NEW_CONNECTION", c.getInt(c.getColumnIndex(KEY_NOTIFICATION_NEW_CONNECTION)));
		values.put("KEY_SOUND_NEW_MESSAGE", c.getInt(c.getColumnIndex(KEY_SOUND_NEW_MESSAGE)));
		values.put("KEY_SOUND_NEW_CONNECTION", c.getInt(c.getColumnIndex(KEY_SOUND_NEW_CONNECTION)));

		return values;
	}

	public ContentValues createContentValues(int _id, int available, int metricSystem, int notificationNewMessage, int notificationNewConnection,
			int soundNewMessage, int soundNewConnection){

		ContentValues values = new ContentValues();
		values.put(KEY_ROWID, _id);
		values.put(KEY_AVAILABLE, available);
		values.put(KEY_METRIC_SYSTEM, metricSystem);
		values.put(KEY_NOTIFICATION_NEW_MESSAGE, notificationNewMessage);
		values.put(KEY_NOTIFICATION_NEW_CONNECTION, notificationNewConnection);
		values.put(KEY_SOUND_NEW_MESSAGE, soundNewMessage);
		values.put(KEY_SOUND_NEW_CONNECTION, soundNewConnection);

		return values;
	}
}
