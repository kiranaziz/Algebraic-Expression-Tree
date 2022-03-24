package assignment1;

import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * ICSI 311 Principles of Programming Languages
 * Fall 2021
 * TA Phipps
 * Student ID: 001440162
 * 
 * Represents an infix expression as a binary tree called an Expression Tree where each token 
 * in the infix expression is made into a Node.
 * @author Kiran Aziz
 * @version 1.0
 */
public class ExpressionTree{
	/**
	 * A reference to the Expression Tree - the root Node where the element is a String data.
	 */
	protected Node<String> root;
	
	/**
	 * A reference to the associated infix expression with operands and operators.
	 */
	private String infixExpression;
	
	/**
	 * Constructs an Expression Tree from an infix expression by converting the infix
	 * expression into a postfix expression, and all tokens into Nodes.
	 * @param infixExpression A reference to the infix expression.
	 */
	public ExpressionTree(String infix){
		this.infixExpression = infix;
		
		//Converts infix expression to a postfix expression.
		String postfixExpression = infixToPostfix(this.infixExpression);
		
		//Splits the postfix expression by spaces to creates tokens, this allows for multiple digit numbers.
		StringTokenizer splitPostfix = new StringTokenizer(postfixExpression, " ");
		
		//Stack of Nodes that eventually help build the binary Expression Tree.
		Stack<Node<String>> stack = new Stack<Node<String>>(); 
		
		//Goes through entire postfix expression and follows shunting-yard algorithm.
		while(splitPostfix.hasMoreTokens()){
			String token = splitPostfix.nextToken(); 
			
			//Converts string into a Node of type String.
			Node<String> tokenNode = new Node<String>(token); 

			//If the token Node is a number, push onto the stack.
			if(isNumber(token)){
				stack.push(tokenNode);
			}
			
			//If the token Node is not a number, it must be an operator.
			//Pops off two token Nodes and forms a new Node, where the operator becomes the root
			//and the left and right children are the popped off token Nodes.
			//Pushes new Node onto the stack.
			else if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")){
				tokenNode.setRight(stack.pop()); 
				tokenNode.setLeft(stack.pop());  
				
				Node<String> newNode = new Node<String>(token, tokenNode.getLeft(), tokenNode.getRight()); 
				
				stack.push(newNode);
			}
		}
		
		//The final node in the stack is the root of the Expression Tree, popping off this Node
		//finally creates the full Expression Tree.
		this.root = stack.pop();
	}
	
	/**
	 * Constructs a default Expression Tree.
	 *
	 */
	public ExpressionTree(){
		this(null);	
	}
	
	
	/**
     * Returns whether the Expression Tree is empty or not.
     * @return A boolean specifying if the tree is empty.
     */
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Removes all nodes from the Expression Tree by setting the root to null.
     */
    public void makeEmpty() {
        this.root = null;
    }
	
	/**
	 * Returns the Expression Tree's root element.
	 * @return A reference to the element of the root.
	 * @throws ExpressionTreeException A ExpressionTreeException is thrown if the tree is empty.
	 */
	public String getRoot() throws ExpressionTreeException {
        if (this.root == null) {
            throw new ExpressionTreeException("ExpressionTreeException: The tree is empty");
        } else {
            return this.root.getElement();
        }
    }

	/**
	 * Traverses the Expression Tree in preorder, visiting the root first, then the left child, and finally the right child of every Node.
	 * @param node A reference to the root Node of the Expression Tree.
	 * @param list A list to contain all of the tokens in preorder.
	 * @return Returns the list of tokens in preorder.
	 */
	public ArrayList<String> preorder(Node<String> node, ArrayList<String> list){
		if(node != null){
			list.add(node.getElement());
			preorder(node.getLeft(), list); 
			preorder(node.getRight(), list);	
		}
		return list;
	}

	/**
	 * Traverses the Expression Tree in inorder, the left child first, then the root, and finally the right child of every Node.
	 * @param node A reference to the root Node of the Expression Tree.
	 * @param list A list to contain all of the tokens in inorder.
	 * @return A list to contain all of the tokens in inorder.
	 */
	public ArrayList<String> inorder(Node<String> node, ArrayList<String> list){
		if(node != null){
			inorder(node.getLeft(), list); 
			list.add(node.getElement());
			inorder(node.getRight(), list);	
		}
		return list;
	}
	
	/**
	 * Traverses the Expression Tree in postorder, the left child first, then the right child, and finally the root of every Node.
	 * @param node A reference to the root Node of the Expression Tree.
	 * @param list A list to contain all of the tokens in postorder.
	 * @return A list to contain all of the tokens in postorder.
	 */
	public ArrayList<String> postorder(Node<String> node, ArrayList<String> list){
		if(node != null){
			postorder(node.getLeft(), list); 
			postorder(node.getRight(), list);
			list.add(node.getElement());
		}
		return list;
	}
	
	/**
	 * An overridden equals() method that compares an Expression Tree to some other object
	 * if the other object is also an Expression Tree and both trees have the exact same
	 * nodes/elements in each location in the trees.
	 * @param obj A reference to an object that could or could not be a Expression Tree.
	 * @return A boolean specifying if the two Expression Trees are equal or not.
	 */
	@Override
	public boolean equals(Object obj){
    	boolean flag = false;
    	
    	//Checks to see if the object is an Expression Tree or not.
    	//If so, call the helper method, isEqual() to check all nodes/elements.
    	if(obj instanceof ExpressionTree){
    		ExpressionTree secondTree = (ExpressionTree) obj;
    		flag = isEqual(this.root, secondTree.root);
    	}
   
    	return flag;
    }
	
	/**
	 * Helper method to traverse through all nodes of two Expression Trees,
	 * beginning at each of their roots.
	 * @param rootNode1 A reference to the root of the first Expression Tree.
	 * @param rootNode2 A reference to the root of the second Expression Tree.
	 * @return
	 */
	private boolean isEqual(Node<String> rootNode1, Node<String> rootNode2){
		boolean flag = false;
		
		//If both root nodes are null, or essentially empty, they are still equal.
		if((rootNode1 == null) && (rootNode2 == null)){
			flag = true;
		}else if((rootNode1 != null) && (rootNode2 != null)){
			//If the root nodes of both trees aren't equal, then immediately it is known
			//that the trees are equal.
			if( !(rootNode1.getElement().equals(rootNode2.getElement())) ){
				flag = false;
			}
			//Recursively checks other nodes/elements in the tree.
			else{
				flag = isEqual(rootNode1.getLeft(), rootNode2.getLeft()) &&
						isEqual(rootNode1.getRight(), rootNode2.getRight());
			}
		}
		return flag;
	}
		
	/**
	 * An overridden toString()method where the Expression Tree is represented as 
	 * a String, and returns the type of this tree, the infix expression of this tree,
	 * the prefix form of this tree, and the postfix form of this tree.
	 * @return A reference to a String representation of this Expression Tree.
	 */
	@Override	
	public String toString(){
		//Holds nodes/elements of each traversal and its specific order.
		//After each traversal, the list is cleared for a new traversal method.
		ArrayList<String> list = new ArrayList<String>();
		
		String preorder = preorder(this.root, list).stream().map(Object::toString).collect(Collectors.joining(" "));
		list.clear();
		
		String postorder = postorder(this.root, list).stream().map(Object::toString).collect(Collectors.joining(" "));
		list.clear();
		
		return	"The type of this Expression Tree is: " + this.root.getClass().getSimpleName() + "\n" +
				"The type of this Expression Tree's root node is: " + this.root.getElement().getClass().getSimpleName() + "\n" +
				"The type of this Expression Tree's infix expression is: " + this.infixExpression.getClass().getSimpleName() + "\n" +
				"The infix expression of this tree is: " + this.infixExpression + "\n" +
				"The prefix form of this tree is: " + preorder + "\n" +
				"The postfix form of this tree is: " + postorder;
	}

	
	/**
	 * Converts an infix expression to a postfix expression, which makes the creation of an Expression Tree easier.
	 * This conversion follows the shunting-yard algorithm.
	 * @param infixExpression A reference to a String infix expression.
	 * @return A reference to a String postfix expression.
	 */
	private static String infixToPostfix(String infixExpression){
		//Splits the infix expression into tokens based on parentheses and operators, while including the operators.
		StringTokenizer newInfix = new StringTokenizer(infixExpression, ")(*+-/", true);
		
		//Stack to hold operator tokens from the infix expression.
		Stack<String> stack = new Stack<String>();
		
		//Postfix String to hold the operand tokens from the infix expression.
		String postfixExpression = "";
		
		
		//As long as there are more tokens in the infix expression, identify each token and allocate to correct holding place.
		while(newInfix.hasMoreTokens()){
			String token = newInfix.nextToken().trim(); 
			
			//If the token is a number, including floating point numbers and multiple digit numbers, then append to the postfix String.
			if(isNumber(token)){
				postfixExpression += token;
				postfixExpression += " ";
			}
			
			//If the token is an open parenthesis, push to the stack.
			else if(token.equals("(")){
				stack.push(token);
			}
			
			//If the token is an operator, check to see if the stack is empty, and then push to the stack.
			//If the stack is not empty, check the precedence of the operator and what is the current peek of the stack.
			else if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")){
				if(stack.isEmpty()){
					stack.push(token);
				}
				
				//If the stack is NOT empty, the top element of the stack is an operator(not an operator or a parentheses), 
				//and the top element of the stack has greater or equal precedence than current operator, then pop off
				//those operators and append to the postfix String. 
				else{
						while((!stack.isEmpty())  &&  (stack.peek() != "(")  &&  (operatorPrecedence(stack.peek()) >= operatorPrecedence(token))){
							postfixExpression += stack.peek();
							postfixExpression += " ";
							stack.pop();
						}
						stack.push(token);
				}
			}
			
			//If the token is a close parenthesis, then pop all operators until a matching parenthesis is found to the postfix String.
			//Remove all parentheses from the stack as well.
			else if(token.equals(")")){
				while(!stack.peek().equals("(") ){
					postfixExpression += stack.pop();
					postfixExpression += " ";
				}
				stack.pop();
			}	
		}
		
		//Pop off all remaining operators from the stack to the postfix String.
		while(!stack.isEmpty()){
			postfixExpression += stack.peek();
			stack.pop();
		}
		
		return postfixExpression;
	}
	
	/**
	 * Assigns numerical representation of precedence with corresponding operator. 
	 * Multiplication and division have higher precedence than addition and subtraction.
	 * @param operator A String reference to an operator, either "*", "/", "+", or "-".
	 * @return An integer value that denotes the precedence level of the operator.
	 */
	private static int operatorPrecedence(String operator){
		int i = 0;
		
		//Multiplication and division symbols are assigned an integer greater than the
		//integer assigned to the addition and subtraction symbols.
		switch (operator){
			case "+": case "-": 
				i = 1;
				break;
			case "*": case "/": 
				i = 2;
				break;
			default:
				break;
		}
		
		return i;
	}
	
	/**
	 * Parses a String token, the operand of an expression, to check if the String can also be a number.
	 * @param s A reference to a String token.
	 * @return A boolean specifying is the String token is a number or not.
	 */
	private static boolean isNumber(String s) {
	    try { 
	        Double.parseDouble(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}
}
