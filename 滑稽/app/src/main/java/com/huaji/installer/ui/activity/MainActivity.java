package com.huaji.installer.ui.activity;

import android.app.*;
import android.graphics.*;
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
import android.content.res.*;
import org.xmlpull.v1.*;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener
{
	private Toolbar toolbar;
	private DrawerLayout drawer;
	private NavigationView nav;
	private FrameLayout f;
	private CompactCalendarView calendar = null;
	private Fragment currentFragment = null;
	private Fragment homeFragment = new HomeFragment();
	private Fragment fictionFragment = new FictionFragment();

	public CompactCalendarView getCalendar()
	{
		if(calendar == null)
		{
			View v = getLayoutInflater().inflate(R.layout.activity_main,null);
			calendar = (CompactCalendarView) v.findViewById(R.id.compactcalendar_view);
		}
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
		calendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
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
					if(calendar.getLayoutParams().height!=0)
					{
						calendar.hideCalendar();
					}
					break;
			}
		}
		catch (Throwable e)
		{
			printToast(e.toString(),1);
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
	
}
