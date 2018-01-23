package at.main.ghwt.terminverwaltung_app.pkgData;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static java.sql.Connection.TRANSACTION_SERIALIZABLE;

/**
 * Created by Martin on 09.01.2018.
 */
//asynchronen task verwenden, um den webservice anzusteuern
public class Database {
    Connection conn = null;
    ArrayList<Appointment> Appointments = null;
    public Database(){
        Appointments = new ArrayList<Appointment>();
    Appointments.add(new Appointment(new Date(116,05,20),"Villach","Teil auswechseln","Katalysator erneuern"));
        Appointments.add(new Appointment(new Date(115,05,20),"Klagenfurt","Teil auswechseln","Katalysator erneuern"));
        Appointments.add(new Appointment(new Date(114,05,20),"Spittal","Teil auswechseln","Katalysator erneuern"));

    }

    public ArrayList<Appointment> getAppointments()
    {
        return this.Appointments;
    }

    public void deleteAppointment(int id)
    {
        Appointments.remove(id);
    }

    public void addAppointment(Appointment newApp)
    {
        Appointments.add(newApp);
    }

}
