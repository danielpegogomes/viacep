package com.tradearena.viacep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CepController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/cep/{cep}")
    public ResponseEntity<?> getEndereco(@PathVariable String cep) {

        if (cep.length() != 8) {
            return ResponseEntity.badRequest().body("O CEP informado é inválido, informe apenas os 8 dígitos do CEP.");
        }

        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        String response = restTemplate.getForObject(url, String.class);

        return ResponseEntity.ok(response);
    }
}

// ------------------------------------------------------------------------------------

/*
package com.tradearena.viacep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CepController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/cep/{cep}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getEndereco(@PathVariable String cep) {

        if (cep.length() != 8) {
            return ResponseEntity.badRequest().body("O CEP informado é inválido, informe apenas os 8 dígitos do CEP.");
        }

        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        String response = restTemplate.getForObject(url, String.class);

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
*/