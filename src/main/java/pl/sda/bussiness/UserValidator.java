package pl.sda.bussiness;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.sda.dto.UserDto;
import pl.sda.repository.UserRepository;

@Component
public class UserValidator {


    @Autowired
    private UserRepository userRepository;

    public boolean notValid(UserDto dto){
        return userRepository.findByUsername(dto.getUsername()).isPresent();
    }

}
