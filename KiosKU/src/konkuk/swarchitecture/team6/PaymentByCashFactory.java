package konkuk.swarchitecture.team6;

public class PaymentByCashFactory extends PaymentFactory {

	@Override
	protected Payment createPayment() {
		// 추후 구현
		return new PaymentByCash();
	}
	
}
