package br.com.proposta.repository;

import br.com.proposta.proposal.Proposal;
import br.com.proposta.proposal.StatusRequester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {

    @Query("select p from Proposal p where p.statusRequester=:elegivel and p.card=null")
    List<Proposal> searchForProposalsToLinkCard(StatusRequester elegivel);
}
