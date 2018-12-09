package com.example.firstandroidapp;

import android.R.bool;
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	private EditText editText1 = null;
	private EditText editText2 = null;
	private Button login = null;
	private Button reset = null;

	private void init() {
		editText1 = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);
		login = (Button) findViewById(R.id.button1);
		reset = (Button) findViewById(R.id.button2);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		// 重置按钮
		reset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editText1.setText("");
				editText2.setText("");
			}
		});
		// 登录按钮
		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String username = editText1.getText().toString();
				String password = editText2.getText().toString();
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				UserDao userDao = new UserDao(getBaseContext());
				Cursor cursor = userDao.query(username, password);
				if (cursor.moveToNext()) { // 数据库内查到该行 即成功登录
					Intent intent = new Intent(MainActivity.this,
							LoginsuccessActivity.class);
					startActivity(intent);
				} else {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							MainActivity.this);
					builder.setTitle("弹出警告框");
					builder.setMessage("数据库没有您的信息，无法登录");
					builder.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Toast.makeText(MainActivity.this,
											"positive: " + which,
											Toast.LENGTH_SHORT).show();
								}
							});
					// 显示出该对话框
					builder.show();
				}
				userDao.close();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
