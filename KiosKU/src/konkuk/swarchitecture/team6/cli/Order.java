package konkuk.swarchitecture.team6.cli;

import java.util.ArrayList;

public class Order {
	private ArrayList<Pair<Item, Integer>> basket;	// (상품, 해당 상품 주문 개수)
	private int totalCount;	// 장바구니에 담은 총 상품 개수
	private int totalPrice;
	private int orderNum;
	private boolean forHere;
	
	public Order(int orderNum) {
		this.orderNum = orderNum;
		basket = new ArrayList<Pair<Item, Integer>>();
		totalCount = 0;
		totalPrice = 0;
		forHere = false;
	}
	
	
	public void addItemToBasket(Item item, Integer count) {
		ArrayList<String> names = getItemNameInBasket();
		int index = getIndexOfItem(names, item);
		
		if(index == -1)	// 해당 상품이 장바구니에 없는 경우 
			basket.add(new Pair<>(item, count));
		else {	// 해당 상품이 장바구니에 있는 경우
			Pair<Item, Integer> p = basket.get(index);
			p.setY(p.getY() + count);
		}
		
		totalCount += count;
		totalPrice += (item.getCost() * count);
	}
	
	public void deleteItemFromBasket(Item item, Integer count) {
		ArrayList<String> names = getItemNameInBasket();
		int index = getIndexOfItem(names, item);
		
		// 해당 상품이 장바구니에 없는 경우
		if(index == -1) {
			System.out.printf("장바구니에 %s이(가) 존재하지 않습니다.\n", item.getTitle());
			return;
		}
		
		// 해당 상품이 장바구니에 있는 경우
		Pair<Item, Integer> p = basket.get(index);
		int num = p.getY() - count;
		if(num >= 0) {	// 개수가 충분한 경우
			p.setY(num);
			totalCount -= count;
			totalPrice -= (p.getX().getCost() * count);
		}
		else {	// 개수가 부족한 경우
			System.out.printf("%s는(은) 최대 %d개 뺄 수 있습니다.\n", item.getTitle(), p.getY());
			return;
		}
	}
	
	public ArrayList<String> getItemNameInBasket() {
		ArrayList<String> names = new ArrayList<String>();
		
		for(Pair<Item, Integer> p : basket) {
			names.add(p.getX().getTitle());
		}
		
		return names;
	}
	
	public int getIndexOfItem(ArrayList<String> names, Item item) {
		for(int i=0; i<names.size(); i++) {
			if(item.getTitle().equals(names.get(i)))
				return i;
		}
		return -1;
	}

	public ArrayList<Pair<Item, Integer>> getBasket() {
		return basket;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public boolean isForHere() {
		return forHere;
	}
	public void selectForHereOrToGo(boolean forHere) {
		this.forHere = forHere;
	}
}


