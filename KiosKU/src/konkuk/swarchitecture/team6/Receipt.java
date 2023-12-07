package konkuk.swarchitecture.team6;

public class Receipt {
	private Order order;
	private Payment payment;
	
	public Receipt(Order order, Payment payment) {
		this.order = order;
		this.payment = payment;
	}
}
