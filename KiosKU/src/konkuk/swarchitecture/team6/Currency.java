package konkuk.swarchitecture.team6;

public class Currency {
	KioskView view = KioskView.getInstance();

	private int total;
	private int[] units;

	public Currency() {
		this.units = new int[Kiosk.CURRENCY_NUM];
		this.total = 0;
	}

	// setUnits & setTotal
	public void init() {
		int[] input = null;
		while(input == null)
			input = view.inputCurrency();

		for(int i=0; i<Kiosk.CURRENCY_NUM; i++)
			units[i] = input[i];
		updateTotal();
	}

	public void bzero() {
		for(int i=0; i<Kiosk.CURRENCY_NUM; i++)
			units[i] = 0;
		total = 0;
	}

	// a.copy(b) = copy b to a
	public void copy(Currency currency) {
		for(int i=0; i<Kiosk.CURRENCY_NUM; i++)
			units[i] = currency.units[i];
		total = currency.total;
	}

	// a.add(b) = add b to a
	public void add(Currency currency) {
		for(int i=0; i<Kiosk.CURRENCY_NUM; i++)
			units[i] += currency.units[i];
		total += currency.total;
	}

	// a.sub(b) = sub b from a
	public void sub(Currency currency) {
		for(int i=0; i<Kiosk.CURRENCY_NUM; i++)
			units[i] -= currency.getUnits()[i];
		total -= currency.total;
	}

	public void show(String message, String title) {
		view.displayCurrency(this, message, title);
	}

	public void updateTotal() {
		total = 0;
		for(int i=0; i<Kiosk.CURRENCY_NUM; i++)
			total += units[i] * Kiosk.UNITS[i];
	}

	public int[] getUnits() {
		return units;
	}
	public int getTotal() {
		return total;
	}
}
