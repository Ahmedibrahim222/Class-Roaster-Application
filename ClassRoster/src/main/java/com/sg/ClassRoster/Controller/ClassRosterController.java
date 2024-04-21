package com.sg.ClassRoster.Controller;

import java.util.List;

import com.sg.ClassRoster.DAO.ClassRosterPersitenceException;
import com.sg.ClassRoster.DTO.Student;
import com.sg.ClassRoster.Service.ClassRosterDataValidationException;
import com.sg.ClassRoster.Service.ClassRosterDuplicateIdException;
import com.sg.ClassRoster.Service.ClassRosterServiceLayer;
import com.sg.ClassRoster.UI.ClassRosterView;

public class ClassRosterController {

	private ClassRosterView view;
	//private UserIO io = new UserIOConsoleImpl();
	//private ClassRosterservice service;
	
	private ClassRosterServiceLayer service;



	public ClassRosterController(ClassRosterView view, ClassRosterServiceLayer service) {
		this.view = view;
		this.service = service;
	}
	public void run() {
		boolean keepGoing = true;
		int menuSelection = 0;

		try {
			while(keepGoing) {
				menuSelection = getMenuSelection();

				switch(menuSelection) {
				case 1:
					listStudents();
					break;
				case 2:
					createStudent();
					break;
				case 3:
					viewStudent();
					break;
				case 4:
					removeStudent();
					break;
				case 5:
					keepGoing = false;
					break;
				default:
					unknownCommand();
				}
			}
			exitMessage();
		}catch(ClassRosterPersitenceException e) {
			view.displayErrorMessage(e.getMessage());
		}
		

	}
	private int getMenuSelection() {
		return view.printMenuAndGetSelection();
	}

	private void createStudent() throws ClassRosterPersitenceException {
		view.displayCreateStudentBanner();
		boolean hasErrors = false;
		
		do {
			Student currentStudent = view.getNewStudentInfo();
			try {
				service.createStudent(currentStudent);
				view.displayCreateSuccessBanner();
				hasErrors = false;
			}catch(ClassRosterDuplicateIdException | ClassRosterDataValidationException e) {
				hasErrors = true;
			}
		}while(hasErrors);
		
	}

	private void listStudents() throws ClassRosterPersitenceException{
		//view.displayDisplayAllBanner();
		List<Student> studentList = service.getAllStudents();
		view.displayStudentList(studentList);
	}

	private void viewStudent() throws ClassRosterPersitenceException{
		//view.displayDisplayStudentBanner();
		String studentId = view.getStudentIdChoice();
		Student student = service.getStudent(studentId);
		view.displayStudent(student);
	}

	private void removeStudent() throws ClassRosterPersitenceException{
		view.displayRemovedStudentBanner();
		String studentId = view.getStudentIdChoice();
		Student removedStudent = service.removeStudent(studentId);
		view.displayRemoveResult(removedStudent);
	}

	private void unknownCommand() {
		view.displayUnknownCommandBanner();
	}

	private void exitMessage() {
		view.displayExitBanner();
	}

}
