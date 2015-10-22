package datastructures;

/**
 * A generic, singly linked list implementation. There is one header sentinel node
 * @author Jalal Khan
 *
 */
public class LinkedList<T> {

	/**
	 * Header sentinel
	 */
	private Node header;
	
	/**
	 * Default constructor. Instantiates the header sentinel
	 */
	public LinkedList() {
		header = new Node();
	}

	/**
	 * Add data to the end of the list
	 * @param T data
	 */
	public Node add(T data) {
		Node temp = header;
		
		//walk down the list until temp gets to the end
		while(temp.next != null) {
			temp = temp.next;
		}
		
		temp.next = new Node(data);
		return temp.next;
	}
	
	/**
	 * Returns the first item's data if it exists, else return null
	 */
	public T getFirst() {
		return header.next == null ? null : header.next.data;
	}
	
	/**
	 * Gets the last item's data and return it
	 */
	public T getLast() {
		Node walker = header.next;
		
		//if the list is empty, return null
		if(walker == null)
			return null;
		
		while(walker.next != null) {
			walker = walker.next;
		}
		
		return walker.data;
	}

	/**
	 * Get the specified item's data
	 * @param int index item to get
	 */
	public T get(int index) {
		Node walker = header.next;
		
		int count = 0;
		while(walker.next != null && index > count) {
			walker = walker.next;
			count++;
		}

		//if the list ends before the index is reached, return null
		if(index > count)
			return null;
		
		return walker.data;
	}

	/**
	 * Finds the length of the list by walking down each node
	 * @return int length of list
	 */
	public int length() {
		
		//start at header
		Node temp = this.header;
		int count = 0;
		
		while(temp.next != null) {
			temp = temp.next;
			count++;
		}
		
		return count;
	}
	
	/**
	 * Removes the first item in the list and returns it for testing
	 * @return
	 */
	public T removeFirst() {
		Node first = header.next;
		if(first == null)
			return null;
		else {
			Node temp = first.next;
			this.header.next = temp;
			return first.data;
		}
	}
	
	/**
	 * Remove the last node in the list and return it before removing for testing
	 * @return
	 */
	public T removeLast() {
		Node walker = header;
		
		if(walker.next == null)
			return null;
		
		//keep going until the second last node
		while(walker.next.next != null) {
			walker = walker.next;
		}
		
		//set second last node next pointer to null
		//effectively deconstructing the last node
		Node last = walker.next;
		walker.next = null; 
		
		return last.data;
		
	}

	/**
	 * Returns false if there are any items in the list
	 * @return
	 */
	public boolean isEmpty() {
		return header.next == null ? true : false;
	}
	class Node {
		T data;
		Node next;
		
		public Node() {
			this(null);
		}
		
		public Node(T data) {
			this.data = data;
		}
	}
}
