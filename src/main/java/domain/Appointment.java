package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Appointment implements Identifiable<String>, Serializable{
    public static final long serialVersionUID = 1L;
    
    // Attributes
    private String id;
    private LocalDate date;
    private String patientId;
    private String dentistId;
    private String description;

    // Constructor
    public Appointment(LocalDate date, Patient patient, Dentist dentist, String description) {
        this.id = patient.getId() + dentist.getId() + date.toString();
        this.date = date;
        this.patientId = patient.getId();
        this.dentistId = dentist.getId();
        this.description = description;
    }

    public Appointment(){
        this.id = "";
        this.dentistId = "";
        this.patientId = "";
        this.date = null;
        this.description = "";
    }

    public static Appointment build(String id, String dentistId, String patientId, String date, String description){
        Appointment a = new Appointment();
        a.id = id;
        a.dentistId = dentistId;
        a.patientId = patientId;
        a.date = LocalDate.parse(date);
        a.description = description;
        return a;
    }

    // Getters and Setters
    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public LocalDate getDate() {return date;}
    public void setDate(LocalDate date) {this.date = date;}
    public String getPatientId() {return patientId;}
    public String getDentistId() {return dentistId;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    // toString
    @Override
    public String toString() {
        return "Appointment [" + getId() + "] - on " + date + ", for " + patientId + ", with Dr. " + dentistId + ", \"" + description + "\"";
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment appointment = (Appointment) o;
        return Objects.equals(id, appointment.id);
    }
}