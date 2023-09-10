package ui;

import java.time.LocalDate;
import java.util.Scanner;

import domain.*;
import service.*;
import exception.RepoException;

public class commandLineInterface {

    private Controller refController;
    Scanner console;
    
    public commandLineInterface(service.Controller ctrl) {
        this.refController = ctrl;
        console = new Scanner(System.in);
    }
    
    public void clear(){
        // clear console
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void run(){
        while(true){
            System.out.print("Operations: \n"+
            "[Q|q] - Dentists menu \n"+
            "[W|w] - Patients menu \n"+
            "[E|e] - Appointments menu \n"+
            "[X|x] - Exit \n\n"+
            "Enter your choice: ");
            
            String choice = console.nextLine();
            switch(Character.toLowerCase(choice.charAt(0))){
                case 'q' -> dentistMenu();
                case 'w' -> patientMenu();
                case 'e' -> appointmentMenu();
                case 'x' -> System.exit(0);
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void dentistMenu() {
        clear();
        while(true){
            System.out.print("Dentists menu: \n"+
            "[Q|q] - Add dentist \n"+
            "[W|w] - Remove dentist \n"+
            "[E|e] - Update dentist \n"+
            "[R|r] - Get all dentists \n"+
            "[T|t] - Get dentist by id \n"+
            "[Y|y] - Get all dentists with a name \n"+
            "[X|x] - Back \n\n"+
            "Enter your choice: ");
            
            String choice = console.nextLine();
            switch(Character.toLowerCase(choice.charAt(0))){
                case 'q' -> addDentist();
                case 'w' -> removeDentist();
                case 'e' -> updateDentist();
                case 'r' -> getAllDentists();
                case 't' -> getDentistById();
                case 'y' -> getDentistsByName();
                case 'x' -> {clear();return;}
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void addDentist() {
        System.out.print("Enter dentist id: ");
        String id = console.nextLine();
        System.out.print("Enter dentist name: ");
        String name = console.nextLine();
        System.out.print("Enter dentist phone: ");
        String phone = console.nextLine();
        System.out.print("Enter dentist email: ");
        String email = console.nextLine();
        try{
            Dentist d = new Dentist(id, name, phone, email);
            refController.addDentist(d);
            clear();
            System.out.println("Dentist [" + id + "] added successfully!");
        }catch(RepoException e){
            System.out.println(e.getMessage());
        }

    }

    private void removeDentist() {
        System.out.print("Enter dentist id: ");
        String id = console.nextLine();
        try{
            Dentist d = new Dentist(id, "", "", "");
            refController.removeDentist(d);
            clear();
            System.out.println("Dentist [" + id + "] removed successfully!");
        }catch(RepoException e){
            System.out.println(e.getMessage());
        }
    }

    private void updateDentist() {
        System.out.print("Enter id of the dentist to update: ");
        String id = console.nextLine();
        System.out.print("Current dentist name: " + refController.getDentistById(id).getName() + "\n");
        System.out.print("Enter new dentist name: ");
        String name = console.nextLine();
        System.out.print("Current dentist phone: " + refController.getDentistById(id).getPhone() + "\n");
        System.out.print("Enter new dentist phone: ");
        String phone = console.nextLine();
        System.out.print("Current dentist email: " + refController.getDentistById(id).getEmail() + "\n");
        System.out.print("Enter new dentist email: ");
        String email = console.nextLine();
        try{
            Dentist d = new Dentist(id, name, phone, email);
            refController.updateDentist(d);
            clear();
            System.out.println("Dentist [" + id + "] updated successfully!");
        }catch(RepoException e){
            System.out.println(e.getMessage());
        }
    }

    private void getAllDentists() {
        clear();
        System.out.println("All dentists: \n");
        for(Dentist d : refController.getAllDentists()){
            System.out.println(d.toString());
        }
    }

    private void getDentistById() {
        clear();
        System.out.print("Enter dentist id: ");
        String id = console.nextLine();
        if(refController.getDentistById(id) == null){
            System.out.println("Dentist not found!");
            return;
        }
        System.out.println(refController.getDentistById(id).toString());
    }

    private void getDentistsByName() {
        clear();
        System.out.print("Enter name: ");
        String name = console.nextLine();
        System.out.println("\n" + refController.filterDentistsByName(name));
    }

    private void patientMenu() {
        clear();
        while(true){
            System.out.print("Patients menu: \n"+
            "[Q|q] - Add patient \n"+
            "[W|w] - Remove patient \n"+
            "[E|e] - Update patient \n"+
            "[R|r] - Get all patients \n"+
            "[T|t] - Get patient by id \n"+
            "[Y|y] - Filter patients by city \n"+
            "[U|u] - Filter patients by name \n"+
            "[X|x] - Back \n\n"+
            "Enter your choice: ");
            
            String choice = console.nextLine();
            switch(choice.toLowerCase().charAt(0)){
                case 'q' -> addPatient();
                case 'w' -> removePatient();
                case 'e' -> updatePatient();
                case 'r' -> getAllPatients();
                case 't' -> getPatientById();
                case 'y' -> filterPatientsByCity();
                case 'u' -> filterPatientsByName();
                case 'x' -> {clear();return;}
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void addPatient() {
        System.out.print("Enter patient id: ");
        String id = console.nextLine();
        System.out.print("Enter patient name: ");
        String name = console.nextLine();
        System.out.print("Enter patient phone: ");
        String phone = console.nextLine();
        System.out.print("Enter patient email: ");
        String email = console.nextLine();
        System.out.print("Enter patient city: ");
        String city = console.nextLine();
        try{
            Patient p = new Patient(id, name, phone, email, city);
            refController.addPatient(p);
            clear();
            System.out.println("Patient [" + id + "] added successfully!");
        }catch(RepoException e){
            System.out.println(e.getMessage());
        }
    }

    private void removePatient() {
        System.out.print("Enter patient id: ");
        String id = console.nextLine();
        try{
            Patient p = new Patient(id, "", "", "", "");
            refController.removePatient(p);
            clear();
            System.out.println("Patient [" + id + "] removed successfully!");
        }catch(RepoException e){
            System.out.println(e.getMessage());
        }
    }

    private void updatePatient() {
        System.out.print("Enter id of the patient to update: ");
        String id = console.nextLine();
        System.out.print("Current patient name: " + refController.getPatientById(id).getName() + "\n");
        System.out.print("Enter new patient name: ");
        String name = console.nextLine();
        System.out.print("Current patient phone: " + refController.getPatientById(id).getPhone() + "\n");
        System.out.print("Enter new patient phone: ");
        String phone = console.nextLine();
        System.out.print("Current patient email: " + refController.getPatientById(id).getEmail() + "\n");
        System.out.print("Enter new patient email: ");
        String email = console.nextLine();
        System.out.print("Current patient city: " + refController.getPatientById(id).getAddress() + "\n");
        System.out.print("Enter new patient city: ");
        String city = console.nextLine();
        try{
            Patient p = new Patient(id, name, phone, email, city);
            refController.updatePatient(p);
            clear();
            System.out.println("Patient [" + id + "] updated successfully!");
        }catch(RepoException e){
            System.out.println(e.getMessage());
        }
    }

    private void getAllPatients() {
        clear();
        System.out.println("All patients: \n");
        for(Patient p : refController.getAllPatients()){
            System.out.println(p.toString());
        }
    }

    private void getPatientById() {
        clear();
        System.out.print("Enter patient id: ");
        String id = console.nextLine();
        if(refController.getPatientById(id) == null){
            System.out.println("Patient not found!");
            return;
        }
        System.out.println(refController.getPatientById(id).toString());
    }

    private void filterPatientsByCity() {
        clear();
        System.out.print("Enter city: ");
        String city = console.nextLine();
        System.out.println(refController.filterPatientsByCity(city));
    }

    private void filterPatientsByName() {
        clear();
        System.out.print("Enter name: ");
        String name = console.nextLine();
        System.out.println(refController.filterPatientsByName(name));
    }



    private void appointmentMenu() {
        clear();
        while(true){
            System.out.print("Appointments menu: \n"+
            "[Q|q] - Add appointment \n"+
            "[W|w] - Remove appointment \n"+
            "[E|e] - Update appointment \n"+
            "[R|r] - Get all appointments \n"+
            "[T|t] - Get appointments of dentist \n"+
            "[Y|y] - Get appointments of patient \n"+
            "[U|u] - Sort appointments by date \n"+
            "[I|i] - Filter appointments by year \n"+
            "[X|x] - Back \n\n"+
            "Enter your choice: ");
            
            String choice = console.nextLine();
            switch(choice.toLowerCase().charAt(0)){
                case 'q' -> addAppointment();
                case 'w' -> removeAppointment();
                case 'e' -> updateAppointment();
                case 'r' -> getAllAppointments();
                case 't' -> getAppointmentsOfDentist();
                case 'y' -> getAppointmentsOfPatient();
                case 'u' -> sortAppointmentsByDate();
                case 'i' -> filterAppointmentsByYear();
                case 'x' -> {clear();return;}
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    
    private void addAppointment() {
        // date, patient, dentist, description
        System.out.print("Enter appointment date: ");
        String date = console.nextLine();
        System.out.print("Enter patient id: ");
        String patientId = console.nextLine();
        System.out.print("Enter dentist id: ");
        String dentistId = console.nextLine();
        System.out.print("Enter appointment description: ");
        String description = console.nextLine();
        Patient p = refController.getPatientById(patientId);
        Dentist d = refController.getDentistById(dentistId);
        LocalDate ld = LocalDate.parse(date);
        try{
            Appointment a = new Appointment(ld, p, d, description);
            refController.addAppointment(a);
            clear();
            System.out.println("Appointment [" + a.getId() + "] added successfully!");
        }catch(RepoException e){
            System.out.println(e.getMessage());
        }

    }

    private void removeAppointment() {
        System.out.print("Enter appointment id: ");
        String id = console.nextLine();
        try{
            Appointment a = refController.getAppointmentById(id);
            refController.removeAppointment(a);
            clear();
            System.out.println("Appointment [" + id + "] removed successfully!");
        }catch(RepoException e){
            System.out.println(e.getMessage());
        }
    }

    private void updateAppointment() {
        System.out.print("Enter id of the appointment to update: ");
        String id = console.nextLine();
        System.out.print("Current appointment date: " + refController.getAppointmentById(id).getDate() + "\n");
        System.out.print("Enter new appointment date: ");
        String date = console.nextLine();
        System.out.print("Current appointment patient id: " + refController.getAppointmentById(id).getPatientId() + "\n");
        System.out.print("Enter new appointment patient id: ");
        String patientId = console.nextLine();
        System.out.print("Current appointment dentist id: " + refController.getAppointmentById(id).getDentistId() + "\n");
        System.out.print("Enter new appointment dentist id: ");
        String dentistId = console.nextLine();
        System.out.print("Current appointment description: " + refController.getAppointmentById(id).getDescription() + "\n");
        System.out.print("Enter new appointment description: ");
        String description = console.nextLine();
        Patient p = refController.getPatientById(patientId);
        Dentist d = refController.getDentistById(dentistId);
        LocalDate ld = LocalDate.parse(date);
        try{
            refController.removeAppointment(refController.getAppointmentById(id));
            Appointment a = new Appointment(ld, p, d, description);
            refController.addAppointment(a);
            clear();
            System.out.println("Appointment updated successfully!");
        }catch(RepoException e){
            System.out.println(e.getMessage());
        }
    }

    private void getAllAppointments() {
        clear();
        System.out.println("All appointments: \n");
        for(Appointment a : refController.getAllAppointments()){
            System.out.println(a.toString());
        }
    }

    private void getAppointmentsOfDentist() {
        clear();
        System.out.print("Enter dentist id: ");
        String id = console.nextLine();
        for(Appointment a : refController.getAppointmentsByDentist(id)){
            System.out.println(a.toString());
        }
    }

    private void getAppointmentsOfPatient() {
        clear();
        System.out.print("Enter patient id: ");
        String id = console.nextLine();
        for(Appointment a : refController.getAppointmentsByPatient(id)){
            System.out.println(a.toString());
        }
    }

    private void sortAppointmentsByDate() {
        clear();
        System.out.println("All appointments sorted by date: \n");
        for(Appointment a : refController.sortAppointmentsByDate()){
            System.out.println(a.toString());
        }
    }

    private void filterAppointmentsByYear() {
        clear();
        System.out.print("Enter year: ");
        int year = console.nextInt();
        for(Appointment a : refController.filterAppointmentsByYear(year)){
            System.out.println(a.toString());
        }
    }

}
