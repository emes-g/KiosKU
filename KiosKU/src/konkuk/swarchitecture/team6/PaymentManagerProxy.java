package konkuk.swarchitecture.team6;

public class PaymentManagerProxy implements PaymentManagerIF {
	private int totalPrice;
	private int headcount;
	private Payment[] paymentList;
	private PaymentManager pManager;
	
	// 여타 Manager들과 달리 매 주문에서 새로 생성됨
	public PaymentManagerProxy(int totalPrice, int headcount) {
		this.totalPrice = totalPrice;
		this.headcount = headcount;
		this.paymentList = new Payment[Kiosk.MAX_HEADCOUNT];
		this.pManager = new PaymentManager(paymentList);
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getHeadcount() {
		return headcount;
	}
	public void setHeadcount(int headcount) {
		this.headcount = headcount;
	}
	public Payment[] getPaymentList() {
		return paymentList;
	}
	
	public int[] calculateAmount() {
		int[] prices = new int[headcount];
		int mod = totalPrice % (headcount * Kiosk.MIN_UNIT_COST);
		
		for(int i=0; i<headcount; i++) {
			prices[i] = totalPrice / (headcount * Kiosk.MIN_UNIT_COST) * Kiosk.MIN_UNIT_COST;
			if(mod > 0) {
				mod -= Kiosk.MIN_UNIT_COST;
				prices[i] += Kiosk.MIN_UNIT_COST;
			}		
		}
		
		return prices;
	}

	// payPreProcessing 역할 (Kiosk가 호출)
	public void pay() {
		int[] prices = calculateAmount();
		
		// 더치페이하는 인원들의 결제 목록 생성
		for(int i=0; i<headcount; i++) {
			PaymentFactory paymentFactory = PaymentFactory.getFactory();
			paymentList[i] = paymentFactory.createPayment(prices[i]);
		}
		
		// 더치페이하는 모든 인원들이 결제 가능한 상태인지 검사
		for(int i=0; i<headcount; i++) {
			if(!paymentList[i].payPreProcessing())
				return;
		}
		
		// 실제 결제 수행
		pManager.pay();
		return;
	}
}
