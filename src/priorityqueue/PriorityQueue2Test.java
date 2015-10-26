package priorityqueue;

import static org.junit.Assert.*;

import org.junit.Test;

public class PriorityQueue2Test {

	@Test
	public void testInteger() {
		PriorityQueue2<Integer> q = new PriorityQueue2<Integer>();
		
		assertNotNull(q);
		assertTrue(q.isEmpty());
		assertFalse(q.isFull());
		
		q.enqueue(30);
		assertFalse(q.isEmpty());
		assertFalse(q.isFull());
		assertEquals(q.dequeue(), 30);
		
		assertTrue(q.isEmpty());
		
		for(int i=0; i<100; i++) {
			q.enqueue(i);
		}
		
		for(int i=99; i>=0; i--) {
			assertEquals(q.dequeue(), i);
		}
		
		assertTrue(q.isEmpty());
	}
	
	@Test
	public void testStudent() {
		PriorityQueue2<Student> q = new PriorityQueue2<Student>();
		
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
