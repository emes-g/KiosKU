package konkuk.swarchitecture.team6.cli;

import java.util.ArrayList;

public class ItemManager {
	private ArrayList<Item> itemList;
	public ItemManager() {
		// 추후 수정
		itemList = new ArrayList<Item>();
		itemList.add(new Item("맥주", 3000));
		itemList.add(new Item("케이크", 25000));
		itemList.add(new Item("치킨", 20000));
	}
	
	public boolean addItem() {
		String title = inputItemTitle();
		int cost = inputItemCost();
		Item item = new Item(title, cost);
		
		if(isExistedItem(title))
			return false;
		
		itemList.add(item);
		return true;
	}
	public boolean editItem() {
		int idx = inputItemIdx();
		Item item = itemList.get(idx);
		
		while(true) {
			String title = inputItemTitle();
			
			if(!isExistedItem(title)) {
				item.setTitle(inputItemTitle());
				break;
			}
			System.out.printf("중복된 이름입니다. 다른 이름을 입력해주세요\n");
		}
		item.setCost(inputItemCost());
		
		return true;
	}
	public boolean deleteItem() {
		int idx = inputItemIdx();
		
		itemList.remove(idx);
		
		return true;
	}
	public int inputItemIdx() {
		int idx;

		System.out.printf("상품 번호 : ");
		idx = Kiosk.scan.nextInt();
		Kiosk.clearBuffer();

		return idx;
	}
	public String inputItemTitle() {
		String title;
		
		System.out.printf("상품명 : ");
		title = Kiosk.scan.nextLine();
		
		return title;
	}
	public int inputItemCost() {
		int cost;

		System.out.printf("상품 가격 : ");
		cost = Kiosk.scan.nextInt();
		Kiosk.clearBuffer();

		return cost;
	}
	
	public boolean isExistedItem(String title) {
		for(Item it : itemList) {
			if(it.getTitle().equals(title))
				return true;
		}
		return false;
	}
	
	public ArrayList<Item> getItemList() {
		return itemList;
	}
}
