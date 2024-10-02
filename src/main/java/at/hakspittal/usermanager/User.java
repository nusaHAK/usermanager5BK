package at.hakspittal.usermanager;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//Durch die Annotation @Entity kann Spring Boot ein MySQL-Tabelle automatisch erzeugen.
@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  //Primärschlüssel der Tabelle User (mit Autoinkrement)

    //Attribute - Felder
    private String firstname;
    private String lastname;
    
    //Standardkonstruktor
    public User() {
    }

    //individuelle Konstruktor
    public User(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    //GETTER/SETTER für ALLE Attribute
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    
    

}
