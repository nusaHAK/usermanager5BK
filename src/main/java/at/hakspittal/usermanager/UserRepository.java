package at.hakspittal.usermanager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    //Hibernate führt automatisch das Erzeugen der notwendigen 
    //Tabellen durch. In unserem Fall wird die Tabelle User mit
    //der id vom Typ Long als Primärschlüssel erzeugt.

    
}
