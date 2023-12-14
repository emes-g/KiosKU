package konkuk.swarchitecture.team6;

import java.util.Scanner;

public class Currency {
	private static Scanner scan = new Scanner(System.in);
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
			units[i] = scan.nextInt();	
			clearBuffer();
			total += units[i] * Kiosk.UNITS[i];
		}
	}
	
	// a.copy(b) = copy b to a
	public void copy(Currency currency) {
		for(int i=0; i<Kiosk.CURRENCY_NUM; i++)
			units[i] = currency.getUnits()[i];
	}
	
	// a.add(b) = add b to a
	public void add(Currency currency) {
		for(int i=0; i<Kiosk.CURRENCY_NUM; i++)
			units[i] += currency.getUnits()[i];
	}
	
	// a.sub(b) = sub b from a
	public void sub(Currency currency) {
		for(int i=0; i<Kiosk.CURRENCY_NUM; i++)
			units[i] -= currency.getUnits()[i];
	}

	public int[] getUnits() {
		return units;
	}

	public int getTotal() {
		return total;
	}
	
	public static void clearBuffer() {
		scan.nextLine();
	}
}
