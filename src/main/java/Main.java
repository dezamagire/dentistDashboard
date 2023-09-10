import service.*;
import repository.*;
import ui.*;

public class Main{
    public static void main(String[] args){
        
        String pathToProperties = "src/main/java/config/settings.properties";

        var rs = new repoSetup(pathToProperties);

        var patientService = new PatientService(rs.patientRepo());
        var dentistService = new DentistService(rs.dentistRepo());
        var appointmentService = new AppointmentService(rs.appointmentRepo());

        var ctrl = new Controller(dentistService, patientService, appointmentService);
        var cli = new commandLineInterface(ctrl);

        cli.run();
    }
}