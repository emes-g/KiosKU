package konkuk.swarchitecture.team6.gui;

public class Pair<X, Y> {
	private X x;
	private Y y;
	
	public Pair(X x, Y y) {
		this.x = x;
		this.y = y;
	}
	public X getX() {
		return x;
	}
	public void setX(X x) {
		this.x = x;
	}
	public Y getY() {
		return y;
	}
	public void setY(Y y) {
		this.y = y;
	}
	
	public void print() {
		System.out.println(x.getClass().getName());
		System.out.println(y.getClass().getName());
	}
}