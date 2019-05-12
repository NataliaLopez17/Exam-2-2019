import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PatientTest {

	private static final double EPSILON = 0.000001;

	private Patient p1, p2, p3, p4, p5, p6, p7, p8;

	@Before
	public void setUP() {
        p1 = new Patient("Jose", "Diego", "Cruz Soto", 48, 85, Patient.BloodType.O, Patient.RH.NEG, Patient.Sex.MALE);
        p2 = new Patient("Nancy", "L", "Fas Mari", 70, 195, Patient.BloodType.O, Patient.RH.POS, Patient.Sex.FEMALE);
        p3 = new Patient("Juan", "Jose", "Soto Detres", 72, 205, Patient.BloodType.AB, Patient.RH.POS, Patient.Sex.MALE);
        p4 = new Patient("Ana", "M", "Cruz Detres", 60, 140, Patient.BloodType.AB, Patient.RH.NEG, Patient.Sex.FEMALE);
        p5 = new Patient("Tom", "B", "Doe Doe", 48, 85, Patient.BloodType.O, Patient.RH.NEG, Patient.Sex.MALE);
        p6 = new Patient("Sam", "L", "Doe Doe", 70, 85, Patient.BloodType.O, Patient.RH.POS, Patient.Sex.FEMALE);
        p7 = new Patient("Mark", "K", "Doe DOe", 72, 205, Patient.BloodType.AB, Patient.RH.POS, Patient.Sex.MALE);
        p8 = new Patient("Gia", "M", "Doe Doe", 60, 205, Patient.BloodType.AB, Patient.RH.NEG, Patient.Sex.FEMALE);	
	}

	@Test
	public void testFindCompatiblePair() {
      Patient[] group1 = new Patient[]{p1, p2, p3, p4};
      Patient[] group2 = new Patient[]{p5, p6, p7, p8};
      Patient[] group3 = new Patient[]{p1, p2}; // NULL
      Patient[] group4= new Patient[] {p2,p3,p6,p7}; // all POS
      Patient[] group5= new Patient[] {p8,p5,p4,p1}; // all NEG
      Patient[] pair1 = new Patient[]{p1, p4};
      Patient[] pair2 = new Patient[]{p5, p8};
      Patient[] pair3 = new Patient[]{p2, p3};
      Patient[] pair4 = new Patient[]{p8, p5};


      assertArrayEquals("Incorrect pair of patients", pair1, Patient.findCompatiblePair(group1)); //AAE
      assertNotNull("Should not be null",Patient.findCompatiblePair(group1)); //NN
      assertNull("Should be null",Patient.findCompatiblePair(group3)); //N
      assertArrayEquals("Incorrect pair of patients",pair2, Patient.findCompatiblePair(group2)); //AAE
      assertNotNull("Should not be null",Patient.findCompatiblePair(group2)); //NN
      assertArrayEquals("Incorrect pair of patients",pair3, Patient.findCompatiblePair(group4));
      assertArrayEquals("Incorrect pair of patients",pair4, Patient.findCompatiblePair(group5));

	}
	
	@Test
	public void testFind() {
      Patient[] group1 = new Patient[]{p1, p2, p3, p4, p5, p6, p7, p8};
      Patient[] group2 = new Patient[]{p1, p2};
      Patient[] group3 = new Patient[]{p1, p2, p3, p4};

		assertEquals("target Patient is in parameter array", 2, p3.find(group1));
		assertEquals("target Patient is not parameter array", -1, p3.find(group2));
		assertNotEquals("invalid position", 4, p4.find(group3));
	}
	
	
	@Test
	public void testAverageWeight() {
		Patient[] group1 = new Patient[]{p1, p2, p3, p4};	
		Patient[] group2 = new Patient[]{p1, p2};
        Patient[] group3= new Patient[] {p2,p3,p6,p7};
        Patient[] group4= new Patient[] {p8,p5,p4,p1};
        Patient[] group5= new Patient[] {p8,p5,p4,p1,p2,p3,p6,p7};
		
		assertEquals("Incorrect Avg.", 156.25, Patient.averageWeight(group1), EPSILON);
		assertEquals("Incorrect Avg.", 140.00, Patient.averageWeight(group2), EPSILON);
		assertEquals("Incorrect Avg.", 172.5, Patient.averageWeight(group3), EPSILON);
		assertEquals("Incorrect Avg.", 128.75, Patient.averageWeight(group4), EPSILON);		
		assertEquals("Incorrect Avg.", 150.625, Patient.averageWeight(group5), EPSILON);	
		assertNotEquals("Not an average", 280, Patient.averageWeight(group2), EPSILON);
		assertNotEquals("Not an average", 625, Patient.averageWeight(group1), EPSILON);
		assertNotEquals("Not an average", 4, Patient.averageWeight(group1), EPSILON);
	}
	
	
	@Test
	public void testFindRecipients() {
        Patient[] group1 = new Patient[]{p1, p2, p3, p4, p5, p6, p7, p8};
        Patient[] compatibles4 = new Patient[]{p1, p4, p5, p8};
        Patient[] compatibles8 = new Patient[]{p1, p2, p3, p4, p5, p6, p7, p8};

        assertNotNull("Should not be null",p1.findRecipients(group1)); //NN
        assertNotNull("Should not be null",p8.findRecipients(group1)); //NN       
        assertArrayEquals("Incorrect list of Recipients", compatibles4, p1.findRecipients(group1));
		assertArrayEquals("Incorrect list of Recipients", compatibles8, p2.findRecipients(group1));
		assertArrayEquals("Incorrect list of Recipients", compatibles8, p3.findRecipients(group1));
		assertArrayEquals("Incorrect list of Recipients", compatibles4, p4.findRecipients(group1));
		assertArrayEquals("Incorrect list of Recipients", compatibles4, p5.findRecipients(group1));
		assertArrayEquals("Incorrect list of Recipients", compatibles8, p6.findRecipients(group1));
		assertArrayEquals("Incorrect list of Recipients", compatibles8, p7.findRecipients(group1));
		assertArrayEquals("Incorrect list of Recipients", compatibles4, p8.findRecipients(group1));
	}
	
	@Test
	public void testCompareTo() {
		
		assertEquals("Both have same weight and height", 0, p1.compareTo(p5));
		assertNotEquals("Both have same weight and height", 1, p1.compareTo(p5));
		assertNotEquals("Both have same weight and height", -1, p1.compareTo(p5));
		assertEquals("Incorrect, one is taller", 1, p7.compareTo(p8));
		assertEquals("Incorrect, one is taller", -1, p8.compareTo(p7));
		assertEquals("Incorrect,one is heavier", -1, p2.compareTo(p3));
		assertEquals("Incorrect,one is heavier", 1, p3.compareTo(p2) );
		
	}

}