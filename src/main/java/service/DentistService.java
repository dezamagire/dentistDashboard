package service;

import java.util.ArrayList;
import java.util.stream.Collectors;

import domain.Dentist;
import exception.RepoException;
import repository.Repository;

public class DentistService {
    private Repository<String, Dentist> refDentistRepo;

    public DentistService(Repository<String, Dentist> refDentistRepo) {
        this.refDentistRepo = refDentistRepo;
    }

    public void addDentist(Dentist d) throws RepoException {
        if (refDentistRepo.get(d.getId()) != null) {throw new RepoException("Dentist already exists!");}
        refDentistRepo.add(d.getId(), d);
    }

    public void removeDentist(Dentist d) throws RepoException {
        if (refDentistRepo.get(d.getId()) == null) {throw new RepoException("Dentist does not exist!");}
        refDentistRepo.remove(d.getId());
    }

    public void updateDentist(Dentist d) throws RepoException {
        if (refDentistRepo.get(d.getId()) == null) {throw new RepoException("Dentist does not exist!");}
        refDentistRepo.update(d.getId(), d);
    }

    public ArrayList<Dentist> getAllDentists() {
        return new ArrayList<Dentist>(refDentistRepo.get());
    }

    public Dentist getDentistById(String id) {
        return refDentistRepo.get(id);
    }

    @Override
    public String toString() {
        return refDentistRepo.toString();
    }

    public ArrayList<Dentist> filterDentistsByName(String name) {
        return (ArrayList<Dentist>) refDentistRepo.get()
            .stream()
            .filter(d -> d.getName().contains(name))
            .collect(Collectors.toList());
    }

}