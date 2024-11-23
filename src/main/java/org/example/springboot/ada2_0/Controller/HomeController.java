package org.example.springboot.ada2_0.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @GetMapping("/home/{group_id}")
    public String Home(@PathVariable int group_id, Model model, HttpSession session) {
        session.setAttribute("group_id", group_id);
        model.addAttribute("group_id", group_id);
        return "home";
    }
}
