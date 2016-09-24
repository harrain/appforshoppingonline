package com.jarvis.mytaobao;

import android.widget.ImageView;

public class Goods {

	private int image;
	private String pro;//¼ò½é
	private int price;//¼ÛÇ®
	
	public Goods() {
		super();
	}

	public Goods(int image, String pro, int price) {
		super();
		this.image = image;
		this.pro = pro;
		this.price = price;
	}


	public int getImage() {return  image;}
	public void setImage() { this.image = image; }

	public String getPro() {
		return pro;
	}

	public void setPro(String pro) {
		this.pro = pro;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Goods [pro=" + pro + ", price=" + price
				+ "]";
	}
	
	
	
}
