package br.com.proposta.card;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String cardNumber;
    @NotNull
    private Instant instantBlock;
    @NotBlank
    private String ipClient;
    @NotBlank
    private String userAgent;
    @Enumerated(EnumType.STRING)
    private Status status;

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

    public boolean isActive() {
        return this.status == Status.ACTIVE;
    }

    public void block() {
        this.status = Status.BLOCKED;
    }
}
