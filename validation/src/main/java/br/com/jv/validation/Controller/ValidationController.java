package br.com.jv.validation.Controller;


import br.com.jv.validation.Model.Usuario;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ValidationController {

    private List<String> listaDeEmails = new ArrayList<>();

    // Inicializa a lista de e-mails
    public ValidationController() {
        listaDeEmails.add("everton@pro.ucsal.br");
        listaDeEmails.add("joao@exemplo.com");
        listaDeEmails.add("neymar@exemplo.com");

    }

    @PostMapping("/validar-email")
    public String validarEmail(@RequestBody String email) {
        // Verifica se o e-mail está na lista
        if (listaDeEmails.contains(email)) {
            return "O e-mail " + email + " é válido.";
        } else {
            return "O e-mail " + email + " não é válido.";
        }
    }

    // endpoint para validar o e-mail do usuário
    @PostMapping("/validar-usuario")
    public String validarUsuario(@RequestBody Usuario usuario) {
        // Verifica se o usuário está na lista
        if (listaDeEmails.contains(usuario.getEmail())) {
            return "O usuário com o e-mail " + usuario.getEmail() + " está presente na lista de usuários.";
        } else {
            return "O usuário com o e-mail " + usuario.getEmail() + " não está presente na lista de usuários.";
        }
    }
}
