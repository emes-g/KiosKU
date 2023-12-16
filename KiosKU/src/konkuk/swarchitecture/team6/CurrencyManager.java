package konkuk.swarchitecture.team6;

public class CurrencyManager {
	KioskView view = KioskView.getInstance();

	private static Currency currencyReserve = new Currency();
	private static Currency subCurrencyReserve = new Currency();
	private static Currency tiedReserve = new Currency();

	public CurrencyManager() {
		view.showMessagePopup("키오스크의 현금보유량을 입력해주세요.", "알림");
		currencyReserve.init();
		view.showMessagePopup("현금보유량 설정 완료.", "알림");
	}

	// change to Currency
	public static boolean checkPayable(int changeAmount, Currency insertedCurrency, Currency change) {		
		subCurrencyReserve.copy(currencyReserve);
		subCurrencyReserve.add(insertedCurrency);
		subCurrencyReserve.sub(tiedReserve);

		int rest = changeAmount;
		for(int i=0; i<Kiosk.CURRENCY_NUM; i++) {
			int cnt = rest / Kiosk.UNITS[i]; 
			if(cnt > 0) {
				if(cnt > subCurrencyReserve.getUnits()[i])
					cnt = subCurrencyReserve.getUnits()[i];
				rest -= cnt * Kiosk.UNITS[i];
				change.getUnits()[i] = cnt;
			}
		}
		subCurrencyReserve.updateTotal();
		change.updateTotal();
		if(rest != 0)
			return false;
		tiedReserve.add(change);
		return true;
	}

	public static void giveBackInsertedCurrency() {
		tiedReserve.bzero();
	}

	public static void changeCurrencyReserve(Currency insertedCurrency, Currency change) {
		currencyReserve.add(insertedCurrency);
		currencyReserve.sub(change);
	}
}
