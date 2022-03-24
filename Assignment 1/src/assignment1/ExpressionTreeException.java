package assignment1;
/**
 * ICSI 311 Principles of Programming Languages
 * Fall 2021
 * TA Phipps
 * Student ID: 001440162
 * 
 * When an operation on a Expression Tree fails, an ExpressionTreeException is thrown to handle that failure.
 * @author Kiran Aziz
 * @version 1.0
 */
public class ExpressionTreeException extends RuntimeException {
	/**
     * Generated serial Version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an exception object with a message.
     * @param message A reference to an exception message.
     */
    public ExpressionTreeException(String message) {
        super(message);
    }
	
}
