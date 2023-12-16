package konkuk.swarchitecture.team6;

import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

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
	private boolean isManagerMode;
	
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
		if(!isOwner()) {
			//System.out.printf("관리자가 아닙니다.");
			view.showMessagePopup("관리자가 아닙니다.", "알림");
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
		
		//System.out.printf("키오스크 비밀번호 입력 : ");
		//pw = scan.nextLine();
		
		pw = view.getPW(view);
		
		return ownerPW.equals(pw);
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
			//System.out.printf("결제 실패\n");
			view.showMessagePopup("결제에 실패했습니다.", "결제 실패");
			return;
		}
		
		rManager.makeReceipts(pManagerProxy);
		
		view.completedWindow(oManager.getCurrentOrderNum());
	}
	
	public boolean setItem() {
		if(!isOwner()) {
			System.out.printf("접근 권한이 없습니다.\n");
			view.showMessagePopup("비밀번호가 다릅니다.", "알림");
			return false;
		}
	
		//switch (option) {
		//case 1:
		//	iManager.addItem();
		//	return true;
		//case 2:
		//	iManager.editItem();
		//	return true;
		//case 3:
		//	iManager.deleteItem();
		//	return true;
		//}
		
		view.itemWindow(iManager);
		
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
