import java.util.ArrayList;
import java.util.Random;

public class Patient {

	private static int lastRecordNumber = 0;

	private int recordNumber;
	private String patientTag;
	private String firstName;
	private String middleName;
	private String lastNames;
	private double height;
	private double weight;
	private BloodType bloodType;
	private RH bloodRH;
	private Sex sex;

	public enum BloodType {
		AB, A, B, O
	}

	public enum RH{
		POS, NEG
	}

	public enum Sex {
		FEMALE, MALE, OTHER
	}

	// Constructors
	public Patient(String firstName, String middleName, String lastNames, double height, double weight, BloodType bloodType, RH bloodRH, Sex sex) {
		this.recordNumber = lastRecordNumber++; 
		this.patientTag = "";  // Initials + random 4-digit number
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastNames = lastNames;
		this.height = height;
		this.weight = weight;
		this.bloodType = bloodType;
		this.bloodRH = bloodRH;
		this.sex = sex;
	}

	/**
	 *  Copy Constructor creates a patient with the same properties as the
	 *  parameter patient, except for the unique identifiers: recordNumber
	 *  and patientTag)
	 */
	public Patient(Patient p) {
		this(p.firstName, p.middleName, p.lastNames, p.height, p.weight, p.bloodType, p.bloodRH, p.sex);
	}


	// Getters
	public int getRecordNumber() { return recordNumber; }
	public String getPatientTag() { return patientTag; }
	public String getFirstName() { return firstName; }
	public String getMiddleName() { return middleName; }
	public String getLastNames() { return lastNames; }
	public double getHeight() { return height; }
	public double getWeight() { return weight; }
	public BloodType getBloodType() { return bloodType; }
	public RH getBloodRH() {return bloodRH;}
	public Sex getSex() { return sex; }


	// Setters
	public void setRecordNumber(int recordNumber) { this.recordNumber = recordNumber; }
	public void setPatientTag(String patientTag) { this.patientTag = patientTag;}
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public void setMiddleName(String middleName) { this.middleName = middleName; }
	public void setLastNames(String lastNames) { this.lastNames = lastNames; }
	public void setHeight(double height) { this.height = height; }
	public void setWeight(double weight) { this.weight = weight; }
	public void setBloodType(BloodType bloodType) { this.bloodType = bloodType; }
	public void setSex(Sex sex) { this.sex = sex; }


	// Instance Methods

	/**
	 * Returns true if and only if both the target and the parameter objects
	 * have exactly the same attributes (except unique identifiers: recordNumber
	 * and patientTag) .  Returns false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Patient)) { return false; }
		Patient c = (Patient) o;
		return ((c.firstName.equals(this.firstName)) &&
				(c.middleName.equals(this.middleName)) &&
				(c.lastNames.equals(this.lastNames)) &&
				(c.height == this.height)&&
				(c.weight == this.weight) &&
				(c.bloodType == this.bloodType) &&
				(c.sex == this.sex));
	}

	/**
	 * Get Initials -- Assumes all name fields have content and that
	 * two last names are separated by a space 
	 * 
	 */
	public String getInitials() {
		return getFirstName().substring(0,1) + getMiddleName().substring(0,1) + 
				getLastNames().substring(0,1) + 
				getLastNames().substring(getLastNames().indexOf(" ")+1,
						getLastNames().indexOf(" ")+2);
	}

	/**
	 * Returns true if the target patient (recipient) and parameter (donor) 
	 * patient have compatible blood types. They are compatible if the donor
	 * has a negative RH factor or if the recipient has a positive RH factor.
	 * 
	 */
	public boolean hasCompatibleRHFactor(Patient p) {
		if(p.bloodRH == RH.NEG)
			return true;

		if(this.getBloodRH() == RH.POS)
			return true;

		return false;
	}


	/**
	 * EXERCISE #1 -- FIND COMPATIBLE PAIR
	 * Returns an array with the first two Patients in the parameter array that 
	 * have compatible RH factors.  Returns NULL is no such two patients exist.
	 */
	public static Patient[] findCompatiblePair(Patient[] patients) {
		Patient[] neue = new Patient[2];
		for(int i = 0; i < patients.length; i++) {
			for(int j = i + 1; j < patients.length; j++) {
				if(patients[i].hasCompatibleRHFactor(patients[j])) {
					neue[0] = patients[i];
					neue[1] = patients[j];
					return neue;
				}
			}
			
		}
		return null;
	}

	/**
	 * EXERCISE #2 - FIND
	 * Returns the position within the parameter array where the target Patient
	 * appears. Use the record number to match the patients.
	 * Returns -1 if the target patient is not in the array.
	 */
	public int find(Patient[] patients) {
		for(int i = 0; i < patients.length; i++) {
			if(patients[i].getRecordNumber() == this.recordNumber) {
				return patients[i].recordNumber;
			}
		}
		return -1;
	}

	/**
	 * EXERCISE #3 - AVERAGE
	 * Returns the average weight among all patients in the given array
	 * You may assume that that the parameter array has at least one
	 * patient.
	 */
	public static double averageWeight(Patient[] patients) {
		int count = 0;
		double sum = 0;
		for(int i = 0; i < patients.length; i++) {
			count++;
			sum += patients[i].getWeight();
		}
		return sum/count;
	}

	/**
	 * EXERCISE #4 -- FIND & RETURN ARRAY
	 * Returns a FULL array of Patient's in the parameter array that have
	 * compatible RH factors with the target patient.  Returns NULL 
	 * if no such patients exist in the parameter array.
	 * HINT: You may find the hasCompatibleRHFactor method useful
	 */
	public Patient[] findRecipients(Patient[] patients) {
		if(patients.length == 0) return null;
		
		ArrayList<Patient> neue = new ArrayList<Patient>();
		for(int i = 0; i < patients.length; i++) {
			if((this.hasCompatibleRHFactor(patients[i]))) {
				neue.add(patients[i]);
			}
		}
		return neue.toArray(new Patient[0]);
	}


	private static boolean eq(double d1, double d2) {
		return Math.abs(d1-d2) < 1e-2;
	}

	/**
	 * EXERCISE #5 -- COMPARE
	 * Compares target and parameter Patient's first by height and then by weight
	 * Returns 1 if target is "larger", -1 if parameter is larger, and 0 otherwise.
	 * A Patient A is larger that a Patient B if:
	 *     A is taller than B, and both have the same weight
	 *     A has the same height as B and A is heavier than B
	 * Heights and weights should be compared to within 1e-2 accuracy. That is
	 * Heights and weights are considered "equal" if their differences are less than 1e-2.
	 * @param p2
	 * @return
	 */
	public int compareTo(Patient p2) {
		if(this.height != p2.getHeight()) {
			if(this.height > p2.getHeight()) {
				return 1;
			}
			return -1;
		}
		else if(this.height == p2.getHeight()) {
			if(this.weight != p2.getWeight()) {
				if(this.weight > p2.getWeight()) {
					return  1;
				}
				return -1;
			}
			
		}
		
		return 0;
	}

}