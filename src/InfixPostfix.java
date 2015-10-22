import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

import datastructures.Map;
import datastructures.Stack;

/**
 * This program will take in a standard infix expression with only variables (no
 * numbers) and convert it to a postfix expression using stacks and maps TODO
 * exponents
 * 
 * @author Jalal Khan
 *
 */
public class InfixPostfix {

	private Stack<String> operatorStack;
	private Map<String, Integer> stackPriority, inputPriority;

	/**
	 * Sets up the data structures used to store each operator and operand
	 * 
	 * @throws FileNotFoundException
	 */
	public InfixPostfix(File file) throws FileNotFoundException {
		setupPriorities();

		Scanner in = new Scanner(file);

		// print out one line from the input file
		while (in.hasNextLine())
			System.out.println(convert(in.nextLine()));

		in.close();
	}

	public void setupPriorities() {
		operatorStack = new Stack<String>();
		stackPriority = new Map<String, Integer>();
		inputPriority = new Map<String, Integer>();

		// setup priorities
		stackPriority.put("^", 4);
		stackPriority.put("*", 3);
		stackPriority.put("/", 3);
		stackPriority.put("+", 2);
		stackPriority.put("-", 2);
		stackPriority.put("==", 1);
		stackPriority.put(">=", 1);
		stackPriority.put("<=", 1);
		stackPriority.put("#", 0);
		stackPriority.put("(", 1);
		stackPriority.put(")", 0);

		inputPriority.put("^", 5);
		inputPriority.put("*", 3);
		inputPriority.put("/", 3);
		inputPriority.put("+", 2);
		inputPriority.put("-", 2);
		inputPriority.put("==", 1);
		inputPriority.put(">=", 1);
		inputPriority.put("<=", 1);
		inputPriority.put("#", 0);
		inputPriority.put("(", 0);
		inputPriority.put(")", 0);
	}

	public String convert(String expression) {

		// create alphabet for reference
		String alphabet = "abcdefghijklmnopqrstuvwxyz";

		// initialize tokenizer to parse infix expression
		StringTokenizer tokenizer = new StringTokenizer(expression);

		// initialize output StringBuffer for efficient appending
		StringBuffer postfix = new StringBuffer();

		// initialize the stack with # as the first element
		operatorStack.push("#");

		// begin reading the expression
		String token = tokenizer.nextToken();

		// read each token from the expression until the end is reached
		while (!token.equals("#")) {

			// token is an operand, add it to the output
			if (alphabet.indexOf(token) != -1) {
				postfix.append(token);
			}
			// token is a parentheses, evaluate sub expression
			else if (token.equals("(")) {
				operatorStack.push(token);
			} else if (token.equals(")")) {
				// add the subexpression to the output
				while (!operatorStack.top().equals("(")) {
					postfix.append(operatorStack.pop());
				}
				// remove '(' from the stack so it does not appear
				// in the output
				operatorStack.pop();
			} else { // token must be an operator
				// check each operator in the stack until one
				// with sufficient priority is reached
				String operator = operatorStack.top();
				while (stackPriority.get(operator) >= inputPriority.get(token)) {
					operator = operatorStack.pop();
					postfix.append(operator);
					operator = operatorStack.top();
				}

				operatorStack.push(token);
			}

			token = tokenizer.nextToken();
		}

		while (!operatorStack.isEmpty()) {
			String operator = operatorStack.pop();
			postfix.append(operator);
		}

		return postfix.toString();
	}

	public static void main(String args[]) {
		// read the input file specified via command line
		try {
			File file = new File(args[0]);
			new InfixPostfix(file);
		} catch (FileNotFoundException e) {
			System.out.println("Input file not found");
		}
	}
}
