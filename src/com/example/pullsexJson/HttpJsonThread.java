package com.example.pullsexJson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.widget.ListView;

import com.example.moder.Person;
import com.example.moder.SchooleInfo;

public class HttpJsonThread extends Thread {
	private String url;
	private MyApdate apdate;
	private ListView listView;
	private Handler handler;

	public HttpJsonThread(String url, MyApdate apdate, ListView listView,
			Handler handler) {
		super();
		this.url = url;
		this.apdate = apdate;
		this.listView = listView;
		this.handler = handler;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			URL img_url = new URL(url);
			HttpURLConnection con = (HttpURLConnection) img_url
					.openConnection();
			con.setReadTimeout(5000);
			con.setRequestMethod("GET");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String len;
			while ((len = br.readLine()) != null) {
				sb.append(len);
			}
			final List<Person> data = parjson(sb.toString());
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					apdate.getList(data);
					listView.setAdapter(apdate);
					/*apdate.notifyDataSetChanged();//�����µ���Ϣ��
					listView.setSelection(data.size());//�������һ��
*/				}
			});
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private List<Person> parjson(String json) {
		try {
			//json��ͷ
			JSONObject object = new JSONObject(json);
			int result = object.getInt("result");
			List<Person> persons = new ArrayList<>();
			if (result == 1) {
				JSONArray array = object.getJSONArray("persons");
				for (int i = 0; i < array.length(); i++) {
					Person personObject = new Person();
					JSONObject person = array.getJSONObject(i);
					String name = person.getString("name");
					String age = person.getString("age");
					String url = person.getString("url");
					personObject.setAge(age);
					personObject.setName(name);
					personObject.setUrl(url);
					JSONArray schoolInfo = person.getJSONArray("schoolInfo");
					List<SchooleInfo> schInfos = new ArrayList<>();
					for (int j = 0; j < array.length(); j++) {
						SchooleInfo school_object = new SchooleInfo();
						JSONObject school = schoolInfo.getJSONObject(j);
						String school_name = school.getString("school_name");
						school_object.setSchool_name(school_name);
						schInfos.add(school_object);
						personObject.setSchooleInfos(schInfos);
					}
					persons.add(personObject);
				}
			}
			return persons;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
