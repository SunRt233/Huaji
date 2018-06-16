package com.huaji.installer.ui.activity;

import android.app.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v4.app.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import com.github.sundeepk.compactcalendarview.*;
import com.huaji.installer.*;
import com.huaji.installer.base.*;
import com.huaji.installer.ui.fragment.*;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import com.huaji.installer.R;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener
{
	private Toolbar toolbar;
	private DrawerLayout drawer;
	private NavigationView nav;
	private FrameLayout f;
	//private Fragment fiction = new FictionFragment();
	private CompactCalendarView calendar;
	private Fragment currentFragment = null;
	Fragment from = null;
	Fragment homeFragment = new HomeFragment();
	Fragment fictionFragment = new FictionFragment();

	public CompactCalendarView getCalendar()
	{
		return calendar;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		// TODO: Implement this method
		super.onCreate(savedInstanceState);

		//SetStuatsBar(this);

		/*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
		 {
		 Window window = getWindow();                            
		 window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		 }*/

		setContentView(R.layout.activity_main);

		initView();
		initToolbar();

		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
			this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

		setExitMode(EXIT_MODE_TWICE);

		calendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);


	}

	private void SetStuatsBar(Activity activity)
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
		{
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
			{
				//5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
				Window window = activity.getWindow();
				View decorView = window.getDecorView();
				//两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
				int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
				decorView.setSystemUiVisibility(option);
				window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
				//window.setStatusBarColor(Color.TRANSPARENT);
				//导航栏颜色也可以正常设置
//                window.setNavigationBarColor(Color.TRANSPARENT);
			}
			else
			{
				Window window = activity.getWindow();
				WindowManager.LayoutParams attributes = window.getAttributes();
				int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
				int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
				attributes.flags |= flagTranslucentStatus;
//                attributes.flags |= flagTranslucentNavigation;
				window.setAttributes(attributes);
			}
		}
	}

	public Toolbar getToolbar()
	{
		return toolbar;
	}

	private void initView()
	{
		toolbar = (Toolbar) findViewById(R.id.Maintoolbar);
		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		nav = (NavigationView) findViewById(R.id.nav_view);
		f = (FrameLayout) findViewById(R.id.activity_mainFrameLayout1);

		nav.setNavigationItemSelectedListener(this);
	}

	private void initToolbar()
	{
		toolbar.setTitle("轻-工具");
		setSupportActionBar(toolbar);

	}

	@Override
	public boolean onNavigationItemSelected(MenuItem p1)
	{
		try
		{
			
			// TODO: Implement this method
			switch (p1.getItemId())
			{
				case R.id.nav_homepage:
					//showFragment(R.id.activity_mainFrameLayout1, homeFragment, "home");
					switchFragment(homeFragment, R.id.activity_mainFrameLayout1, "homeFragment");
					break;
				case R.id.nav_fiction:
					switchFragment(fictionFragment, R.id.activity_mainFrameLayout1, "fictionFragment");
					break;
			}
		}
		catch (Throwable e)
		{
			printToast(e.toString(),10);
		}
		return true;
	}

	public void switchFragment(Fragment f, int id, String tag)
	{
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		if(currentFragment == null)
		{
			transaction.add(id, f, tag).commit();
			currentFragment = f;
			printToast("初始化成功",1);
		}
		else
		{
			if(manager.findFragmentByTag(tag) == null)
			{
				transaction.hide(currentFragment).add(id, f, tag).commit();
				currentFragment = f;
				printToast("添加成功",1);
			}
			if(manager.findFragmentByTag(tag) != null&&currentFragment.getTag() != tag)
			{
				for(Fragment inf : manager.getFragments())
				{
					transaction.hide(inf);
				}
				transaction.show(manager.findFragmentByTag(tag)).commit();
				currentFragment = f;
				printToast("显示成功",1);
			}
		}
	}
	
	public void showFragment(int id, Fragment f, String tag)
	{
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(id, f, tag).commit();
	}

//	private void switchFragment(BaseFragment targetFragment, int id, String tag)
//	{
//		FragmentManager manager = getSupportFragmentManager();
//		FragmentTransaction transaction = manager.beginTransaction();
//
//		if (currentFragment == null)
//		{
//			targetFragment.setIsAdded(true);
//			targetFragment.setPrivateTag(tag);
//			transaction
//				.add(id, targetFragment)
//				.commit();
//			printToast("初始化", 0);
//
//
//			currentFragment = targetFragment;
//		}
//		else
//		{
//			if (targetFragment.getAddedStatus() == true&&currentFragment.getPrivateTag() != tag)
//			{
//				targetFragment.setIsAdded(true);
//				transaction
//					.hide(currentFragment)
//					.show(targetFragment)
//					.commit();
//				printToast("显示成功",0);
//				
//				currentFragment = targetFragment;
//			}
//			if(targetFragment.getAddedStatus() == false&&currentFragment.getPrivateTag() != tag)
//			{
//				targetFragment.setIsAdded(true);
//				targetFragment.setPrivateTag(tag);
//				transaction
//					.hide(currentFragment)
//					.add(id, targetFragment)
//					.commit();
//				printToast("添加成功",0);
//				
//				currentFragment = targetFragment;
//			}
//			else if(targetFragment.getAddedStatus() == true&&currentFragment.getPrivateTag() == tag)
//			{
//				printToast("重复",0);
//			}
//
//			
//		}
//		/*
//		 if (currentFragment == null)
//		 {
//		 Log.v("srt","cxh");
//
//		 transaction
//		 .add(id, targetFragment)
//		 .commit();
//		 printToast("初始化",1);
//
//		 currentFragment = targetFragment;
//		 //printToast(currentFragment.getTag(),1);
//		 }
//		 //		else if(currentFragment.getTag() == tag)
//		 //		{
//		 //			printToast("重复",1);
//		 //		}
//		 else
//		 {
//		 for(Fragment f: manager.getFragments())
//		 {
//		 if(f.getTag() == tag)
//		 {
//		 if(currentFragment.getTag() != tag)
//		 {
//		 transaction
//		 .hide(currentFragment)
//		 .show(manager.findFragmentByTag(tag))
//		 .commit();
//		 currentFragment = targetFragment;
//		 }
//		 printToast("重复",1);
//		 }
//		 else
//		 {
//		 transaction
//		 .hide(currentFragment)
//		 .add(id, targetFragment, tag)
//		 .commit();
//
//		 printToast("添加成功",1);
//
//		 currentFragment = targetFragment;
//		 }
//		 }*/
//		/*
//		 if (currentFragment.getBaseTag() != targetFragment.getBaseTag()) {
//		 Log.v("srt","add");
//		 transaction
//		 .hide(manager.findFragmentByTag(currentFragment.getTag()))
//		 .add(id, targetFragment, tag)
//		 .commit();
//		 printToast("还没添加呢",1);
//		 currentFragment = targetFragment;
//		 } 
//		 else {
//		 Log.v("srt","show");
//		 transaction
//		 .hide(currentFragment)
//		 .show((BaseFragment)manager.findFragmentByTag(tag))
//		 .commit();
//		 printToast("添加了( ⊙o⊙ )哇",1);
//		 currentFragment = targetFragment;
//		 }
//
//		 */
//		
//	}

}
