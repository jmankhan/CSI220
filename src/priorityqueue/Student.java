package priorityqueue;
public class Student implements Comparable<Student> {
	private String lname = null; // Student�s last name
	private String fname = null; // Student�s first name
	private Integer year = null; // Graduation year


	/**
	 * Default constructor, takes first name, last name, and student's grad. year
	 */
	public Student(String lname, String fname, Integer year) {
		this.lname = lname; // Assign the parameters (whose
		this.fname = fname; // scope is local) to the
		this.year = year; // global instance variables.
	}

	/**
	 * Compares this Student object to another Student object
	 * If this students year is smaller, 1 will be returned, -1 if bigger
	 * If the years are equal, but this students last name comes first
	 * alphabetically, 1 will be returned, -1 if it comes after
	 * If last name and years are equal, 0 will be returned
	 * 
	 */
	public int compareTo(Student that) {
		if(Integer.compare(this.year, that.year) == 0) {
			return -this.lname.compareTo(that.lname);
		} else {
			return -Integer.compare(this.year, that.year);
		}
	}

	public String toString() {
		return lname + " " + fname + " " + year;
	}
	
	
}