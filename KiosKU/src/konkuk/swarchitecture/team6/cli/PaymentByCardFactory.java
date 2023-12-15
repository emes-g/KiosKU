package konkuk.swarchitecture.team6.cli;

public class PaymentByCardFactory extends PaymentFactory {

	@Override
	protected Payment createPayment(int price) {
		return new PaymentByCard(price);
	}

}