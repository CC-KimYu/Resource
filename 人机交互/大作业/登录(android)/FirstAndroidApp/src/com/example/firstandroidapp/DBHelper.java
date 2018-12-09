package com.example.firstandroidapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	private final static String dataBaseName="info.db";
	private final static String table_name="User";
	private static final int version =1;//版本号
	
	public DBHelper(Context context){
		super(context,dataBaseName, null, version);
	}
	//该方法会自动调用，首先系统会检查该程序中是否存在数据库名为‘info.db’的数据库，如果存在则不会执行该方法，如果不存在则会执行该方法。
	@Override
	public void onCreate(SQLiteDatabase db){
		String sql="CREATE TABLE User(username VARCHAR(20) not null primary key, password VARCHAR(20) not null)";
		db.execSQL(sql);
	}
	//重写的onUpgrade方法。当数据库结构修改，优化后，需要更新版本时，执行该方法，具体就是将旧的数据库删除，重写创建数据库。以达到更新的目的。方法中的oldVersion和newVersion分别对应新旧版本，可以用户自己定义，系统会自动回调该方法并判断版本是否发生变化
	@Override
	public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
		String sql="drop table if exists"+table_name;
		db.execSQL(sql);
		onCreate(db);
	}
}
