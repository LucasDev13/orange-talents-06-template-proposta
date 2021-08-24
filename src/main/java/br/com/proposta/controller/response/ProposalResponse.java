package br.com.proposta.controller.response;

import br.com.proposta.proposal.Proposal;
import br.com.proposta.proposal.StatusRequester;

import java.math.BigDecimal;

public class ProposalResponse {

    private String document;
    private StatusRequester statusRequester;
    private String email;
    private String name;
    private String address;
    private BigDecimal salary;


    public ProposalResponse(Proposal proposal) {
        this.document = proposal.getDocument();
        this.statusRequester = proposal.getStatusRequester();
        this.email = proposal.getEmail();
        this.name = proposal.getName();
        this.address = proposal.getAddress();
        this.salary = proposal.getSalary();
    }

    public String getDocument() {
        return document;
    }

    public StatusRequester getStatusRequester() {
        return statusRequester;
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

    @Override
    public String toString() {
        return "ProposalResponse{" +
                "document='" + document + '\'' +
                ", statusRequester=" + statusRequester +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                '}';
    }
}
