package com.ruijie.rush.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.widget.Toast;

public class DbHelper extends SQLiteOpenHelper {

	private static final String CREATE_BOOK = "CREATE TABLE IF NOT EXISTS Book ("
			+ "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "author TEXT, "
			+ "name TEXT, " 
			+ "price REAL, " 
			+ "pages INTEGER, "
			+ "category_id integer)";
	private static final String CREATE_CATEGORY = "CREATE TABLE IF NOT EXISTS Category ("
			+ "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "category_name TEXT, "
			+ "category_code integer)";
	private static final String ALTER_BOOK = "ALTER TABLE Book ADD COLUMN category_id INTEGER";

	private Context context = null;
	
	public DbHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_BOOK);
		db.execSQL(CREATE_CATEGORY);
		Toast.makeText(context, "Create success", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		switch (oldVersion) {
		case 1:
			db.execSQL(CREATE_CATEGORY);//不能有beak语句，避免数据库跨版本升级
		case 2:
			db.execSQL(ALTER_BOOK);
		}
	}

}
