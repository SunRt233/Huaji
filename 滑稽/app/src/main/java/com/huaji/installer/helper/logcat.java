package com.huaji.installer.helper;

import android.content.*;

public class logcat
{
	private logcat Logcatcher = null;
	
	private logcat(Context mContext)
	{
		init(mContext);
	}

	private void init(Context mContext)
	{
		// TODO: Implement this method
	}
	
	public logcat getLogcatcher(Context c)
	{
		if(Logcatcher == null)
		{
			Logcatcher = new logcat(c);
		}
		
		return Logcatcher;
	}
	
	public void start()
	{
		
	}

	private class logcatcher extends Thread
	{
		String[] running = new String[]{"logcat","-s","adb logcat *: *"};
		@Override
		public void run()
		{
			// TODO: Implement this method
			super.run();
		}

	}

}


