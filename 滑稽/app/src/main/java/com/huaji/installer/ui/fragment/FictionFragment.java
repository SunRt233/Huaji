package com.huaji.installer.ui.fragment;

import android.os.*;
import android.view.*;
import com.huaji.installer.*;
import com.huaji.installer.base.*;
import android.support.v4.app.*;

public class FictionFragment extends Fragment
{
	View view;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		view = inflater.inflate(R.layout.fragment_fiction,container,false);
		setHasOptionsMenu(true);
		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		// TODO: Implement this method
		inflater.inflate(R.menu.fragment_fiction_menu,menu);

		super.onCreateOptionsMenu(menu, inflater);
	}
	
	
	
}
