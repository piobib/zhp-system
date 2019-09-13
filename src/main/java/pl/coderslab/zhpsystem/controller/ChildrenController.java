package pl.coderslab.zhpsystem.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.zhpsystem.DTO.ChildrenDTO;
import pl.coderslab.zhpsystem.DTO.ChildrenTokenDTO;
import pl.coderslab.zhpsystem.DTO.UserDTO;
import pl.coderslab.zhpsystem.entity.Children;
import pl.coderslab.zhpsystem.repository.ChildrenRepository;
import pl.coderslab.zhpsystem.service.ChildrenServiceImpl;
import pl.coderslab.zhpsystem.validation.UserEditGroup;

import java.util.List;
import java.util.Map;

@Controller
@Secured({"ROLE_ADMIN", "ROLE_TEACHER", "ROLE_SUBTEACHER"})
@RequestMapping("/account/children")
public class ChildrenController {

    private final ChildrenServiceImpl childrenServiceImpl;
    private final ChildrenRepository childrenRepository;

    public ChildrenController(ChildrenServiceImpl childrenServiceImpl, ChildrenRepository childrenRepository) {
        this.childrenServiceImpl = childrenServiceImpl;
        this.childrenRepository = childrenRepository;
    }

    @GetMapping("")
    public ModelAndView accountChildrenAdd(Model model) {
        return new ModelAndView("childrenList", Map.of(
                "children", childrenServiceImpl.getAllChildren()
        ));
    }

    @GetMapping("/add")
    public String accountChildrenList(Model model) {
        model.addAttribute("children", new ChildrenDTO());
        return "childrenAdd";
    }

    @PostMapping("/add")
    public String userEditSave(@ModelAttribute("children") @Validated ChildrenDTO children, BindingResult result) {

        if (result.hasErrors()) {    //sprawdza czy są błędy walidacji
            return "childrenAdd";
        }
        Children newChildren = new Children();
        newChildren.setFirstName(children.getFirstName());
        newChildren.setLastName(children.getLastName());
        newChildren.setPesel(children.getPesel());
        newChildren.setActive(1);
        childrenRepository.save(newChildren);


        return "redirect:/account/children";
    }

    @Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
    @GetMapping("/edit/{id}")
    public String userEdit(Model model, @PathVariable("id") long id) {
        ChildrenDTO childrenDTO = childrenServiceImpl.findByChildrenId(id);
        model.addAttribute("children", childrenDTO);
        return "childrenEdit";
    }

    @Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
    @PostMapping("/edit")
    public String childrenEditSave(@ModelAttribute("children") @Validated ChildrenDTO childrenDto, BindingResult result) {

        if (result.hasErrors()) {    //sprawdza czy są błędy walidacji
            return "childrenEdit";
        }

        childrenServiceImpl.updateChildren(childrenDto);

        return "redirect:/account/children";
    }
    @Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
    @GetMapping("/tokens/{id}")
    public String childrenToken(Model model, @PathVariable("id") long id) {
        model.addAttribute("childrenTokenList", childrenServiceImpl.findTokensByChildrenId(id));
        model.addAttribute("childrenId", id);
        return "childrenToken";
    }
    @Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
    @GetMapping("/newToken/{id}")
    public String childrenCreateToken(@PathVariable("id") long id) {

        childrenServiceImpl.createNewToken(id);

        return "redirect:/account/children/tokens/{id}";
    }

}
