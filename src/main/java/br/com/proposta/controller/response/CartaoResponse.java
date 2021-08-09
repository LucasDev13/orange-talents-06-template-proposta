package br.com.proposta.controller.response;

import com.fasterxml.jackson.annotation.JsonCreator;

public class CartaoResponse {

    private String id;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CartaoResponse(String id) {
        this.id = id;
    }

    public String getCard() {
        return id;
    }
}
