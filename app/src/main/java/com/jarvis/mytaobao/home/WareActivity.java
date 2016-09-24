package com.jarvis.mytaobao.home;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jarvis.http.CU_JSONResolve;
import com.jarvis.http.GetHttp;
import com.jarvis.mytaobao.Goods;
import com.jarvis.mytaobaotest.R;
import com.javis.Adapter.Adapter_ListView_ware;
import com.lesogo.cu.custom.xListview.XListView;
import com.lesogo.cu.custom.xListview.XListView.IXListViewListener;
import com.zdp.aseo.content.AseoZdpAseo;


@SuppressLint("SimpleDateFormat")
public class WareActivity extends Activity implements OnTouchListener, IXListViewListener {
	//��ʾ������Ʒ���б�
	private XListView listView;
	//�������������ؼ����������غ���ʾ�ײ������ؼ�
	private LinearLayout ll_search;
	//���ذ�ť
	private ImageView iv_back;
	@SuppressWarnings("unused")
	private EditText ed_search;
	
	private AnimationSet animationSet;
	/**��һ�ΰ�����Ļʱ��Y����*/
	float fist_down_Y = 0;
	/**�������ݵ�ҳ��*/
	private int pageIndex = 0;
	/**�洢���緵�ص�����*/
	private HashMap<String, Object> hashMap;
	/**�洢���緵�ص������е�data�ֶ�*/
	private ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();

	List<Goods> list;
	String pro[] = { "Apple iPhone 5s (A1530) 16GB 金色 移动联通4G手机",
			"Apple iPhone 6s (A1700) 64G 玫瑰金色 移动联通电信4G手机",
			"Apple iPhone 6 (A1586) 64GB 深空灰色 移动联通电信4G手机",
			"OPPO R9 4GB+64GB内存版 玫瑰金 全网通4G手机 双卡双待",
			"荣耀 畅玩5X 3GB内存版 落日金 移动联通电信4G手机",
			"荣耀8 4GB+32GB 全网通版 珠光白",
			"小米 红米Note3 高配全网通版 3GB+32GB 金色 移动联通电信4G手机 双卡双待",
			"vivo X7 全网通 4GB+64GB 移动联通电信4G手机 双卡双待 星空灰",
			"小米 红米 3S 高配全网通 3GB内存 32GB ROM 经典金色 移动联通电信4G手机 双卡双待",
			"乐视（Le）乐2（X620）32GB 原力金 移动联通电信4G手机 双卡双待",
			"小米5 全网通 标准版 3GB内存 32GB ROM 白色 移动联通电信4G手机",
			"华为 P9 全网通 4GB+64GB版 金色 移动联通电信4G手机 双卡双待",
			"努比亚(nubia)【3+64GB】小牛5 Z11mini 黑色 移动联通电信4G手机 双卡双待",
			"三星 Galaxy S7 edge（G9350）32G版 铂光金移动联通电信4G手机 双卡双待 骁龙820手机",
			"魅族 PRO5 32GB 银黑色 移动联通双4G手机 双卡双待",
			"小米 Max 全网通 标准版 3GB内存 32GB ROM 金色 移动联通电信4G手机",
			"金立M6 Plus 香槟金 4GB+64GB版 移动联通电信4G手机 双卡双待"
	};
	int price[] = { 2198,5099, 4599, 2499, 1099, 2299,1099,2498,899,1099,1799,3688,1299,5688,2199,1499,2999 };
	int imageId[] = {R.drawable.iphone5,R.drawable.iphone6s,R.drawable.iphone6s,R.drawable.oppo,
	R.drawable.rongyao5x,R.drawable.rong8qian,R.drawable.hongminote3,R.drawable.vivox7,
	R.drawable.hongmi3squan,R.drawable.leshi2,R.drawable.xiaomi5,R.drawable.huaweip9quan,
	R.drawable.nubiyaquan,R.drawable.sanxingedge7quan,R.drawable.meizupro5,
	R.drawable.xiaomimax,R.drawable.jinlim6quan};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ware_a);
		initView();
		//������������
		new WareTask().execute();
	}

	private void initView() {
		AseoZdpAseo.initType(this, AseoZdpAseo.INSERT_TYPE);
		ll_search = (LinearLayout) findViewById(R.id.ll_choice);
		ed_search = (EditText) findViewById(R.id.ed_Searchware);
		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		listView = (XListView) findViewById(R.id.listView_ware);
		listView.setOnTouchListener(this);
		listView.setXListViewListener(this);
		// ���ÿ��Խ����������صĹ���
		listView.setPullLoadEnable(true);
		listView.setPullRefreshEnable(true);
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onTouch(View arg0, MotionEvent event) {
		float y = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			//��һ�ΰ���ʱ������
			fist_down_Y = y;
			break;
		case MotionEvent.ACTION_MOVE:
			// ���ϻ���������������
			if (fist_down_Y - y > 250 && ll_search.isShown()) {
				if (animationSet != null) {
					animationSet = null;
				}
				animationSet = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.up_out);
				ll_search.startAnimation(animationSet);
				ll_search.setY(-100);
				ll_search.setVisibility(View.GONE);
			}
			// ���»�������ʾ������
			if (y - fist_down_Y > 250 && !ll_search.isShown()) {
				if (animationSet != null) {
					animationSet = null;
				}
				animationSet = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.down_in);
				ll_search.startAnimation(animationSet);
				ll_search.setY(0);
				ll_search.setVisibility(View.VISIBLE);
			}
			break;

		}
		return false;

	}

	private class WareTask extends AsyncTask<Void, Void, HashMap<String, Object>> {
		
		ProgressDialog dialog=null;

		@Override
		protected void onPreExecute() {
			if (dialog==null) {
				dialog=ProgressDialog.show(WareActivity.this, "","正在加载");
				dialog.show();
			}
			
			
		}

		@Override
		protected HashMap<String, Object> doInBackground(Void... arg0) {
			String url = "";
			if (pageIndex == 0) {
				url = "http://192.168.0.111:3000/taoBaoQuery";
			} else {
				url = "http://192.168.0.111:3000/taoBaoQuery?pageIndex=" + pageIndex;
			}
			//�������ݣ�����json
			String json = GetHttp.RequstGetHttp(url);
			//��һ������������ֶ�
			String[] LIST1_field = { "data" };
			
			//�ڶ���Ķ��������ֶ�
			String[] STR2_field = { "id", "name", "price", "sale_num", "address", "pic" };
			ArrayList<String[]> aL_STR2_field = new ArrayList<String[]>();
			//�ڶ���Ķ��������ֶη����һ������������ֶ���
			aL_STR2_field.add(STR2_field);
			//�������ص�json
			hashMap = CU_JSONResolve.parseHashMap2(json, null, LIST1_field, aL_STR2_field);
			return hashMap;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void onPostExecute(HashMap<String, Object> result) {
			
			if (dialog!=null&&dialog.isShowing()) {
				dialog.dismiss();
				dialog=null;
			}

			list = new ArrayList<Goods>();
			for(int i = 0;i < pro.length;i++){
				Goods goods = new Goods(imageId[i],pro[i],price[i]);
				list.add(goods);
			}
			listView.setAdapter(new Adapter_ListView_ware(WareActivity.this, list));
			listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
					Intent intent = new Intent(WareActivity.this, BabyActivity.class);
					startActivity(intent);
				}
			});

			//���������������ʧ�ܣ���ô��ʾĬ�ϵ�����
			/*if (result != null && result.get("data") != null) {
				//�õ�data�ֶε�����
				arrayList.addAll((Collection<? extends HashMap<String, Object>>) result.get("data"));
				listView.setAdapter(new Adapter_ListView_ware(WareActivity.this, arrayList));
				listView.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
						Intent intent = new Intent(WareActivity.this, BabyActivity.class);
						startActivity(intent);
					}
				});

			}else {
				listView.setAdapter(new Adapter_ListView_ware(WareActivity.this));
				listView.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
						Intent intent = new Intent(WareActivity.this, BabyActivity.class);
						startActivity(intent);
					}
				});
			}*/

			// ֹͣˢ�ºͼ���

			onLoad();

		}

	}

	/** ˢ�� */
	@Override
	public void onRefresh() {
		// ˢ������
		pageIndex = 0;
		arrayList.clear();
		new WareTask().execute();
		// ֹͣˢ�ºͼ���
		onLoad();

	}

	/** ���ظ��� */
	@Override
	public void onLoadMore() {
		pageIndex += 1;
		if (pageIndex >= 4) {
			Toast.makeText(this, "�Ѿ����һҳ��", Toast.LENGTH_SHORT).show();
			onLoad();
			return;
		}
		new WareTask().execute();

	}

	/** ֹͣ���غ�ˢ�� */
	private void onLoad() {
		listView.stopRefresh();
		// ֹͣ���ظ���
		listView.stopLoadMore();

		// �������һ��ˢ��ʱ��
		listView.setRefreshTime(getCurrentTime(System.currentTimeMillis()));
	}

	/** �򵥵�ʱ���ʽ */
	public static SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd HH:mm");

	public static String getCurrentTime(long time) {
		if (0 == time) {
			return "";
		}

		return mDateFormat.format(new Date(time));

	}

}
