package algorithms.binarytree;

/**
 * 二叉树节点
 * 
 * @author XiangZhuRui
 *
 */
public class TreeNode<T> {
	private T data;
	private TreeNode<?> left;
	private TreeNode<?> right;

	public TreeNode() {
		super();
	}

	public TreeNode(T data, TreeNode<?> left, TreeNode<?> right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public TreeNode<?> getLeft() {
		return left;
	}

	public void setLeft(TreeNode<?> left) {
		this.left = left;
	}

	public TreeNode<?> getRight() {
		return right;
	}

	public void setRight(TreeNode<?> right) {
		this.right = right;
	}
}
