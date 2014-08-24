package com.ruijie.rush.ui;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.ruijie.rush.R;
import com.ruijie.rush.base.BaseActivity;
import com.ruijie.rush.db.DbHelper;
import com.ruijie.rush.db.DbManager;
import com.ruijie.rush.model.Book;

public class UsingDatabaseActivity extends BaseActivity implements OnClickListener {

	private DbHelper helper = null;
	private SQLiteDatabase db = null;
	private DbManager manager = null;
	private List<Book> books = null;
	private Book book1 = null;
	private Book book2 = null;
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		manager.closeDb();
	}
	
	@Override
	protected void setContentView() {
		setContentView(R.layout.act_using_database);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initData() {
		helper = new DbHelper(this, "test.db", null, 3);
		db = helper.getWritableDatabase();//创建数据库
		manager = new DbManager(this);
		book1 = new Book("Dan Brown", "The Da Vinci Code", (float) 16.96, 454, 1);
		book2 = new Book("Dan Brown", "The Lost Symbol", (float) 19.95, 510, 1);
	}

	@Override
	protected void setListener() {
		findViewById(R.id.btn_create).setOnClickListener(this);
		findViewById(R.id.btn_add).setOnClickListener(this);
		findViewById(R.id.btn_update).setOnClickListener(this);
		findViewById(R.id.btn_delete).setOnClickListener(this);
		findViewById(R.id.btn_query).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_create:
			db = helper.getWritableDatabase();
			break;
		case R.id.btn_add:
			manager.insert(book1);
			manager.insert(book2);
			break;
		case R.id.btn_update:
			ContentValues values = new ContentValues();
			values.put(DbManager.COLUMN_BOOK_PRICE, (float) 10.99);
			manager.update(values, "name = ?", new String[] {"The Da Vinci Code"});
			break;
		case R.id.btn_delete:
			manager.delete(DbManager.COLUMN_BOOK_PAGES + " > ?", new String[] {"500"});
			break;
		case R.id.btn_query:
			books = manager.query();
			for(Book book : books){
				String bookInfo = "";
				bookInfo += "书名：" + book.getName();
				bookInfo += "\n作者：" + book.getAuthor();
				bookInfo += "\n价格：" + book.getPrice();
				bookInfo += "\n页码：" + book.getPages();
				bookInfo += "\n类别代码：" + book.getCategoryId();
				Log.d("UsingDatabaseActivity", bookInfo);
			}
			break;
		}
	}
	
	public static void actionStart(Context context) {
		context.startActivity(new Intent(context, UsingDatabaseActivity.class));
	}
	
}
