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

    public Card toModel(String idCard, String ipClient, String userAgent){
        return new Card(idCard, ipClient, userAgent);
    }

    public Card verifyBlockedCard(String idCard, CardRepository repository) {
        var returnQuery = repository.findByCardNumber(idCard);
        //podemos buscar o cartão na proposta para incluir no cartão
        //por que ele vai ser criado com o valor ativo.
//        if (returnQuery.isPresent()){
//            if(returnQuery.isActive()){
//                returnQuery.block();
//                return returnQuery;
//            }
//        }
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
