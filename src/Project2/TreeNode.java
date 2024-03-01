package Project2;

public class TreeNode {
	private int x;
	private TreeNode right;
	private TreeNode left;

	// Constructors:

	TreeNode(int x) {
		this.x = x;
		this.left = null;
		this.right = null;
	}

	TreeNode(int x, TreeNode left, TreeNode right) {
		this.x = x;
		this.left = left;
		this.right = right;
	}

	TreeNode()// creates an empty treeNode
	{
		this.left = null;
		this.right = null;
	}

	// Methods:
	public int getData()// returns this.data (x in this case)
	{
		return this.x;
	}

	public void setData(int x)// assigns the passed data into this.data
	{
		this.x = x;
	}

	public TreeNode getRight()// returns the right child of this treeNode
	{
		return this.right;
	}

	public TreeNode getLeft()// returns the left child of this treeNode
	{
		return this.left;
	}

	public void setRight(TreeNode right) // sets the right child of this node
	{
		this.right = right;
	}

	public void setLeft(TreeNode left)// sets the left child of this node
	{
		this.left = left;
	}

	public boolean isLeaf() {
		if (this.left == null)
			if (this.right == null)
				return true;
		return false;
	}

}
