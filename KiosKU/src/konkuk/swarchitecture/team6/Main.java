package konkuk.swarchitecture.team6;

public class Main {
	public static void main(String[] args) {
		System.out.println("push test\n");
		
		Kiosk k = new Kiosk();
		if(!k.init())
			return;
		k.order();
		k.pay();
	}
}