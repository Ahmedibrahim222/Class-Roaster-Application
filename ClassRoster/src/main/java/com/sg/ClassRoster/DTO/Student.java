package com.sg.ClassRoster.DTO;

public class Student {
	
	private String firstName;
	private String lastName;
	final String studentId;
	private String cohort;
	
	public Student(String studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCohort() {
		return cohort;
	}

	public void setCohort(String cohort) {
		this.cohort = cohort;
	}

	public String getStudentId() {
		return studentId;
	}
	

}
