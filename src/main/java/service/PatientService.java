package service;

import java.util.ArrayList;
import java.util.stream.Collectors;

import domain.Patient;
import exception.RepoException;
import repository.Repository;

public class PatientService {
    private Repository<String, Patient> refPatientRepo;

    public PatientService(Repository<String, Patient> refPatientRepo) {
        this.refPatientRepo = refPatientRepo;
    }

    public void addPatient(Patient p) throws RepoException {
        if (refPatientRepo.get(p.getId()) != null) {throw new RepoException("Patient already exists!");}
        refPatientRepo.add(p.getId(), p);
    }

    public void removePatient(Patient p) throws RepoException {
        if (refPatientRepo.get(p.getId()) == null) {throw new RepoException("Patient does not exist!");}
        refPatientRepo.remove(p.getId());
    }

    public void updatePatient(Patient p) throws RepoException {
        if (refPatientRepo.get(p.getId()) == null) {throw new RepoException("Patient does not exist!");}
        refPatientRepo.update(p.getId(), p);
    }

    public ArrayList<Patient> getAllPatients() {
        return new ArrayList<Patient>(refPatientRepo.get());
    }

    public Patient getPatientById(String id) {
        return refPatientRepo.get(id);
    }
    
    @Override
    public String toString() {
        return refPatientRepo.toString();
    }

    public ArrayList<Patient> filterPatientsByCity(String city) {
        return (ArrayList<Patient>) refPatientRepo.get()
            .stream()
            .filter(p -> p.getAddress().contains(city))
            .collect(Collectors.toList());
    }

    public ArrayList<Patient> filterPatientsByName(String name) {
        return (ArrayList<Patient>) refPatientRepo.get()
            .stream()
            .filter(p -> p.getName().contains(name))
            .collect(Collectors.toList());
    }
}