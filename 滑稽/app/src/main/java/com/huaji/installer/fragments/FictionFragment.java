package com.huaji.installer.fragments;

import android.support.v4.app.*;
import android.view.*;
import android.os.*;
import com.huaji.installer.R;

public class FictionFragment extends Fragment
{
	View view;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		view = inflater.inflate(R.layout.fragment_fiction,container);
		setHasOptionsMenu(true);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		// TODO: Implement this method
		inflater.inflate(R.menu.fragment_fiction_menu,menu);

		super.onCreateOptionsMenu(menu, inflater);
	}
	
	
	
}
