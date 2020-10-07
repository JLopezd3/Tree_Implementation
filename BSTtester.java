package hw08;

import java.util.ArrayList;
import java.util.Random;

public class BSTtester {
	public static void main(String args[]) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Random rand = new Random();
		for(int i=0; i<10; i++) {
			list.add(rand.nextInt(100)+i);
			System.out.print(list.get(i) + ", ");
		}
		System.out.println(" ");
		BinarySearchTree tree = new BinarySearchTree(list);
		for(int i=0; i < list.size(); i++) {
			//tree.insert(list.get(i));
			System.out.print(tree.find(list.get(i)) + ", ");
		}
		System.out.println("\nBinary Tree: \n");
		tree.printTree();
		//
		System.out.println("GrandParent of " + tree.root.getData() + " is: " + tree.grandparent(tree.root));
		System.out.println("Uncle of " + tree.root.getData() + " is: " + tree.uncle(tree.root));
		System.out.println("Sibling of " + tree.root.getData() + " is: " + tree.sibling(tree.root));
		if(tree.root.left != null && tree.root.left.left != null) {
			System.out.println("GrandParent of " + tree.root.left.left.getData() + " is: " + (tree.grandparent(tree.root.left.left) != null ? tree.grandparent(tree.root.left.left).getData():tree.grandparent(tree.root.left.left)));
			System.out.println("Uncle of " + tree.root.left.left.getData() + " is: " + (tree.uncle(tree.root.left.left)!= null ? tree.uncle(tree.root.left.left).getData() : tree.uncle(tree.root.left.left)));
			System.out.println("Sibling of " + tree.root.left.left.getData() + " is: " + (tree.sibling(tree.root.left.left) != null ? tree.sibling(tree.root.left.left).getData() : tree.sibling(tree.root.left.left)));
		}else {
			System.out.println("node can't be tested, does not exist ");
		}
		
		//
		System.out.println("PostOrder: " + tree.postorder());
		System.out.println("PreOrder: " + tree.preorder());
		System.out.println("InOrder: " + tree.inorder());
		System.out.println("BreadthFirst: " + tree.breadthfirst());
		// test delete
		System.out.println("Tree with root deleted " + tree.root.getData());
		tree.delete(tree.root.getData());
		tree.printTree();
		
		System.out.println("--------------------------------------------------------------------------------------------" + 
		"\n BIGINNING RED BLACK TREE TESTING: \n");
		RedBlackTree RBT = new RedBlackTree();
		for(int i=0; i < list.size(); i++) {
			RBT.insert(list.get(i));
			System.out.print(list.get(i) + ", ");
		}
	
		System.out.println("\n Red Black Tree: \n");
		RBT.printTree();
		
	}
}
