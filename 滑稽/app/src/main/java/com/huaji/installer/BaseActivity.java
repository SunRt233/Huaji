package com.huaji.installer;

import android.content.*;
import android.content.pm.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class BaseActivity extends AppCompatActivity
{
	private boolean isExit = false;
	public BaseDialog baseDialog;
	public int EXIT_MODE_ONCE = 0;
	public int EXIT_MODE_TWICE = 1;
	private int EXIT_MODE = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			exit();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	public PackageInfo getAppVersion()
	{
		Object[] ver_info = new String[4];

        try
		{
            //PackageManager管理器
            PackageManager pm = this.getPackageManager();
            //获取相关信息
            PackageInfo packageInfo = pm.getPackageInfo(this.getPackageName(), 0);
            //版本名称
            String name = packageInfo.versionName;
            //版本号
            int version = packageInfo.versionCode;

            ver_info[0] = pm;
			ver_info[1] = packageInfo;
			ver_info[2] = name;
			ver_info[3] = version;

            return packageInfo;
        }
		catch (PackageManager.NameNotFoundException e)
		{
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //如果出现异常抛出null
        return null;
    }

	public BaseDialog getDialog()
	{
		return baseDialog;
	}

	public void setExitMode(int Mode)
	{
		EXIT_MODE = Mode;
	}

	public int getExitMode()
	{
		return EXIT_MODE;
	}

	public void exit()
	{
		switch (EXIT_MODE)
		{
			case 0:
				finish();
				break;
			case 1:
				if (!isExit)
				{
					isExit = true;
					printToast("再按一次退出程序", Toast.LENGTH_LONG);
					Timer t = new Timer();
					t.schedule(new TimerTask(){

							@Override
							public void run()
							{
								isExit = false;
							}
						}, 2000);
				}
				else
				{
					finish();
				}
				break;

		}

	}

	public void printToast(String msg, int time)
	{

		Toast.makeText(getApplicationContext(), msg.toString(), time).show();

	}

	public class BaseDialog 
	{
		String DTitle = "";
		String Dmsg = "";
		String DokButton = "确认";
		String DnavButton = "取消";
		AlertDialog.Builder thisDialog;

		BaseDialog(Context c, String title, String msg, String okbutton, String navbutton, int mode)
		{
			thisDialog = new AlertDialog.Builder(c);

			if (title == null || msg == null)
			{
				thisDialog.setTitle(DTitle);
				thisDialog.setMessage(Dmsg);
			}
			else if (title != null && msg != null)
			{
				DTitle = title;
				Dmsg = msg;

				thisDialog.setTitle(DTitle);
				thisDialog.setMessage(Dmsg);

			}
		}

		public AlertDialog.Builder getDialog()
		{
			return thisDialog;
		}
	}

}
