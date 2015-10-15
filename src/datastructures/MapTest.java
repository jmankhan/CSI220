package datastructures;

import static org.junit.Assert.*;

import org.junit.Test;

public class MapTest {

	@Test
	public void testMap() {
		Map<Integer, Integer> map = new Map<Integer, Integer>();
		assertNotNull(map);
		assertEquals(map.table.length, 64);
		assertNull(map.get(new Integer(5)));
	}

	@Test
	public void testMapInt() {
		Map<Integer, Integer> map = new Map<Integer, Integer>(50);
		assertNotNull(map);
		assertEquals(map.table.length, 50);
		assertNull(map.get(new Integer(51)));
	}

	@Test
	public void testGet() {
		Map<Integer, Integer> map = new Map<Integer, Integer>();
		map.put(5, 5);
		map.put(0, 1);

		assertNull(map.get(1));
		assertNotNull(map.get(0));

		assertEquals((int) map.get(0), 1);
		assertEquals((int) map.get(5), 5);
	}

	@Test
	public void testPut() {
		Map<Integer, Integer> map = new Map<Integer, Integer>();
		map.put(18, 5);
		map.put(18, 1);

		assertNull(map.get(17));
		assertNotNull(map.get(18));

		assertEquals((int) map.get(18), 1);
		assertNotEquals((int) map.get(18), 5);
		
		for(int i=0; i<500; i++) {
			map.put(i, -1);
		}
		
		assertNull(map.get(0));
	}
}
