package pl.coderslab.zhpsystem.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.zhpsystem.DTO.ChildrenDTO;
import pl.coderslab.zhpsystem.DTO.ChildrenTokenDTO;
import pl.coderslab.zhpsystem.repository.ChildrenRepository;
import pl.coderslab.zhpsystem.service.ChildrenServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Secured({"ROLE_ADMIN", "ROLE_TEACHER", "ROLE_SUBTEACHER"})
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
    @ResponseBody
    public String myChildrenAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pesel = request.getParameter("pesel");
        String token = request.getParameter("token");
        if(childrenServiceImpl.checkToken(pesel, token)){

           return "Sukces";
        }
        return "porażka";

    }

}
