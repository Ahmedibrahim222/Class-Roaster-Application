package com.sg.ClassRoster.DAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.sg.ClassRoster.DTO.Student;

public class ClassRosterDaoFileImpl implements ClassRosterDao{
	
	public static final String ROSTER_FILE = "roster.txt";
	public static final String DELIMITER = "::";
	
	final Map<String, Student> students = new HashMap<>();

	@Override
	public Student addStudent(String studentId, Student student) throws ClassRosterPersitenceException{
		
			loadRoster();
			Student newStudent = students.put(studentId, student);
			writerRoster();
			return newStudent;
		//return students.put(studentId, student);
	}

	@Override
	public List<Student> getAllStudents() throws ClassRosterPersitenceException{
		
		loadRoster();
		return new ArrayList<Student>(students.values());
	}

	@Override
	public Student getStudent(String studentId) throws ClassRosterPersitenceException{
		loadRoster();
		return students.get(studentId);
	}

	@Override
	public Student removeStudent(String studentId) throws ClassRosterPersitenceException{
		loadRoster();
		Student removedStudent = students.remove(studentId);
		writerRoster();
		return removedStudent;
	}
	
	private Student unmarshalStudent(String studentAsText) {
		String[] studentTokens = studentAsText.split(DELIMITER);
		String studentId = studentTokens[0];
		Student studentFromFile = new Student(studentId);
		
		studentFromFile.setFirstName(studentTokens[1]);
		studentFromFile.setLastName(studentTokens[2]);
		studentFromFile.setCohort(studentTokens[3]);
		
		return studentFromFile;
	}
	
	private void loadRoster() throws ClassRosterPersitenceException{
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
			String currentLine;
			Student currentStudent;
			
			while(scanner.hasNext()) {
				currentLine = scanner.nextLine();
				currentStudent = unmarshalStudent(currentLine);
				
				students.put(currentStudent.getStudentId(), currentStudent);
			}
		}catch(FileNotFoundException e) {
			throw new ClassRosterPersitenceException("-_- Could not load roster data into memory.", e);
		}
		finally {
			if(scanner!=null) {
				scanner.close();
			}
		}
	}
	
	private String marshalStudent(Student aStudent) {
		String studentAsText = aStudent.getStudentId() + DELIMITER;
		studentAsText += aStudent.getFirstName() + DELIMITER;
		studentAsText += aStudent.getLastName() + DELIMITER;
		studentAsText += aStudent.getCohort();
		
		return studentAsText;
	}
	
	private void writerRoster() throws ClassRosterPersitenceException{
		
		PrintWriter out = null;
		
		try {
			out = new PrintWriter(new FileWriter(ROSTER_FILE));
			
			String studentAsText;
			List<Student> studentList = new ArrayList(students.values());
			for(Student currentStudent : studentList) {
				studentAsText = marshalStudent(currentStudent);
				out.println(studentAsText);
				out.flush();
			}
		}catch(IOException e) {
			throw new ClassRosterPersitenceException("Could not save student data.", e);
		}
		finally {
			if(out!=null) {
				out.close();
			}
		}
		
	}

}
