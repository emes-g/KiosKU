package konkuk.swarchitecture.team6;

public abstract class Payment {
	private int totalPrice;
	private boolean payable;
	private boolean successed;
	private String receiptInfo;
	
	public Payment() {
		this.totalPrice = 0;
		this.payable = false;
		this.successed = false;
		this.receiptInfo = "영수증 정보없음\n";
	}
	
	public Payment(int totalPrice, boolean payable, boolean successed, String receiptInfo) {
		this.totalPrice = totalPrice;
		this.payable = payable;
		this.successed = successed;
		this.receiptInfo = receiptInfo;
	}
	
	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
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
	public abstract boolean pay();
}
