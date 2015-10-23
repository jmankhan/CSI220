package datastructures;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void testLinkedList() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		assertNotNull(list);
	}

	@Test
	public void testAdd() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		
		assertEquals((int) list.add(0).data, 0);
		assertEquals((int) list.add(1).data, 1);
		assertNotEquals((int) list.add(0).data, 1);
	}

	@Test
	public void testGetFirst() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(10);
		assertEquals((int) list.getFirst(), 10);
		
		list.add(15);
		list.add(-134);
		assertEquals((int) list.getFirst(), 10);
	}

	@Test
	public void testGetLast() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(10);
		
		assertEquals((int) list.getLast(), 10);
		
		list.add(15);
		assertEquals((int) list.getLast(), 15);

		list.add(15);
		assertEquals((int) list.getLast(), 15);
		
		list.add(-1532);
		assertEquals((int) list.getLast(), -1532);
	}

	@Test
	public void testGet() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		
		list.add(10);
		list.add(15);
		list.add(-10);
		list.add(1234);
		
		assertNotNull(list.get(0));
		assertEquals((int) list.get(0), 10);
		assertEquals((int) list.get(1), 15);
		assertEquals((int) list.get(2), -10);
		assertEquals((int) list.get(3), 1234);
	}

	@Test
	public void testRemoveFirst() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		
		list.add(10);
		assertNotNull(list.removeFirst());
		
		list.add(10);
		assertEquals((int)list.removeFirst(), 10);
	}

	@Test
	public void testRemoveLast() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		
		list.add(10);
		assertNotNull(list.removeLast());
		
		list.add(10);
		assertEquals((int) list.removeLast(), 10);
		
		list.add(10);
		list.add(15);
		list.add(-15);
		list.add(100);
		assertEquals((int) list.removeLast(), 100);
		assertEquals((int) list.removeLast(), -15);
		assertEquals((int) list.removeLast(), 15);
		assertEquals((int) list.removeLast(), 10);
		
	}
	
	@Test
	public void testIsEmpty() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		assertTrue(list.isEmpty());
		
		list.add(10);
		assertFalse(list.isEmpty());
	}
}
