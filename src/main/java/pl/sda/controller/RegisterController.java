package pl.sda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.bussiness.UserBoImpl;
import pl.sda.bussiness.UserValidator;
import pl.sda.dto.UserDto;

import javax.validation.Valid;

@Controller
public class RegisterController {


//    private static final String USER_EXISTS_MSG = "Użytkownik o takim loginie już jest w systemie";
//    private static final String BAD_USER = "Zły format adresu email!!!";
//    private static final String BAD_PASSWORD = "Złe hasło!!!";
    private static final String USER_REGISTRED_CORRECTLY = "Użytkownik zarejestrowany poprawnie";


    @Autowired
    private UserBoImpl userBo;


    @Autowired
    private UserValidator validator;

    @GetMapping("/register")
    public String register(Model model){
        initModel(model);
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@Valid @ModelAttribute(name= "user") UserDto user, BindingResult bindingResult, Model model){
//        initModel(model);

        if(bindingResult.hasErrors() || validate(user, model)){
//            model.addAttribute("badUser", BAD_USER);
//            model.addAttribute("badPassword", BAD_PASSWORD);
            return "register";
        }
        userBo.saveUser(user);
        model.addAttribute("userRegisteredCorrectly", USER_REGISTRED_CORRECTLY);
        return "login";
    }

//    private boolean checkUserAlreadyExists(@ModelAttribute @Valid UserDto user, Model model) {
//        boolean result = validator.notValid(user);
//        if (result) {
//            model.addAttribute("userAlreadyExists", USER_EXISTS_MSG);
//        }
//        return result;
//    }

    private boolean validate(UserDto user, Model model) {
        String result = validator.notValid(user, true);
        if (result != null) {
            model.addAttribute("commonError", result);
        }
        return result != null;
    }

    private void initModel(Model model) {
        model.addAttribute("user", new UserDto());
    }


}
