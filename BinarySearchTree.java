package hw08;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree <E extends Comparable<E>>{
	protected BSTNode<E> root; 

	public BinarySearchTree() {
		super();
		
	}
	
	//The values of the variable-length parameter list should be inserted into the tree in the order in which they are listed in the constructor invocation.
	public BinarySearchTree(ArrayList<E> list) { 
		for(E value : list) {
			insert(value);
		}
	}
	
	//returns the parent to the node/value that will be inserted
	protected BSTNode<E> insertionPoint(E value) throws DuplicateItemException {
		BSTNode<E> current;
		BSTNode<E> parent = null;
		current = root;
		
		while(current != null) {
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
	
	//Method which adds a new value to the tree according to the rules of a Binary Search Tree.
	public void insert(E value) {
		BSTNode<E> child = new BSTNode<E>(value, 'N');
		
		if(isEmpty()) {
			this.root = child;
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
			}
			catch(DuplicateItemException e){
				System.out.println("duplicate item");
			}
		}
	}
	
	protected int numChildren(BSTNode<E> node) {
		int count = 0;
		if(node.left != null) {
			count++;
		}
		if(node.right != null) {
			count++;
		}
		return count;
	}
	private BSTNode<E> nodetoDelete(E key){
		BSTNode<E> current;
		current = root;
		while(current != null) {
			if(key.compareTo(current.getData()) == 0) {
				return current;
			}
			else if (key.compareTo(current.getData()) < 0){
				current = current.left;
			}
			else if (key.compareTo(current.getData()) > 0) {
				current = current.right;
			}
		}
		return null;
	}
	
	private BSTNode<E> maxLeftSubtree(BSTNode<E> node){
		BSTNode<E> max;
		max = node.left;
		if(max.right != null) {
			if(max.right.getData().compareTo(max.getData()) > 0) {
				max = max.right;
			}
		}
		return max;
		
	}
	//Method which takes a key and removes the node from the tree.
	public void delete (E key) {
		delete(nodetoDelete(key));
	}
	public void delete(BSTNode<E> node) {
		BSTNode<E> child;
		if(isLeaf(node)) { //if node is leaf
			if(isLeftChild(node)) {
				node.parent.left = null;
			}
			else if(isRightChild(node)) {
				node.parent.right = null;
			}
		}
		else if(numChildren(node) == 1) {
			if(node.left != null) {
				child = node.left;
			}
			else {
				child = node.right;
			}
			if(isLeftChild(node)) {
				node.parent.left = child;
			}
			else if(isRightChild(node)) {
				node.parent.right = child;
			}
		}
		else if(numChildren(node) == 2) {
			BSTNode<E> max = maxLeftSubtree(node); 
			node.setData(max.getData());
			delete(max);
		}
	}
	
	//Returns true or false depending on if the key is found in the tree or not.
	public Boolean find(E value) {
		BSTNode<E> current = new BSTNode<E> (value, 'N');
		current = this.root;
		while (current != null) {
 		 if (value.compareTo(current.getData()) == 0) {
 			 return true;
 		 }
 		 else if(value.compareTo(current.getData()) < 0) {
 			current = current.left;
 		 }
	    else if (value.compareTo(current.getData()) > 0){
			current = current.right; 
 		 }
		}
		return false;
	}
	
	//Returns true or false if the tree is empty or not
	public Boolean isEmpty() {
		return this.root == null;
	}
	
	//Returns an ArrayList of values generated using preorder traversal.
	public ArrayList<E> preorder(){
		ArrayList<E>list = new ArrayList<>();
		return preorder(this.root, list);
	}
	private ArrayList<E> preorder(BSTNode<E> node, ArrayList<E> list) {
		if(node == null) {
			return list;
		}
		list.add(node.getData());
		preorder(node.left, list);
		preorder(node.right, list);
		return list;
		
	}
	
	//Returns an ArrayList of values generated using inorder traversal.
	public ArrayList<E> inorder(){
		ArrayList<E>list = new ArrayList<>();
		return inorder(this.root, list);
	}
	private ArrayList<E> inorder(BSTNode<E> node, ArrayList<E> list) {
		if(node == null) {
			return null;
		}
		inorder(node.left,list);
		list.add(node.getData());
		inorder(node.right,list);
		return list;
		
	}
	
	//Returns an ArrayList of values generated using postorder traversal.
	public ArrayList<E> postorder(){
		ArrayList<E>list = new ArrayList<>();
		return postorder(this.root, list);
	}
	private ArrayList<E> postorder(BSTNode<E> node, ArrayList<E> list) {
		if(node == null) {
			return null;
		}
		postorder(node.left, list);
		postorder(node.right, list);
		list.add(node.getData());
		return list;
	}
	
	//Returns an ArrayList of values generated using breadthfirst traversal.
	public ArrayList<E> breadthfirst(){
		ArrayList<E>list = new ArrayList<>();
		return breadthfirst(this.root, list);
	}
	private ArrayList<E> breadthfirst(BSTNode<E> node, ArrayList<E> list) {
		Queue<BSTNode<E>> q = new LinkedList<>();
		q.add(node);
		while(!q.isEmpty()) {
			node = q.remove();
			if(node != null) {
				list.add(node.getData());
				q.add(node.left);
				q.add(node.right);
			}
		}
		return list;
	}
	public void printTree() {

	    if (this.root.right != null) {
	        this.printTree(this.root.right, true, "");
	    }

	    printNodeValue(this.root);

	    if (this.root.left != null) {
	        this.printTree(this.root.left, false, "");
	    }
	}

	private void printTree(BSTNode<E> node, boolean isRight, String indent) {
	    if (node.right != null) {
	        printTree(node.right, true, indent + (isRight ? "        " : " |      "));
	    }

	    System.out.print(indent);

	    if (isRight) {
	        System.out.print(" /");
	    }
	    else {
	        System.out.print(" \\");
	    }
	    System.out.print("----- ");
	    printNodeValue(node);
	    if (node.left != null) {
	        printTree(node.left, false, indent + (isRight ? " |      " : "        "));
	    }
	}

	private void printNodeValue(BSTNode<E> node) {
	    if (node == null) {
	        System.out.print("<null>");
	    }
	    else {
	        System.out.print(node.getData() + "(" + node.color + ")");
	    }
	    System.out.println();
	}

	
	//PROTECTED METHODS:
	
	//Returns true or false if the node is a leaf or not.
	protected Boolean isLeaf(BSTNode<E>  node) {
		return node.left == null && node.right == null;
		
	}
	
	// Returns true or false if the given node is a left child of its parent, or not.
	protected Boolean isLeftChild(BSTNode<E> node) {
		if(node == this.root || node.parent.left.getData() == null) {
			return false;
		}
		else {
			return node.parent.left.getData().compareTo(node.getData()) == 0;	
		}
	}
	
	//Returns true or false if the given node is a right child of its parent or not.
	protected Boolean isRightChild(BSTNode<E> node) {
		if(node == this.root || node.parent.right.getData() == null) {
			return false;
		}
		else {
			return node.parent.right.getData().compareTo(node.getData()) == 0;
		}	
	}
	
	//Returns the sibling node of the given node.  (This will be a useful method later
	protected BSTNode<E> sibling(BSTNode<E> node) {
		BSTNode<E> sibling = null;
		if(isLeftChild(node)) {
			sibling = node.parent.right;
		}
		else if(isRightChild(node)) {
			sibling = node.parent.left; 
		}
		return sibling;
	}
	
	//Returns the uncle node of the given node. (This will be a useful method later
	protected BSTNode<E> uncle(BSTNode<E> node) {
		BSTNode<E> uncle;
		if(node == this.root) {
			uncle = null;
		}
		else {
			uncle = sibling(node.parent);	
		}
		return uncle;
		
	}
	
	//Returns the grandparent of the given node. (This will be useful method later
	protected BSTNode<E> grandparent(BSTNode<E> node) {
		BSTNode<E> grandparent;
		if(node == this.root) {
			grandparent = null;
		}
		else {
			grandparent = node.parent.parent;
		}
		return grandparent;
		
	}
	
	
	
	

}
