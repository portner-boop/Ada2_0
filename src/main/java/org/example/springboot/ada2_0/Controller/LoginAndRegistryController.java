package org.example.springboot.ada2_0.Controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.springboot.ada2_0.Dto.RegistryUser;
import org.example.springboot.ada2_0.Entity.MyUser;
import org.example.springboot.ada2_0.Services.RegistryUserHandler;
import org.example.springboot.ada2_0.Services.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginAndRegistryController {
    @Autowired
    private RegistryUserHandler registryUserHandler;
    @Autowired
    private UserHandler userHandler;


    @GetMapping("/login")
    public String goLogin(){
        return "login";
    }
    @GetMapping("/registration/user")
    public String goRegistration(Model model){
        RegistryUser user = new RegistryUser();
        model.addAttribute("user", user);
        return "registration";
    }
    @PostMapping("/registration/user")
    public String createUser(@Valid @ModelAttribute("user") RegistryUser user, BindingResult bindingResult, Model model, HttpSession session){

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "registration";
        }
        MyUser myUser = registryUserHandler.userHandlerProc(user);
        if(myUser == null){
            model.addAttribute("error1", "Такой группы нет или логин занят");
            model.addAttribute("user", user);
            return "registration";
        }
        userHandler.saveUser(myUser);
        session.setAttribute("user", myUser);
        return "redirect:/login";


    }
    @GetMapping("/api/user")
    public ResponseEntity<MyUser> getUser(HttpSession session) {
        MyUser user = (MyUser) session.getAttribute("user");
        if (user != null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

}
