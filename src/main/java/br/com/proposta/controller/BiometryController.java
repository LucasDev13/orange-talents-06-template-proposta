package br.com.proposta.controller;

import br.com.proposta.controller.request.BiometryRequest;
import br.com.proposta.repository.BiometryRepository;
import br.com.proposta.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/biometry")
public class BiometryController {

    private BiometryRepository biometryRepository;
    private ProposalRepository proposalRepository;

    @Autowired
    public BiometryController(BiometryRepository biometryRepository,
                              ProposalRepository proposalRepository) {
        this.biometryRepository = biometryRepository;
        this.proposalRepository = proposalRepository;
    }

    @PostMapping("/{idCard}")
    @Transactional
    public ResponseEntity<?> saveBiometry(@PathVariable("idCard") String idCard,
                                          @RequestBody @Valid BiometryRequest biometryRequest,
                                          UriComponentsBuilder uriComponentsBuilder){
        var card = proposalRepository.findByCard(idCard);
        if (card.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrei o cartão.");
        }
        var biometry = biometryRequest.toModel(idCard);
        biometryRepository.save(biometry);
        var uri = uriComponentsBuilder.path("/biometry/{idCard}")
                .buildAndExpand(biometry.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
