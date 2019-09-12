package pl.coderslab.zhpsystem.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.zhpsystem.DTO.ChildrenDTO;
import pl.coderslab.zhpsystem.DTO.UserDTO;
import pl.coderslab.zhpsystem.service.ChildrenServiceImpl;

import java.util.Map;

@Controller
@Secured({"ROLE_ADMIN", "ROLE_TEACHER", "ROLE_SUBTEACHER"})
@RequestMapping("/account/children")
public class ChildrenController {

    private final ChildrenServiceImpl childrenServiceImpl;

    public ChildrenController(ChildrenServiceImpl childrenServiceImpl) {
        this.childrenServiceImpl = childrenServiceImpl;
    }

    @GetMapping("/add")
    public String accountChildrenList(Model model) {
        model.addAttribute("children", new ChildrenDTO());
        return "childrenAdd";
    }


    @GetMapping("")
    public ModelAndView accountChildrenAdd(Model model) {
        return new ModelAndView("childrenList", Map.of(
                "children", childrenServiceImpl.getAllChildren()
        ));
    }

}
