package konkuk.swarchitecture.team6;

public class PaymentManager implements PaymentManagerIF {
	private int totalPrice;
	private int headcount;
	private Payment[] paymentList;
	
	public PaymentManager(int totalPrice, int headcount, Payment[] paymentList) {
		this.totalPrice = totalPrice;
		this.headcount = headcount;
		this.paymentList = paymentList;
	}

	@Override
	public boolean pay() {
		// payPreProcessing 역할
		// 추후 구현
		return false;
	}
}
