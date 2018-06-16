package com.huaji.installer.ui.activity;

import android.*;
import android.content.*;
import android.content.pm.*;
import android.net.*;
import android.os.*;
import android.provider.*;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.support.v4.content.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import com.huaji.installer.*;
import com.huaji.installer.base.*;
import com.huaji.installer.ui.activity.*;
import java.io.*;
import android.support.v7.widget.Toolbar;
import com.huaji.installer.R;

public class InstallActivity extends BaseActivity 
{
	static String[] name = {"Server_Host","SOUSRT","LZX","ZY","SX","WY","LX","LDQ","LYY","GXQ","XY"};
	Toolbar toolbar;
	private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("滑稽安装器 V1.0");
		setSupportActionBar(toolbar);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
		{

			// 检查该权限是否已经获取
			int i = ContextCompat.checkSelfPermission(this, permissions[0]);
			// 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
			if (i != PackageManager.PERMISSION_GRANTED)
			{
				// 如果没有授予该权限，就去提示用户请求
				showDialogTipUserRequestPermission();

			}

		}

		Button b1=(Button) findViewById(R.id.mainButton1);
		Button b2=(Button) findViewById(R.id.mainButton2);
		Button b3=(Button) findViewById(R.id.mainButton3);
		Button b4=(Button) findViewById(R.id.mainButton4);
		Button b5=(Button) findViewById(R.id.mainButton5);
		Button b6=(Button) findViewById(R.id.mainButton6);
		Button b7=(Button) findViewById(R.id.mainButton7);
		Button b8=(Button) findViewById(R.id.mainButton8);
		Button b9=(Button) findViewById(R.id.mainButton9);
		Button b10=(Button) findViewById(R.id.mainButton10);

		b1.setOnClickListener(new b());
		b2.setOnClickListener(new b());
		b3.setOnClickListener(new b());
		b4.setOnClickListener(new b());
		b5.setOnClickListener(new b());
		b6.setOnClickListener(new b());
		b7.setOnClickListener(new b());
		b8.setOnClickListener(new b());
		b9.setOnClickListener(new b());
		b10.setOnClickListener(new b());



	}

	private AlertDialog dialog;


	private void showDialogTipUserRequestPermission()
	{

		new AlertDialog.Builder(this)
			.setTitle("存储权限不可用")
			.setMessage("由于需要获取存储空间，为你存储个人信息；\n否则，您将无法正常使用")
			.setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					startRequestPermission();
				}
			})
			.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					finish();
				}
			}).setCancelable(false).show();
	}

	private void startRequestPermission()
	{
		ActivityCompat.requestPermissions(this, permissions, 321);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
	{
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);

		if (requestCode == 321)
		{
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
			{
				if (grantResults[0] != PackageManager.PERMISSION_GRANTED)
				{
					// 判断用户是否 点击了不再提醒。(检测该权限是否还可以申请)
					boolean b = shouldShowRequestPermissionRationale(permissions[0]);
					if (!b)
					{
						// 用户还是想用我的 APP 的
						// 提示用户去应用设置界面手动开启权限
						showDialogTipUserGoToAppSettting();
					}
					else
						finish();
				}
				else
				{
					Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
				}
			}
		}
	}

	private void showDialogTipUserGoToAppSettting()
	{

		dialog = new AlertDialog.Builder(this)
			.setTitle("存储权限不可用")
			.setMessage("请在-应用设置-权限-中，允许支付宝使用存储权限来保存用户数据")
			.setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					// 跳转到应用设置界面
					goToAppSetting();
				}
			})
			.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					finish();
				}
			}).setCancelable(false).show();
	}

	private void goToAppSetting()
	{
        Intent intent = new Intent();

        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);

        startActivityForResult(intent, 123);
    }

    //
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123)
		{

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
			{
                // 检查该权限是否已经获取
                int i = ContextCompat.checkSelfPermission(this, permissions[0]);
                // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
                if (i != PackageManager.PERMISSION_GRANTED)
				{
                    // 提示用户应该去应用设置界面手动开启权限
                    showDialogTipUserGoToAppSettting();
                }
				else
				{
                    if (dialog != null && dialog.isShowing())
					{
                        dialog.dismiss();
                    }
                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


	public void install(int n)
	{


        try
		{
            File file = new File("/mnt/sdcard/games/com.mojang/skin_packs");
            File file2 = new File("/mnt/sdcard/games/com.mojang/minecraftpe");
            File file3 = new File("/storage/emulated/0/games/com.mojang/skin_packs");
            File file4 = new File("/storage/emulated/0/games/com.mojang/minecraftpe");
            if (!file.exists() || !file2.exists())
			{
                file.mkdirs();
                file2.mkdirs();
            }
            if (!file.exists() || !file2.exists())
			{
                file3.mkdirs();
                file4.mkdirs();
            }
            FileOutputStream huajiOut = new FileOutputStream("/mnt/sdcard/games/com.mojang/skin_packs/huaji.mcpack");
            InputStream huajiIn = this.getAssets().open("huaji.mcpack");
            byte[] huajibyte = new byte[1024];
            int huajiLenth = huajiIn.read(huajibyte);

			while (huajiLenth > 0)
			{
				huajiOut.write(huajibyte, 0, huajiLenth); 
				huajiLenth = huajiIn.read(huajibyte);
			}

			huajiOut.flush();
			huajiIn.close();
			huajiOut.close();

			InputStream opin = this.getAssets().open("valid_known_packs.json");
			FileOutputStream opout = new FileOutputStream("/mnt/sdcard/games/com.mojang/minecraftpe/valid_known_packs.json");
			byte[] opbyte = new byte[1024];
			int opLenth =  opin.read(opbyte);

			while (opLenth > 0)
			{
				opout.write(opbyte, 0, opLenth); 
				opLenth = opin.read(opbyte);
			}

			if (n > 1)
			{
				PrintWriter writer = new PrintWriter("/mnt/sdcard/games/com.mojang/minecraftpe/options.txt", "UTF-8");
				writer.println("mp_username:" + name[n]);
				writer.println("gfx_dpadscale:1");
				writer.println("game_skintypefull:9552da54-a549-36b0-aeb7-0341e1d63ad7_" + name[n]);
				writer.close();

				PrintWriter isInstall = new PrintWriter("/mnt/sdcard/games/com.mojang/minecraftpe/install.info" , "UTF-8");
				isInstall.println("username:" + name[n]);
				isInstall.close();

			}
			else if (n == 1)
			{
				PrintWriter writer = new PrintWriter("/mnt/sdcard/games/com.mojang/minecraftpe/options.txt", "UTF-8");
				writer.println("mp_username:SOUSRT");
				writer.println("gfx_dpadscale:1");
				writer.println("game_skintypefull:9552da54-a549-36b0-aeb7-0341e1d63ad7_SRT");
				writer.close();


				PrintWriter isInstall = new PrintWriter("/mnt/sdcard/games/com.mojang/minecraftpe/install.info" , "UTF-8");
				isInstall.println("username:" + name[n]);
				isInstall.close();
			}
			else if (n <= 0)
			{

			}

			opout.flush();
			opin.close();
			opout.close();



		}
		catch (Throwable e)
		{
			Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
		}
	}



	class b implements View.OnClickListener
	{
		@Override
		public void onClick(View p1)
		{
			switch (p1.getId())
			{
				case R.id.mainButton1:
					install(1);
					Toast.makeText(InstallActivity.this, "dhd", Toast.LENGTH_LONG).show();
					break;
				case R.id.mainButton2:
					install(2);
					break;
				case R.id.mainButton3:
					install(3);
					break;
				case R.id.mainButton4:
					install(4);
					break;
				case R.id.mainButton5:
					install(5);
					break;
				case R.id.mainButton6:
					install(6);
					break;
				case R.id.mainButton7:
					install(7);
					break;
				case R.id.mainButton8:
					install(8);
					break;
				case R.id.mainButton9:
					install(9);
					break;
				case R.id.mainButton10:
					install(10);
					break;
			}

			startActivity(new Intent(getApplicationContext(), MainActivity.class));
			finish();
			// TODO: Implement this method
		}
	}
}


