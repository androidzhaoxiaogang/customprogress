package com.example.progress;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.widget.ProgressBar;

@SuppressLint("HandlerLeak")
public class MainActivity extends Activity {
	ProgressBar pb;
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			if(msg.what == 1) {
				pb.setProgress((Integer)msg.obj);
			} else {
				pb.setProgress(80);
			}
		}
		
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pb = (ProgressBar) findViewById(R.id.left_progress);
		pb.setMax(100);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 1; i<= 80; i += 2) {
					handler.obtainMessage(1, i).sendToTarget();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				handler.sendEmptyMessage(2);
			}
		}).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
