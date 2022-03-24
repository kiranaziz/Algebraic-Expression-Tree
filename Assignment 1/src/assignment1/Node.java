package assignment1;
/**
 * ICSI 311 Principles of Programming Languages
 * Fall 2021
 * TA Phipps
 * Student ID: 001440162
 * 
 * Represents a generic Node in the binary tree, Expression Tree, where each Node contains a data value
 * and two references, one for the left child and one for the right child.
 * @author Kiran Aziz
 * @version 1.0
 */
public class Node<E> {
	/**
	 * The data of this node.
	 */
	private E element;
	
	/**
	 * The reference to the left child of this node.
	 */
	private Node<E> left;
	
	/**
	 * The reference to the right child of this node
	 */
	private Node<E> right;
	
	
	/**
	 * Constructs a node with an element and two descendant nodes.
	 * @param element A reference to the data of this node.
     * @param left    A Reference to the left child of this node.
     * @param right   A reference to the right child of this node.
	 */
	public Node(E element, Node<E> left, Node<E> right){
		this.element = element;
		this.left = left;
		this.right = right;
	}
	
	
	/**
	 * Constructs a node with a given element.
	 * @param element A reference to data of this node.
	 */
	public Node(E element){
		this(element, null, null);
	}
	
	
	/**
	 * Constructs a default node.
	 */
	public Node(){
		this(null, null, null);
	}
	
	
	/**
	 * Returns the element of this node.
	 * @return A reference to the element of this node.
	 */
	public E getElement() {
        return element;
    }
	
	
	/**
	 * Returns the right child of this node.
	 * @return A reference to the right child of this node.
	 */
	public Node<E> getRight() {
        return right;
    }
	
	
	/**
	 * Changes the right child of this node.
	 * @param right A reference to the right child of this node.
	 */
	public void setRight(Node<E> right) {
        this.right = right;
    }
	
	
	/**
	 * Returns the left child of this node.
	 * @return A reference to the left child of this node.
	 */
	public Node<E> getLeft() {
	        return left;
	}

	
	/**
	 * Changes the left child of this node.
	 * @param left A reference to the left child of this node.
	 */
    public void setLeft(Node<E> left) {
        this.left = left;
    }
    
    
    /**
     * Changes the element of this node.
     * @param element The element of this node.
     */
    public void setElement(E element) {
        this.element = element;
    }

    
    /**
     * Checks if nodes are equivalent by comparing the elements in each node.
     * @param node A reference to the node.
     * @return A boolean specifying if the two nodes are equal.
     */
    public boolean equals(Node<E> node){
    	boolean flag = false;
    	
    	//If the element of the first Node is equal to the element of the second Node,
    	//then, the two Nodes are equal. Otherwise, return false.
    	if(this.element == node.getElement()){
    		flag = true;
    	}

    	return flag;
    }

    
    
    /**
     * Returns the node as a string representation.
     * @return A reference to the data within the node.
     */
    @Override
    public String toString(){
    	return "{" + this.left + ", " + this.element + ", " + this.right + "}";
    }
}
