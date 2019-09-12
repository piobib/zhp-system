package pl.coderslab.zhpsystem.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.zhpsystem.DTO.UserDTO;
import pl.coderslab.zhpsystem.entity.User;
import pl.coderslab.zhpsystem.repository.RoleRepository;
import pl.coderslab.zhpsystem.repository.UserRepository;
import pl.coderslab.zhpsystem.service.UserServiceImpl;
import pl.coderslab.zhpsystem.validation.UserEditGroup;

import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final UserRepository userRepository;
    private final UserServiceImpl userServiceImpl;
    private final RoleRepository roleRepository;
    public AccountController(UserRepository userRepository, UserServiceImpl userServiceImpl, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userServiceImpl = userServiceImpl;
        this.roleRepository = roleRepository;
    }

    @GetMapping("")
    public String accountMain() {
        return "account";
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/users")
    public ModelAndView accountUserList(Model model) {
        return new ModelAndView("userList", Map.of(
                "users", userServiceImpl.getAllUsers()
        ));
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/users/edit/{id}")
    public String userEdit(Model model, @PathVariable("id") long id) {
        UserDTO user = userServiceImpl.findByUserId(id);
        model.addAttribute("user",user);
        model.addAttribute("roles",roleRepository.findAllRoles());
        model.addAttribute("userRole",roleRepository.findRoleById(id));
        return "userEdit";

    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/users/edit")
    public String userEditSave(@ModelAttribute("user") @Validated(UserEditGroup.class) UserDTO userDto, BindingResult result) {

        if (result.hasErrors()) {	//sprawdza czy są błędy walidacji
            return "userEdit";
        }
        User currentUser = userRepository.findUserById(userDto.getId());

        currentUser.setUsername(userDto.getUsername());
        currentUser.setFirstName(userDto.getFirstName());
        currentUser.setLastName(userDto.getLastName());
        currentUser.setEnabled(userDto.getEnabled());
        currentUser.setRoles(userDto.getRoles());
        userRepository.save(currentUser);


        return "redirect:/account/users";
    }
}
