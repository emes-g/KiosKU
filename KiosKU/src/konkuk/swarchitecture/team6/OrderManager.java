package konkuk.swarchitecture.team6;

import java.util.ArrayList;

public class OrderManager {
	private ArrayList<Order> orderList;
	private int currentOrderNum;
	
	public OrderManager(ArrayList<Order> orderList, int currentOrderNum) {
		super();
		this.orderList = orderList;
		this.currentOrderNum = currentOrderNum;
	}
	public ArrayList<Order> getOrderList() {
		return orderList;
	}
	public int getCurrentOrderNum() {
		return currentOrderNum;
	}
	public void setCurrentOrderNum(int currentOrderNum) {
		this.currentOrderNum = currentOrderNum;
	}
}
