package repository.file.txt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.exit;

import domain.Dentist;
import repository.EntityMemRepo;
import repository.file.FileRepository;


public class dentistRepoTxt extends FileRepository<String, Dentist>{
    public dentistRepoTxt(String filename) {
        super(filename);
        readFromFile();
    }

    @Override
    public void readFromFile() {
        var tempRepo = new EntityMemRepo<Dentist>();        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = "";
            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                String id = parts[0].strip();
                String name = parts[1].strip();
                String phone = parts[2].strip();
                String email = parts[3].strip();
                Dentist dentist = new Dentist(id, name, phone, email);
                tempRepo.add(id, dentist);
            }
            reader.close();
            this.entities = tempRepo.entities;
        } catch (IOException e) {
            e.printStackTrace();
            exit(1);
        }
    }

    @Override
    public void writeToFile() {
        try{
            var writer = new BufferedWriter(new FileWriter(filename));
            for (var dentist : entities.values()) {                writer.write(dentist.getId() + "," + dentist.getName() + "," + dentist.getPhone() + "," + dentist.getEmail());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            exit(1);
        }
        
    }
    
}
