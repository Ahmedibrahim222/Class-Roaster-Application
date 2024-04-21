package com.sg.ClassRoster.Service;

import java.util.List;

import com.sg.ClassRoster.DAO.ClassRosterPersitenceException;
import com.sg.ClassRoster.DTO.Student;

public interface ClassRosterServiceLayer {
	
	void createStudent(Student student) throws 
			ClassRosterDuplicateIdException,
			ClassRosterDataValidationException,
			ClassRosterPersitenceException;
	
	List<Student> getAllStudents()throws ClassRosterPersitenceException;
	Student getStudent(String studentId) throws ClassRosterPersitenceException;
	Student removeStudent(String studentId) throws ClassRosterPersitenceException;

}
