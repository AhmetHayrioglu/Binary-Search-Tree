package Project2;

public class Main {

	public static void main(String[] args) {
		
		BST tree = new BST(new TreeNode(6));
		/*
		System.out.println("Deleting then printing the tree when the root node when it is leaf: ");
		tree.delete(6);
		tree.print();
		tree.draw();*/
		                  //the above ^ works fine no need to check again
		
		tree.insert(4);
		tree.insert(29);
		tree.insert(10);
		tree.insert(3);
		tree.insert(0);
		tree.insert(2);
		
		System.out.println("BST after insertion: ");
		tree.print();

		tree.insert(5);
		tree.insert(25);
		tree.insert(50);
		tree.insert(39);
		tree.insert(8);
		tree.insert(1);
		
		tree.draw();		
		System.out.println("BST after more insertion: ");
		tree.print();
		
		tree.delete(10);
		tree.delete(5);
		tree.delete(39);
		
		System.out.println("BST after deletion: ");
		tree.print();
		
		tree.draw();
		
	}

}
