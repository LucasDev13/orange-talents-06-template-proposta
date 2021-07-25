package br.com.proposta.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/proposal")
public class ProposalController {

    @Autowired private ProposalRepository proposalRepository;

    @PostMapping
    public ResponseEntity<?> saveProposal(@Valid @RequestBody ProposalRequest request,
                                          UriComponentsBuilder uriComponentsBuilder){
        Proposal proposal = proposalRepository.save(request.toModel());
        return ResponseEntity.created(uriComponentsBuilder.path("/proposal/{id}").buildAndExpand(proposal.getId()).toUri()).body(proposal);
    }
}
