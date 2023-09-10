package domain;

import java.io.Serializable;
import java.util.Objects;

public class Patient implements Identifiable<String>, Serializable{
    public static final long serialVersionUID = 1L;
    
    // Attributes
    private String id;
    private String name;
    private String phone;
    private String email;
    private String address;

    // Constructor
    public Patient(String id, String name, String phone, String email, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    // Getters and Setters
    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    // toString
    @Override
    public String toString() {
        return "Patient [" + getId() + "] - " + name + ", " + phone + ", {" + email + "}, living at " + address;
    }
    
    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id);
    }

}