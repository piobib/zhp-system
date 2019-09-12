package pl.coderslab.zhpsystem.service;

import org.springframework.validation.BindingResult;
import pl.coderslab.zhpsystem.DTO.UserDTO;
import pl.coderslab.zhpsystem.entity.User;
import pl.coderslab.zhpsystem.exceptions.ValidationException;

import java.util.List;

public interface UserService {

    User findByUserName(String name);

    UserDTO findByUserId(Long id);

    void saveUser(User user);

    User authorization(UserDTO userDTO) throws ValidationException;

    List<UserDTO> getAllUsers();
}