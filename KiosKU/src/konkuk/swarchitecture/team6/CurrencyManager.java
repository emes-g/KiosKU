package konkuk.swarchitecture.team6;

import java.util.Scanner;

public class CurrencyManager {
	private Scanner scan = new Scanner(System.in);
	private static Currency currencyReserve = new Currency();
	private static Currency subCurrencyReserve = new Currency();

	public CurrencyManager() {
		currencyReserve.init();
	}
	
	// change to Currency
	public static boolean checkPayable(int changeAmount, Currency insertedCurrency, Currency change) {
		
		subCurrencyReserve.copy(currencyReserve);
		subCurrencyReserve.add(insertedCurrency);
		
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
		if(rest != 0)
			return false;
		return true;
	}
	
	public static void changeCurrencyReserve(Currency insertedCurrency, Currency change) {
		currencyReserve.add(insertedCurrency);
		currencyReserve.sub(change);
	}
}
