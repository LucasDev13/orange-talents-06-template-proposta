package br.com.proposta.controller.request;

import br.com.proposta.card.Card;
import br.com.proposta.repository.CardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotBlank;

public class CardRequest {

    @NotBlank
    private String idCard;
    @NotBlank
    private String ipClient;
    @NotBlank
    private String userAgent;

    public CardRequest(String idCard, String ipClient, String userAgent) {
        this.idCard = idCard;
        this.ipClient = ipClient;
        this.userAgent = userAgent;
    }

    public Card verifyBlockedCard(String idCard, CardRepository repository) {
        Card returnQuery = repository.findByIdCard(idCard);
        if (returnQuery != null){
            if(returnQuery.isActive()){
                returnQuery.block();
                return returnQuery;
            }
        }
        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Override
    public String toString() {
        return "CardRequest{" +
                "idCard='" + idCard + '\'' +
                ", ipClient='" + ipClient + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
