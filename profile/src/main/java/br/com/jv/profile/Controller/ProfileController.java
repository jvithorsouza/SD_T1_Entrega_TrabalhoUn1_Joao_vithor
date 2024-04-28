package br.com.jv.profile.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProfileController {

    // HashMap para mapear e-mails aos perfis dos usuários
    private Map<String, String> dados = new HashMap<>();

    // Inicializa o HashMap
    public ProfileController() {
        dados.put("everton@pro.ucsal.br", "Professor");
        dados.put("joao@exemplo.com", "Aluno");
        dados.put("maria@exemplo.com", "Funcionário");
    }

    @PostMapping("/perfil")
    public String verificarPerfil(@RequestBody String email) {
        // Verifica se o e-mail está no HashMap
        if (dados.containsKey(email)) {
            String perfil = dados.get(email);
            return "O perfil do usuário com o e-mail " + email + " é: " + perfil;
        } else {
            return "O e-mail " + email + " não possui perfil associado.";
        }
    }
}
