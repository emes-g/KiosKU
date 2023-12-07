package konkuk.swarchitecture.team6;

import java.util.ArrayList;

public class Kiosk {
	private ArrayList<String> pList;
	private int[] currencyReserve;
	private Order basket;
	private ItemManager iManager;
	private OrderManager oManager;
	private PaymentManager pManagerProxy;
	private ReceiptManager rManager;
	private boolean isManagerMode;
	private String ownerPW;
	private int[][] changeArray;
	
	public Kiosk(ArrayList<String> pList, int[] currencyReserve, Order basket, ItemManager iManager,
			OrderManager oManager, PaymentManager pManagerProxy, ReceiptManager rManager, boolean isManagerMode,
			String ownerPW, int[][] changeArray) {
		this.pList = pList;
		this.currencyReserve = currencyReserve;
		this.basket = basket;
		this.iManager = iManager;
		this.oManager = oManager;
		this.pManagerProxy = pManagerProxy;
		this.rManager = rManager;
		this.isManagerMode = isManagerMode;
		this.ownerPW = ownerPW;
		this.changeArray = changeArray;
	}

	public ArrayList<String> getpList() {
		return pList;
	}

	public int[] getCurrencyReserve() {
		return currencyReserve;
	}

	public Order getBasket() {
		return basket;
	}

	public ItemManager getiManager() {
		return iManager;
	}

	public OrderManager getoManager() {
		return oManager;
	}
	
	public PaymentManager getpManagerProxy() {
		return pManagerProxy;
	}

	public ReceiptManager getrManager() {
		return rManager;
	}

	public boolean order() {
		// 추후 구현
		return false;
	}

	public boolean pay() {
		// 추후 구현
		return false;
	}
	
	public boolean checkPayable() {
		// 추후 구현
		return false;
	}
	
	public boolean setItem(int option) {
		// 추후 구현
		return false;
	}
}
