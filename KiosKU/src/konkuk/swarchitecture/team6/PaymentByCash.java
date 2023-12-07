package konkuk.swarchitecture.team6;

public class PaymentByCash extends Payment {
	private int[] insertedCurrency;
	private int totalInsertedCurrency;
	private int[] changeCurrency;
	
	public PaymentByCash() {
		super();
		this.insertedCurrency = new int[8];
		this.totalInsertedCurrency = 0;
		this.changeCurrency = new int[8];
	}
	
	public PaymentByCash(int totalPrice, boolean payable, boolean successed, 
			String receiptInfo, int[] insertedCurrency, 
			int totalInsertedCurrency, int[] changeCurrency) {
		super(totalPrice, payable, successed, receiptInfo);
		this.insertedCurrency = insertedCurrency;
		this.totalInsertedCurrency = totalInsertedCurrency;
		this.changeCurrency = changeCurrency;
	}

	@Override
	public boolean payPreProcessing() {
		// 추후 구현
		return false;
	}

	@Override
	public boolean pay() {
		// 추후 구현
		return false;
	}
	
	
}
