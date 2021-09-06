package br.com.proposta.card;

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
    @NotNull
    private Instant instantBlock;
    private String ipClient;
    private String userAgent;
    @Enumerated(EnumType.STRING)
    private Status status;

//    @OneToOne @NotNull
//    @JoinColumn(name = "idProposal")
//    private Proposal proposal;

    @Deprecated
    public Card() {
    }

    public Card(String idCard, String ipClient,
                String userAgent) {
        this.cardNumber = idCard;
        this.instantBlock = Instant.now();
        this.ipClient = ipClient;
        this.userAgent = userAgent;
        this.status = Status.ACTIVE;
    }

    public Card(String cardNumber) {
        this.cardNumber = cardNumber;
        this.instantBlock = Instant.now();
        this.status = Status.ACTIVE;
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

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


}
