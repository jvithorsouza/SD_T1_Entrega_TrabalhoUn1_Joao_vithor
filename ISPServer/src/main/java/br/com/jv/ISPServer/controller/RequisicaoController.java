package br.com.jv.ISPServer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class RequisicaoController {
   /* @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/health")
    public String healthy() {
        return "Sou o ISP Server e estou online!" + LocalDateTime.now();
    }*/

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/health")
    public String healthy() {
        return "Sou o ISP Server e estou online! " + LocalDateTime.now();
    }

    @GetMapping("/processarRequisicao")
    public ResponseEntity<String> processarRequisicao(@RequestParam String url) {
        try {
            // Obter lista de serviços registrados
            List<String> services = discoveryClient.getServices();

            // Encontrar o serviço correspondente à URL
            String serviceUrl = encontrarUrlServico(url, services);

            if (serviceUrl != null) {
                // Fazer chamada para o serviço
                ResponseEntity<String> response = fazerChamadaServico(serviceUrl, url);
                return ResponseEntity.ok(response.getBody());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Serviço correspondente não encontrado para a URL: " + url);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao processar a requisição: " + e.getMessage());
        }
    }

    private String encontrarUrlServico(String url, List<String> services) {

        for (String service : services) {
            if (url.contains(service)) {
                return service;
            }
        }
        return null;
    }

    private ResponseEntity<String> fazerChamadaServico(String serviceUrl, String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://" + serviceUrl + url, String.class);
        return response;
    }

}