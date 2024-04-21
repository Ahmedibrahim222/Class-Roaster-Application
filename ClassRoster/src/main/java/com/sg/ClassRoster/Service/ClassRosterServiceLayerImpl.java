package com.sg.ClassRoster.Service;

import java.util.List;

import com.sg.ClassRoster.DAO.ClassRosterAuditDao;
import com.sg.ClassRoster.DAO.ClassRosterDao;
import com.sg.ClassRoster.DAO.ClassRosterPersitenceException;
import com.sg.ClassRoster.DTO.Student;

public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer{
	
	private final ClassRosterDao dao;
	private final ClassRosterAuditDao auditDao;

	public ClassRosterServiceLayerImpl(ClassRosterDao dao, ClassRosterAuditDao auditDao) {
		this.dao = dao;
		this.auditDao = auditDao;
	}

	@Override
	public void createStudent(Student student)
			throws ClassRosterDuplicateIdException, ClassRosterDataValidationException, ClassRosterPersitenceException {
		
		if(dao.getStudent(student.getStudentId()) != null) {
			throw new ClassRosterDuplicateIdException(
					"ERROR: Could not create student. Student Id" + student.getStudentId() + "already exists");
		}
		validateStudentData(student);
		
		dao.addStudent(student.getStudentId(), student);
		
		auditDao.writeAuditEntry(
				"Student " + student.getStudentId() + " CREATED.");
	}

	@Override
	public List<Student> getAllStudents() throws ClassRosterPersitenceException {
		return dao.getAllStudents();
	}

	@Override
	public Student getStudent(String studentId) throws ClassRosterPersitenceException {
		return dao.getStudent(studentId);
	}

	@Override
	public Student removeStudent(String studentId) throws ClassRosterPersitenceException {
		
		Student removeStudent = dao.removeStudent(studentId);
		auditDao.writeAuditEntry("Student " + studentId + " REMOVED.");
		return removeStudent;
	}
	
	private void validateStudentData(Student student) throws ClassRosterDataValidationException{
		
		if(student.getFirstName() == null || student.getFirstName().trim().length() == 0
				|| student.getLastName() == null || student.getLastName().trim().length() == 0
				|| student.getCohort() == null || student.getCohort().trim().length() == 0) {
			throw new ClassRosterDataValidationException(
					"ERROR: ALL fields [First Name, Last Name, Cohort] are required.");
		}
		
	}

}
