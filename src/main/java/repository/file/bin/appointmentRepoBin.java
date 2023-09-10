package repository.file.bin;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import static java.lang.System.exit;

import domain.Appointment;
import repository.EntityMemRepo;
import repository.file.FileRepository;

public class appointmentRepoBin extends FileRepository<String, Appointment>{
    public appointmentRepoBin(String filename) {
        super(filename);
        readFromFile();
    }
    
    @Override
    public void readFromFile() {
        var tempRepo = new EntityMemRepo<Appointment>();
        try{
            var in = new ObjectInputStream(new FileInputStream(filename));
            var r = new ArrayList<Appointment>();
            r = (ArrayList<Appointment>)in.readObject();
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
            out.writeObject(new ArrayList<Appointment>(entities.values()));
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            exit(1);
        }
    }

}
