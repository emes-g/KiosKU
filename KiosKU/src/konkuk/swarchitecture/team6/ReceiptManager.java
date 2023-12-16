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
	
	public void makeReceipts(PaymentManagerIF pManager) {
		ArrayList<Receipt> receipts = new ArrayList<Receipt>();
		
		this.pManager = (PaymentManagerProxy)pManager;
		for(int i=0; i<this.pManager.getHeadcount(); i++)
			makeReceipt(i, receipts);
		receiptList.add(receipts);
	}
	
	public void makeReceipt(int idx, ArrayList<Receipt> receipts) {
		ArrayList<Pair<Item, Integer>> basket = oManager.getLastOrder().getBasket();
		String receiptInfo;
		Receipt receipt;
		
		receiptInfo = String.format("========영수증========\n");
		receiptInfo += String.format("주문번호 : %d\n", oManager.getCurrentOrderNum());
		receiptInfo += String.format("결제 인원 수 : %d\n", pManager.getHeadcount());
		receiptInfo += String.format("결제자  순서 : %d\n", idx + 1);
		receiptInfo += String.format("--------------------\n");
		receiptInfo += String.format("<메뉴>\n");
		for(int i=0; i<basket.size(); i++) {
			Item item = basket.get(i).getX();
			Integer cnt = basket.get(i).getY();
			
			receiptInfo += String.format("%s: %d개 %d원\n", item.getTitle(), cnt, item.getCost() * cnt);
		}
		receiptInfo += String.format("합계 : %d원\n", oManager.getLastOrder().getTotalPrice());
		receiptInfo += String.format("--------------------\n");
		receiptInfo += String.format(pManager.getPaymentList()[idx].getPaymentInfo());
		receiptInfo += String.format("--------------------\n");
		receiptInfo += String.format("====================\n\n");
		
		receipt = new Receipt(receiptInfo);
		receipts.add(receipt);
		System.out.printf(receipt.getReceiptInfo());
	}
	
	public ArrayList<Receipt> getLastReceipts() {
		return receiptList.get(receiptList.size() - 1);
	}
	
	public Receipt getLastReceipt(int idx) {
		return getLastReceipts().get(idx);
	}
	
	public ArrayList<ArrayList<Receipt>> getReceiptList() {
		return receiptList;
	}
}
