package at.hakspittal.usermanager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
public class UserController {
    
    @Autowired
    private UserService userservice;

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        
        List<User> listUsers = userservice.listAll();
        //erstes Argument: ist Variablenbezeichner im View
        //zweites Argument: Liste aller User aus DB (List<User>)
        model.addAttribute("listUser",listUsers);
        return "allUsersView";
    }

    @GetMapping("/newUser")
    public String showNewUserForm(Model model) {
       
        //ein leeres User-Objekt erzeugen 
        User user = new User();

        //und an den View weiterleiten
        //model.addAttribute("user", new User());
        model.addAttribute("user", user);

        //View aufrufen
        return "newUserFormView";
    }
        
    @PostMapping("/save_user")
    public String saveUser(@ModelAttribute User user) {
        //mittels Post wird das befüllte User-Objekt übergeben 
        //und kann nun in der DB gespeichert werden
        userservice.save(user);
        
        return "confirmation";
    }
    
    

}
