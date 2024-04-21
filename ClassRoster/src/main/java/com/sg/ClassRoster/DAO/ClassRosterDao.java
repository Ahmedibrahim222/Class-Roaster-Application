package com.sg.ClassRoster.DAO;

import java.util.List;

import com.sg.ClassRoster.DTO.Student;

public interface ClassRosterDao {
	
	Student addStudent(String studentId, Student student) throws ClassRosterPersitenceException;
	List<Student> getAllStudents() throws ClassRosterPersitenceException;
	Student getStudent(String studentId) throws ClassRosterPersitenceException;
	Student removeStudent(String studentId) throws ClassRosterPersitenceException;

}
