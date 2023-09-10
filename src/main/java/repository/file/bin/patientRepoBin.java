package repository.file.bin;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import static java.lang.System.exit;

import domain.Patient;
import repository.EntityMemRepo;
import repository.file.FileRepository;

public class patientRepoBin extends FileRepository<String, Patient>{
    public patientRepoBin(String filename) {
        super(filename);
        readFromFile();
    }
    
    @Override
    public void readFromFile() {
        var tempRepo = new EntityMemRepo<Patient>();
        try{
            var in = new ObjectInputStream(new FileInputStream(filename));
            var r = new ArrayList<Patient>();
            r = (ArrayList<Patient>)in.readObject();
            for (var d : r) {
                tempRepo.add(d.getId(), d);
            }
            in.close();
            this.entities = tempRepo.entities;
        } catch (Exception e) {
            e.printStackTrace();
            exit(1);
        }
    }

    @Override
    public void writeToFile() {
        try{
            var out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(new ArrayList<Patient>(entities.values()));
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            exit(1);
        }
    }

}
