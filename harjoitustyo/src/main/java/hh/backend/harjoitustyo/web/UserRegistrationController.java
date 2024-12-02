package hh.backend.harjoitustyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.backend.harjoitustyo.domain.AppUser;
import hh.backend.harjoitustyo.domain.AppUserRepository;
import hh.backend.harjoitustyo.domain.UserRegistration;
import jakarta.validation.Valid;

@Controller
public class UserRegistrationController {
	@Autowired
    private AppUserRepository repository; 
	
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String addNewUser(Model model){
    	model.addAttribute("registration", new UserRegistration());
        return "registration";
    }	
    
    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("newAppUser") UserRegistration newUser, BindingResult bindingResult) {
    	if (!bindingResult.hasErrors()) { // jos on virheitä
    		if (newUser.getPassword().equals(newUser.getPasswordCheck())) { // tarkista että salasanat ovat samat		
	    		String pwd = newUser.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
	
		    	AppUser user = new AppUser();
		    	user.setPasswordHash(hashPwd);
		    	user.setUsername(newUser.getUsername());
		    	user.setRole("USER");
		    	if (repository.findByUsername(newUser.getUsername()) == null) { // tarkistetaan onko käyttäjä olemassa
		    		repository.save(user);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			return "registration";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			return "registration";
    		}
    	}
    	else {
    		return "registration";
    	}
    	return "redirect:/login";    	
    }    
    
}
