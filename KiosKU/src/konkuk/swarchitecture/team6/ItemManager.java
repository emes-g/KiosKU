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
	public ArrayList<Item> getItemList() {
		return itemList;
	}
	
	public boolean addItem(Item item) {
		// 내부 나중에
		return true;
	}
	public boolean editItem(int num) {	// num == index
		// 내부 나중에
		return true;
	}
	public boolean deleteItem(int num) {
		// 내부 나중에
		return true;
	}
}
