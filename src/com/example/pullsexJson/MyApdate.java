package com.example.pullsexJson;

import java.util.ArrayList;
import java.util.List;

import com.example.moder.Person;
import com.example.moder.SchooleInfo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyApdate extends BaseAdapter{
	private List<Person>list;
	private LayoutInflater inflater;
	private Handler handler=new Handler();
	public MyApdate(Context context){
		inflater=LayoutInflater.from(context);
	}
	public void getList(List<Person>list){
		this.list=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.item, null);
		Person person=list.get(position);
		ImageView img=(ImageView) view.findViewById(R.id.img);
		TextView name=(TextView) view.findViewById(R.id.name);
		TextView age=(TextView) view.findViewById(R.id.age);
		TextView school=(TextView) view.findViewById(R.id.school);
		new BitmapHttp(person.getUrl(), handler,img).start();
		name.setText(person.getName());
		age.setText(person.getAge());
		List<SchooleInfo>school1=person.getSchooleInfos();
		school.setText(school1.get(0).getSchool_name());
		return view;
	}
	class ViewHoder{
		private ImageView img;
		private TextView name,age,schoole;
	}
}
