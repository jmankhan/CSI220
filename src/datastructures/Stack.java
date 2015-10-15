package datastructures;

/**
 * A Stack implementation using a LinkedList allowing for *infinite* size
 * @author Jalal Khan
 *
 */
public class Stack<T> {
	
	private LinkedList<T> list;
	
	/**
	 * Default constructor. Initializes the linkedlist
	 */
	public Stack() {
		list = new LinkedList<T>();
	}
	
	/**
	 * Pushes a new item on top of the stack
	 */
	public void push(T data) {
		list.add(data);
	}
	
	/**
	 * Removes and returns the top item on the stack (most recently added)
	 */
	public T pop() {
		return list.removeLast();
	}
	
	/**
	 * Returns the top item on the stack (most recently added)
	 * Does not remove it from the stack
	 * @return
	 */
	public T top() {
		return list.getLast();
	}
	
	/**
	 * Checks if there are any items in the list. True if there are 0, False if > 0
	 * @return
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}
}