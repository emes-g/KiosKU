package konkuk.swarchitecture.team6;

import java.util.ArrayList;

public class Kiosk {
	static final int MAX_HEADCOUNT = 6;	// 최대 결제 가능 인원 수
	static final int MIN_UNIT_COST = 100;	// 상품 최소 금액 단위
	static final int CURRENCY_NUM = 8;	// 화폐 종류 수 
	static final int[] UNITS = {50000, 10000, 5000, 1000, 500, 100, 50, 10};	// 현금 단위
	private ArrayList<String> pList;
	private Order basket;
	private ItemManager iManager;
	private OrderManager oManager;
	private PaymentManager pManagerProxy;
	private ReceiptManager rManager;
	private CurrencyManager cManager;
	private boolean isManagerMode;
	private String ownerPW;
	
	public Kiosk(ArrayList<String> pList, Order basket, ItemManager iManager, OrderManager oManager, 
			PaymentManager pManagerProxy, ReceiptManager rManager, CurrencyManager cManager, 
			boolean isManagerMode, String ownerPW) {
		// 키오스크 키면 현금 보유량부터 입력받기
		this.pList = pList;
		this.basket = basket;
		this.iManager = iManager;
		this.oManager = oManager;
		this.pManagerProxy = pManagerProxy;
		this.rManager = rManager;
		this.cManager = cManager;
		this.isManagerMode = isManagerMode;
		this.ownerPW = ownerPW;
	}

	public ArrayList<String> getpList() {
		return pList;
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
	
	public CurrencyManager getcManager() {
		return cManager;
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
