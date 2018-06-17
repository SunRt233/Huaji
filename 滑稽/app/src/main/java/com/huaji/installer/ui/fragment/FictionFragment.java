package com.huaji.installer.ui.fragment;

import android.os.*;
import android.view.*;
import com.huaji.installer.*;
import com.huaji.installer.base.*;
import android.support.v4.app.*;
import android.support.v7.widget.*;
import com.huaji.installer.ui.activity.*;

public class FictionFragment extends Fragment
{
	View view;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		view = inflater.inflate(R.layout.fragment_fiction,container,false);
		setHasOptionsMenu(true);
		
		getActivityToolbar().setSubtitle(null);
		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		menu.clear();
		// TODO: Implement this method
		inflater.inflate(R.menu.fragment_fiction_menu,menu);

		super.onCreateOptionsMenu(menu, inflater);
	}
	
	public Toolbar getActivityToolbar()
	{
		return ((MainActivity) getActivity()).getToolbar();
	}
	
}
