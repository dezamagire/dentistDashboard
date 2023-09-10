package service;

import java.util.ArrayList;
import java.util.stream.Collectors;

import domain.Appointment;
import exception.RepoException;
import repository.Repository;

public class AppointmentService {
    private Repository<String, Appointment> refAppointmentRepo;

    public AppointmentService(Repository<String, Appointment> refAppointmentRepo) {
        this.refAppointmentRepo = refAppointmentRepo;
    }

    public void addAppointment(Appointment a) throws RepoException {
        if (refAppointmentRepo.get(a.getId()) != null) {throw new RepoException("Appointment already exists!");}
        refAppointmentRepo.add(a.getId(), a);
    }

    public void removeAppointment(Appointment a) throws RepoException {
        if (refAppointmentRepo.get(a.getId()) == null) {throw new RepoException("Appointment does not exist!");}
        refAppointmentRepo.remove(a.getId());
    }

    public void updateAppointment(Appointment a) throws RepoException {
        if (refAppointmentRepo.get(a.getId()) == null) {throw new RepoException("Appointment does not exist!");}
        refAppointmentRepo.update(a.getId(), a);
    }

    public ArrayList<Appointment> getAllAppointments() {
        return new ArrayList<Appointment>(refAppointmentRepo.get());
    }

    public ArrayList<Appointment> getAppointmentsByDentist(String dentistId) {
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        for (Appointment a : refAppointmentRepo.get()) {
            if (a.getDentistId().equals(dentistId)) {appointments.add(a);}
        }
        return appointments;
    }

    public ArrayList<Appointment> getAppointmentsByPatient(String patientId) {
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        for (Appointment a : refAppointmentRepo.get()) {
            if (a.getPatientId().equals(patientId)) {appointments.add(a);}
        }
        return appointments;
    }

    @Override
    public String toString() {
        return refAppointmentRepo.toString();
    }
    
    public Appointment getAppointmentById(String id) {
        return refAppointmentRepo.get(id);
    }

    public ArrayList<Appointment> sortAppointmentsByDate() {
        return (ArrayList<Appointment>) refAppointmentRepo.get()
            .stream()
            .sorted((a1, a2) -> a1.getDate().compareTo(a2.getDate()))
            .collect(Collectors.toList());
    }

    public ArrayList<Appointment> filterAppointmentsByYear(int year){
        return (ArrayList<Appointment>) refAppointmentRepo.get()
            .stream()
            .filter(a -> a.getDate().getYear() == year)
            .collect(Collectors.toList()); 
    }
}