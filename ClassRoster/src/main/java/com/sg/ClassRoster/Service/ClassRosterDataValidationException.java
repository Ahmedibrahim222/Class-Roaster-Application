package com.sg.ClassRoster.Service;

public class ClassRosterDataValidationException extends Exception{

	public ClassRosterDataValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClassRosterDataValidationException(String message) {
		super(message);
	}
}
