package com.example.pullsexJson;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListView listView;
	private Handler handler=new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView=(ListView) findViewById(R.id.listview);
		MyApdate apdate=new MyApdate(this);
		new HttpJsonThread("http://192.168.56.1:8080/D/MyJson", apdate, listView, handler).start();
	}

}
