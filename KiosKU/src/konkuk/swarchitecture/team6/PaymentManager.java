package konkuk.swarchitecture.team6;

public class PaymentManager implements PaymentManagerIF {
	private Payment[] paymentList;

	public PaymentManager(Payment[] paymentList) {
		this.paymentList = paymentList;
	}

	@Override
	public boolean pay() {
		for(int i=0; i<paymentList.length; i++) {
			if(paymentList[i] == null) {
				if(i == 0)
					return false;
				return true;
			}
			paymentList[i].pay();
		}
		return true;
	}
}
