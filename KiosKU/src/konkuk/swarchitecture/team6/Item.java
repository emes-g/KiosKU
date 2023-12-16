package konkuk.swarchitecture.team6;

// VO(Value Object) Class
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
	public int getCost() {
		return cost;
	}
}
