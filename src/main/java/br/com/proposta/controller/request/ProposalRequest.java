package br.com.proposta.controller.request;

import br.com.proposta.config.validation.Document;
import br.com.proposta.config.validation.UnicValue;
import br.com.proposta.proposal.Proposal;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class ProposalRequest {

    @UnicValue(domainClass = Proposal.class, fieldName = "document")
    @Document
    @NotBlank @NotNull @NotEmpty
    private String document;
    @Email @NotBlank @NotNull @NotEmpty
    private String email;
    @NotBlank @NotNull @NotEmpty
    private String name;
    @NotBlank @NotNull @NotEmpty
    private String address;
    @Positive @NotNull
    private BigDecimal salary;

    public Proposal toModel(){
        return new Proposal(this.document, this.email, this.name, this.address, this.salary);
    }

    public String getDocument() {
        return document;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getSalary() {
        return salary;
    }
}
