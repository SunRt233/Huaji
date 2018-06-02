package com.huaji.installer;

import android.content.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import com.huaji.installer.adapter.*;
import com.huaji.installer.helper.*;
import com.huaji.installer.widget.*;
import java.io.*;
import java.util.*;

import android.support.v7.widget.Toolbar;
import android.support.v4.view.*;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener
{
	private Toolbar toolbar;
	private TextView username,skinpack,gbastatus;
	private BetterViewPager CONTENT_VIEW;
	private BetterViewPagerAdapter CONTENT_VIEW_ADAPTER;
	private TabLayout tab;
	private ArrayList<View> viewlist = new ArrayList<View>();
	private ArrayList<String> titlelist = new ArrayList<String>();
	private Button reset,fix,gba_install,gba_uninstall,kdygazbh_install;
	static String[] name = {"Server_Host","SOUSRT","LZX","ZY","SX","WY","LX","LDQ","LYY","GXQ","XY"};
	private LayoutInflater l;
	private Context mc;
	private DrawerLayout drawer;
	private NavigationView nv;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		toolbar = (Toolbar) findViewById(R.id.Maintoolbar);
		toolbar.setTitle("滑稽 V2.0");
		setSupportActionBar(toolbar);

		l = getLayoutInflater();
		initContentView();
		initStatus();
		initEvents();

		Log.i("info", "successful");

		mc = this.getApplicationContext();
		
		printToast("233",Toast.LENGTH_LONG);
	}

	
	

	private void initContentView()
	{
		CONTENT_VIEW = (BetterViewPager) findViewById(R.id.activitymainBetterViewPager1);
		tab = (TabLayout) findViewById(R.id.tab_layout);

		View VIEW_STATUS = l.inflate(R.layout.view_status, null);
		View VIEW_GBA = l.inflate(R.layout.view_gba, null);

		viewlist.add(VIEW_STATUS);
		viewlist.add(VIEW_GBA);

		titlelist.add("状态");
		titlelist.add("GBA模拟器");

		CONTENT_VIEW.setOffscreenPageLimit(2);
		CONTENT_VIEW_ADAPTER = new BetterViewPagerAdapter(viewlist, titlelist);

		CONTENT_VIEW.setAdapter(CONTENT_VIEW_ADAPTER);

		tab.setTabsFromPagerAdapter(CONTENT_VIEW_ADAPTER);
		tab.setupWithViewPager(CONTENT_VIEW);
		CONTENT_VIEW.setCurrentItem(0, true);
		tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

				@Override
				public void onTabSelected(TabLayout.Tab p1)
				{
					CONTENT_VIEW.setCurrentItem(p1.getPosition());
					// TODO: Implement this method
				}

				@Override
				public void onTabUnselected(TabLayout.Tab p1)
				{
					// TODO: Implement this method
				}

				@Override
				public void onTabReselected(TabLayout.Tab p1)
				{
					// TODO: Implement this method
				}
			});

		username = (TextView) VIEW_STATUS.findViewById(R.id.uesrnameTV);
		skinpack = (TextView) VIEW_STATUS.findViewById(R.id.skinpackTV);
		reset = (Button) VIEW_STATUS.findViewById(R.id.reset);
		fix = (Button) VIEW_STATUS.findViewById(R.id.fix);
		gba_install = (Button) VIEW_GBA.findViewById(R.id.gba_install);
		gba_uninstall = (Button) VIEW_GBA.findViewById(R.id.gba_uninstall);
		gbastatus = (TextView) VIEW_GBA.findViewById(R.id.gbastatus);
		kdygazbh_install = (Button) VIEW_GBA.findViewById(R.id.kdygazbh_install);
		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		nv = (NavigationView) findViewById(R.id.nav_view);
		
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
			this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
		
		nv.setNavigationItemSelectedListener(this);
	}

	private void initEvents()
	{
		gba_install.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					initGBA();
				}
			});

		kdygazbh_install.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					initPKM_AZBH();
				}
			});

		File pkm_azbh = new File("/mnt/sdcard/0gba游戏/口袋妖怪暗之冰花.gba");

		if (pkm_azbh.exists())
		{
			kdygazbh_install.setText("已经安装(重装)");
			CONTENT_VIEW_ADAPTER.notifyDataSetChanged();
		}

		setExitMode(EXIT_MODE_TWICE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO: Implement this method
		if (item.getItemId() == R.id.about)
		{
			startActivity(new Intent(getApplicationContext(), AboutActivity.class));
			/*try
			{
				Downloader.downLoad("https://sunrt233.wodemo.net/huajiupdate", "/mnt/sdcard/AIDE/", "update_info.json");
			}
			catch (Throwable t)
			{
				printToast(t.toString(), Toast.LENGTH_LONG);
			}*/
		}
		return super.onOptionsItemSelected(item);
	}

	public void initStatus()
	{
		String un = "不可用";

		try
		{
			FileReader fr = new FileReader("/mnt/sdcard/games/com.mojang/minecraftpe/options.txt");
			BufferedReader br = new BufferedReader(fr);

			un = (br.readLine().toString());


		}
		catch (FileNotFoundException e)
		{}
		catch (IOException e)
		{}

		username.append(un);
		File sk = new File("/mnt/sdcard/games/com.mojang/skin_packs/huaji.mcpack");


		if (sk.exists())
		{
			skinpack.append("可用");
		}
		else
		{
			skinpack.append("不可用");
		}

		fix.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					startActivity(new Intent(getApplicationContext(), InstallActivity.class));
					
					// TODO: Implement this method
				}
			});

		if (InitHelper.isAvilible(getApplicationContext(), "com.fastemulator.gba"))
		{
			gbastatus.setText("已安装");
			gba_install.setText("启动");


		}
	}


	private void initGBA()
	{
		if (InitHelper.isAvilible(getApplicationContext(), "com.fastemulator.gba"))
		{
			File f = new File("/mnt/sdcard/0gba游戏/");
			if (!f.exists())
			{
				f.mkdir();
			}

			Intent i = new Intent();
			i.setComponent(new ComponentName("com.fastemulator.gba", "com.fastemulator.gba.MainActivity"));

			startActivity(i);

		}
		else
		{
			try
			{
				InputStream gba = getAssets().open("GBA/GBA_RUNTIME.apk");
				FileOutputStream out = new FileOutputStream("/mnt/sdcard/GBA_RUNTIME.apk");
				byte[] gba_b = new byte[1024];
				int gba_l = gba.read(gba_b);

				while (gba_l > 0)
				{

					out.write(gba_b, 0, gba_l);
					gba_l = gba.read(gba_b);

				}

				out.flush();
				gba.close();
				out.close();

				File f = new File("/mnt/sdcard/GBA_RUNTIME.apk");  
				InitHelper.installApk(getApplicationContext(), f);


				gbastatus.setText("已安装");
				gba_install.setText("启动");

				CONTENT_VIEW_ADAPTER.notifyDataSetChanged();

			}
			catch (IOException e)
			{}
		}
	}

	private void initPKM_AZBH()
	{
		File pkm_azbh = new File("/mnt/sdcard/0gba游戏/口袋妖怪暗之冰花.gba");

		if (!pkm_azbh.exists())
		{
			try
			{
				InputStream pkm_AZBH = getAssets().open("GBA/pkm_AZBH.gba");
				FileOutputStream gameout = new FileOutputStream("/mnt/sdcard/0gba游戏/口袋妖怪暗之冰花.gba");
				byte[] pkm_AZBH_b = new byte[1024];
				int pkm_AZBH_l = pkm_AZBH.read(pkm_AZBH_b);

				while (pkm_AZBH_l > 0)
				{

					gameout.write(pkm_AZBH_b, 0, pkm_AZBH_l);
					pkm_AZBH_l = pkm_AZBH.read(pkm_AZBH_b);

				}

				gameout.flush();
				pkm_AZBH.close();
				gameout.close();

				Toast.makeText(getApplicationContext(), "安装成功", Toast.LENGTH_LONG).show();

				kdygazbh_install.setText("已经安装(重装)");

			}
			catch (IOException e)
			{}

		}
		else
		{
			kdygazbh_install.setText("已经安装(重装)");
			Toast.makeText(getApplicationContext(), "安装成功", Toast.LENGTH_LONG).show();

		}

	}
	
	@Override
	public boolean onNavigationItemSelected(MenuItem p1)
	{
		// TODO: Implement this method
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	

}
