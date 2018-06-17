package com.huaji.installer.ui.activity;

import android.app.*;
import android.content.*;
import android.os.*;
import android.widget.*;
import java.util.*;
import com.huaji.installer.*;

public class IndexActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);

		Timer timer = new Timer();
		timer.schedule(task, 3000);
		
	}
	
	public void print(String s)
	{
		Toast.makeText(getApplication(),s,Toast.LENGTH_LONG).show();
	}

	TimerTask task = new TimerTask(){

		@Override
		public void run()
		{
			
//			File file = new File("/mnt/sdcard/games/com.mojang/minecraftpe/install.info");
//			if (!file.exists())
//			{
//				startActivity(new Intent(getApplicationContext(), InstallActivity.class));
//			}
//			else
//			{
				startActivity(new Intent(getApplicationContext(), MainActivity.class));
//			}
			finish();
		}


	};

}
