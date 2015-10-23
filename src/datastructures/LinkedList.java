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
	private Node header, tail;
	
	/**
	 * Default constructor. Instantiates the header sentinel
	 */
	public LinkedList() {
		header = new Node();
		tail = new Node();

		header.next = tail;
		tail.prev = header;
	}

	/**
	 * Add data to the end of the list
	 * @param T data
	 */
	public Node add(T data) {
		
		//create node to insert
		Node temp = new Node();
		temp.data = data;
		
		//add it after the last item, but before tail
		//make sure to assign each pointer in the correct order
		tail.prev.next = temp;
		temp.prev = tail.prev;
		tail.prev = temp;
		temp.next = tail;
		
		return tail.prev;
	}
	
	/**
	 * Returns the first item's data if it exists, else return null
	 */
	public T getFirst() {
		return header.next == null ? null : header.next.data;
	}
	
	/**
	 * Gets the last item if it exists (if the list is not empty),
	 * else returns null
	 */
	public T getLast() {
		return tail.prev == null ? null : tail.prev.data;
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

		//if the list ends before the index is reached
		//an index greater than the length of the list was requested
		//so return null
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
		
		while(temp.next != tail) {
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
			temp.prev = header;
			return first.data;
		}
	}
	
	/**
	 * Remove the last node in the list and return it before removing for testing
	 * @return
	 */
	public T removeLast() {
		if(tail.prev == null) {
			return null;
		}

		Node last = tail.prev;
		last.prev.next = tail;
		tail.prev = last.prev;
		
		return last.data;
	}

	/**
	 * Returns false if there are any items in the list
	 * @return
	 */
	public boolean isEmpty() {
		return header.next == tail ? true : false;
	}
	
	class Node {
		T data;
		Node next, prev;
		
		public Node() {
			this(null);
		}
		
		public Node(T data) {
			this.data = data;
		}
	}
}
