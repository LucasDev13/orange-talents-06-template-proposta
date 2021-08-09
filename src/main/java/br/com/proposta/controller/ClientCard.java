package br.com.proposta.controller;

import br.com.proposta.controller.response.CartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cards", url = "http://localhost:8888/api/cartoes")
public interface ClientCard {

    @GetMapping("?idProposta={id}")
    CartaoResponse sendCards(@RequestParam String id);
}
