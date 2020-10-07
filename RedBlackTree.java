package hw08;

public class RedBlackTree<E extends Comparable<E> > extends BinarySearchTree<E>{
	BSTNode<E> NIL = new BSTNode<E>(null, 'B');
	
	
	public RedBlackTree() {
		super();
		
	}

	//public methods
		
	//Method which adds a new value to the tree according to the rules of a Binary Search Tree.
	@Override
	protected BSTNode<E> insertionPoint(E value) throws DuplicateItemException {
		BSTNode<E> current;
		BSTNode<E> parent = NIL;
		current = this.root;
		
		while(current != NIL) {
			if(value.compareTo(current.getData()) == 0) {
				throw new DuplicateItemException();		
			}
			else if(value.compareTo(current.getData())<0) {
				parent = current;
				current = current.left;
			}
			else if(value.compareTo(current.getData()) > 0) {
				parent = current;
				current = current.right;
			}
			
		}
		return parent;
	}
	
	@Override
	public void insert(E value) {
		BSTNode<E> child = new BSTNode<E>(value, 'R');
		child.left = NIL;
		child.right = NIL;
		if(isEmpty()) {
			this.root = child;
			insertCleanup(child);
		}
		else {
			try {
				child.parent = insertionPoint(value);
				if(value.compareTo(child.parent.data) < 0) {
					child.parent.left = child;
				}
				else if(value.compareTo(child.parent.data) > 0) {
					child.parent.right = child;
				}
				insertCleanup(child);
			}
			catch(DuplicateItemException e){
				System.out.println("duplicate item");
			}
		}
	}

	
	//private methods
	
	//Performs a left rotation rooted at subTreeRoot.
	private void leftRotate(BSTNode<E> node) {
		BSTNode<E> rootR = node;
		BSTNode<E> pivot = node.right;
			pivot.parent = rootR.parent;
			if(rootR == this.root) {
				this.root = pivot;
				pivot.parent = NIL;
			}
			else if(isLeftChild(rootR)) {
				//pivot.parent = rootR.parent.left;
				rootR.parent.left = pivot;
				pivot.parent = rootR.parent;
			}
			else if(isRightChild(rootR)){
				//pivot.parent = rootR.parent.right;
				rootR.parent.right = pivot;
				pivot.parent = rootR.parent;
			}	
			//rootR.parent = pivot;
		
		rootR.right = pivot.left;
		pivot.left.parent = rootR;
		pivot.left = rootR;
		rootR.parent = pivot;
	}
	
	//Performs a right rotation rooted at subTreeRoot.
	private void rightRotate(BSTNode<E> node) {
		BSTNode<E> rootR = node;
		BSTNode<E> pivot = node.left;
		if(rootR == this.root) {
			this.root = pivot;
			pivot.parent = NIL; 
		}
		else if(isLeftChild(rootR)) {
				//pivot.parent = root.parent.left;
				rootR.parent.left = pivot;
				pivot.parent = rootR.parent;
		}
		else if(isRightChild(rootR)){
				//pivot.parent = root.parent.right;
				rootR.parent.right = pivot;
				pivot.parent = rootR.parent;
		}	
			//rootR.parent = pivot;
		rootR.left = pivot.right;
		pivot.right.parent = rootR;
		pivot.right = rootR;
		rootR.parent = pivot;
	}
	
	//This method is used after insertion to rebalance the tree
	private void insertCleanup(BSTNode<E> child) {
		//case 1
		if(this.root.color == 'R') {
			this.root.color = 'B';
			return;
		}
		
		//case 2
		if(child.parent.color == 'B'){
			//nothing happens
			return;
		}
		
		//case 3 
		if(child.parent.color == 'R' && uncle(child).color == 'R') {
			child.parent.color = 'B';
			uncle(child).color = 'B';
			grandparent(child).color = 'R';
			insertCleanup(grandparent(child));
		}
		
		//case 4
		if(child.parent.color == 'R' && uncle(child).color == 'B') {
			if(isRightChild(child) && isLeftChild(child.parent)) {
				leftRotate(child.parent);
				child = child.left;
			}
			else if(isLeftChild(child) && isRightChild(child.parent)){
				rightRotate(child.parent);
				child = child.right;
			}
		}
		
		//case 5
		if(child.parent.color == 'R' && uncle(child).color == 'B') {
			if(isLeftChild(child) && isLeftChild(child.parent)) {
				child.parent.color = 'B';
				grandparent(child).color = 'R';
				rightRotate(grandparent(child));
			}
			else if(isRightChild(child) && isRightChild(child.parent)) {
				child.parent.color = 'B';
				grandparent(child).color = 'R';
				leftRotate(grandparent(child));
			}
		}
	}

}
