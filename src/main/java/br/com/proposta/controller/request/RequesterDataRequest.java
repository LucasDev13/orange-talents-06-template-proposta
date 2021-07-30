package br.com.proposta.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequesterDataRequest{
    private String documento;
    private String nome;
    @JsonProperty("idProposta")
    private String idProposta;

    public RequesterDataRequest(String document, String name, Long id) {
        this.documento = document;
        this.nome = name;
        this.idProposta = id.toString();
    }


    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }

    @Override
    public String toString() {
        return "RequesterDataRequest{" +
                "documento='" + documento + '\'' +
                ", nome='" + nome + '\'' +
                ", idProposta='" + idProposta + '\'' +
                '}';
    }
}
