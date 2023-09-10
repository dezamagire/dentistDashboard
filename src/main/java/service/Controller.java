package service;

import java.util.ArrayList;

import domain.Appointment;
import domain.Dentist;
import domain.Patient;
import exception.RepoException;

public class Controller {

    public DentistService refDentistService;
    public PatientService refPatientService;
    public AppointmentService refAppointmentService;

    public Controller(DentistService refDentistService, PatientService refPatientService, AppointmentService refAppointmentService) {
        this.refDentistService = refDentistService;
        this.refPatientService = refPatientService;
        this.refAppointmentService = refAppointmentService;
    }

    public void addDentist(Dentist d) throws RepoException {
        refDentistService.addDentist(d);
    }

    public void removeDentist(Dentist d) throws RepoException {
        refDentistService.removeDentist(d);
        for(Appointment a : refAppointmentService.getAllAppointments()){
            if(a.getDentistId().equals(d.getId())){
                refAppointmentService.removeAppointment(a);
            }
        }
    }

    public void updateDentist(Dentist d) throws RepoException {
        refDentistService.updateDentist(d);
    }

    public ArrayList<Dentist> getAllDentists() {
        return refDentistService.getAllDentists();
    }

    public Dentist getDentistById(String id) {
        return refDentistService.getDentistById(id);
    }

    public void addPatient(Patient p) throws RepoException {
        refPatientService.addPatient(p);
    }

    public void removePatient(Patient p) throws RepoException {
        refPatientService.removePatient(p);
        for(Appointment a : refAppointmentService.getAllAppointments()){
            if(a.getPatientId().equals(p.getId())){
                refAppointmentService.removeAppointment(a);
            }
        }
    }

    public void updatePatient(Patient p) throws RepoException {
        refPatientService.updatePatient(p);
    }

    public ArrayList<Patient> getAllPatients() {
        return refPatientService.getAllPatients();
    }

    public Patient getPatientById(String id) {
        return refPatientService.getPatientById(id);
    }

    public void addAppointment(Appointment a) throws RepoException {
        refAppointmentService.addAppointment(a);
    }

    public void removeAppointment(Appointment a) throws RepoException {
        refAppointmentService.removeAppointment(a);
    }

    public void updateAppointment(Appointment a) throws RepoException {
        refAppointmentService.updateAppointment(a);
    }

    public ArrayList<Appointment> getAllAppointments() {
        return refAppointmentService.getAllAppointments();
    }

    public ArrayList<Appointment> getAppointmentsByDentist(String id) {
        return refAppointmentService.getAppointmentsByDentist(id);
    }

    public ArrayList<Appointment> getAppointmentsByPatient(String id) {
        return refAppointmentService.getAppointmentsByPatient(id);
    }

    public Appointment getAppointmentById(String id) {
        return refAppointmentService.getAppointmentById(id);
    }

    public ArrayList<Patient> filterPatientsByCity(String city) {
        return refPatientService.filterPatientsByCity(city);
    }

    public ArrayList<Appointment> sortAppointmentsByDate(){
        return refAppointmentService.sortAppointmentsByDate();
    }

    public ArrayList<Appointment> filterAppointmentsByYear(int year) {
        return refAppointmentService.filterAppointmentsByYear(year);
    }

    public ArrayList<Patient> filterPatientsByName(String name) {
        return refPatientService.filterPatientsByName(name);
    }

    public ArrayList<Dentist> filterDentistsByName(String name) {
        return refDentistService.filterDentistsByName(name);
    }

}