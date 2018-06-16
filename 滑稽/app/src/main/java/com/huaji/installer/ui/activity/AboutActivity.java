package com.huaji.installer.ui.activity;

import android.content.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import com.huaji.installer.*;
import com.huaji.installer.base.*;

public class AboutActivity extends ToolbarActivity
{
	Button update;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		
		getCustomToolbar().setTitle("关于");
		getCustomToolbar().setNavigationIcon(R.drawable.abc_ic_ab_back_material);
		getCustomToolbar().setNavigationOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					finish();
					Log.i("test","只是一个测试");
					// TODO: Implement this method
				}
			});
		
		initView();
		initEvents();
		
		
	}

	private void initView()
	{
		
		// TODO: Implement this method
	}

	private void initEvents()
	{
		update.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					startActivity(new Intent(getApplicationContext(),UpdateActivity.class));
				}
				
			});
		
		// TODO: Implement this method
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if(item.getItemId()==R.id.home){
			startActivity(new Intent(AboutActivity.this,MainActivity.class));
			finish();
		}
		// TODO: Implement this method
		return super.onOptionsItemSelected(item);
	}
	
	
	
}
