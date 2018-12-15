package com.example.firstandroidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDao {
	private DBHelper dbhelper;

	public UserDao(Context context) {
		dbhelper = new DBHelper(context);
	}

	public void close(){
		try {
			dbhelper.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�ر����ݿ�ʧ��");
		}
	}
	// ��ѯ
	public Cursor query(String username,String password) {
		try {
			SQLiteDatabase db = dbhelper.getReadableDatabase();
			String sql="select * from User where username=? and password=?";
			Cursor cursor = db.rawQuery(sql,new String[]{username,password});
			return cursor;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��ѯ���ݳ���");
		}
		return null;
	}

	// ���
	public void insert(User user) {
		try {
			SQLiteDatabase db = dbhelper.getWritableDatabase();
			ContentValues contentvalues = new ContentValues();
			contentvalues.put("username", user.getUsername());
			contentvalues.put("password", user.getPassword());
			db.insert("User", null, contentvalues);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("������ݳ���");
		}
	}
	
}
