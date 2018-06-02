package com.huaji.installer;

import android.content.*;
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
import cn.bmob.v3.*;

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
		setContentView(R.layout.activity_main);
		
		initViewId();
		initToolbar();
		

		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
			this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
		
		setExitMode(EXIT_MODE_TWICE);
		
		
		//startActivity(new Intent(getApplicationContext(),AboutActivity.class));
	}
	
	private void initViewId()
	{
		toolbar = (Toolbar) findViewById(R.id.Maintoolbar);
		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		nav = (NavigationView) findViewById(R.id.nav_view);
		f = (FrameLayout) findViewById(R.id.activity_mainFrameLayout);
		
		nav.setNavigationItemSelectedListener(this);
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
		switch(p1.getItemId())
		{
			case R.id.nav_homepage:
				manager = getSupportFragmentManager();
				transaction = manager.beginTransaction();
				homepage = new HomeFragment();
				//changeFrag();
				transaction.replace(R.id.activity_mainFrameLayout,homepage).commit();
				
				break;
			case R.id.nav_fiction:
				manager = getSupportFragmentManager();
				transaction = manager.beginTransaction();
				fiction = new FictionFragment();
				//changeFrag();
				transaction.replace(R.id.activity_mainFrameLayout,fiction).commit();

				break;
		}
		
		return true;
	}
	
	private void changeFrag()
	{
		if(manager.getFragments().size()>0){
		for(Fragment f : manager.getFragments())
		{
			transaction.remove(f);
		}
		}
	}
	
	

}
