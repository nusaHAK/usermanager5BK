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
     */
    public List<User> listAll(){
        return repo.findAll();  //gibt alle Einträge aus der Tabelle User zurück
    }

    /*
     * speichern eines neuen Users
     */
    public void save(User user){
        repo.save(user);
    }
    
}
