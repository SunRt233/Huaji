package com.huaji.installer;

import android.view.*;
import android.support.v7.widget.*;

public class ToolbarActivity extends BaseActivity
{
	private ToolbarActivityHelper mToolbarHelper;
	public Toolbar toolbar;
	
	@Override
	public void setContentView(int LayoutID)
	{
		// TODO: Implement this method
		mToolbarHelper = new ToolbarActivityHelper(this, LayoutID);
		toolbar = mToolbarHelper.getToolBar();
		
		setContentView(mToolbarHelper.getContentView());
		onCreateCustomToolBar(toolbar);
		
	}
	
	public void onCreateCustomToolBar(Toolbar toolbar){
        toolbar.setContentInsetsRelative(0,0);
    }
	
	public Toolbar getCustomToolbar()
	{
		return toolbar;
	}
	
}
