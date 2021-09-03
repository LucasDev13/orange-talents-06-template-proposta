package br.com.proposta.controller.request;

import br.com.proposta.card.BlockedStatus;
import br.com.proposta.card.Card;
import br.com.proposta.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class CardRequest {

    @NotBlank
    private String idCard;
    @NotBlank
    private String ipClient;
    @NotBlank
    private String userAgent;

    public CardRequest() {
    }

    public CardRequest(String idCard, String ipClient, String userAgent) {
        this.idCard = idCard;
        this.ipClient = ipClient;
        this.userAgent = userAgent;
    }

    public Card toModel(){
        return new Card(this.idCard, ipClient, userAgent);
    }

    public Card verifyBlockedCard(String idCard, CardRepository repository) {
        Card returnQuery = repository.findByIdCard(idCard);
        if (returnQuery != null){
            if (returnQuery.getBlockedStatus() == BlockedStatus.NOT_BLOCKED){
                returnQuery.setBlockedStatus();
            }
        }
        return returnQuery;
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
