package priorityqueue;

import datastructures.LinkedList;

public class PriorityQueue<T extends Comparable<T>> implements PQInterface {
	
	LinkedList<Comparable<T>> queue;
	
	public PriorityQueue() {
		queue = new LinkedList<Comparable<T>>();
	}

	@Override
	public void enqueue(Comparable item) {
		for(int i=0; i<queue.length(); i++) {
			
			//if item has higher priority than the currently
			//enqueued item, insert item in front of it
			//break out of the loop so we don't keep inserting
			//the item in front of everything
			if(item.compareTo(queue.get(i)) > 0) {
				queue.insert(item, i);
				break;
			}
		}
	}

	@Override
	public Comparable dequeue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparable front() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}
}
