package assignment1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * ICSI 311 Principles of Programming Languages
 * Fall 2021
 * TA Phipps
 * Student ID: 001440162
 * 
 * Tests the Expression Tree implementation by reading in a file of infix expressions and other tests.
 * @author Kiran Aziz
 * @version 1.0
 */
public class Driver {
	/**
	 * Tests the Expression Tree implementation by reading in a file of infix expressions and other tests.
	 * @param args A reference to a String array of arguments.
	 * @throws FileNotFoundException If a file cannot be found, a FileNotFoundException will be thrown.
	 * @throws ExpressionTreeException If an operation on the Expression Tree fails, a ExpressionTreeException will be thrown.
	 */
	public static void main(String[] args) throws FileNotFoundException, ExpressionTreeException{
		//Scans list of infix expressions from a text file and outputs Expression
		//Trees from those infix expressions.
		Scanner scanner = new Scanner(new File("InfixExpressions.txt"));
		
		while(scanner.hasNextLine()){
			String infixExpression = scanner.nextLine();
			ExpressionTree infix = new ExpressionTree(infixExpression);
			System.out.println(infix + "\n");
		}
		scanner.close();
		
		
		
		
		//Additional Testing
		//Node class methods: getElement(), getRight(), setRight(), getLeft(),setLeft(), setElement(), equals(), toString()
		System.out.println("********************Testing for Node class and its methods********************");
		
		Node<String> testNode1 = new Node<String>("20");
		Node<String> testNode2 = new Node<String>("10");
		Node<String> testNode3 = new Node<String>("30");
		Node<String> testNode4 = new Node<String>("200");
		
		//Prints out entire node, including left and right child, but this node has no children yet so should print out null.
		//The console should print out the following: {null, 20, null}
		//Tests toString() method for Node class, implicitly and explicitly.
		System.out.println("First test Node: " + testNode1);
		System.out.println("First test Node, again: " + testNode1.toString());
		
		//Tests and sets left and right nodes for first test node.
		testNode1.setLeft(testNode2);
		testNode1.setRight(testNode3);
		
		//Test getElement() method for first test node.
		System.out.println("The element of the first test Node is: " + testNode1.getElement());
		
		//Prints out entire node, including left and right child.
		//The console should print out the following: {{null, 10, null}, 20, {null, 30, null}}
		//Tests toString() method for Node class.
		System.out.println("First test Node after adding left and right children: " + testNode1);
		
		//Tests setElement() method for first test node.
		testNode1.setElement("200");
		System.out.println("The element of the first test Node is now changed to: " + testNode1.getElement());
		
		//Tests equals() method for Node class, should return true since first test node and fourth test node have the same element.
		System.out.println("The first test Node is equal to the fourth test Node (true or false): " + testNode1.equals(testNode4));
		
		//Tests equals() method for Node class, should return false since first test node and second test node do not have the same element.
		System.out.println("The first test Node is equal to the second test Node (true or false): " + testNode1.equals(testNode2));
		
		
		
		
		
		
		
		
		//ExpressionTree methods: isEmpty(), makeEmpty(), getRoot(), preorder(), inorder(), postorder(), equals(), toString()
		System.out.println("\n********************Testing for ExpressionTree class and its methods********************");
		String testInfixExpression1 = "(121 + (101 + 0))";
		String testInfixExpression2 = "((1 + 2) * (3 * (44 + 55)))";
		String testInfixExpression3 = "(121 + (101 + 0))";
		
		ExpressionTree testTree1 = new ExpressionTree(testInfixExpression1);
		ExpressionTree testTree2 = new ExpressionTree(testInfixExpression2);
		ExpressionTree testTree3 = new ExpressionTree(testInfixExpression3);
		
		//Tests isEmpty() method for the second test expression tree.
			//The console should print out false since the second test expression tree is not empty right now.
			System.out.println("The second test Expression Tree is empty (true or false): " + testTree2.isEmpty());
			
			//Tests the makeEmpty() method for the second expression tree.
			//The console should print out true since the second test expression tree is now empty.
			System.out.println("Let's make the second test Expression Tree empty.");
			testTree2.makeEmpty();
			System.out.println("The second test Expression Tree is empty (true or false): " + testTree2.isEmpty());
			
		//Tests getRoot() method for the first Expression Tree.	
			//The console should print out the root of the tree, which is "+", the plus sign for addition.
			//
			//Visual of the first Expression Tree below.
			//		 +
			//	   /   \
			//   121     +
			//         /   \
			//        101   0
			//
			System.out.println("The root of the first test Expression Tree is: " + testTree1.getRoot());
			
			//The console should print out an error for attempting to get the root of an empty
			//Expression Tree like the second test Expression Tree.
			//System.out.println("The root of the second Expression Tree is: " + testTree2.getRoot());
			
		//Tests preorder() method for the first test Expression Tree, and then converting that ArrayList into a single String.
			ArrayList<String> testList = new ArrayList<String>();
			System.out.println("The preorder form of the first test Expression Tree: " + 
								testTree1.preorder(testTree1.root, testList).stream().map(Object::toString).collect(Collectors.joining(" ")));
			testList.clear();
			
		//Tests inorder() method for the first test Expression Tree, and then converting that ArrayList into a single String.
			System.out.println("The inorder form of the first test Expression Tree: " + 
								testTree1.inorder(testTree1.root, testList).stream().map(Object::toString).collect(Collectors.joining(" ")));
			testList.clear();
			
		//Tests postorder() method for the first test Expression Tree, and then converting that ArrayList into a single String.
			System.out.println("The postorder form of the first test Expression Tree: " + 
								testTree1.postorder(testTree1.root, testList).stream().map(Object::toString).collect(Collectors.joining(" ")));
			
			
		//Tests the equals() method for the first test Expression Tree and the third test Expression Tree.
			System.out.println("The first test Expression Tree is equal to the third test Expression Tree (true or false): " + testTree1.equals(testTree3));
			
		//Tests the equals() method for the first test Expression Tree and itself.	
			System.out.println("The first test Expression Tree is equal to itself (true or false): " + testTree1.equals(testTree1));
			
		//Tests the equals() method for the first test Expression Tree and the second test Expression Tree.	
			System.out.println("The first test Expression Tree is equal to the second test Expression Tree (true or false): " + testTree1.equals(testTree2));
			
		//Tests the equals() method for the second test Expression Tree and the third test Expression Tree.	
			System.out.println("The second test Expression Tree is equal to the third test Expression Tree (true or false): " + testTree2.equals(testTree3));
			
		//Tests the toString() method for the first Expression Tree, implicitly and explicitly.	
			System.out.println("\n" + testTree1 + "\n");
			System.out.println(testTree1.toString());
	}
}