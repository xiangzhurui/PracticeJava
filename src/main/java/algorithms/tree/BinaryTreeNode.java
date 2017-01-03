package algorithms.tree;

/**
 * 二叉树节点
 * 
 * @author XiangZhuRui
 *
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
