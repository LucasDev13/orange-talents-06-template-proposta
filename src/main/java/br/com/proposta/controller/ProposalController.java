package br.com.proposta.controller;

import br.com.proposta.controller.request.ProposalRequest;
import br.com.proposta.controller.request.RequesterDataRequest;
import br.com.proposta.controller.response.RequesterDataResponse;
import br.com.proposta.proposal.AnalysisRestriction;
import br.com.proposta.proposal.NewAnalysi;
import br.com.proposta.proposal.Proposal;
import br.com.proposta.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/proposal")
public class ProposalController {

    private ProposalRepository proposalRepository;
    private ClientRequester clientRequester;
    private ClientCard clientCard;

    @Autowired
    public ProposalController(ProposalRepository proposalRepository,
                              ClientRequester clientRequester, ClientCard clientCard) {
        this.proposalRepository = proposalRepository;
        this.clientRequester = clientRequester;
        this.clientCard = clientCard;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> saveProposal(@Valid @RequestBody ProposalRequest request,
                                          UriComponentsBuilder uriComponentsBuilder){
        var convert = request.toModel();
        var saved = proposalRepository.save(convert);
        saved = clientDataRequester(saved);
        System.out.println("id: " + saved.getId().toString());
        proposalRepository.save(saved);
        return ResponseEntity.created(uriComponentsBuilder.path("/proposal/{id}").buildAndExpand(saved.getId()).toUri()).body(saved);
    }

    public Proposal clientDataRequester(Proposal proposal){
        var requesterDataRequest = new RequesterDataRequest(proposal.getDocument(), proposal.getName(), proposal.getId());
        var response = clientRequester.sendRequester(requesterDataRequest);
        System.out.println(response.toString());
        return new Proposal(proposal, response.getAnalysisRestriction().convertStatus());
    }
}
