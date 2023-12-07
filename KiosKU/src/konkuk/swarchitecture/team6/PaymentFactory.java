package konkuk.swarchitecture.team6;

public abstract class PaymentFactory {
	
	public Payment newInstance() {
		Payment p = createPayment();
		return p;
	}
	
	protected abstract Payment createPayment();
}
