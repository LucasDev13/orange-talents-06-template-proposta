package br.com.proposta.proposal;

import br.com.proposta.config.Document;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class ProposalRequest {

    @Document @NotBlank @NotNull @NotEmpty
    private String document;
    @Email @NotBlank @NotNull @NotEmpty
    private String email;
    @NotBlank @NotNull @NotEmpty
    private String name;
    @NotBlank @NotNull @NotEmpty
    private String address;
    @Positive @NotNull
    private BigDecimal salary;

    Proposal toModel(){
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
