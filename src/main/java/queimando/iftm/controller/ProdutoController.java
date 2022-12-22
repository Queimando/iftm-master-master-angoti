package queimando.iftm.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import queimando.iftm.model.Produto;
import queimando.iftm.model.Usuario;
import queimando.iftm.repository.ProdutoRepository;
import queimando.iftm.security.QueimandoSecurityUserDetails;

@Controller
public class ProdutoController {

    @Autowired
    ProdutoRepository repository;

    @GetMapping("cadastrarpro")
    public String formCadastrPromo(Model model) {
        model.addAttribute("promocao", new Produto());
        return "form_cadastro";
    }

    @PostMapping("cadastrarpro")
    public String gravaNovaPromo(Produto produto, @RequestParam MultipartFile arquivo) throws IOException, URISyntaxException {
        System.out.println("----------------------------------------");
        System.out.println(arquivo.getOriginalFilename());
        System.out.println(arquivo.getInputStream().toString());
        Path root = Paths.get("src/main/resources/static/image-upload");
        String nomeArquivo = arquivo.getOriginalFilename().replace(".", new StringBuffer(Math.abs(Instant.now().hashCode()) + "."));
        Files.copy(arquivo.getInputStream(), root.resolve(nomeArquivo));
        produto.setFoto(nomeArquivo);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long id_user = ((QueimandoSecurityUserDetails) authentication.getPrincipal()).getUsuario().getId();
        produto.setId(id_user);
        repository.save(produto);
        return "redirect:/home";
    }
    @GetMapping("prod_comp")
    public String exibeProduto(@RequestParam Long id,Model model) {
        Produto produto=repository.findById(id);
        model.addAttribute("prod", produto);
        System.out.println(produto);
        return "comprar";
    }
}