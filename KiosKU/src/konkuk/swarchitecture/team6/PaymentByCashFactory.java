package konkuk.swarchitecture.team6;

public class PaymentByCashFactory extends PaymentFactory {

	@Override
	protected Payment createPayment(int price) {
		return new PaymentByCash(price);
	}
	
}
