package br.com.proposta.controller.request;

import br.com.proposta.card.Card;
import br.com.proposta.card.Status;

import javax.validation.constraints.NotBlank;

public class CardRequest {

    @NotBlank
    private String idCard;
    @NotBlank
    private String ipClient;
    @NotBlank
    private String userAgent;

    private Status status;

    public CardRequest(String idCard, String ipClient, String userAgent, Status status) {
        this.idCard = idCard;
        this.ipClient = ipClient;
        this.userAgent = userAgent;
        this.status = status;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getIpClient() {
        return ipClient;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Status getStatus() {
        return status;
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
