package repository;

import domain.*;
import repository.file.bin.appointmentRepoBin;
import repository.file.bin.dentistRepoBin;
import repository.file.bin.patientRepoBin;
import repository.file.txt.appointmentRepoTxt;
import repository.file.txt.dentistRepoTxt;
import repository.file.txt.patientRepoTxt;
import repository.database.databaseAppointmentRepo;
import repository.database.databaseDentistRepo;
import repository.database.databasePatientRepo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import static java.lang.System.exit;

public class repoSetup {
    private Repository<String, Patient> pr;

    private Repository<String, Dentist> dr;

    private Repository<String, Appointment> ar;

    public repoSetup(String pathToProperties){
        Properties properties = new Properties();
        try (InputStream pf = new FileInputStream(pathToProperties)) {
            properties.load(pf);
            var repoMode = properties.getProperty("repoMode");
            switch (repoMode) {
                case "mem" -> {
                    dr = new EntityMemRepo<Dentist>();
                    pr = new EntityMemRepo<Patient>();
                    ar = new EntityMemRepo<Appointment>(); 
                }
                case "txt" -> {
                    dr = new dentistRepoTxt(properties.getProperty("dentistsTxt"));
                    pr = new patientRepoTxt(properties.getProperty("patientsTxt"));
                    ar = new appointmentRepoTxt(properties.getProperty("appointmentsTxt"));
                }
                case "bin" -> {
                    dr = new dentistRepoBin(properties.getProperty("dentistsBin"));
                    pr = new patientRepoBin(properties.getProperty("patientsBin"));
                    ar = new appointmentRepoBin(properties.getProperty("appointmentsBin"));
                }
                case "db" -> {
                    dr = new databaseDentistRepo(properties.getProperty("dbUrl"));
                    pr = new databasePatientRepo(properties.getProperty("dbUrl"));
                    ar = new databaseAppointmentRepo(properties.getProperty("dbUrl"));
                }
                default -> throw new IOException("invalid repoMode");
            }

        } catch (IOException e) {
            e.printStackTrace();
            exit(1);
        }
    }

    public Repository<String, Patient> patientRepo() {return pr;}
    public Repository<String, Dentist> dentistRepo() {return dr;}
    public Repository<String, Appointment> appointmentRepo() {return ar;}

}
