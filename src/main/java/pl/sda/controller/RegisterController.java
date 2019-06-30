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


    private static final String USER_EXISTS_MSG = "Użytkownik o takim loginie istnieje w systemie";

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
    public String saveUser(@Valid @ModelAttribute UserDto user, BindingResult bindingResult, Model model){
        initModel(model);

        if(bindingResult.hasErrors() || checkUserAlreadyExists(user, model)){
            return "register";
        }
        userBo.saveUser(user);

        return "login";
    }

    private boolean checkUserAlreadyExists(@ModelAttribute @Valid UserDto user, Model model) {
        boolean result = validator.notValid(user);
        if (result) {
            model.addAttribute("userAlreadyExists", USER_EXISTS_MSG);
        }
        return result;
    }


    private void initModel(Model model) {
        model.addAttribute("user", new UserDto());
    }


}
