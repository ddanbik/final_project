package pl.sda.bussiness;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.dto.UserDto;
import pl.sda.model.Role;
import pl.sda.model.User;
import pl.sda.repository.RoleRepository;
import pl.sda.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserBoImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void saveUser(UserDto dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("USER"));
//        roles.add(roleRepository.findByName("ADMIN"));
        user.setRoles(roles);

        userRepository.save(user);
    }
}
