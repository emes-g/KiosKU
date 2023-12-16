package konkuk.swarchitecture.team6;

public class Main {
	public static void main(String[] args) {
		Kiosk k = Kiosk.getInstance();
		if(!k.init())
			return;
		k.run();
	}
}