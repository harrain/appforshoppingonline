package com.javis.ab.view;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 
 * ���ƣ�AbViewPagerAdapter.java 
 * ������һ��ͨ�õ�ViewPager������
 */
@SuppressLint("UseSparseArrays")
@SuppressWarnings("unused")
public class AbViewPagerAdapter extends PagerAdapter{
	
	/** The m context. */
	private Context mContext;
	
	/** The m list views. */
	private ArrayList<View> mListViews = null;
	
	/** The m views. */
	private HashMap <Integer,View> mViews = null;


	/**
	 * Instantiates a new ab view pager adapter.
	 * @param context the context
	 * @param mListViews the m list views
	 */
	public AbViewPagerAdapter(Context context,ArrayList<View> mListViews) {
		this.mContext = context;
		this.mListViews = mListViews;
		this.mViews = new HashMap <Integer,View>();
	}

	/**
	 * ��������ȡ����.
	 *
	 * @return the count
	 * @see PagerAdapter#getCount()
	 */
	@Override
	public int getCount() {
		return mListViews.size();
	}

	/**
	 * ������Object�Ƿ��Ӧ���View.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 * @return true, if is view from object
	 * @see PagerAdapter#isViewFromObject(View, Object)
	 */
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == (arg1);
	}

	/**
	 * ��������ʾView.
	 *
	 * @param container the container
	 * @param position the position
	 * @return the object
	 * @see PagerAdapter#instantiateItem(View, int)
	 */
	@Override
	public Object instantiateItem(View container, int position) {
		View v = mListViews.get(position);
		((ViewPager) container).addView(v);
		return v;
	}

	/**
	 * �������Ƴ�View.
	 *
	 * @param container the container
	 * @param position the position
	 * @param object the object
	 * @see PagerAdapter#destroyItem(View, int, Object)
	 */
	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView((View)object);
	}
	
	/**
	 * ����������Ҫ��������notifyDataSetChanged.
	 *
	 * @param object the object
	 * @return the item position
	 * @see PagerAdapter#getItemPosition(Object)
	 */
	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}
	

}
