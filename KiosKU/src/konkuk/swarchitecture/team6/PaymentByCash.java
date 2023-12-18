package konkuk.swarchitecture.team6;

public class PaymentByCash extends Payment {
	KioskView view = KioskView.getInstance();

	private Currency insertedCurrency;
	private Currency change;

	public PaymentByCash(int price, int num) {
		super(price, num);
		this.insertedCurrency = new Currency();
		this.change = new Currency();
	}

	@Override
	public boolean payPreProcessing() {
		view.showMessagePopup("투입할 금액을 입력해주세요.", "결제자 " + num + " - 현금결제");
		insertedCurrency.init();
		if(insertedCurrency.getTotal() < price) {
			view.showMessagePopup("투입금액이 부족합니다.", "결제자 " + num + " - 현금결제");
			return false;
		}
		else if(!CurrencyManager.checkPayable(insertedCurrency.getTotal() - price, insertedCurrency, change)) {
			view.showMessagePopup("키오스크에 화폐가 부족합니다.", "결제자 " + num + " - 현금결제");
			return false;
		}
		payable = true;
		view.showMessagePopup("선승인 완료 | 선승인 금액 : " + price + "원", "결제자 " + num + " - 현금결제");
		return true;
	}

	@Override
	public void pay() {
		CurrencyManager.changeCurrencyReserve(insertedCurrency, change);
		makePaymentInfo();
		view.showMessagePopup("결제 완료 | 결제 금액 : " + price + "원", "결제자 " + num + " - 현금결제");
	}

	@Override
	public void revert(int idx) {
		CurrencyManager.giveBackInsertedCurrency();
		if(!payable) 
			view.showMessagePopup("결제 실패", "결제자 " + num + " - 현금결제");
		else
			view.showMessagePopup("선승인 취소 | 취소 금액 : " + price + "원", "결제자 " + num + " - 현금결제");
		insertedCurrency.show("투입금액을 반환합니다.",  "결제자 " + num + " - 현금결제");
	}

	@Override
	public void makePaymentInfo() {
		paymentInfo = "<결제 정보>\n";
		paymentInfo += "결제 방식 : 현금결제\n";
		paymentInfo += "투입 금액 : " + insertedCurrency.getTotal() + "\n";
		paymentInfo += "결제 금액 : " + price + "\n";
		paymentInfo += "------------------------------------------------------------\n";
		paymentInfo += "<거스름돈>\n";
		for (int i = 0; i < Kiosk.CURRENCY_NUM; i++) {
			paymentInfo += (Kiosk.UNITS[i] + "원 X " + change.getUnits()[i] + "개\n");
		}
		paymentInfo += ("총 금액 : " + change.getTotal() + "원\n");
	}
}