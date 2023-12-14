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
		System.out.printf("결제 완료 | 결제 금액 : %d\n", price);
	}
}
