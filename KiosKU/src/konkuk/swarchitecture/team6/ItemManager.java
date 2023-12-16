package konkuk.swarchitecture.team6;

import java.util.ArrayList;

public class ItemManager {
	private ArrayList<Item> itemList;

	public ItemManager() {
		itemList = ItemDAO.getItemFromItemDB();
	}

	public boolean addItem(String title, int cost) {
		Item item = new Item(title, cost);

		if(!ItemDAO.addItem(itemList, new Item(title, cost)))
			return false;
		itemList.add(item);
		return true;
	}

	public boolean editItem(String title, int cost, int idx) {
		Item oldItem = itemList.get(idx);
		Item newItem = new Item(title, cost);

		if(isExistedItem(title, idx))
			return false;
		else if(!ItemDAO.editItem(itemList, oldItem, newItem))
			return false;
		itemList.set(idx, newItem);
		return true;
	}

	public boolean deleteItem(int idx) {
		Item item = itemList.get(idx);

		if(!ItemDAO.deleteItem(itemList, item))
			return false;
		itemList.remove(item);
		return true;
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
