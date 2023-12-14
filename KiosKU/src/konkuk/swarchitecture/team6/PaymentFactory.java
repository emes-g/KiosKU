package konkuk.swarchitecture.team6;

import java.util.Scanner;

public abstract class PaymentFactory {
	private static Scanner scan = new Scanner(System.in);
	
	public static PaymentFactory getFactory() {
		String paymentMethod = getUserPaymentMethod();
		
		if(paymentMethod.equals("현금")) 
			return new PaymentByCashFactory();
		else if(paymentMethod.equals("카드"))
			return new PaymentByCardFactory();
		else
			return null;
	}
	
	public static String getUserPaymentMethod() {
		String paymentMethod;
		
		System.out.printf("결제 수단 입력 (현금, 카드) : ");
		paymentMethod = scan.nextLine();
		
		return paymentMethod;
	}
	
	protected abstract Payment createPayment(int price);
}
