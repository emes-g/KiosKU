package konkuk.swarchitecture.team6;

public abstract class PaymentFactory {
	static KioskView view = KioskView.getInstance();
	
	public static PaymentFactory getFactory(int num) {
		String paymentMethod = getUserPaymentMethod(num);
		
		if(paymentMethod.equals("현금")) 
			return new PaymentByCashFactory();
		else if(paymentMethod.equals("카드"))
			return new PaymentByCardFactory();
		else
			return null;
	}
	
	public static String getUserPaymentMethod(int num) {
		String paymentMethod = null;
		
		System.out.printf("결제 수단 입력 (현금, 카드) : ");
		
		//paymentMethod = Kiosk.scan.nextLine();
		while(paymentMethod == null) {
			paymentMethod = view.selectPaymentMethod(num);	
		}
		return paymentMethod;
	}
	
	protected abstract Payment createPayment(int price, int num);
}
