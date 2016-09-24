package com.jarvis.mytaobao.home;

import com.jarvis.mytaobaotest.R;
import com.lesogo.cu.custom.ScaleView.HackyViewPager;
import com.zdp.aseo.content.AseoZdpAseo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * ��ʾ��ͼ����
 *
 *
 */
public class ShowBigPictrue extends FragmentActivity {

	private HackyViewPager viewPager;
	private int[] resId={R.drawable.s5};
	/**�õ���һ��������ͼƬ��λ��*/
	private int position=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_big_pictrue_a);
		Intent intent=getIntent();
		position=intent.getIntExtra("position", 0);
		AseoZdpAseo.initType(this, AseoZdpAseo.INSERT_TYPE);
		initViewPager();
	}
	
private void initViewPager(){
		
		viewPager = (HackyViewPager) findViewById(R.id.viewPager_show_bigPic);
		ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
		//��ת���ڼ�������
		viewPager.setCurrentItem(position);
		
	}
	
	private class ViewPagerAdapter extends FragmentStatePagerAdapter{

		public ViewPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			int show_resId=resId[position];
			return new PictrueFragment(show_resId);
		}

		@Override
		public int getCount() {
			return resId.length;
		}

		
	}

}
