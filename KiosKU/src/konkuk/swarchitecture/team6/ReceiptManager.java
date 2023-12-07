package konkuk.swarchitecture.team6;

import java.util.ArrayList;

public class ReceiptManager {
	private OrderManager oManager;
	private PaymentManagerIF pManager;
	private ArrayList<ArrayList<Receipt>> receiptList;
	
	public ReceiptManager(OrderManager oManager, PaymentManagerIF pManager, 
			ArrayList<ArrayList<Receipt>> receiptList) {
		this.oManager = oManager;
		this.pManager = pManager;
		this.receiptList = receiptList;
	}
	public ArrayList<ArrayList<Receipt>> getReceiptList() {
		return receiptList;
	}
}
