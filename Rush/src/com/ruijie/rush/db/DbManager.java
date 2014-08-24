package com.ruijie.rush.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ruijie.rush.model.Book;

public class DbManager {

	public static final String TABLE_NAME = "Book";
	public static final String COLUMN_BOOK_NAME = "name";
	public static final String COLUMN_BOOK_AUTHOR = "author";
	public static final String COLUMN_BOOK_PRICE = "price";
	public static final String COLUMN_BOOK_PAGES = "pages";
	public static final String COLUMN_BOOK_CATEGORY_ID = "category_id";

	private DbHelper helper = null;
	private SQLiteDatabase db = null;
	
	public DbManager(Context context) {
		this.helper = new DbHelper(context, "test.db", null, 1);
		this.db = helper.getWritableDatabase();
	}
	
	public void insert(Book book){
		ContentValues values = new ContentValues();
		values.put(COLUMN_BOOK_NAME, book.getName());
		values.put(COLUMN_BOOK_AUTHOR, book.getAuthor());
		values.put(COLUMN_BOOK_PRICE, book.getPrice());
		values.put(COLUMN_BOOK_PAGES, book.getPages());
		values.put(COLUMN_BOOK_CATEGORY_ID, book.getCategoryId());
		db.insert(TABLE_NAME, null, values);
	}
	
	public int delete(int recordId) {
		return db.delete(TABLE_NAME, "_id=?", new String[] { String.valueOf(recordId) });
	}
	
	public int delete(String whereClause, String[] whereArgs){
		return db.delete(TABLE_NAME, whereClause, whereArgs);
	}
	
	public int update(ContentValues values, String whereClause, String[] whereArgs) {
		return db.update(TABLE_NAME, values, whereClause, whereArgs);
	}
	
	public List<Book> query(){
		List<Book> results = new ArrayList<Book>();
		Cursor c= getCursor();
		while (c.moveToNext()) {
			Book book = new Book();
			book.setName(c.getString(c.getColumnIndex(COLUMN_BOOK_NAME)));
			book.setAuthor(c.getString(c.getColumnIndex(COLUMN_BOOK_AUTHOR)));
			book.setPrice(c.getFloat(c.getColumnIndex(COLUMN_BOOK_PRICE)));
			book.setPages(c.getInt(c.getColumnIndex(COLUMN_BOOK_PAGES)));
			book.setCategoryId(c.getInt(c.getColumnIndex(COLUMN_BOOK_CATEGORY_ID)));
			results.add(book);
		}
		c.close();
		return results;
	}
	
	public Cursor getCursor(){
		return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
	}
	
	public void closeDb(){
		if (db.isOpen()) {
			db.close();
		}
	}
	
}
