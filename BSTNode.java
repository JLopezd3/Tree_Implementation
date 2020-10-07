package hw08;

public class BSTNode <E extends Comparable<E>>  {
	protected BSTNode<E> parent;
	protected BSTNode<E> left;
	protected BSTNode<E> right;
	protected E data;
	protected char color;
	
	
	public BSTNode(E data, char color) {
		super();
		this.data = data;
		this.color = color;
	}
	
	public E getData() {
		return data;
	}
	
	public void setData(E data) {
		this.data = data;
	}
	
	

}
