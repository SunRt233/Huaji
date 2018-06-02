package com.huaji.installer;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.*;

public class ToolbarActivityHelper
{
	private Context mContext;

    /*base view*/
    private LinearLayout mContentView;
	
	private View mToolbarLayout;
	
	private View toolbar;

    /*用户定义的view*/
    private View mUserView;

    /*toolbar*/
    private Toolbar mToolBar;

    /*视图构造器*/
    private LayoutInflater mInflater;

    /*
	 * 两个属性
	 * 1、toolbar是否悬浮在窗口之上
	 * 2、toolbar的高度获取
	 * */
    private static int[] ATTRS = {
		R.attr.windowActionBarOverlay,
		R.attr.actionBarSize
    };

    public ToolbarActivityHelper(Context context, int layoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        /*初始化整个内容*/
        initContentView();
        /*初始化用户定义的布局*/
		initToolBar();
        initUserView(layoutId);
        /*初始化toolbar*/
        
    }

    private void initContentView() {
        /*直接创建一个帧布局，作为视图容器的父容器*/
        mContentView = new LinearLayout(mContext);
        mContentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        mContentView.setOrientation(LinearLayout.VERTICAL);
		mContentView.setGravity(Gravity.TOP);
		
//		mToolbarLayout = new LinearLayout(mContext);
//		mToolbarLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
//		mToolbarLayout.setOrientation(LinearLayout.VERTICAL);
//		mToolbarLayout.setGravity(Gravity.CENTER|Gravity.TOP);
	}

    private void initToolBar() {
        /*通过inflater获取toolbar的布局文件*/
        toolbar = mInflater.inflate(R.layout.toolbar, null);
        mToolBar = (Toolbar) toolbar.findViewById(R.id.id_toolbar);
		//mToolbarLayout.addView(mToolBar);
    }

    private void initUserView(int id) {
        mUserView = mInflater.inflate(id, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TypedArray typedArray = mContext.getTheme().obtainStyledAttributes(ATTRS);
        /*获取主题中定义的悬浮标志*/
        boolean overly = typedArray.getBoolean(0, false);
        /*获取主题中定义的toolbar的高度*/
        int toolBarSize = (int) typedArray.getDimension(1,(int) mContext.getResources().getDimension(R.dimen.abc_action_bar_default_height_material));
        typedArray.recycle();
        /*如果是悬浮状态，则不需要设置间距*/
        params.topMargin = overly ? 0 : toolBarSize;
		//mContentView.addView(mToolBar,params);
		//View v = mInflater.inflate(R.layout.toolbar,null);
		mContentView.addView(toolbar);
        mContentView.addView(mUserView);

    }

    public LinearLayout getContentView() {
        return mContentView;
    }

    public Toolbar getToolBar() {
        return mToolBar;
    }
	
	
	
}
