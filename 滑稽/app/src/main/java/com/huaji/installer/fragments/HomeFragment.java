package com.huaji.installer.fragments;

import android.support.v4.app.*;
import android.view.*;
import android.os.*;
import com.huaji.installer.*;
import android.widget.*;
import android.view.View.*;
import android.support.v7.app.*;
import android.support.v7.widget.Toolbar;

public class HomeFragment extends Fragment
{
	private View view;
	private Toolbar toolbar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		view = inflater.inflate(R.layout.fragment_home,container,false);
		setHasOptionsMenu(true);
		
		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		// TODO: Implement this method
		inflater.inflate(R.menu.main,menu);
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	
	
}
