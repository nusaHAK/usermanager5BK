package at.hakspittal.usermanager;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    /*
     * liste alle User auf
     * SELECT * FROM User
     */
    public List<User> listAll(){
        return repo.findAll();  //gibt alle Einträge aus der Tabelle User zurück
    }

    /*
     * speichern eines neuen Users
     * INSERT INTO USER VALUES ...
     * Wenn das Primärschlüsselelement nicht existiert, 
     * wird ein INSERT sonst ein UPDATE durchgeführt.
     */
    public void save(User user){
        repo.save(user);
    }

    /*
     * Laden der Daten eines bestimmten Users
     * SELECT * FROM User WHERE User.id = {id}
     */
    public User get(Long id){
        return repo.findById(id).get();  
    }

    /*
     * Löschen eines bestimmten Users
     * DELETE FROM User WHERE id = {id}
     */
    public void delete(Long id){
        repo.deleteById(id);
    }
    
}
