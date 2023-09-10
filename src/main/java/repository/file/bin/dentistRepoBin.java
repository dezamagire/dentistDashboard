package repository.file.bin;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import static java.lang.System.exit;

import domain.Dentist;
import repository.EntityMemRepo;
import repository.file.FileRepository;

public class dentistRepoBin extends FileRepository<String, Dentist>{
    public dentistRepoBin(String filename) {
        super(filename);
        readFromFile();
    }
    
    @Override
    public void readFromFile() {
        var tempRepo = new EntityMemRepo<Dentist>();
        try(var in = new ObjectInputStream(new FileInputStream(this.filename))){
            var dentists = ((ArrayList<Dentist>)in.readObject());
            for (var d : dentists) {
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
            out.writeObject(new ArrayList<Dentist>(entities.values()));
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            exit(1);
        }
    }

}
