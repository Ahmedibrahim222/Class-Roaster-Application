package com.sg.ClassRoster.DAO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ClassRosterAuditDaoFileImpl implements ClassRosterAuditDao{
	
	public static final String AUDIT_FILE = "audit.txt";

	@Override
	public void writeAuditEntry(String entry) throws ClassRosterPersitenceException {
		
		PrintWriter out;
		
		try {
			out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
		}catch(IOException e) {
			throw new ClassRosterPersitenceException("Could not persist audit information.", e);
		}
		
		LocalDateTime timestamp =  LocalDateTime.now();
		out.println(timestamp.toString() + " : " + entry);
	}

}
