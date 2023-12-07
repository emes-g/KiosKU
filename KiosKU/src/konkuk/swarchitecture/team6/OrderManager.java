package konkuk.swarchitecture.team6;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderManager {
	private ArrayList<Order> orderList;
	private int currentOrderNum;
	private ItemManager iManager;
	private Scanner scan;
	
	public OrderManager(ItemManager iManager) {
		this.iManager = iManager; 
		orderList = new ArrayList<Order>();
		currentOrderNum = 0;
		scan = new Scanner(System.in);
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
		forHere = scan.nextBoolean();
		clearBuffer();
		basket.selectForHereOrToGo(forHere);
		
		while(true) {
			String command;
			boolean isSuccess = false;
			
			System.out.printf("상품 관련 명령 선택 (add, delete, q): ");
			command = scan.nextLine();
			// 종료
			if(command.equals("q")) {
				System.out.printf("상품 추가 종료\n");
				break;
			}
			// add or delete
			String title;
			Integer count;

			System.out.printf("상품명 : ");
			title = scan.nextLine();
			System.out.printf("개수 : ");
			count = scan.nextInt();
			clearBuffer();

			for(Item item : iManager.getItemList()) {
				if(title.equals(item.getTitle())) {
					if(command.equals("add"))	// 장바구니에 상품 추가
						basket.addItemToBasket(item, count);
					else	// 장바구니에서 상품 삭제
						basket.deleteItemFromBasket(item, count);
					isSuccess = true;
					break;
				}
			}
			if(!isSuccess) {
				System.out.printf("아이템 목록에 %s이(가) 존재하지 않습니다.\n", title);
			}
		}
		// make order
		orderList.add(basket);
		basket.printOrderInfo();
	}
	
	public void clearBuffer() {
		scan.nextLine();
	}
}
