package com.Online_Bazar.modal;

public class Order extends product {
	private int orderId;
	private int uid;
	private int quantity;
	private String date;
	
	public Order() {}

	public Order(int id, String name, String category, double price, String img, int orderId, int uid, int quantity,
			String date) {
		super(id, name, category, price, img);
		this.orderId = orderId;
		this.uid = uid;
		this.quantity = quantity;
		this.date = date;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
