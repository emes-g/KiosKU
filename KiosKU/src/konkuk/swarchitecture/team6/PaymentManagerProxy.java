package konkuk.swarchitecture.team6;

public class PaymentManagerProxy implements PaymentManagerIF {
	private int totalPrice;
	private int headcount;
	private Payment[] paymentList;
	private PaymentManager pManager;
	private boolean allPayable;
	
	public PaymentManagerProxy(int totalPrice, int headcount, Payment[] paymentList, 
			PaymentManager pManager, boolean allPayable) {
		this.totalPrice = totalPrice;
		this.headcount = headcount;
		this.paymentList = paymentList;
		this.pManager = pManager;
		this.allPayable = allPayable;
	}

	public boolean isAllPayable() {
		return allPayable;
	}
	
	public int[] calculateAmount() {
		// 추후 구현
		int[] temp = {1, 2};
		return temp;
	}

	@Override
	public boolean pay() {
		// payPreProcessing 역할
		// 추후 구현
		return false;
	}
}
