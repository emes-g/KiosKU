package konkuk.swarchitecture.team6;

public class PaymentByCardFactory extends PaymentFactory {

	@Override
	protected Payment createPayment(int price, int num) {
		return new PaymentByCard(price, num);
	}

}
