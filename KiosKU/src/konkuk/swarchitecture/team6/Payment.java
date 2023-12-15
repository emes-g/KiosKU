package konkuk.swarchitecture.team6;

public abstract class Payment {
	protected int price;	// 1인 부담액
	protected String paymentInfo;	// 결제 정보
	private boolean payable;
	private boolean successed;
	
	public Payment(int price) {
		this.price = price;
		this.payable = false;
		this.successed = false;
		this.paymentInfo = "영수증 정보없음\n";
	}
	
	public abstract boolean payPreProcessing();
	public abstract void pay();
	public abstract void revert(int idx);
	public abstract void makePaymentInfo();
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isPayable() {
		return payable;
	}
	public void setPayable(boolean payable) {
		this.payable = payable;
	}
	public boolean isSuccessed() {
		return successed;
	}
	public void setSuccessed(boolean successed) {
		this.successed = successed;
	}
	public String getPaymentInfo() {
		return paymentInfo;
	}
	public void setPaymentInfo(String paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
}
