package br.com.proposta.card;

import br.com.proposta.controller.request.CardRequest;
import br.com.proposta.proposal.Proposal;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;
    private Instant instantBlock;
    private String ipClient;
    private String userAgent;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    @JoinColumn(name = "idProposal")
    private Proposal proposal;

    @Deprecated
    public Card() {
    }

    public Card(String idCard, String ipClient,
                String userAgent, Status status) {
        this.cardNumber = idCard;
        this.instantBlock = Instant.now();
        this.ipClient = ipClient;
        this.userAgent = userAgent;
        this.status = status;
    }

    public Card(String cardNumber) {
        this.cardNumber = cardNumber;
        this.status = Status.ACTIVE;
    }

    public void card(CardRequest cardRequest){
        this.cardNumber = cardRequest.getIdCard();
        this.ipClient = cardRequest.getIpClient();
        this.userAgent = cardRequest.getUserAgent();
        this.status = cardRequest.getStatus();
    }

    public boolean isActive() {
        return this.status == Status.ACTIVE;
    }

    public boolean isBlock() {
        return this.status == Status.BLOCKED;
    }

    public void block() {
        this.status = Status.BLOCKED;
    }

    public void addCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Status getStatus() {
        return status;
    }
}
