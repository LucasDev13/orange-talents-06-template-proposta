package br.com.proposta.controller;

import br.com.proposta.card.Card;
import br.com.proposta.controller.request.CardRequest;
import br.com.proposta.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/card")
public class CardController {

    private HttpServletRequest request;
    private CardRepository cardRepository;

    @Autowired
    public CardController(HttpServletRequest request, CardRepository cardRepository) {
        this.request = request;
        this.cardRepository = cardRepository;
    }

    @PostMapping("/{idCard}/bloqueio-cartao")
    @Transactional
    public ResponseEntity<?> create(@PathVariable() String idCard) {
        //CARTÃO NÃO ENCONTRADO RETORNA 404 - NOT_FOUND
        var returnQuery =cardRepository.findByCardNumber(idCard);
        if (returnQuery.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        String ipClient = request.getRemoteHost();
        if (ipClient == null || "".equals(ipClient)) {
            ipClient = request.getRemoteHost();
        }
        String userAgent = request.getHeader("User-Agent");

        var cardRequest = new CardRequest(idCard, ipClient, userAgent);
        var card =cardRequest.toModel(idCard, ipClient, userAgent);

        //CARTÃO JÁ BLOQUEADO RETORNA 422 HttpStatus.UNPROCESSABLE_ENTITY
        if (!card.isActive()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        cardRepository.save(card);
//        var resultCard = cardRequest.verifyBlockedCard(idCard, cardRepository);
//        cardRepository.save(card);
        //200 em caso de bloqueio com sucesso
        return ResponseEntity.ok().build();
    }
}
