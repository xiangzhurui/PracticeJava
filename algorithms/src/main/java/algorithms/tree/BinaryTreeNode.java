package algorithms.tree;

/**
 * 二叉树节点
 *
 * @author XiangZhuRui
 */
public class BinaryTreeNode<T> {
    private T value;
    private BinaryTreeNode<T> leftChild;
    private BinaryTreeNode<T> rightChild;

    public BinaryTreeNode(T value, BinaryTreeNode<T> leftChild, BinaryTreeNode<T> rightChild) {
        super();
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public BinaryTreeNode() {
        super();
    }

    public int depth(BinaryTreeNode binaryTreeNode) {
        if (binaryTreeNode == null) {
            return 0;
        }
        int l = depth(binaryTreeNode.leftChild);
        int r = depth(binaryTreeNode.rightChild);
        if (l > r) {
            return l + 1;
        } else {
            return r + 1;
        }
    }

    /**
     * 是否为根节点
     *
     * @return
     */
    public boolean isRoot() {
        return false;
    }

    /**
     * 是否为叶子节点
     *
     * @return
     */
    public boolean isLeaf() {
        if (this.leftChild == null && this.rightChild == null) {
            return true;
        }
        return false;
    }

    /**
     * 遍历
     */
    public void traverse() {

    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BinaryTreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }

}
