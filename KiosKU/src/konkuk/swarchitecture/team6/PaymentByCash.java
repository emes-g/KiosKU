package konkuk.swarchitecture.team6;

public class PaymentByCash extends Payment {
	private Currency insertedCurrency;
	private Currency change;
	
	public PaymentByCash(int price) {
		super(price);
		this.insertedCurrency = new Currency();
		this.change = new Currency();
	}
	
	@Override
	public boolean payPreProcessing() {
		insertedCurrency.init();
		if(insertedCurrency.getTotal() < price) {
			System.out.printf("투입금액이 부족합니다.\n");
			return false;
		}
		else if(!CurrencyManager.checkPayable(insertedCurrency.getTotal() - price, insertedCurrency, change)) {
			System.out.printf("키오스크에 화폐가 부족합니다.\n");
			return false;
		}
		System.out.printf("선승인 완료 | 선승인 금액 : %d\n", price);
		return true;
	}

	@Override
	public void pay() {
		CurrencyManager.changeCurrencyReserve(insertedCurrency, change);
		makePaymentInfo();
		// 거스름돈이 어떻게 구성되어있는지 출력
		System.out.printf("결제 완료 | 결제 금액 : %d\n", price);
	}
	
	@Override
	public void revert(int idx) {
		CurrencyManager.giveBackInsertedCurrency();
		System.out.printf("%d번째 결제자의 가승인 취소 | 취소 금액 : %d\n", idx + 1, price);
		insertedCurrency.print();
	}

	@Override
	public void makePaymentInfo() {
		paymentInfo = "<결제 정보>\n";
		paymentInfo += "결제 방식 : 현금결제\n";
		paymentInfo += "투입 금액 : " + insertedCurrency.getTotal() + "\n";
		paymentInfo += "결제 금액 : " + price + "\n";
		paymentInfo += " 거스름돈 : " + change.getTotal() + "\n";
	}
}
