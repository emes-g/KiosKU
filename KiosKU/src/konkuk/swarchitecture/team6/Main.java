package konkuk.swarchitecture.team6;

public class Main {
	public static void main(String[] args) {
//		Pair<String, Integer> p1 = new Pair<>("대머리", 3);
//		p1.print();
//		Pair<Item, Integer> p2 = new Pair<>(new Item("아이스크림", 500), 4);
//		p2.print();
		
		CurrencyManager cm = new CurrencyManager();
		ItemManager im = new ItemManager();
		
		OrderManager om = new OrderManager(im);
		om.makeOrder();
		
		CardCompany.getCardDatabase().add(new CardInformation("aaaabbbbccccdddd", 1000000, 0, 100));
//		PaymentManagerProxy pmProxy = new PaymentManagerProxy(5500);
		PaymentManagerIF pmProxy = new PaymentManagerProxy(om.getLastOrder().getTotalPrice());
		pmProxy.pay();
		ReceiptManager rm = new ReceiptManager(om);
		rm.makeReceipts(pmProxy);
	}
}
