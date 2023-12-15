package konkuk.swarchitecture.team6;

import java.util.ArrayList;

public class OrderManager {
	private ArrayList<Order> orderList;
	private int currentOrderNum;
	private ItemManager iManager;
	
	public OrderManager(ItemManager iManager) {
		this.iManager = iManager; 
		orderList = new ArrayList<Order>();
		orderList.add(new Order(0));	// 깡통
		currentOrderNum = 0;
	}
	public ArrayList<Order> getOrderList() {
		return orderList;
	}
	public int getCurrentOrderNum() {
		return currentOrderNum;
	}
	public void setCurrentOrderNum(int currentOrderNum) {
		this.currentOrderNum = currentOrderNum;
	}
	
	public void makeOrder() {
		Order basket = new Order(++currentOrderNum);
		boolean forHere;
		
		System.out.printf("%d번째 주문을 시작합니다.\n", currentOrderNum);
		System.out.printf("매장 식사 | 포장 선택 (true, false): ");
		forHere = Kiosk.scan.nextBoolean();
		Kiosk.clearBuffer();
		basket.selectForHereOrToGo(forHere);
		
		while(true) {
			String command;
			
			System.out.printf("상품 관련 명령 선택 (add, delete, q): ");
			command = Kiosk.scan.nextLine();
			// 종료
			if(command.equals("q")) {
				System.out.printf("상품 추가 종료\n");
				break;
			}
			// add or delete
			addOrDeleteItem(basket, command);
		}
		// make order
		orderList.add(basket);
//		basket.printOrderInfo();
	}
	
	public boolean addOrDeleteItem(Order basket, String command) {
		String title;
		Integer count;

		System.out.printf("상품명 : ");
		title = Kiosk.scan.nextLine();
		System.out.printf("개수 : ");
		count = Kiosk.scan.nextInt();
		Kiosk.clearBuffer();

		for(Item item : iManager.getItemList()) {
			if(title.equals(item.getTitle())) {
				if(command.equals("add"))	// 장바구니에 상품 추가
					basket.addItemToBasket(item, count);
				else	// 장바구니에서 상품 삭제
					basket.deleteItemFromBasket(item, count);
				return true;
			}
		}
		System.out.printf("아이템 목록에 %s이(가) 존재하지 않습니다.\n", title);
		return false;
	}
	
	public Order getLastOrder() {
		return orderList.get(currentOrderNum);
	}
}
