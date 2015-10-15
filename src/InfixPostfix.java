import datastructures.Map;
import datastructures.Stack;

/**
 * This application will convert infix notation to postfix
 * It will prompt for an equation or expression until '#' marks the end
 * and output the converted form
 * @author Jalal Khan
 * 10/15/15
 *
 */
public class InfixPostfix {

	private Map<String, Integer> stackMap, incomingMap;
	private Stack<Character> stack;
	
	/**
	 * Default constructor. Initializes both maps and the stack
	 */
	public InfixPostfix() {
		
		stack		= new Stack<Character>();
		
		stackMap 	= new Map<String, Integer>();
		incomingMap = new Map<String, Integer>();
	}
}