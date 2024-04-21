package com.sg.ClassRoster;

import com.sg.ClassRoster.Controller.ClassRosterController;
import com.sg.ClassRoster.DAO.ClassRosterAuditDao;
import com.sg.ClassRoster.DAO.ClassRosterAuditDaoFileImpl;
import com.sg.ClassRoster.DAO.ClassRosterDao;
import com.sg.ClassRoster.DAO.ClassRosterDaoFileImpl;
import com.sg.ClassRoster.Service.ClassRosterServiceLayer;
import com.sg.ClassRoster.Service.ClassRosterServiceLayerImpl;
import com.sg.ClassRoster.UI.ClassRosterView;
import com.sg.ClassRoster.UI.UserIO;
import com.sg.ClassRoster.UI.UserIOConsoleImpl;

/**
 * @Ahmed Ibrahim
 *
 */
public class App {
    public static void main( String[] args ){
    	
    	UserIO myIO = new UserIOConsoleImpl();
    	ClassRosterView myView = new ClassRosterView(myIO);
    	ClassRosterDao myDao = new ClassRosterDaoFileImpl();
    	ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
    	ClassRosterServiceLayer myservice = new ClassRosterServiceLayerImpl(myDao, myAuditDao);
        ClassRosterController controller = new ClassRosterController(myView, myservice);
        controller.run();
    }
}
