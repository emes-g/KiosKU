package konkuk.swarchitecture.team6;

import java.util.ArrayList;

public class OrderManager {
	KioskView view = KioskView.getInstance();

	private ArrayList<Order> orderList;
	private int currentOrderNum;
	private ItemManager iManager;

	public OrderManager(ItemManager iManager) {
		this.iManager = iManager; 
		orderList = new ArrayList<Order>();
		orderList.add(new Order(0));	// 깡통
		currentOrderNum = 0;
	}

	public void makeOrder() {
		Order basket = new Order(++currentOrderNum);

		switch(view.selectForHereOrToGo()) {
		case -1:	// 취소 키
			break;
		case 0:		// 매장
			basket.selectForHereOrToGo(true);
			view.orderWindow(this, basket);
			break;
		case 1:		// 포장
			basket.selectForHereOrToGo(false);
			view.orderWindow(this, basket);
			break;
		}
	}

	public Order getLastOrder() {
		return orderList.get(currentOrderNum);
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
	public ItemManager getItemManager() {
		return iManager;
	}
}
