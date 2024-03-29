package pl.coderslab.zhpsystem.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.zhpsystem.repository.ChildrenRepository;
import pl.coderslab.zhpsystem.service.ChildrenServiceImpl;
import pl.coderslab.zhpsystem.service.CurrentUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@Secured({"ROLE_ADMIN", "ROLE_TEACHER", "ROLE_SUBTEACHER", "ROLE_PARENT"})
@RequestMapping("/account/my-children")
public class MyChildrenController {

    private final ChildrenServiceImpl childrenServiceImpl;
    private final ChildrenRepository childrenRepository;

    public MyChildrenController(ChildrenServiceImpl childrenServiceImpl, ChildrenRepository childrenRepository) {
        this.childrenServiceImpl = childrenServiceImpl;
        this.childrenRepository = childrenRepository;
    }

    @GetMapping("/add")
    public String myChildrenAdd() {

        return "myChildrenAdd";

    }

    @PostMapping("/add")
    public String myChildrenAdd(@AuthenticationPrincipal CurrentUser customUser, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pesel = request.getParameter("pesel");
        String token = request.getParameter("token");
        Long userId = customUser.getUser().getId();
        if(childrenServiceImpl.checkToken(pesel, token, userId)){

           return "redirect:/account/my-children/list";
        }
        request.setAttribute("pesel", pesel);
        request.setAttribute("token", token);
        return "myChildrenAdd";

    }

    @GetMapping("/list")
    public ModelAndView myChildrenList(Model model, @AuthenticationPrincipal CurrentUser customUser) {
        Long loggedUser = customUser.getUser().getId();
        return new ModelAndView("myChildrenList", Map.of(
                "children", childrenServiceImpl.getAllChildrenByParentId(loggedUser)
        ));
    }
    @GetMapping("/transfer-data/{id}")
    public String myChildrenTransferData(Model model, @PathVariable("id") long id) {
        String transferData = childrenServiceImpl.createTransferData(id);
        model.addAttribute("transferData", transferData);
        return "myChildrenTransferData";

    }


}
