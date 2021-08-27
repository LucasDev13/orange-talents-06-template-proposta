package br.com.proposta.proposal;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = false)
    private String document;
    @Enumerated(EnumType.STRING)
    private StatusRequester statusRequester;
    @Email @NotBlank
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String address;

    @Positive @NotNull
    private BigDecimal salary;

    private String card;

    @Deprecated
    public Proposal(){
    }

    public Proposal(String document, String email,
                    String name, String address, BigDecimal salary){
        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public Proposal(Proposal proposal, StatusRequester convertStatus) {
        this.id = proposal.getId();
        this.document = proposal.getDocument();
        this.email = proposal.getEmail();
        this.name = proposal.getName();
        this.address = proposal.getAddress();
        this.salary = proposal.getSalary();
        this.statusRequester = convertStatus;
    }

    public Long getId() {
        return id;
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

    public String getCard() {
        return card;
    }

    public void addCard(String card){
        this.card = card;
    }

    @Override
    public String toString() {
        return "Proposal{" +
                "id=" + id +
                ", document='" + document + '\'' +
                ", statusRequester=" + statusRequester +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", card='" + card + '\'' +
                '}';
    }
}
