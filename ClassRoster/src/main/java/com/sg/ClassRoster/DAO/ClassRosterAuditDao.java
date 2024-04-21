package com.sg.ClassRoster.DAO;

public interface ClassRosterAuditDao {
	
	public void writeAuditEntry(String entry) throws ClassRosterPersitenceException;

}
