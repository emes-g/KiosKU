package konkuk.swarchitecture.team6.gui;

public class Main {
	public static void main(String[] args) {		
		Kiosk k = new Kiosk();
		if(!k.init())
			return;
		k.order();
		k.pay();
	}
}