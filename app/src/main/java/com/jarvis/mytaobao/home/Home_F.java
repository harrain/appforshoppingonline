package com.jarvis.mytaobao.home;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jarvis.mytaobao.Log;
import com.jarvis.mytaobao.Regist;
import com.jarvis.mytaobaotest.R;
import com.javis.Adapter.Adapter_GridView;
import com.javis.Adapter.Adapter_GridView_hot;
import com.javis.ab.view.AbOnItemClickListener;
import com.javis.ab.view.AbSlidingPlayView;
import com.zxing.activity.CaptureActivity;


public class Home_F extends Fragment {
	//����������
	private TextView tv_top_title;
	//����ľŹ���
	private GridView gridView_classify;
	//�����г��ľŹ���
	private GridView my_gridView_hot;//轮播图
	private Adapter_GridView adapter_GridView_classify;
	private Adapter_GridView_hot adapter_GridView_hot;
	//��ҳ�ֲ�
	private AbSlidingPlayView viewPager;
	private TextView tv_log;  private TextView tv_regist;
	//ɨһɨ
	/*private ImageView iv_shao;*/
	// ����Ź������Դ�ļ�
	/*private int[] pic_path_classify = {R.drawable.t1, R.drawable.t2, R.drawable.t3, R.drawable.t4};*/
	private int[] pic_path_classify = {R.drawable.menu_guide_4,R.drawable.menu_guide_5, R.drawable.menu_guide_6,  R.drawable.menu_guide_8};

	// �����г�����Դ�ļ�   R.drawable.imenu1, R.drawable.imenu2, R.drawable.imenu3, R.drawable.imenu4,
	private int[] pic_path_hot = {  };
	/**�洢��ҳ�ֲ��Ľ���*/  //R.drawable.menu_1, R.drawable.menu_2, R.drawable.menu_3, R.drawable.menu_4, R.drawable.menu_5, R.drawable.menu_6
	private ArrayList<View> allListView;
	/**��ҳ�ֲ��Ľ������Դ*/
	private int[] resId = { R.drawable.lunbo_1, R.drawable.lunbo_2, R.drawable.lunbo_3};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = LayoutInflater.from(getActivity()).inflate(R.layout.home_f, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		/*iv_shao=(ImageView) view.findViewById(R.id.iv_shao);
		iv_shao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//��ת����ά��ɨ��
				Intent intent=new Intent(getActivity(),CaptureActivity.class); //二维码扫描
				startActivity(intent);
			}
		});*/

		tv_log = (TextView) view.findViewById(R.id.log);
		tv_regist = (TextView) view.findViewById(R.id.regist);
		tv_log.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//��ս��������������
				Intent intent=new Intent(getActivity(),Log.class);//商品带搜索栏
				startActivity(intent);
			}
		});
		tv_regist.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//��ս��������������
				Intent intent=new Intent(getActivity(),Regist.class);//商品带搜索栏
				startActivity(intent);
			}
		});
		tv_top_title=(TextView) view.findViewById(R.id.tv_top_title);
		tv_top_title.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//��ս��������������
				Intent intent=new Intent(getActivity(),WareActivity.class);//商品带搜索栏
				startActivity(intent);
			}
		});
		
		gridView_classify = (GridView) view.findViewById(R.id.my_gridview);
		my_gridView_hot = (GridView) view.findViewById(R.id.my_gridview_hot);
		gridView_classify.setSelector(new ColorDrawable(Color.TRANSPARENT));
		my_gridView_hot.setSelector(new ColorDrawable(Color.TRANSPARENT));
		adapter_GridView_classify = new Adapter_GridView(getActivity(), pic_path_classify);
		adapter_GridView_hot = new Adapter_GridView_hot(getActivity(), pic_path_hot);
		gridView_classify.setAdapter(adapter_GridView_classify);
		my_gridView_hot.setAdapter(adapter_GridView_hot);

		viewPager = (AbSlidingPlayView) view.findViewById(R.id.viewPager_menu);
		//���ò��ŷ�ʽΪ˳�򲥷�
		viewPager.setPlayType(1);
		//���ò��ż��ʱ��
		viewPager.setSleepTime(3000);

		gridView_classify.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				//��ս��������������
				Intent intent = new Intent(getActivity(), WareActivity.class);
				startActivity(intent);
			}
		});
		my_gridView_hot.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				//��ת�������������
				Intent intent = new Intent(getActivity(), BabyActivity.class);
				startActivity(intent);
			}
		});
		
		initViewPager();
	}

	private void initViewPager() {

		if (allListView != null) {
			allListView.clear();
			allListView = null;
		}
		allListView = new ArrayList<View>();

		for (int i = 0; i < resId.length; i++) {
			//����ViewPager�Ĳ���
			View view = LayoutInflater.from(getActivity()).inflate(R.layout.pic_item, null);
			ImageView imageView = (ImageView) view.findViewById(R.id.pic_item);
			imageView.setImageResource(resId[i]);
			allListView.add(view);
		}
		
		
		viewPager.addViews(allListView);
		//��ʼ�ֲ�
		viewPager.startPlay();
		viewPager.setOnItemClickListener(new AbOnItemClickListener() {
			@Override
			public void onClick(int position) {
				//��ת���������
				Intent intent = new Intent(getActivity(), BabyActivity.class);
				startActivity(intent);
			}
		});
	}

}
