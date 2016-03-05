package com.example.pullsexJson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

public class BitmapHttp extends Thread {
	private String url;
	private Handler handler;
	private ImageView imageView;
	public BitmapHttp(String url, Handler handler, ImageView imageView) {
		super();
		this.url = url;
		this.handler = handler;
		this.imageView = imageView;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			URL img_url=new URL(url);
			HttpURLConnection con=(HttpURLConnection) img_url.openConnection();
			con.setRequestMethod("GET");
			con.setReadTimeout(5000);
			final Bitmap bitmap=BitmapFactory.decodeStream(con.getInputStream());
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					imageView.setImageBitmap(bitmap);
				}
			});
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
