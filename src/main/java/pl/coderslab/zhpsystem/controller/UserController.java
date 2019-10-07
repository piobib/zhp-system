package pl.coderslab.zhpsystem.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.zhpsystem.DTO.UserDTO;
import pl.coderslab.zhpsystem.entity.User;
import pl.coderslab.zhpsystem.exceptions.ObjectError;
import pl.coderslab.zhpsystem.exceptions.ValidationException;
import pl.coderslab.zhpsystem.service.CurrentUser;
import pl.coderslab.zhpsystem.service.EmailService;
import pl.coderslab.zhpsystem.service.UserService;
import pl.coderslab.zhpsystem.validation.UserEditGroup;
import pl.coderslab.zhpsystem.validation.UserRegistrationGroup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
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
            if (!result.hasErrors()) {
                userService.saveUser(user);
                EmailService emailService = new EmailService(user.getUsername(), "e-Harcerz: aktywacja konta", "register", user.getActToken());

            }



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


    @GetMapping("/activation/{email}/{token}")
    public String userActivate(Model model, @PathVariable("email") String email, @PathVariable("token") String token, HttpServletRequest request, HttpServletResponse response) {

        try {
            userService.activationVerify(email, token);
            model.addAttribute("activation", true);

        } catch (Exception e) {

        }
        CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        cookieClearingLogoutHandler.logout(request, response, null);
        securityContextLogoutHandler.logout(request, response, null);
        return "redirect:/activation";


    }
    @GetMapping("/activation")
    public String activation() {


        return "activation";
    }
    @GetMapping("/admin")
    @ResponseBody
    public String admin(@AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();

        return "Hello " + entityUser.getId();
    }

    @GetMapping("/login/error")
    public String admin(Model model) {
        model.addAttribute("error", true);

        return "login";
    }


}
