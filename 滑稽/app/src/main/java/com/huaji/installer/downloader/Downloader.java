package com.huaji.installer.downloader;

import java.io.*;
import java.net.*;
import java.util.*;

public class Downloader
{
	public static void downLoad(String url, String savePath, String saveName)
	{
		new Downloader_Thread(url, savePath, saveName).start();
	}
	
	public static void dowLoadUpdateInfo(String url)
	{
		String sPath = "/mnt/sdcard/Android/data/com.huaji.installer/file/update/";
		
		File s = new File(sPath);
		
		if(!s.exists())
		{
			s.mkdirs();
		}
		
		new Downloader_Thread(url, sPath, "update_info.json");
	}
	
	static class Downloader_Thread extends Thread
	{
		String path = null;
		String sPath = null;
		String sName = null;
		
		public Downloader_Thread(String url, String savePath ,String saveName)
		{
			path = url;
			sPath = savePath;
			
			if(saveName != null)
			{
				sName = saveName;
			}
			
		}
		
		@Override
		public void run()
		{
			try
			{
				URL url = new URL(path);
				InputStream is = url.openStream();
				//截取最后的文件名
				String end = path.substring(path.lastIndexOf("."));
				//打开手机对应的输出流,输出到文件中
				
				FileOutputStream os;
				if(sName == null)
				{
					os = new FileOutputStream(new File(sPath,getFileName(path)));
				}
				else{
					os = new FileOutputStream(new File(sPath,sName));
				}
				
				System.out.println(getFileName(path));
				byte[] buffer = new byte[1024];
				int len = 0;
				//从输入六中读取数据,读到缓冲区中
				while ((len = is.read(buffer)) > 0)
				{
					os.write(buffer, 0, len);
				}
				//关闭输入输出流
				is.close();
				os.close();
			}
			catch (IOException e)
			{}
			// TODO: Implement this method
			super.run();
		}
		
	}

	public static String getFileName(String url)
	{
		String filename = "";


        boolean isok = false;
        // 从UrlConnection中获取文件名称
        try
		{
            URL myURL = new URL(url);

            URLConnection conn = myURL.openConnection();
            if (conn == null)
			{
                return null;
            }
            Map<String, List<String>> hf = conn.getHeaderFields();
            if (hf == null)
			{
                return null;
            }
            Set<String> key = hf.keySet();
            if (key == null)
			{
                return null;
            }

            for (String skey : key)
			{
                List<String> values = hf.get(skey);
                for (String value : values)
				{
                    String result;
                    try
					{
                        result = new String(value.getBytes("ISO-8859-1"), "GBK");
                        int location = result.indexOf("filename");
                        if (location >= 0)
						{
                            result = result.substring(location
													  + "filename".length());
                            filename = result
								.substring(result.indexOf("=") + 1);
                            isok = true;
                        }
                    }
					catch (UnsupportedEncodingException e)
					{
                        e.printStackTrace();
                    }// ISO-8859-1 UTF-8 gb2312
                }
                if (isok)
				{
                    break;
                }
            }
        }
		catch (MalformedURLException e)
		{
            e.printStackTrace();
        }
		catch (IOException e)
		{
            e.printStackTrace();
        }
        // 从路径中获取
        if (filename == null || "".equals(filename))
		{
            filename = url.substring(url.lastIndexOf("/") + 1);

        }
		filename = filename.substring(1, filename.length() - 1);
        return filename;
    }
}
