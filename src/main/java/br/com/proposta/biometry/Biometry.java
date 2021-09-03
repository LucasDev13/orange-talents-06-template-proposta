package br.com.proposta.biometry;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class Biometry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank private String fingerprintBiometic;
    @NotBlank private String idCard;
    private LocalDate dateRegister;

    @Deprecated
    public Biometry() {
    }

    public Biometry(String fingerprintBiometic, String idCard) {
        this.fingerprintBiometic = fingerprintBiometic;
        this.dateRegister = LocalDate.now();
        this.idCard = idCard;
    }

    @Override
    public String toString() {
        return "Biometry{" +
                "id=" + id +
                ", fingerprintBiometic='" + fingerprintBiometic + '\'' +
                ", idCard='" + idCard + '\'' +
                ", dateRegister=" + dateRegister +
                '}';
    }

    public Long getId() {
        return id;
    }

}
