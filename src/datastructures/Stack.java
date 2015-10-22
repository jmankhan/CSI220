package datastructures;

<<<<<<< HEAD

/*
 *    An implementation of the StackInterface.
 *    A singly linked list implementation is used.
 */

public class Stack <T> implements StackInterface<T> {

	private Node header = null; // Pointer to the first item in the list.
	
	/*
	 *   push(item) - add item to the stack.  It is at
	 *                the front of the list.
	 */
	
	public void push(T item)
	{
		Node ptr = new Node();  // Get a new node.
		
		ptr.data = item;    // Store the new item in the node.
		ptr.next = header;  // Link new node to front of list.
		header = ptr;       // New node is now at Top of Stack. 
	}
	
	
	/*
	 *   pop() - remove the top item from the stack and
	 *           return it as the function value.
	 */
	
	public T pop()
	{
		if (!isEmpty()) {
		    T item = top();        // Get the top item to return.
		    header = header.next;  // Remove top item from the stack.
		    return item;
		}
		return null;
	}
	
	/*
	 *   top() - return the top stack item.
	 */
	
	public T top()
	{
		if (!isEmpty()) {
		    return header.data;  // Return the item at the top
		}                        // of the stack.
		return null;
	}
	
	/*
	 *    isEmpty() - return True if the stack is empty
	 *                otherwise return False.
	 */
	
	public boolean isEmpty()
	{                             // The stack is empty if header
		return header == null;    // doesn't point to a Node.
	}
	
	
	public boolean isFull()    // For a linked list implementation
	{                          // the stack is never full.
		return false;
	}
	
	
	public String toString()
	{
		Node ptr = header;
		String str = "The Stack\n---------\n";
		
		while (ptr != null) {
			str = str + ptr.data.toString() + "\n";
			ptr = ptr.next;
		}
		
		str = str + "---------\n";
		return str;
	}
		
	
	/*
	 *    Inner Class - Node objects for a singly linked list.
	 *    
	 */

	private class Node
	{
		public T data;      // Data stored in the Node.
		public Node next;   // Pointer to next Node in the list.
	}
}
=======
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
>>>>>>> 86e4c49ebca419df296ec91a6d01aeff97ed2c0d
