package konkuk.swarchitecture.team6;

public class Main {
	public static void main(String[] args) {
//		Pair<String, Integer> p1 = new Pair<>("대머리", 3);
//		p1.print();
//		Pair<Item, Integer> p2 = new Pair<>(new Item("아이스크림", 500), 4);
//		p2.print();
		
//		ItemManager im = new ItemManager();
//		OrderManager om = new OrderManager(im);
//		om.makeOrder();
		
		CurrencyManager cm = new CurrencyManager();
		CardCompany.getCardDatabase().add(new CardInformation("aaaabbbbccccdddd", 2000, 0, 100));
		PaymentManagerProxy pmProxy = new PaymentManagerProxy(5500, 3);
		pmProxy.pay();
	}
}
