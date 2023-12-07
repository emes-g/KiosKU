package konkuk.swarchitecture.team6;

import java.util.ArrayList;

public class Order {
	private ArrayList<Pair<Item, Integer>> basket;	// (상품, 해당 상품 주문 개수)
	private int count;	// 장바구니에 담은 총 상품 개수
	private int totalPrice;
	private int orderNum;
	private boolean forHere;
	
	public Order(int orderNum) {
		this.orderNum = orderNum;
		basket = new ArrayList<Pair<Item, Integer>>();
		count = 0;
		totalPrice = 0;
		forHere = false;
	}
	
	public ArrayList<Pair<Item, Integer>> getBasket() {
		return basket;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public boolean isForHere() {
		return forHere;
	}
}


