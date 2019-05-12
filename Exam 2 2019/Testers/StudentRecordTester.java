import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StudentRecordTester {
	
	public StudentRecord s1;
	public StudentRecord s2;
	public StudentRecord s3;
	public StudentRecord s4;
	public StudentRecord s5;
	public StudentRecord s6;
	public StudentRecord s7;
	public StudentRecord s8;
	
	public StudentRecord[] onlyOne1;
	public StudentRecord[] onlyOne2;
	public StudentRecord[] onlyThree1;
	public StudentRecord[] onlyThree2;
	public StudentRecord[] oddStudents;
	public StudentRecord[] evenStudents;
	public StudentRecord[] allStudents;
	
	public StudentRecord[] withDuplicates1;
	public StudentRecord[] withDuplicates2;
	
	@Before
	public void setup() {
		
		s1 = new StudentRecord("1", "Bienve", "Computer Science", 3.23, 4);
		s2 = new StudentRecord("2", "Harry", "Computer Engineering", 3.5, 2);
		s3 = new StudentRecord("3", "John", "Mathematics", 3.5, 1);
		s4 = new StudentRecord("4", "Mary", "Computer Science", 3.7, 2);
		s5 = new StudentRecord("5", "Jeane", "Computer Science", 3.8, 1);
		s6 = new StudentRecord("6", "Ana", "Physics", 4.0, 3);
		s7 = new StudentRecord("7", "Enrique", "English", 2.8, 5);
		s8 = new StudentRecord("8", "Josian", "Software Engineering", 3.0, 3);
		
		onlyOne1 = new StudentRecord[] {s1};
		onlyOne2 = new StudentRecord[] {s2};
		
		onlyThree1 = new StudentRecord[] {s1, s3, s5};
		onlyThree2 = new StudentRecord[] {s2, s4, s6};
		
		oddStudents = new StudentRecord[] {s1, s3, s5, s7};
		evenStudents = new StudentRecord[] {s2, s4, s6, s8};
		
		allStudents = new StudentRecord[] {s1, s2, s3, s4, s5, s6, s7, s8};
		
		withDuplicates1 = new StudentRecord[] {s1, s1, s2, s3};
		withDuplicates2 = new StudentRecord[] {s1, s2, s3, s1};
		
	}

	

	@Test
	public void testHasDuplicates() {
		assertFalse("hasDuplicates incorrectly returns true", StudentRecord.hasDuplicateStudents(onlyOne1));
		assertFalse("hasDuplicates incorrectly returns true", StudentRecord.hasDuplicateStudents(onlyOne2));
		assertTrue("hasDuplicates incorrectly returns false", StudentRecord.hasDuplicateStudents(withDuplicates1));
		assertTrue("hasDuplicates incorrectly returns false", StudentRecord.hasDuplicateStudents(withDuplicates2));
		assertFalse("hasDuplicates incorrectly returns true", StudentRecord.hasDuplicateStudents(allStudents));
	}
	
	@Test
	public void testIsInClass() {
		
		assertFalse("isInClass incorrectly returns true", s1.isInClass(onlyOne2));
		assertTrue("isInClass incorrectly returns false", s1.isInClass(onlyOne1));
		assertFalse("isInClass incorrectly returns true", s3.isInClass(evenStudents));
		assertTrue("isInClass incorrectly returns false", s4.isInClass(evenStudents));
		assertTrue("isInClass incorrectly returns false", s8.isInClass(allStudents));
		
	}
	
	@Test
	public void testFindLowestGPA() {
		assertEquals("findLowestGPA returns incorrect student ", s1, StudentRecord.findLowestGPA(onlyOne1));
		assertEquals("findLowestGPA returns incorrect student ", s2, StudentRecord.findLowestGPA(onlyOne2));
		assertNotEquals("findLowestGPA returns incorrect student ", s1, StudentRecord.findLowestGPA(onlyOne2));
		assertEquals("findLowestGPA returns incorrect student ", s7, StudentRecord.findLowestGPA(allStudents));
		assertEquals("findLowestGPA returns incorrect student ", s7, StudentRecord.findLowestGPA(oddStudents));
	}

	@Test
	public void testCountByGPAAndMajor() {
		assertEquals("countByGPAMajor returns incorrect count", 3, StudentRecord.countByGPAAndMajor(allStudents,3.0,"Computer Science"));
		assertEquals("countByGPAMajor returns incorrect count", 1, StudentRecord.countByGPAAndMajor(onlyOne1,3.0,"Computer Science"));
		assertEquals("countByGPAMajor returns incorrect count", 0, StudentRecord.countByGPAAndMajor(onlyOne1,3.0,"Physics"));
		assertEquals("countByGPAMajor returns incorrect count", 0, StudentRecord.countByGPAAndMajor(allStudents,3.0,"Literature"));
		assertEquals("countByGPAMajor returns incorrect count", 1, StudentRecord.countByGPAAndMajor(allStudents,3.0,"Software Engineering"));
		assertEquals("countByGPAMajor returns incorrect count", 0, StudentRecord.countByGPAAndMajor(allStudents,3.5,"Software Engineering"));
	}
	
	@Test
	public void testCompareTo() {
		assertEquals("compareTo returns incorrect result", 0, s1.compareTo(s1));
		assertNotEquals("compareTo returns incorrect result", 1, s1.compareTo(s2));
		assertNotEquals("compareTo returns incorrect result", -1, s2.compareTo(s1));
		assertEquals("compareTo returns incorrect result", 1, s2.compareTo(s3));
		assertEquals("compareTo returns incorrect result", -1, s3.compareTo(s2));
		assertEquals("compareTo returns incorrect result", 1, s5.compareTo(s3));
		assertEquals("compareTo returns incorrect result", -1, s3.compareTo(s5));
	}
}