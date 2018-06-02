package com.huaji.installer.helper;

import android.content.pm.*;
import android.content.*;
import android.util.*;
import java.net.*;
import java.io.*;
import org.json.*;
import android.os.*;
import com.huaji.installer.downloader.*;
import com.huaji.installer.*;
import java.util.stream.*;
import com.huaji.installer.utils.*;

public class UpdateHelper extends InitHelper
{
	
	int versionCode;
	String content;
	String url;
	
	public boolean checkUpdate(int vCode)
	{
		getUpdateInfo();
		analUpdateInfo();
		
		if(vCode<versionCode)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private void analUpdateInfo()
	{
		
		try
		{
			JSONObject jsonObject = new JSONObject(Utils.toStream(new FileInputStream(new File("/mnt/sdcard/Android/data/com.huaji.installer/file/update/update_info.json"))));
// 获取版本名
//			String versionName = jsonObject.getString("versionName");
// 获取版本号
			versionCode = jsonObject.getInt("versionCode");
// 获取更新内容
			content = jsonObject.getString("content");
// 获取下载地址
			url = jsonObject.getString("url");
			
			
		}
		catch (Throwable e)
		{}
		
	}
	
	private void getUpdateInfo()
	{
		Downloader.dowLoadUpdateInfo("https://sunrt233.wodemo.net/down/479039/update_info.txt?encoding=UTF-8");
	}
	
}
