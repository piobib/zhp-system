package pl.coderslab.zhpsystem.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.zhpsystem.DTO.UserDTO;
import pl.coderslab.zhpsystem.entity.User;
import pl.coderslab.zhpsystem.exceptions.ObjectError;
import pl.coderslab.zhpsystem.exceptions.ValidationException;
import pl.coderslab.zhpsystem.service.CurrentUser;
import pl.coderslab.zhpsystem.service.UserService;
import pl.coderslab.zhpsystem.validation.UserEditGroup;
import pl.coderslab.zhpsystem.validation.UserRegistrationGroup;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerUserForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "registerForm";
    }

    @PostMapping("/register")
    public String registerUserAccount(@ModelAttribute("user") @Validated(UserRegistrationGroup.class) UserDTO userDto,
                                      BindingResult result) {

        try {
            User user = userService.authorization(userDto);
            userService.saveUser(user);
        } catch (ValidationException e) {
            List<ObjectError> errors = e.getErrors();
            for (ObjectError err : errors) {
                if (result.getFieldError(err.getFieldName()) == null) {
                    result.rejectValue(err.getFieldName(), null, err.getMessage());
                }
            }
        }

        if (result.hasErrors()) {
            return "registerForm";
        }
        return "redirect:login";
    }




    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        User user = new User();
        user.setUsername("admin1@test.pl");
        user.setPassword("admin");
        userService.saveUser(user);
        return "admin";
    }


    @GetMapping("/admin")
    @ResponseBody
    public String admin(@AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();

        return "Hello " + entityUser.getId();
    }


}
