package pl.coderslab.zhpsystem.service;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import pl.coderslab.zhpsystem.DTO.UserDTO;
import pl.coderslab.zhpsystem.entity.User;
import pl.coderslab.zhpsystem.exceptions.ObjectError;
import pl.coderslab.zhpsystem.exceptions.ValidationException;
import pl.coderslab.zhpsystem.repository.RoleRepository;
import pl.coderslab.zhpsystem.repository.UserRepository;

import pl.coderslab.zhpsystem.entity.Role;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Validator validator;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, Validator validator) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }


    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(0);
        Role userRole = roleRepository.findByName("ROLE_DISABLED");
        Set<Role> roles = new HashSet<Role>();
        roles.add(userRole);
        user.setRoles(roles);
        user.setActToken(createNewToken());
        userRepository.save(user);
    }

    @Override
    public User authorization(UserDTO userDto) throws ValidationException {
        Set<ConstraintViolation<UserDTO>> errors = validator.validate(userDto);
        ValidationException ex = new ValidationException();
        if (!errors.isEmpty()) {
            for (ConstraintViolation<UserDTO> err : errors) {
                ex.addError(new ObjectError(err.getPropertyPath().toString(), err.getMessage()));
            }
            throw ex;
        }
        User user = new User();
        User existing = findByUserName(userDto.getUsername());
        if (existing != null) {
            ex.addError(new ObjectError("username", "W systemie istnieje już użytkownik o takim adresie email"));
//            result.rejectValue("username", null, "There is already an account registered with that email");
        }
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            ex.addError(new ObjectError("password", "Błąd potwierdzenia hasła, podane hasła nie są zgodne"));//            result.rejectValue("password", null, "Password2 are incorrect");
        }
        if (ex.hasErrors()) {
            throw ex;
//            return user;
        }
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        //todo :: save
        //userRepository.save(user);
        return user;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepository.findAllUsers();
        List<UserDTO> userListDto = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            UserDTO currentUser = new UserDTO();
            currentUser.setUsername(userList.get(i).getUsername());
            currentUser.setId(userList.get(i).getId());
            currentUser.setCreated(formatDate(userList.get(i).getCreated()));
            currentUser.setEnabled(userList.get(i).getEnabled());
            userListDto.add(currentUser);
        }
        return userListDto;
    }

    @Override
    public UserDTO findByUserId(Long id) {
        User user = userRepository.findUserById(id);
        UserDTO editingUser = new UserDTO();

        editingUser.setId(user.getId());
        editingUser.setUsername(user.getUsername());
        editingUser.setFirstName(user.getFirstName());
        editingUser.setLastName(user.getLastName());
        editingUser.setEnabled(user.getEnabled());
        editingUser.setRoles(user.getRoles());
        return editingUser;
    }

    @Override
    public String formatDate(LocalDateTime beforeFormatting){

        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String afterFormatting = beforeFormatting.format(myFormatObj);
        return afterFormatting;

    }

    @Override
    public Boolean activationVerify(String email, String token){

        User user = userRepository.findByUsernameAndActToken(email, token);

        if(user.getId()>0) {
            user.setEnabled(1);
            Role userRole = roleRepository.findByName("ROLE_PARENT");
            Set<Role> roles = new HashSet<Role>();
            roles.add(userRole);
            user.setRoles(roles);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public String createNewToken(){

        char[] str = new char[100];

        for (int i = 0; i < 10; i++)
        {
            str[i] = (char) (((int)(Math.random() * 26)) + (int)'A');
        }

        return (new String(str, 0, 10));

    }
}