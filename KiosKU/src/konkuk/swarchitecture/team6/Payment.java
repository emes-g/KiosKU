package konkuk.swarchitecture.team6;

public abstract class Payment {
	protected int price;	// 1인 부담액
	private boolean payable;
	private boolean successed;
	private String receiptInfo;
	
	public Payment(int price) {
		this.price = price;
		this.payable = false;
		this.successed = false;
		this.receiptInfo = "영수증 정보없음\n";
	}
	
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

	public String getReceiptInfo() {
		return receiptInfo;
	}

	public void setReceiptInfo(String receiptInfo) {
		this.receiptInfo = receiptInfo;
	}

	public abstract boolean payPreProcessing();
	public abstract void pay();
	public abstract void revert();
}
