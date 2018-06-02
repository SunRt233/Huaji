package com.huaji.installer;

import android.app.*;
import android.os.*;
import java.util.*;
import android.content.*;
import java.io.*;

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

	TimerTask task = new TimerTask(){

		@Override
		public void run()
		{
			File file = new File("/mnt/sdcard/games/com.mojang/minecraftpe/install.info");
			if (!file.exists())
			{
				startActivity(new Intent(getApplicationContext(), InstallActivity.class));
			}
			else
			{
				startActivity(new Intent(getApplicationContext(), MainActivity.class));
			}
			finish();
		}


	};

}
