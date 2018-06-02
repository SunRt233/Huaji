package com.huaji.installer.widget;

import android.content.*;
import android.support.v4.view.*;
import android.util.*;
import android.view.*;

public class BetterViewPager extends ViewPager
 {
	private static boolean scrollable = true;

	public BetterViewPager(Context context) {
		super(context);
	}

	public BetterViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * 设置viewpager是否可以滚动
	 * 
	 * @param enable
	 */
	public static void setScrollable(boolean enable) {
		scrollable = enable;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		if (scrollable) {
			return super.onInterceptTouchEvent(event);
		} else {
			return false;
		}
	}
}
