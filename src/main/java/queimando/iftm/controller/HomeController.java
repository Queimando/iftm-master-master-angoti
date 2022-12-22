package queimando.iftm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import queimando.iftm.repository.ProdutoRepository;

@Controller
public class HomeController {
    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping({ "home", "/" })
    public String home(Model model) {
        model.addAttribute("lista", produtoRepository.findAll());
        return "home";
    }
}
