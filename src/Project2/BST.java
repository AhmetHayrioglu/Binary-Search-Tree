package Project2;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class BST {
	private TreeNode root;

	BST() {
		this.root = null;
	}

	BST(TreeNode root) {
		this.root = root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public void insert(int x) {
		TreeNode currNode = this.root;
		TreeNode temp = null;
		
		while (currNode != null) {
			temp = currNode;
			if (x == currNode.getData()) {
				return; // terminate if x already exists
			}
			// continue to iterate until you hit upon null
			else if (x > currNode.getData()) // go to the right subtree if x is larger
			{
				currNode = currNode.getRight();
			}

			else if (x < currNode.getData())// go to the left subtree if x is smaller
			{
				currNode = currNode.getLeft();
			}

		}
		
		if (this.root == null)// if the tree is empty add new node as the root of the tree
			this.root = new TreeNode(x);
		
		else if (x > temp.getData()) // temp keeps the pointer of the leaf to which we are going
								// add our new node
			temp.setRight(new TreeNode(x));

		else if (x < temp.getData()) // add the new node as a left child of temp if x is smaller
										// add it as a right child if x is larger
			temp.setLeft(new TreeNode(x));

	
	}
	
	public TreeNode findMin() {
		return findMin(this.root);
	}
	
	private TreeNode findMin(TreeNode root) {
		if (root == null)
			return null;
		
		if (root.getLeft() == null)
			return root;
		
		return findMin(root.getLeft());
	}

	
	public void delete(int x) {
		TreeNode nodeToDelete = this.root;
		TreeNode parent = nodeToDelete;
	
	    while (nodeToDelete != null && nodeToDelete.getData() != x) {
	        parent = nodeToDelete;
	        if (x > nodeToDelete.getData()) {
	            nodeToDelete = nodeToDelete.getRight();
	        } else if (x < nodeToDelete.getData()) {
	            nodeToDelete = nodeToDelete.getLeft();
	        }
	    }
	
	    if (nodeToDelete != null) {
			if (x > parent.getData()) {
			    deleteCaseForRightChild(parent, nodeToDelete);
			} else if (x < parent.getData()) {
			    deleteCaseForLeftChild(parent, nodeToDelete);
			} else { // Node that we are going to delete is the root
			    deleteCaseForRoot();
			}
	    }
	}
	
	private void deleteCaseForRightChild(TreeNode parent, TreeNode nodeToDelete) {
		// bug fixed, don't touch it .
	    if (nodeToDelete.isLeaf()) {
	        parent.setRight(null);
	    } else if (nodeToDelete.getRight() != null) {
	    	
			if (nodeToDelete.getLeft() == null) {
			    parent.setRight(nodeToDelete.getRight());
			} else {
			    TreeNode min = findMin(nodeToDelete.getRight());
			    nodeToDelete.setData(min.getData());
			    deleteCase12(parent.getRight(), min);
			}
	    } else if (nodeToDelete.getLeft() != null) {
	        parent.setRight(nodeToDelete.getLeft());
	    }
	}
	
	private void deleteCaseForLeftChild(TreeNode parent, TreeNode nodeToDelete) {
		// it is similar to the above fn
		
	    if (nodeToDelete.isLeaf()) {
	        parent.setLeft(null);
	    } else if (nodeToDelete.getRight() != null) {
			
	    	if (nodeToDelete.getLeft() == null) {
			    parent.setLeft(nodeToDelete.getRight());
			} else {
			    TreeNode min = findMin(nodeToDelete.getRight());
			    nodeToDelete.setData(min.getData());
			    deleteCase12(parent.getLeft(), min);
			}
	    } else if (nodeToDelete.getLeft() != null) {
	        parent.setLeft(nodeToDelete.getLeft());
	    }
	}
	
	private void deleteCaseForRoot() {
	    if (this.root.isLeaf()) {
	        this.root = null;
	    } else if (this.root.getRight() != null) {
			if (this.root.getLeft() == null) {
			    this.root = this.root.getRight();
			} else {
			    TreeNode min = findMin(this.root.getRight());
			    this.root.setData(min.getData());
			    deleteCase12(this.root, min);
			}
	    } else if (this.root.getLeft() != null) {
	        this.root = this.root.getLeft();
	    }
	}

	private void deleteCase12(TreeNode root, TreeNode min) {

		TreeNode parentOfMin = null;
		TreeNode nodeToDelete = root.getRight();// min is in the right subtree of root
		while (nodeToDelete != null && nodeToDelete.getData() != min.getData()) { // Traverse the subtree until you find
																					// min node
			parentOfMin = nodeToDelete;
			if (min.getData() > nodeToDelete.getData()) {
				nodeToDelete = nodeToDelete.getRight();
			}
			if (min.getData() < nodeToDelete.getData()) {
				nodeToDelete = nodeToDelete.getLeft();
			}

		}

		if (parentOfMin != null) {
			if (nodeToDelete.getData() > parentOfMin.getData()) { // if the min is a right child of parent
				if (nodeToDelete.isLeaf()) // if min node is a leaf
					// delete by setting the right child of parentOfmin to null
					parentOfMin.setRight(null);

				if (nodeToDelete.getRight() != null) { // since we are deletin the min it can only have a right child at
														// most
					parentOfMin.setRight(nodeToDelete.getRight());
					// if the min to be deleted has a right child
					// we delete it by adjusting the pointer of parentOfMin
				}
			} else if (nodeToDelete.getData() < parentOfMin.getData()) { 
				if (nodeToDelete.isLeaf())
					parentOfMin.setLeft(null);

				if (nodeToDelete.getRight() != null)
					parentOfMin.setLeft(nodeToDelete.getRight());
			}
		}
		if (parentOfMin == null) {
			root.setRight(null);
		}
	}
	
	public void print() {
		if(root != null) {
			printHelper(root);
			System.out.println();
		} else {
			System.out.println("Tree is empty!");
		}
		
	}

	private void printHelper(TreeNode node) {
		if(node != null) {
			printHelper(node.getLeft());
			System.out.print(node.getData() + " ");
			printHelper(node.getRight());
		}
		
	}
	
	public void draw() {
		
		if(root != null) {
			DrawMethod dm = new DrawMethod(this.root);
		} else {
			System.out.println("Tree is empty, nothing to draw");
		}
	}

	private class DrawMethod extends JFrame {
		private TreeNode root;
		private final int x = 1200;
		private final int y = 800;
		private int nodeWidth = 35;
		private int yOffset = 50;

		DrawMethod(TreeNode root) {
			this.root = root;
			setFrame();

		}
		
		private void setFrame() {
			
			this.setTitle("BST Display");
			this.setDefaultCloseOperation(this.EXIT_ON_CLOSE); 
			this.setResizable(false); 
			this.setSize(x, y); 
			this.setVisible(true); 
			this.getContentPane().setBackground(new Color(0xFFFFFF)); // change color of background to white
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			drawCircle(g, x / 2, this.yOffset, nodeWidth, String.valueOf(this.root.getData()));
			drawTree(g, x / 2, this.yOffset, this.root, x / 6);
			// drawTree(Graphics object, initial x position ,
			// initial y position,the root of the tree, horizontal offset from the parent)
		}

		public void drawTree(Graphics g, int x, int y, TreeNode node, int xOffSet) {
			if (node != null)// draw current node if it exist
			{
				
				drawCircle(g, x, y, nodeWidth, String.valueOf(node.getData()));

				if (node.getLeft() != null) { // if the left child exists make the recursive call on it
					int xLeft = x - xOffSet;
					// go to the left of the parent x-position by
					int yLeft = y + this.yOffset; // go down by YOffset distance
					// (go to the next level to draw the children)
					drawLine(g, x, y, xLeft, yLeft);
					drawTree(g, xLeft, yLeft, node.getLeft(), xOffSet / 2);
					// as we go to lower levels the horizontal offset from the parent
					// (the edge length) is halved
				}

				if (node.getRight() != null) {
					int xRight = x + xOffSet;
					// go to the right by xOffSet distance from the parent to draw its right child

					int yRight = y + this.yOffset;// go to the next level
					drawLine(g, x, y, xRight, yRight);
					drawTree(g, xRight, yRight, node.getRight(), xOffSet / 2);
				}
			}

		}

		private void drawCircle(Graphics g, int x, int y, int diameter, String text) {
			g.drawOval(x - diameter / 2, y - diameter / 2, diameter, diameter);
			g.drawString(text, x - 5, y + 5); // to print the txt in the middle
		}

		private void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
			g.drawLine(x1, y1 + nodeWidth / 2, x2, y2 - nodeWidth / 2);
		}
	}
}
