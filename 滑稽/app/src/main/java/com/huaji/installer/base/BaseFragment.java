package com.huaji.installer.base;

import android.support.v4.app.*;

public class BaseFragment extends Fragment
{
	public boolean added = false;
	public String privateTag = null;

	public void setPrivateTag(String privateTag)
	{
		this.privateTag = privateTag;
	}

	public String getPrivateTag()
	{
		return privateTag;
	}

	public void setIsAdded(boolean baseTag)
	{
		added = baseTag;
	}

	public boolean getAddedStatus()
	{
		return added;
	}
}
