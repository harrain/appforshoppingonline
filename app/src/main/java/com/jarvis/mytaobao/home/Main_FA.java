package com.jarvis.mytaobao.home;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.jarvis.mytaobao.Data.Data;
import com.jarvis.mytaobao.cart.Cart_F;
/*import com.jarvis.mytaobao.discover.Discover_F;
import com.jarvis.mytaobao.tao.Tao_F;*/
import com.jarvis.mytaobao.user.User_F;
import com.jarvis.mytaobaotest.R;
import com.javis.mytools.IBtnCallListener;
import com.zdp.aseo.content.AseoZdpAseo;

/**
 * ����������ײ�Ŀ��Activity�����е�Fragment���������ڴ�Activity�����ڵ�
 * 
 *
 * 
 */
public class Main_FA extends FragmentActivity implements OnClickListener, IBtnCallListener {

// ����ײ��Ĳ˵���ť
private ImageView[] bt_menu = new ImageView[3];
// ����ײ��Ĳ˵���ťid
private int[] bt_menu_id = { R.id.iv_menu_0,  R.id.iv_menu_3, R.id.iv_menu_4 };

// ����ײ���ѡ�в˵���ť��Դ
private int[] select_on = { R.drawable.guide_home_on,  R.drawable.guide_cart_on, R.drawable.guide_account_on };
// ����ײ���δѡ�в˵���ť��Դ
private int[] select_off = { R.drawable.bt_menu_0_select,  R.drawable.bt_menu_3_select, R.drawable.bt_menu_4_select };

/** ������ */
private Home_F home_F;
/** ΢�Խ��� */
/*
private Tao_F tao_F;
*/
/** ���ֽ��� *//*

private Discover_F discover_F;
*/
/** ���ﳵ���� */
private Cart_F cart_F;
/** �ҵ��Ա����� */
private User_F user_F;

public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_fa);
		getSaveData();
		initView();
		}

/** �õ�����Ĺ��ﳵ���� */
private void getSaveData() {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		AseoZdpAseo.initTimer(this);
		SharedPreferences sp = getSharedPreferences("SAVE_CART", Context.MODE_PRIVATE);
		int size = sp.getInt("ArrayCart_size", 0);
		for (int i = 0; i < size; i++) {
		hashMap.put("type", sp.getString("ArrayCart_type_" + i, ""));
		hashMap.put("color", sp.getString("ArrayCart_color_" + i, ""));
		hashMap.put("num", sp.getString("ArrayCart_num_" + i, ""));
		Data.arrayList_cart.add(hashMap);
		}

		}

// ��ʼ�����
private void initView() {
		// �ҵ��ײ��˵��İ�ť�����ü���
		for (int i = 0; i < bt_menu.length; i++) {
		bt_menu[i] = (ImageView) findViewById(bt_menu_id[i]);
		bt_menu[i].setOnClickListener(this);
		}

		// ��ʼ��Ĭ����ʾ�Ľ���
		if (home_F == null) {
		home_F = new Home_F();
		addFragment(home_F);
		showFragment(home_F);
		} else {
		showFragment(home_F);
		}
		// ����Ĭ����ҳΪ���ʱ��ͼƬ
		bt_menu[0].setImageResource(select_on[0]);
		AseoZdpAseo.init(this,AseoZdpAseo.SCREEN_TYPE);

		}

@Override
public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_menu_0:
		// ������
		if (home_F == null) {
		home_F = new Home_F();
		// �жϵ�ǰ�����Ƿ����أ�������ؾͽ��������ʾ��false��ʾ��ʾ��true��ʾ��ǰ��������
		addFragment(home_F);
		showFragment(home_F);
		} else {
		if (home_F.isHidden()) {
		showFragment(home_F);
		}
		}

		break;
		/*case R.id.iv_menu_1:
		// ΢�Խ���
		if (tao_F == null) {
		tao_F = new Tao_F();
		// �жϵ�ǰ�����Ƿ����أ�������ؾͽ��������ʾ��false��ʾ��ʾ��true��ʾ��ǰ��������
		if (!tao_F.isHidden()) {
		addFragment(tao_F);
		showFragment(tao_F);
		}
		} else {
		if (tao_F.isHidden()) {
		showFragment(tao_F);
		}
		}

		break;
		case R.id.iv_menu_2:
		// ���ֽ���
		if (discover_F == null) {
		discover_F = new Discover_F();
		// �жϵ�ǰ�����Ƿ����أ�������ؾͽ��������ʾ��false��ʾ��ʾ��true��ʾ��ǰ��������
		if (!discover_F.isHidden()) {
		addFragment(discover_F);
		showFragment(discover_F);
		}
		} else {
		if (discover_F.isHidden()) {
		showFragment(discover_F);
		}
		}

		break;*/
		case R.id.iv_menu_3:
		// ���ﳵ����
		if (cart_F != null) {
		removeFragment(cart_F);
		cart_F = null;
		}
		cart_F = new Cart_F();
		// �жϵ�ǰ�����Ƿ����أ�������ؾͽ��������ʾ��false��ʾ��ʾ��true��ʾ��ǰ��������
		addFragment(cart_F);
		showFragment(cart_F);

		break;
		case R.id.iv_menu_4:
		// �ҵ��Ա�����
		if (user_F == null) {
		user_F = new User_F();
		// �жϵ�ǰ�����Ƿ����أ�������ؾͽ��������ʾ��false��ʾ��ʾ��true��ʾ��ǰ��������
		if (!user_F.isHidden()) {
		addFragment(user_F);
		showFragment(user_F);
		}
		} else {
		if (user_F.isHidden()) {
		showFragment(user_F);
		}
		}

		break;
		}

		// ���ð�ť��ѡ�к�δѡ����Դ
		for (int i = 0; i < bt_menu.length; i++) {
		bt_menu[i].setImageResource(select_off[i]);
		if (v.getId() == bt_menu_id[i]) {
		bt_menu[i].setImageResource(select_on[i]);
		}
		}
		}

/** ���Fragment **/
public void addFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
		ft.add(R.id.show_layout, fragment);
		ft.commit();
		}

/** ɾ��Fragment **/
public void removeFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
		ft.remove(fragment);
		ft.commit();
		}

/** ��ʾFragment **/
public void showFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
		// ����Fragment���л�����
		ft.setCustomAnimations(R.anim.cu_push_right_in, R.anim.cu_push_left_out);

		// �ж�ҳ���Ƿ��Ѿ�����������Ѿ���������ô�����ص�
		if (home_F != null) {
		ft.hide(home_F);
		}
		/*if (tao_F != null) {
		ft.hide(tao_F);
		}
		if (discover_F != null) {
		ft.hide(discover_F);
		}*/
		if (cart_F != null) {
		ft.hide(cart_F);
		}
		if (user_F != null) {
		ft.hide(user_F);
		}

		ft.show(fragment);
		ft.commitAllowingStateLoss();

		}

/** ���ذ�ť�ļ��� */
@Override
public void onBackPressed()
		{
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addCategory(Intent.CATEGORY_HOME);
		startActivity(intent);
		}

/** Fragment�Ļص����� */
@SuppressWarnings("unused")
private IBtnCallListener btnCallListener;

@Override
public void onAttachFragment(Fragment fragment) {
		try {
		btnCallListener = (IBtnCallListener) fragment;
		} catch (Exception e) {
		}

		super.onAttachFragment(fragment);
		}

/**
 * ��Ӧ��Fragment�д���������Ϣ
 */
@Override
public void transferMsg() {
		if (home_F == null) {
		home_F = new Home_F();
		addFragment(home_F);
		showFragment(home_F);
		} else {
		showFragment(home_F);
		}
		bt_menu[3].setImageResource(select_off[3]);
		bt_menu[0].setImageResource(select_on[0]);

		System.out.println("��Fragment�д���������Ϣ");
		}

		}
