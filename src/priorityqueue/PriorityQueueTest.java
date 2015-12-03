package priorityqueue;

import static org.junit.Assert.*;

import org.junit.Test;

public class PriorityQueueTest {

	@Test
	public void testInteger() {
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		
		assertNotNull(q);
		assertTrue(q.isEmpty());
		assertFalse(q.isFull());
		
		q.enqueue(30);
		assertFalse(q.isEmpty());
		assertFalse(q.isFull());
		assertEquals(q.dequeue(), new Integer(30));
		
		assertTrue(q.isEmpty());
		
		for(int i=0; i<100; i++) {
			q.enqueue(i);
		}
		
		for(int i=99; i>=0; i--) {
			assertEquals(q.dequeue(), new Integer(i));
		}
		
		assertTrue(q.isEmpty());
	}
	
	@Test
	public void testStudent() {
		PriorityQueue<Student> q = new PriorityQueue<Student>();
		
		assertTrue(q.isEmpty());
		Student a = new Student("Lhan", "Jalal", 2015);
		Student b = new Student("Khan", "Jalal", 2016);
		Student c = new Student("Abcd", "Jalal", 2018);
		Student d = new Student("Khan", "Jalal", 2018);
		
		q.enqueue(d);
		q.enqueue(c);
		q.enqueue(a);
		q.enqueue(b);
		
		assertEquals(q.dequeue(), a);
		assertEquals(q.dequeue(), b);
		assertEquals(q.dequeue(), c);
		assertEquals(q.dequeue(), d);
		
		assertTrue(q.isEmpty());
		assertFalse(q.isFull());
	}

}
