package com.hackhaton.brunolima.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class DatabaseControlSearchConfiguration {

	//Table name
	private static final String TABLE_NAME = "d8_searchconf";

	private static final String KEY_ROWID = "_id";
	private static final String KEY_LOOKINGFOR = "lookingfor";
	private static final String KEY_AGE_FROM = "age_from";
	private static final String KEY_AGE_TO = "age_to";
	private static final String KEY_DISTANCE = "distance";
	private static final String KEY_ADVANCE_FILTER = "advance_filter";
	private static final String KEY_AD_HEIGHT_FROM = "ad_height_from";
	private static final String KEY_AD_HEIGHT_TO = "ad_height_to";
	private static final String KEY_AD_WEIGHT_FROM = "ad_weight_from";
	private static final String KEY_AD_WEIGHT_TO= "ad_weight_to";
	private static final String KEY_AD_ETHNICITY_ALL = "ad_ethnicity_all";
	private static final String KEY_AD_ETHNICITY_ASIAN = "ad_ethnicity_asian";
	private static final String KEY_AD_ETHNICITY_BLACK = "ad_ethnicity_black";
	private static final String KEY_AD_ETHNICITY_LATIN = "ad_ethnicity_latin";
	private static final String KEY_AD_ETHNICITY_ARABIC = "ad_ethnicity_arabic";
	private static final String KEY_AD_ETHNICITY_BROWN = "ad_ethnicity_brown";
	private static final String KEY_AD_ETHNICITY_NATIVE_AMERICAN = "ad_ethnicity_native_american";
	private static final String KEY_AD_ETHNICITY_SOUTH_ASIAN = "ad_ethnicity_south_asian";
	private static final String KEY_AD_ETHNICITY_WHITE = "ad_ethnicity_white";
	private static final String KEY_AD_CITY = "ad_city";
	private static final String KEY_AD_CITIES = "ad_cities";


	private Context context;
	private SQLiteDatabase db;
	private DatabaseHelper dbHelper;

	public DatabaseControlSearchConfiguration(Context context){
		this.context = context;
	}

	public DatabaseControlSearchConfiguration open() throws SQLiteException{
		dbHelper = new DatabaseHelper(context);
		db = dbHelper.getWritableDatabase();
		return this;	
	}

	public void close(){
		dbHelper.close();	
	}

	public long createDefaultSearchConfiguration(){
		ContentValues values = createContentValues(1,3, 18, 60, 200, 0, 60, 250, 40, 300, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, "");
		return db.insert(TABLE_NAME, null, values);
	}

	public boolean update(int _id, int lookingfor, int age_from, int age_to, int distance, int advance_filter, int ad_height_from,
			int ad_height_to, int ad_weight_from, int ad_weight_to, int ad_ethnicity_all, int ad_ethnicity_asian, int ad_ethnicity_black,
			int ad_ethnicity_latin, int ad_ethnicity_arabic, int ad_ethnicity_brown, int ad_ethnicity_native_american, int ad_ethnicity_south_asian,
			int ad_ethnicity_white, int ad_city, String ad_cities){
		
		ContentValues updateValues = createContentValues(_id, lookingfor, age_from, age_to, distance, advance_filter, ad_height_from,
				ad_height_to, ad_weight_from, ad_weight_to, ad_ethnicity_all, ad_ethnicity_asian, ad_ethnicity_black,
				ad_ethnicity_latin, ad_ethnicity_arabic, ad_ethnicity_brown, ad_ethnicity_native_american, ad_ethnicity_south_asian,
				ad_ethnicity_white, ad_city, ad_cities);

		return db.update(TABLE_NAME, updateValues, KEY_ROWID + " = " + _id, null) > 0;
	}
	
	public boolean updateFromContent(ContentValues content){
		
		ContentValues updateValues = createContentValues(content.getAsInteger("KEY_ROWID"), content.getAsInteger("KEY_LOOKINGFOR"), content.getAsInteger("KEY_AGE_FROM"), content.getAsInteger("KEY_AGE_TO"),
				content.getAsInteger("KEY_DISTANCE"), content.getAsInteger("KEY_ADVANCE_FILTER"), content.getAsInteger("KEY_AD_HEIGHT_FROM"), content.getAsInteger("KEY_AD_HEIGHT_TO"), content.getAsInteger("KEY_AD_WEIGHT_FROM"),
				content.getAsInteger("KEY_AD_WEIGHT_TO"), content.getAsInteger("KEY_AD_ETHNICITY_ALL"), content.getAsInteger("KEY_AD_ETHNICITY_ASIAN"), content.getAsInteger("KEY_AD_ETHNICITY_BLACK"),
				content.getAsInteger("KEY_AD_ETHNICITY_LATIN"), content.getAsInteger("KEY_AD_ETHNICITY_ARABIC"), content.getAsInteger("KEY_AD_ETHNICITY_BROWN"), content.getAsInteger("KEY_AD_ETHNICITY_NATIVE_AMERICAN"),
				content.getAsInteger("KEY_AD_ETHNICITY_SOUTH_ASIAN"), content.getAsInteger("KEY_AD_ETHNICITY_WHITE"), content.getAsInteger("KEY_AD_CITY"), content.getAsString("KEY_AD_CITIES"));
		
		return db.update(TABLE_NAME, updateValues, KEY_ROWID + " = " + content.getAsInteger("KEY_ROWID"), null) > 0;
	}

	public void drop(){
		db.execSQL("DROP TABLE IF EXISTS d8_searchconf;");
	}
	
	public ContentValues getSearchConfiguration(){
		Cursor c;
		c = db.rawQuery("select * from "+TABLE_NAME+" where _id = ?", new String[]{"1"});
		c.moveToFirst();
		
		ContentValues values = new ContentValues();

		values.put("KEY_ROWID", c.getInt(c.getColumnIndex(KEY_ROWID)));
		values.put("KEY_LOOKINGFOR", c.getInt(c.getColumnIndex(KEY_LOOKINGFOR)));
		values.put("KEY_AGE_FROM", c.getInt(c.getColumnIndex(KEY_AGE_FROM)));
		values.put("KEY_AGE_TO", c.getInt(c.getColumnIndex(KEY_AGE_TO)));
		values.put("KEY_DISTANCE", c.getInt(c.getColumnIndex(KEY_DISTANCE)));
		values.put("KEY_ADVANCE_FILTER", c.getInt(c.getColumnIndex(KEY_ADVANCE_FILTER)));
		values.put("KEY_AD_HEIGHT_FROM", c.getInt(c.getColumnIndex(KEY_AD_HEIGHT_FROM)));
		values.put("KEY_AD_HEIGHT_TO", c.getInt(c.getColumnIndex(KEY_AD_HEIGHT_TO)));
		values.put("KEY_AD_WEIGHT_FROM", c.getInt(c.getColumnIndex(KEY_AD_WEIGHT_FROM)));
		values.put("KEY_AD_WEIGHT_TO", c.getInt(c.getColumnIndex(KEY_AD_WEIGHT_TO)));
		values.put("KEY_AD_ETHNICITY_ALL", c.getInt(c.getColumnIndex(KEY_AD_ETHNICITY_ALL)));
		values.put("KEY_AD_ETHNICITY_ASIAN", c.getInt(c.getColumnIndex(KEY_AD_ETHNICITY_ASIAN)));
		values.put("KEY_AD_ETHNICITY_BLACK", c.getInt(c.getColumnIndex(KEY_AD_ETHNICITY_BLACK)));
		values.put("KEY_AD_ETHNICITY_LATIN", c.getInt(c.getColumnIndex(KEY_AD_ETHNICITY_LATIN)));
		values.put("KEY_AD_ETHNICITY_ARABIC", c.getInt(c.getColumnIndex(KEY_AD_ETHNICITY_ARABIC)));
		values.put("KEY_AD_ETHNICITY_BROWN", c.getInt(c.getColumnIndex(KEY_AD_ETHNICITY_BROWN)));
		values.put("KEY_AD_ETHNICITY_NATIVE_AMERICAN", c.getInt(c.getColumnIndex(KEY_AD_ETHNICITY_NATIVE_AMERICAN)));
		values.put("KEY_AD_ETHNICITY_SOUTH_ASIAN", c.getInt(c.getColumnIndex(KEY_AD_ETHNICITY_SOUTH_ASIAN)));
		values.put("KEY_AD_ETHNICITY_WHITE", c.getInt(c.getColumnIndex(KEY_AD_ETHNICITY_WHITE)));
		values.put("KEY_AD_CITY", c.getInt(c.getColumnIndex(KEY_AD_CITY)));
		values.put("KEY_AD_CITIES", c.getString(c.getColumnIndex(KEY_AD_CITIES)));

		return values;
	}

	public ContentValues createContentValues(int _id, int lookingfor, int age_from, int age_to, int distance, int advance_filter, int ad_height_from,
			int ad_height_to, int ad_weight_from, int ad_weight_to, int ad_ethnicity_all, int ad_ethnicity_asian, int ad_ethnicity_black,
			int ad_ethnicity_latin, int ad_ethnicity_arabic, int ad_ethnicity_brown, int ad_ethnicity_native_american, int ad_ethnicity_south_asian,
			int ad_ethnicity_white, int ad_city, String ad_cities){

		ContentValues values = new ContentValues();
		values.put(KEY_ROWID, _id);
		values.put(KEY_LOOKINGFOR, lookingfor);
		values.put(KEY_AGE_FROM, age_from);
		values.put(KEY_AGE_TO, age_to);
		values.put(KEY_DISTANCE, distance);
		values.put(KEY_ADVANCE_FILTER, advance_filter);
		values.put(KEY_AD_HEIGHT_FROM, ad_height_from);
		values.put(KEY_AD_HEIGHT_TO, ad_height_to);
		values.put(KEY_AD_WEIGHT_FROM, ad_weight_from);
		values.put(KEY_AD_WEIGHT_TO, ad_weight_to);
		values.put(KEY_AD_ETHNICITY_ALL, ad_ethnicity_all);
		values.put(KEY_AD_ETHNICITY_ASIAN, ad_ethnicity_asian);
		values.put(KEY_AD_ETHNICITY_BLACK, ad_ethnicity_black);
		values.put(KEY_AD_ETHNICITY_LATIN, ad_ethnicity_latin);
		values.put(KEY_AD_ETHNICITY_ARABIC, ad_ethnicity_arabic);
		values.put(KEY_AD_ETHNICITY_BROWN, ad_ethnicity_brown);
		values.put(KEY_AD_ETHNICITY_NATIVE_AMERICAN, ad_ethnicity_native_american);
		values.put(KEY_AD_ETHNICITY_SOUTH_ASIAN, ad_ethnicity_south_asian);
		values.put(KEY_AD_ETHNICITY_WHITE, ad_ethnicity_white);
		values.put(KEY_AD_CITY, ad_city);
		values.put(KEY_AD_CITIES, ad_cities);

		return values;
	}
}
