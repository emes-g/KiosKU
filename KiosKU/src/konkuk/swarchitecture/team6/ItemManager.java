package konkuk.swarchitecture.team6;

import java.util.ArrayList;

public class ItemManager {
	private ArrayList<Item> itemList;
	private int count;
	public ArrayList<Item> getItemList() {
		return itemList;
	}
	public ItemManager(ArrayList<Item> itemList, int count) {
		this.itemList = itemList;
		this.count = count;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
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
