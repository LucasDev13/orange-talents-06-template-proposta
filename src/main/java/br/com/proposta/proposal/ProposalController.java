package br.com.proposta.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/proposal")
public class ProposalController {

    @Autowired private ProposalRepository proposalRepository;

    @PostMapping
    public ResponseEntity<?> saveProposal(@RequestBody ProposalRequest request){
        proposalRepository.save(request.toModel());
        return ResponseEntity.ok().build();
    }
}
