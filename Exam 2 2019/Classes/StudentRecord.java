/**
 * Immutable class representing student records in a student record system.
 */
public class StudentRecord {

	private String id;
	private String name;
	private String major;
	private double gpa;
	private int yearsInCollege;

	public StudentRecord(String id, String name, String major, double gpa, int yearsInCollege) {
		this.id = id;
		this.name = name;
		this.major = major;
		this.gpa = gpa;
		this.yearsInCollege = yearsInCollege; 
	}

	// Getters
	public String getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getMajor() {
		return major;
	}

	public double getGPA() {
		return gpa;
	}

	public int getYearsInCollege() {
		return yearsInCollege;
	}

	/**
	 * Returns true if and only if target and parameter StudentRecord's have equal ID's
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof StudentRecord) {
			StudentRecord sr = (StudentRecord) o;
			return this.getID().equals(sr.getID());
		}
		return false;
	}

	/**
	 * Returns a human-readable String representation of the target StudentRecord
	 */
	@Override
	public String toString() {
		return "Student[" + 
				this.getID() + "," +
				this.getName() + "," +
				this.getMajor() + "," +
				this.getGPA() + "," +
				this.getYearsInCollege()
				;
	}

	/**
	 * Exercise 1
	 * Returns true if and only if there is at least one duplicate StudentRecord in the parameter array.
	 * StudentRecord's are considered duplicates if they have the same ID.
	 * You may assume that the course has at least one student registered.
	 * @param course
	 * @return
	 */
	public static boolean hasDuplicateStudents(StudentRecord[] course) {
		for(int i = 0; i < course.length; i++) {
			for(int j = i + 1; j < course.length; j++) {
				if(course[i].id == course[j].id) {
					return true;
				}
			}

		}
		return false;
	}

	/**
	 * Exercise 2
	 * Returns true if and only if the target student is registered in the parameter course.
	 * You may assume that the course has at least one student registered.
	 * @param course
	 * @return
	 */
	public boolean isInClass(StudentRecord[] course) {
		for(int i = 0; i < course.length; i++) {
			if(this == course[i]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Exercise 3
	 * Returns the StudentRecord with the lowest GPA in a course.
	 * If multiple students have the same minimal GPA, then return
	 * the first student in the list (lowest index) with that GPA.
	 * You may assume that the course has at least one student registered.
	 * @param course
	 * @return
	 */
	public static StudentRecord findLowestGPA(StudentRecord[] course) {
		int lowest = (int) course[0].getGPA();
		StudentRecord stud = course[0];
		for(int i = 0; i < course.length; i++) {
			if(course[i].getGPA() < lowest) {
				lowest = (int) course[i].getGPA();
				stud = course[i];
			}
		}

		return stud;
	}

	/**
	 * Exercise 4
	 * Returns the number of students in the parameter course with a GPA
	 * greater or equal to the parameter minGPA and who are in the parameter
	 * major (e.g. "Computer Science").
	 * @param course
	 * @return
	 */
	public static int countByGPAAndMajor(StudentRecord[] course, double minGPA, String major) {
		int count = 0;
		for(int i = 0; i < course.length; i++) {
			if(course[i].getMajor() == major && (course[i].getGPA() >= minGPA)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Exercise 5
	 * Compares the target object with the given student.
	 * 
	 * Return 0 if they have the same GPA and Years In College.
	 * If they have different GPAs:
	 *     Returns a positive value if the target student has a higher GPA.
	 *     Returns a negative value if the target student has a lower GPA.
	 * If they have the same GPA use the Years in College for comparison as follows:
	 *     Returns a positive value if the target student has more years in college.
	 *     Returns a negative value if the target student has less years in college.
	 * 
	 * @param otherStudent
	 * @return
	 */
	public int compareTo(StudentRecord otherStudent) {
		if((this.gpa != otherStudent.getGPA())){
			if(this.gpa > otherStudent.getGPA()) {
				return 1;
			}
			return -1;
		}
		else if((this.gpa == otherStudent.getGPA()) ) {
			if(this.yearsInCollege != otherStudent.getYearsInCollege()) {
				if(this.yearsInCollege > otherStudent.getYearsInCollege()) {
					return 1;
				}
				return -1;
			}
		}
		return 0; 
	}
}