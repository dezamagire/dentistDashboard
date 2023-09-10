package repository.file.txt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.exit;

import domain.Patient;
import repository.EntityMemRepo;
import repository.file.FileRepository;


public class patientRepoTxt extends FileRepository<String, Patient>{
    public patientRepoTxt(String filename) {
        super(filename);
        readFromFile();
    }

    @Override
    public void readFromFile() {
        var tempRepo = new EntityMemRepo<Patient>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = "";
            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                String id = parts[0].strip();
                String name = parts[1].strip();
                String phone = parts[2].strip();
                String email = parts[3].strip();
                String address = parts[4].strip();
                Patient patient = new Patient(id, name, phone, email, address);
                tempRepo.add(id, patient);
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
            for (var patient : entities.values()) {
                writer.write(patient.getId() + "," + patient.getName() + "," + patient.getPhone() + "," + patient.getEmail() + "," + patient.getAddress());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            exit(1);
        }

    }
    
}
