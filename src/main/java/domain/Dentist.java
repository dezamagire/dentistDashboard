package domain;

import java.io.Serializable;
import java.util.Objects;

public class Dentist implements Identifiable<String>, Serializable{
    public static final long serialVersionUID = 1L;
    
    // Attributes
    private String id;
    private String name;
    private String phone;
    private String email;

    // Constructor
    public Dentist(String id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
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

    // toString 
    @Override
    public String toString() {
        return "Dentist [" + getId() + "] - " + name + ", " + phone + ", {" + email + '}';
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dentist dentist = (Dentist) o;
        return Objects.equals(id, dentist.id);
    }
    
}