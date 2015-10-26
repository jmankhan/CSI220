package priorityqueue;

import datastructures.LinkedList;

public class PriorityQueue<T extends Comparable<T>> implements PQInterface {
	
	LinkedList<Comparable<T>> queue;
	
	public PriorityQueue() {
		queue = new LinkedList<Comparable<T>>();
	}

	/**
	 * Adds an item to the queue according to its priority
	 */
	@Override
	public void enqueue(Comparable item) {
		for(int i=0; i<queue.length(); i++) {
			
			//if item has higher priority than the currently
			//enqueued item, insert item in front of it
			//break out of the loop so we don't keep inserting
			//the item in front of everything
			if(item.compareTo(queue.get(i)) > 0) {
				queue.insert(item, i);
				return;
			}
		}
		
		//if the item is the lowest priority in the queue,
		//or the queue is empty
		//add it to the back
		queue.add(item);
	}

	/**
	 * Removes the first item from the queue and returns it
	 * Uses the LinkedList method removeFirst() 
	 */
	@Override
	public Comparable dequeue() {
		Comparable item = queue.removeFirst();
		return item;
	}

	/**
	 * Return the first item in the queue
	 */
	@Override
	public Comparable front() {
		return queue.getFirst();
	}

	/**
	 * Use the linkedlist implementation to check if the queue is empty
	 */
	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	/**
	 * Since this is a linked list implementation, the queue cannot be full
	 */
	@Override
	public boolean isFull() {
		return false;
	}
	
	public int length() {
		return queue.length();
	}

	@Override
	public String toString() {
		String output = "";
		for(int i=0; i<queue.length(); i++) {
			output += queue.get(i) + "\n";
		}
		
		return output;
	}
	
	
}
