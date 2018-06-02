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
	View view;
	private Button bn;
	private Toolbar toolbar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		view = inflater.inflate(R.layout.fragment_home,container,false);
		bn = (Button) view.findViewById(R.id.fragmenthomeButton1);
		
		
		bn.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
					
				}
			});
			
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
