package com.huaji.installer.ui.fragment;

import android.content.*;
import android.os.*;
import android.support.v4.app.*;
import android.support.v7.widget.*;
import android.view.*;
import com.github.sundeepk.compactcalendarview.*;
import com.huaji.installer.*;
import com.huaji.installer.ui.activity.*;
import java.text.*;
import java.util.*;

import com.huaji.installer.R;

public class HomeFragment extends Fragment
{
	private View view;
	private Toolbar toolbar;
	private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("yyyy年-MM月-dd日");
	Calendar calendar = new GregorianCalendar();
	TimeZone timeZone = calendar.getTimeZone();
	private CompactCalendarView calendarView;
	private CardView MRYW;
	private ClickEvent clickevent = new ClickEvent();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		view = inflater.inflate(R.layout.fragment_home, container, false);
		/*View ac = inflater.inflate(R.layout.activity_main,null);
		CompactCalendarView mCalendar = (CompactCalendarView) v.findViewById(R.id.compactcalendar_view);*/
		calendarView = ((MainActivity) getActivity()).getCalendar();
		
		setHasOptionsMenu(true);
		getActivityToolbar().hideOverflowMenu();
		initCalendar();
		
		MRYW = (CardView) view.findViewById(R.id.fragment_homeMRYW);
		MRYW.setOnClickListener(clickevent);
		
		
		return view;
	}
	
	class ClickEvent implements View.OnClickListener
	{
		@Override
		public void onClick(View p1)
		{
			// TODO: Implement this method
			switch(p1.getId())
			{
				case R.id.fragment_homeMRYW:
					startActivity(new Intent(getActivity().getApplicationContext(),ArticleActivity.class));
			}
		}
		
	}

	private void initCalendar()
	{
		// TODO: Implement this method
		getActivityToolbar().setSubtitle(dateFormatForMonth.format(new Date()));
		calendarView.setVisibility(View.VISIBLE);
		calendarView.showCalendar();
		calendarView.setLocale(timeZone,Locale.CHINESE);
        calendarView.setUseThreeLetterAbbreviation(true);
		calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
				@Override
				public void onDayClick(Date dateClicked)
				{
					getActivityToolbar().setSubtitle(dateFormatForMonth.format(dateClicked));
				}

				@Override
				public void onMonthScroll(Date firstDayOfNewMonth)
				{
					getActivityToolbar().setSubtitle(dateFormatForMonth.format(firstDayOfNewMonth));
				}
			});

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		menu.clear();
		// TODO: Implement this method
		inflater.inflate(R.menu.fragment_home_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO: Implement this method
		switch(item.getItemId())
		{
			case R.id.fragment_home_menu_showCalendar:
				if(calendarView.getLayoutParams().height==0)
				{
					calendarView.showCalendar();
				}
				else
				{
					calendarView.hideCalendar();
				}
				break;
			
		}
		return super.onOptionsItemSelected(item);
	}
	
	

	public Toolbar getActivityToolbar()
	{
		return ((MainActivity) getActivity()).getToolbar();
	}

}
