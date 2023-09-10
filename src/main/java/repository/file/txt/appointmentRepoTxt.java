package repository.file.txt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.exit;

import domain.Appointment;
import repository.EntityMemRepo;
import repository.file.FileRepository;


public class appointmentRepoTxt extends FileRepository<String, Appointment>{
    public appointmentRepoTxt(String filename) {
        super(filename);
        readFromFile();
    }

    @Override
    public void readFromFile() {
        var tempRepo = new EntityMemRepo<Appointment>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = "";
            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                String id = parts[0].strip();
                String patientId = parts[1].strip();
                String dentistId = parts[2].strip();
                String date = parts[3].strip();
                String description = parts[4].strip();
                Appointment appointment = Appointment.build(id, dentistId, patientId, date, description);
                tempRepo.add(id, appointment);
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
            for (var appointment : entities.values()) {
                writer.write(appointment.getId() + "," + appointment.getDentistId() + "," + appointment.getPatientId() + "," + appointment.getDate() + "," + appointment.getDescription());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            exit(1);
        }
        
    }
    
}
