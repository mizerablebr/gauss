package br.unifil.gauss;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    public static final String SISTEMA = "sistema";

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute(SISTEMA, new MathSystem());
        return "index";
    }

    @PostMapping
    public String postPage(@ModelAttribute MathSystem equacao, Model model) {
        System.out.println(equacao);
        return "index";
    }

}
