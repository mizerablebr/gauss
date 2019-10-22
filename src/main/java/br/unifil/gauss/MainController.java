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
        model.addAttribute(SISTEMA, equacao);
        // Multiplicando L * U
        Float[][] resultadoMultiplicacao = new Float[equacao.getN()][equacao.getN()];
        Float[][] l = equacao.getL();
        Float[][] u = equacao.getU();

        for (int i = 0; i < equacao.getN(); i++) {
            for (int j = 0; j < equacao.getN(); j++) {
                resultadoMultiplicacao[i][j] = 0f;
                for (int k = 0; k < equacao.getN(); k++) {
                    resultadoMultiplicacao[i][j] += l[i][k] * u[k][j];
                }
            }
        }

        model.addAttribute("resultadoMultiplicacao", resultadoMultiplicacao);
        return "index";
    }

}
