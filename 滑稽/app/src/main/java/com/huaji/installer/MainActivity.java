package com.huaji.installer;

import android.os.*;
import android.support.design.widget.*;
import android.support.v4.app.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import com.huaji.installer.fragments.*;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.graphics.*;
import android.app.Activity;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener
{
	private Toolbar toolbar;
	private DrawerLayout drawer;
	private NavigationView nav;
	private FrameLayout f;
	private Fragment homepage,fiction;
	private FragmentManager manager;
	private FragmentTransaction transaction;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		// TODO: Implement this method
		super.onCreate(savedInstanceState);

		SetStuatsBar(this);

		setContentView(R.layout.activity_main);

		initViewId();
		initToolbar();


		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
			this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

		setExitMode(EXIT_MODE_TWICE);

 
	}
	
	private void SetStuatsBar(Activity activity) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				//5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
				Window window = activity.getWindow();
				View decorView = window.getDecorView();
				//两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
				int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
				decorView.setSystemUiVisibility(option);
				window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
				window.setStatusBarColor(Color.TRANSPARENT);
				//导航栏颜色也可以正常设置
//                window.setNavigationBarColor(Color.TRANSPARENT);
			} else {
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

	private void initViewId()
	{
		toolbar = (Toolbar) findViewById(R.id.Maintoolbar);
		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		nav = (NavigationView) findViewById(R.id.nav_view);
		f = (FrameLayout) findViewById(R.id.activity_mainFrameLayout);

		nav.setNavigationItemSelectedListener(this);
		drawer.setDrawerListener(new DrawerLayout.DrawerListener(){

				@Override
				public void onDrawerSlide(View p1, float p2)
				{
					// TODO: Implement this method
				}

				@Override
				public void onDrawerOpened(View p1)
				{
					
					// TODO: Implement this method
				}

				@Override
				public void onDrawerClosed(View p1)
				{
					// TODO: Implement this method
				}

				@Override
				public void onDrawerStateChanged(int p1)
				{
					// TODO: Implement this method
				}
			});
	}

	private void initToolbar()
	{
		toolbar.setTitle("轻-工具");
		toolbar.inflateMenu(R.menu.main);
		setSupportActionBar(toolbar);

	}

	@Override
	public boolean onNavigationItemSelected(MenuItem p1)
	{
		// TODO: Implement this method
		switch (p1.getItemId())
		{
			case R.id.nav_homepage:
				manager = getSupportFragmentManager();
				transaction = manager.beginTransaction();
				homepage = new HomeFragment();
				//changeFrag();
				transaction.replace(R.id.activity_mainFrameLayout, homepage).commit();

				break;
			case R.id.nav_fiction:
				manager = getSupportFragmentManager();
				transaction = manager.beginTransaction();
				fiction = new FictionFragment();
				//changeFrag();
				transaction.replace(R.id.activity_mainFrameLayout, fiction).commit();

				break;
		}

		return true;
	}

	private void changeFrag()
	{
		if (manager.getFragments().size() > 0)
		{
			for (Fragment f : manager.getFragments())
			{
				transaction.remove(f);
			}
		}
	}



}
