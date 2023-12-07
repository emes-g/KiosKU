package konkuk.swarchitecture.team6;

public class PaymentByCardFactory extends PaymentFactory {

	@Override
	protected Payment createPayment() {
		// 추후 구현
		return new PaymentByCard();
	}

}
