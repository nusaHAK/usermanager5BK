package at.hakspittal.usermanager;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import jakarta.annotation.PostConstruct;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;






@Controller
public class UserController {
    
    @Autowired
    private UserService userservice;

    //globale Variable laden
    //die CSV-Werte werden automatisch (vom Typ String) in eine Liste (Typ List) umgewandelt.
    @Value("${countries}")
  	private List<String> countries;

    //Konvertierung des ISO-8859-1 Listenelemente in UTF-8
    //PostConstruct wird automatisch direkt nach der Initialisierung der Bean (des Controllers)
    //aufgerufen. Muss nicht explizit angestoßen werden!
    @PostConstruct
    public void init() {
        // durchlaufen der Liste und umwandeln jedes Listeneintrags von ISO-8859-1 in UTF-8 
        countries = countries.stream()
                .map(country -> new String(country.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8))
                .collect(Collectors.toList());
    }
    // -----------Konvertierung der Länder in UTF-8 abgeschlossen -------------

    @RequestMapping("/delete_user/{id}")
    public String deleteUser(@PathVariable(name="id") Long id) {

        //1. Löschen des Users mit Hilfe der passenden Methode aus 
        //   der UserService-Klasse.
        userservice.delete(id);

        //2. Nach dem Löschen auf allUsersView weiterleiten
        return "redirect:/users";
    }
    

    @RequestMapping("/edit_user/{id}")
    public ModelAndView showUserEditForm(@PathVariable(name="id") Long id) {

        //1. UserDaten aus der DB holen, um sie im Formular anzeigen zu können
        User user = userservice.get(id);  //lokales Objekt muss zum Modell hinzugefügt werden,
                                          // damit es im View verwendet werden kann.

        //2. Modell für UserDaten vorbereiten
        ModelAndView mav = new ModelAndView("editUserFormView");
      

        //3.  Weiterleitung der Daten an den View 
        mav.addObject("user", user);
        mav.addObject("countries", countries);

        return mav;
    }
    

    //READ (SELECT)
    @GetMapping("/users")
    public String showAllUsers(Model model) {
        
        List<User> listUsers = userservice.listAll();
        //erstes Argument: ist Variablenbezeichner im View
        //zweites Argument: Liste aller User aus DB (List<User>)
        model.addAttribute("listUser",listUsers);
        return "allUsersView";
    }

    //CREATE (INSERT)
    @GetMapping("/newUser")
    public String showNewUserForm(Model model) {
       
        //1. ein leeres User-Objekt erzeugen 
        User user = new User();

        //und an den View weiterleiten
        //2. model.addAttribute("user", new User());
        model.addAttribute("user", user);
        model.addAttribute("countries", countries);

        //3. View aufrufen
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
