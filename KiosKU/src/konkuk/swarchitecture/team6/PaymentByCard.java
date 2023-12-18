package konkuk.swarchitecture.team6;

public class PaymentByCard extends Payment {
	KioskView view = KioskView.getInstance();

	private String insertedCardID;

	public PaymentByCard(int price, int num) {
		super(price, num);
		this.insertedCardID = "카드 정보없음\n";
	}

	@Override
	public boolean payPreProcessing() {
		view.showMessagePopup("카드 번호를 입력해주세요.", "결제자 " + num + " - 카드결제");
		insertedCardID = view.inputCardID();

		if(!CardCompany.checkPayable(insertedCardID, price)) {
			view.showMessagePopup("해당 카드로는 결제로 불가능합니다.", "결제자 " + num + " - 카드결제");
			return false;
		}
		payable = true;
		CardInformationDAO.updatePreAuthorized(insertedCardID, price, true);
		view.showMessagePopup("선승인 완료 | 선승인 금액 : " + price + "원" + "\n카드 번호 : " + protectCardInfo(insertedCardID),
				"결제자 " + num + " - 카드결제");
		
		return true;
	}

	@Override
	public void pay() {
		CardCompany.changeCardData(insertedCardID, price);
		makePaymentInfo();
		view.showMessagePopup("결제 완료 | 결제 금액 : " + price + "원" + "\n카드 번호 : " + protectCardInfo(insertedCardID),
				"결제자 " + num + " - 카드결제");
	}

	@Override
	public void revert(int idx) {
		if(!payable) {
			view.showMessagePopup("결제 실패", "결제자 " + num + " - 카드결제");
			return;
		}
		CardCompany.cancelPreAuthorization(insertedCardID, price);
		view.showMessagePopup("선승인 취소 | 카드 번호 : " + protectCardInfo(insertedCardID), "결제자 " + num + " - 카드결제");
	}

	@Override
	public void makePaymentInfo() {
		paymentInfo = "<결제 정보>\n";
		paymentInfo += "결제 방식 : 카드결제\n";
		paymentInfo += "카드 번호 : " + protectCardInfo(insertedCardID) + "\n";
		paymentInfo += "결제 금액 : " + price + "\n";
	}

	public String protectCardInfo(String cardID) {
		String protectedCardID = cardID.substring(0, 4);
		protectedCardID += "********";
		protectedCardID += cardID.substring(12, 16);

		return protectedCardID;
	}

	public String getInsertedCardID() {
		return insertedCardID;
	}
}