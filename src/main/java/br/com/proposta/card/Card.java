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
    private String idCard;
    @NotNull
    private Instant instantBlock;
    @NotBlank
    private String ipClient;
    @NotBlank
    private String userAgent;
    @Enumerated(EnumType.STRING)
    private BlockedStatus blockedStatus;

    @Deprecated
    public Card() {
    }

    public Card(String idCard, String ipClient,
                String userAgent) {
        this.idCard = idCard;
        this.instantBlock = Instant.now();
        this.ipClient = ipClient;
        this.userAgent = userAgent;
        this.blockedStatus = BlockedStatus.NOT_BLOCKED;
    }

    public BlockedStatus getBlockedStatus() {
        return blockedStatus;
    }

    public void setBlockedStatus(){
        this.blockedStatus = BlockedStatus.BLOCKED;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", idCard='" + idCard + '\'' +
                ", instantBlock=" + instantBlock +
                ", ipClient='" + ipClient + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", blockedStatus=" + blockedStatus +
                '}';
    }
}
