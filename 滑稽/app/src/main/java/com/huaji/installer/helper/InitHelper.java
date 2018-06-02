package com.huaji.installer.helper;
import android.content.*;
import android.content.pm.*;
import android.net.*;
import android.os.*;
import android.support.v4.content.*;
import java.io.*;
import java.util.*;

public class InitHelper
{
	public static boolean isAvilible(Context context, String packageName)
	{   
		final PackageManager packageManager = context.getPackageManager();//获取packagemanager   
		List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);//获取所有已安装程序的包信息   
		List<String> pName = new ArrayList<String>();//用于存储所有已安装程序的包名   

		if (pinfo != null)
		{   
			for (int i = 0; i < pinfo.size(); i++)
			{   
				String pn = pinfo.get(i).packageName;   
				pName.add(pn);   
			}   
		}   
		return pName.contains(packageName);//判断pName中是否有目标程序的包名，有TRUE，没有FALSE   
	}
	
	public static void installApk(Context mcontext, File file)
	{
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		if (Build.VERSION.SDK_INT >= 24)
		{                    
			Uri apkUri = FileProvider.getUriForFile(mcontext, "com.huaji.installer.FileProvider", file);
			intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
		}
		else
		{
			intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		}
		
		mcontext.startActivity(intent);
		
	}
	
}
