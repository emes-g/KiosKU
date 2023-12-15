package konkuk.swarchitecture.team6;

import java.util.ArrayList;

public class ReceiptManager {
	private OrderManager oManager;
	private PaymentManagerProxy pManager;
	private ArrayList<ArrayList<Receipt>> receiptList;
	
	public ReceiptManager(OrderManager oManager) {
		this.oManager = oManager;
		this.receiptList = new ArrayList<ArrayList<Receipt>>();
	}
	public ArrayList<ArrayList<Receipt>> getReceiptList() {
		return receiptList;
	}
	
	public void makeReceipts(PaymentManagerIF pManager) {
		this.pManager = (PaymentManagerProxy)pManager;
		
		for(int i=0; i<this.pManager.getHeadcount(); i++)
			makeReceipt(i);
	}
	
	public void makeReceipt(int idx) {
		ArrayList<Pair<Item, Integer>> basket = oManager.getLastOrder().getBasket();
		
		System.out.printf("========영수증========\n");
		System.out.printf("주문번호 : %d\n", oManager.getCurrentOrderNum());
		System.out.printf("결제 인원 수 : %d\n", pManager.getHeadcount());
		System.out.printf("결제자  순서 : %d\n", idx + 1);
		System.out.printf("--------------------\n");
		System.out.printf("<메뉴>\n");
		for(int i=0; i<basket.size(); i++) {
			Item item = basket.get(i).getX();
			Integer cnt = basket.get(i).getY();
			
			System.out.printf("%s: %d개 %d원\n", item.getTitle(), cnt, item.getCost() * cnt);
		}
		System.out.printf("합계 : %d원\n", oManager.getLastOrder().getTotalPrice());
		System.out.printf("--------------------\n");
		System.out.printf(pManager.getPaymentList()[idx].getPaymentInfo());
		System.out.printf("--------------------\n");
		System.out.printf("====================\n");
		System.out.println();
	}
	
	public ArrayList<Receipt> getLastReceipts() {
		return receiptList.get(receiptList.size() - 1);
	}
	
	public Receipt getLastReceipt(int idx) {
		return getLastReceipts().get(idx);
	}
}
