package priorityqueue;

public class PriorityQueue<T extends Comparable<T>> implements PQInterface {
	
	private Node header, tail;
	
	public PriorityQueue() {
		this.header = new Node();
		this.tail = new Node();
		
		header.next = tail;
		tail.prev = header;
	}
	
	/**
	 * Walk down the queue until an element with less priority or the end
	 * of the queue is reached. Insert a new node there and push everything
	 * else down
	 */
	public void enqueue(Comparable item) {
		//walk down the queue until the appropriate node is reached
		Node current = this.header.next;
		while(current.next != null && item.compareTo(current.data) <= 0) {
			current = current.next;
		}
		
		//create a new node and insert it into the list
		//pushing the current node down
		Node insert = new Node();
		insert.data = (T) item;
		
		insert.next = current;
		insert.prev = current.prev;
		current.prev = insert;
		insert.prev.next = insert;
	}

	/**
	 * Remove the first item from the queue and return it
	 */
	public T dequeue() {
		
		Node first = header.next;
		
		header.next = first.next;
		first.next.prev = header;
		
		return first.data;
	}

	/**
	 * Return the first node's data if that node exists
	 * Otherwise, return null
	 */
	public T front() {
		return header.next != tail ? header.next.data : null;
	}

	/**
	 * If header points to tail, the list is empty
	 */
	public boolean isEmpty() {
		return header.next == tail;
	}

	/**
	 * A linked list implementation cannot be full
	 */
	public boolean isFull() {
		return false;
	}

	
	public String toString() {
		String output = "";
		Node walker = header.next;
		
		while(walker.next != null) {
			output += walker.data + "\n";
			walker = walker.next;
		}
		
		return output;
	}


	class Node {
		T data;
		Node next, prev;
	}
	
	public static void main(String args[]) {
		PriorityQueue<Student> q = new PriorityQueue<Student>();
	}
}
