package konkuk.swarchitecture.team6;

public abstract class Payment {
	protected int price;	// 1인 부담액
	protected boolean payable;	// 결제 가능 여부
	protected String paymentInfo;	// 결제 정보
	protected int num;	// 결제자 번호

	public Payment(int price, int num) {
		this.price = price;
		this.payable = false;
		this.paymentInfo = "영수증 정보없음\n";
		this.num = num;
	}

	public abstract boolean payPreProcessing();
	public abstract void pay();
	public abstract void revert(int idx);
	public abstract void makePaymentInfo();

	public int getPrice() {
		return price;
	}
	public boolean isPayable() {
		return payable;
	}
	public String getPaymentInfo() {
		return paymentInfo;
	}
	public int getNum() {
		return num;
	}
}