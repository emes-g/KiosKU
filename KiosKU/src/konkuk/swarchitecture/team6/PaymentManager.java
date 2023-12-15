package konkuk.swarchitecture.team6;

public class PaymentManager implements PaymentManagerIF {
	private Payment[] paymentList;
	
	public PaymentManager(Payment[] paymentList) {
		this.paymentList = paymentList;
	}

	@Override
	public void pay() {
		for(int i=0; i<paymentList.length; i++) {
			if(paymentList[i] == null)
				return;
			paymentList[i].pay();
		}
	}
}
