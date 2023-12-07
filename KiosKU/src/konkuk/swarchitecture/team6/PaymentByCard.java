package konkuk.swarchitecture.team6;

public class PaymentByCard extends Payment {
	private String insertedCardID;

	public PaymentByCard() {
		super();
		insertedCardID = "카드 정보없음\n";
	}
	
	public PaymentByCard(int totalPrice, boolean payable, boolean successed, 
			String receiptInfo, String insertedCardID) {
		super(totalPrice, payable, successed, receiptInfo);
		this.insertedCardID = insertedCardID;
	}

	public String getInsertedCardID() {
		return insertedCardID;
	}

	public void setInsertedCardID(String insertedCardID) {
		this.insertedCardID = insertedCardID;
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
