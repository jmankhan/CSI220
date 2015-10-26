package priorityqueue;

import static org.junit.Assert.*;

import org.junit.Test;

public class PriorityQueueTest {

	@Test
	public void testEnqueue() {
		PriorityQueue<Student> q = new PriorityQueue<Student>();
		
		assertTrue(q.isEmpty());

		q.enqueue(new Student("B", "A", 2015));
		q.enqueue(new Student("A", "B", 2015));
		q.enqueue(new Student("A", "C", 2015));
		q.enqueue(new Student("A", "D", 2015));
		q.enqueue(new Student("A", "B", 2015));
		q.enqueue(new Student("Khan", "Jalal", 2018));
		q.enqueue(new Student("Mhan", "Jalal", 2018));
		q.enqueue(new Student("Lhan", "Jalal", 2018));
		
		assertFalse(q.isFull());
		assertFalse(q.isEmpty());
	}
	
	@Test
	public void testDequeue() {
		PriorityQueue<Student> q = new PriorityQueue<Student>();
		q.enqueue(new Student("B", "A", 2015));
		q.enqueue(new Student("A", "B", 2015));
		q.enqueue(new Student("A", "C", 2015));
		q.enqueue(new Student("A", "D", 2015));
		q.enqueue(new Student("A", "B", 2015));
		q.enqueue(new Student("Khan", "Jalal", 2018));
		q.enqueue(new Student("Mhan", "Jalal", 2018));
		q.enqueue(new Student("Lhan", "Jalal", 2018));
		q.enqueue(new Student("D", "E", 2016));
		int length = q.length();
		
		for(int i=0; i<length; i++) {
			System.out.println(q.dequeue());
		}

	}

}
