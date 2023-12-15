package konkuk.swarchitecture.team6.gui;

public class Item {
	private String title;
	private int cost;
	
	public Item(String title, int cost) {
		this.title = title;
		this.cost = cost;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
}
