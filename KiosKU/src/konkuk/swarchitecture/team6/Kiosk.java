package konkuk.swarchitecture.team6;

import java.util.Scanner;

public class Kiosk {
	static final int MAX_HEADCOUNT = 6;	// 최대 결제 가능 인원 수
	static final int MIN_UNIT_COST = 100;	// 상품 최소 금액 단위
	static final int CURRENCY_NUM = 8;	// 화폐 종류 수 
	static final int[] UNITS = {50000, 10000, 5000, 1000, 500, 100, 50, 10};	// 현금 단위
	static Scanner scan = new Scanner(System.in);
	private static String ownerPW = "1234";
	private ItemManager iManager;
	private OrderManager oManager;
	private PaymentManagerIF pManagerProxy;
	private ReceiptManager rManager;
	private CurrencyManager cManager;
	private boolean isManagerMode;
	
	public Kiosk() {}
	
	public boolean init() {
		if(!isOwner()) {
			System.out.printf("관리자가 아닙니다.");
			return false;
		}
		this.iManager = new ItemManager();
		this.oManager = new OrderManager(iManager);
		this.rManager = new ReceiptManager(oManager);
		this.cManager = new CurrencyManager();
		this.isManagerMode = true;
		
		return true;
	}
	
	public boolean isOwner() {
		String pw;
		
		System.out.printf("키오스크 비밀번호 입력 : ");
		pw = scan.nextLine();
		
		return ownerPW.equals(pw);
	}

	public void order() {
		oManager.makeOrder();
	}

	public void pay() {
		pManagerProxy = new PaymentManagerProxy(oManager.getLastOrder().getTotalPrice());
		
		if(!pManagerProxy.pay()) {
			System.out.printf("결제 실패\n");
			return;
		}
		
		rManager.makeReceipts(pManagerProxy);
	}
	
	public boolean setItem(int option) {
		if(!isManagerMode) {
			System.out.printf("접근 권한이 없습니다.\n");
			return false;
		}
	
		switch (option) {
		case 1:
			iManager.addItem();
			return true;
		case 2:
			iManager.editItem();
			return true;
		case 3:
			iManager.deleteItem();
			return true;
		}
		
		return false;
	}
	
	public static void clearBuffer() {
		scan.nextLine();
	}
	
	public ItemManager getiManager() {
		return iManager;
	}
	public OrderManager getoManager() {
		return oManager;
	}
	public PaymentManagerIF getpManagerProxy() {
		return pManagerProxy;
	}
	public ReceiptManager getrManager() {
		return rManager;
	}
	public CurrencyManager getcManager() {
		return cManager;
	}
}
