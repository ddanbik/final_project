package pl.sda.bussiness;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.dto.UserDto;
import pl.sda.model.AccountStatus;
import pl.sda.model.AccountType;
import pl.sda.model.Role;
import pl.sda.model.User;
import pl.sda.repository.RoleRepository;
import pl.sda.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
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
        user.setCreateDate(new Date());
        user.setStatus(AccountStatus.ACTIVE);
        user.setType(AccountType.NORMAL);
        user.setCity(dto.getCity());
        user.setAddress(dto.getAddress());
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("USER"));
//        roles.add(roleRepository.findByName("ADMIN"));
        user.setRoles(roles);

        userRepository.save(user);
    }

    public void updateUser(UserDto dto) {
        User user = userRepository.findByUsername(dto.getUsername()).get();
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setCity(dto.getCity());
        user.setAddress(dto.getAddress());
        userRepository.save(user);
    }

    public UserDto getUser(String username) {
        User user = userRepository.findByUsername(username).get();
        return new UserDto(user);
    }
}
