package br.com.proposta.controller.request;

import br.com.proposta.biometry.Biometry;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Base64;

public class BiometryRequest {

    @Pattern(regexp = "^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{4})$")
    @NotBlank
    private String fingerprintBiometric;
    private String idCard;
    private LocalDate dateRegister;

    public BiometryRequest(String fingerprintBiometric, LocalDate dateRegister, String idCard) {
        this.fingerprintBiometric = fingerprintBiometric;
        this.dateRegister = LocalDate.now();
        this.idCard = idCard;
    }

    public Biometry toModel(String idCard) {
        return new Biometry(fingerprintBiometric(this.fingerprintBiometric), idCard);
    }

    private String fingerprintBiometric(String fingerprintBiometric) {
        var base64 = Base64.getEncoder()
                .encodeToString(fingerprintBiometric.getBytes(StandardCharsets.UTF_8));
        return base64;
    }

    public String getIdCard() {
        return idCard;
    }
}
