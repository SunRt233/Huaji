package com.huaji.installer.ui.activity;



import android.os.*;
import android.support.v7.widget.*;
import android.widget.*;
import com.huaji.installer.*;
import com.huaji.installer.base.*;
import java.io.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import android.support.v7.widget.Toolbar;
import android.view.*;

public class ArticleActivity extends BaseActivity
{
	private TextView articleTitle;
	private TextView articleAuthor;
	private TextView articleContext;
	private Toolbar toolbar;
	private mHandlerMessage mMessage = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article);
		
		toolbar = (Toolbar) findViewById(R.id.activity_articleToolbar);
		toolbar.setTitle("每日一文");
		setSupportActionBar(toolbar);
		
		articleTitle = (TextView) findViewById(R.id.activity_articleTitle);
		articleAuthor = (TextView) findViewById(R.id.activity_articleAuthor);
		articleContext = (TextView) findViewById(R.id.activity_articleContext);
		
		new Thread()
		{
			@Override
			public void run()
			{
				// TODO: Implement this method
				super.run();
				
				try
				{
					String[] msgs = {"获取失败","null","请检查您的网络连接"};
					Message handlerMessage = new Message();
					Document doc = Jsoup.connect("https://meiriyiwen.com/random/iphone").get();
					Element div = doc.body();
					Element container = doc.body().getElementsByClass("container").first();
					Element title = container.select("h2").first();
					Element author = container.getElementsByClass("articleAuthorName").first();
					Elements contexts = container.getElementsByClass("articleContent").first().select("p");

					String context = "\n    ";
					for(Element e: contexts)
					{
						context = context + e.text() +"\n\n    ";
					}
					
					msgs[0] = title.text();
					msgs[1] = author.text();
					msgs[2] = context;
					
					String s = msgs[0]+"/s/"+msgs[1]+"/s/"+msgs[2];
					
					myHandle.sendEmptyMessage(0);
					
					handlerMessage.obj = s;
					myHandle.sendMessage(handlerMessage);
					
					
					PrintWriter writer = new PrintWriter("/mnt/sdcard/article.txt", "UTF-8");
					writer.write(title.text());
					writer.close();

//				System.out.println(msg.getArticleTitle());
//				System.out.println(msg.getArticleAuthor());
//				System.out.println(msg.getArticleContext());
//				System.out.println(container.getElementsByClass("articleContent").html());


				}
				catch (Throwable e)
				{

				}

				//;
			}
		}.start();
		
		//articleTitle.setText("");
		//articleAuthor.setText(mMessage.getArticleAuthor());
		//articleContext.setText(mMessage.getArticleContext());
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// TODO: Implement this method
		
		return super.onCreateOptionsMenu(menu);
	}

	

	Handler myHandle = new Handler()
	{

		@Override
		public void handleMessage(Message msg)
		{
			// TODO: Implement this method
			super.handleMessage(msg);
			
			switch(msg.what)
			{
				case 0:
					try{
					String[] str = ((String) msg.obj).split("/s/");
					//mHandlerMessage mMsg = (mHandlerMessage) msg.obj;
					articleTitle.setText(str[0].toString());
					articleAuthor.setText(str[1].toString());
					articleContext.setText(str[2].toString());
					}
					catch(Throwable e)
					{
						printToast(e.toString(),1);
					}
					break;
			}
			
		}

	};
	
}

class mHandlerMessage
{
	private String articleTitle = "null";
	private String articleAuthor = "null";
	private String articleContext = "null";

	public mHandlerMessage()
	{

	}
	
	public mHandlerMessage(String t, String a, String c)
	{
		setArticleTitle(t);
		setArticleAuthor(a);
		setArticleContext(c);
	}

	public void setArticleContext(String articleContext)
	{
		this.articleContext = articleContext;
	}

	public String getArticleContext()
	{
		return articleContext;
	}

	public void setArticleAuthor(String articleAuthor)
	{
		this.articleAuthor = articleAuthor;
	}

	public String getArticleAuthor()
	{
		return articleAuthor;
	}


	public void setArticleTitle(String articleTitle)
	{
		this.articleTitle = articleTitle;
	}

	public String getArticleTitle()
	{
		return articleTitle;
	}

}
