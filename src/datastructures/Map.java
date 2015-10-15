package datastructures;

/**
 * A simple implementation of a Hashmap. Use a hash function to store Entries into an array.
 * This provides a unique index for each entry, allowing for O(1) access and modification
 * The hash function can be elaborated to be more efficient, accurate, etc.
 * @author Jalal Khan
 * 10/15/15
 * 
 */
public class Map<T> {

	/**
	 * The size of the storage array, 128 is the default, 
	 * but it can be specified by the overloaded constructor as well
	 */
	final int SIZE = 64;
	
	/**
	 * The array in which each entry is stored, allowing for fast lookup and modification
	 */
	Entry<T>[] table;
	
	/**
	 * Default constructor. Creates an array of size 64
	 */
	public Map() {
		table = new Entry[SIZE];
	}
	
	/**
	 * Overloaded constructor. Creates an array of a specified size
	 * @param size
	 */
	public Map(int size) {
		table = new Entry[size];
	}
	
	/**
	 * Gets an element based on a generic key in O(1) time due to the hash function that
	 * is applied
	 * @param T key 
	 * @return T value, null if not found
	 */
	public T get(T key) {
		int index = hash(key);

		if(table[index] == null) {
			return null;
		} else if(table[index].key == key) {
			return table[index].value;
		} else {
			return null;
		}
	}
	
	/**
	 * Store a generic value based on a unique, generic key
	 * @param T key identifier 
	 * @param T value object to store
	 */
	public void put(T key, T value) {
		int index = hash(key);
		table[index] = new Entry(key, value);
	}
	
	/**
	 * A simple hash function to compute an index for our table based on a unique key
	 * @param key the unique key would like to lookup
	 * @return int index of array the key is in
	 */
	private int hash(T key) {
		return key.hashCode() % table.length;
	}
	
	class Entry<T> {
		T key, value;
		Entry<T> next;
		
		public Entry(T key, T value) {
			this.key 	= key;
			this.value 	= value;
		}
	}
}
