package br.com.proposta.controller.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CardResponse {
    @JsonProperty("id")
    private String numberCard;

    @Deprecated
    public CardResponse() {
    }
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CardResponse(String numberCard_id) {
        this.numberCard = numberCard;
    }

    public String getNumberCard() {
        return numberCard;
    }

    @Override
    public String toString() {
        return "CardResponse{" +
                "numberCard_id='" + numberCard + '\'' +
                '}';
    }
}
