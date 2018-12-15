package com.example.firstandroidapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	private final static String dataBaseName="info.db";
	private final static String table_name="User";
	private static final int version =1;//�汾��
	
	public DBHelper(Context context){
		super(context,dataBaseName, null, version);
	}
	//�÷������Զ����ã�����ϵͳ����ó������Ƿ�������ݿ���Ϊ��info.db�������ݿ⣬��������򲻻�ִ�и÷�����������������ִ�и÷�����
	@Override
	public void onCreate(SQLiteDatabase db){
		String sql="CREATE TABLE User(username VARCHAR(20) not null primary key, password VARCHAR(20) not null)";
		db.execSQL(sql);
	}
	//��д��onUpgrade�����������ݿ�ṹ�޸ģ��Ż�����Ҫ���°汾ʱ��ִ�и÷�����������ǽ��ɵ����ݿ�ɾ������д�������ݿ⡣�Դﵽ���µ�Ŀ�ġ������е�oldVersion��newVersion�ֱ��Ӧ�¾ɰ汾�������û��Լ����壬ϵͳ���Զ��ص��÷������жϰ汾�Ƿ����仯
	@Override
	public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
		String sql="drop table if exists"+table_name;
		db.execSQL(sql);
		onCreate(db);
	}
}
