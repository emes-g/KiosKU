package konkuk.swarchitecture.team6;

public class Currency {
	private int total;
	private int[] units;
	
	public Currency() {
		this.units = new int[Kiosk.CURRENCY_NUM];
		this.total = 0;
	}
	
	// setUnits & setTotal
	public void init() {
		for(int i=0; i<Kiosk.CURRENCY_NUM; i++) {
			System.out.printf("%d원 개수 : ", Kiosk.UNITS[i]);
			units[i] = Kiosk.scan.nextInt();	
			Kiosk.clearBuffer();
		}
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
	
	public void print() {
		for(int i=0; i<Kiosk.CURRENCY_NUM; i++) {
			if(i == Kiosk.CURRENCY_NUM / 2)
				System.out.println();
			System.out.printf("%d원 : %d개\t", Kiosk.UNITS[i], units[i]);
		}
		System.out.println();
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
