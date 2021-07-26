package br.com.proposta.proposal;

import br.com.proposta.config.validation.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Document
    private String document;
    @Enumerated(EnumType.STRING)
    private Restrictions restrictions = Restrictions.SEM_RESTRICAO;
    @Email @NotBlank
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String address;

    @Positive @NotNull
    private BigDecimal salary;

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

    public Long getId() {
        return id;
    }
}
