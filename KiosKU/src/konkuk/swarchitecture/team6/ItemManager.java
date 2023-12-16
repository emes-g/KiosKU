package konkuk.swarchitecture.team6;

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
	
	public boolean addItem(String title, int cost) {
		//String title = inputItemTitle();
		//int cost = inputItemCost();
		Item item = new Item(title, cost);
		
		if(isExistedItem(title, -1))
			return false;
		itemList.add(item);
		return true;
	}
	public boolean editItem(String title, int cost, int idx) {
		//int idx = inputItemIdx();
		Item item = itemList.get(idx);
		
		if(!isExistedItem(title, idx)) {
			item.setTitle(title);
			item.setCost(cost);
			return true;
		}
		return false;
	}
	public boolean deleteItem(int idx) {
		//int idx = inputItemIdx();
		
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
	
	public boolean isExistedItem(String title, int idx) {
		for(int i = 0; i < itemList.size(); i++) {
			if(itemList.get(i).getTitle().equals(title) && i != idx)
				return true;
		}
		return false;
	}
	
	public ArrayList<Item> getItemList() {
		return itemList;
	}
}
