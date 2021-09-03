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
    public ResponseEntity<?> saveCardBlock(@Valid @PathVariable("idCard") String idCard,
            CardRequest cardRequest) {
        //Caso o cartão esteja já bloqueado devemos retornar um erro de negócio.

        String ipClient = request.getRemoteHost();
        if (ipClient == null || "".equals(ipClient)) {
            ipClient = request.getRemoteHost();
        }
        String userAgent = request.getHeader("User-Agent");

        cardRequest = new CardRequest(idCard, ipClient, userAgent);
        var card = cardRequest.toModel();
        cardRepository.save(card);
        try {
            //preciso salvar o cartão antes de verificar.
            var resultCard = cardRequest.verifyBlockedCard(idCard, cardRepository);
            //atualiza o cartão
            cardRepository.save(card);
            System.out.println("Controller result: " + card);
        }catch (RuntimeException e){
//            return ResponseEntity.unprocessableEntity().build();
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return ResponseEntity.ok(card.toString());
    }

}
