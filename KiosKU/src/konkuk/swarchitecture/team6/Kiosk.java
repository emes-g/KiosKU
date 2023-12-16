package konkuk.swarchitecture.team6;

import java.util.Scanner;

public class Kiosk {
	KioskView view = KioskView.getInstance();

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

	private static Kiosk instance;

	public static Kiosk getInstance() {
		if (instance == null) {
			instance = new Kiosk();
		}
		return instance;
	}

	public Kiosk() {}

	public void setView(KioskView view) {
		this.view = view;
	}

	public boolean init() {
		int ownerCode;
		
		while(true) {
			ownerCode = getOwnerCode();
			if(ownerCode == -1) {
				view.showMessagePopup("프로그램을 종료합니다.", "알림");
				System.exit(0);
			}
			else if(ownerCode == 0)
				view.showMessagePopup("비밀번호가 틀렸습니다.", "알림");
			else
				break;
		}

		this.iManager = new ItemManager();
		this.oManager = new OrderManager(iManager);
		this.rManager = new ReceiptManager(oManager);
		this.cManager = new CurrencyManager();

		return true;
	}

	public int getOwnerCode() {
		String pw = view.getPW(view);
		
		if(pw == null)	// 취소(X)버튼 눌렀을 때
			return -1;
		else if(!ownerPW.equals(pw))	// 비밀번호 틀렸을 때
			return 0;
		return 1;	// 비밀번호 맞았을 때
	}

	public void run() {
		view.mainWindow();
	}

	public void order() {
		oManager.makeOrder();
	}

	public void pay() {
		pManagerProxy = new PaymentManagerProxy(oManager.getLastOrder().getTotalPrice());

		if(!pManagerProxy.pay()) {
			view.showMessagePopup("결제에 실패했습니다.", "결제 실패");
			return;
		}

		rManager.makeReceipts(pManagerProxy);

		view.completedWindow(oManager.getCurrentOrderNum());
	}

	public boolean setItem() {
		int ownerCode;
		
		while(true) {
			ownerCode = getOwnerCode();
			
			if(ownerCode == -1)	// 취소 키
				return false;
			else if(ownerCode == 0)
				view.showMessagePopup("비밀번호가 틀렸습니다.", "알림");
			else {
				view.itemWindow(iManager);
				return true;
			}
		}
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
