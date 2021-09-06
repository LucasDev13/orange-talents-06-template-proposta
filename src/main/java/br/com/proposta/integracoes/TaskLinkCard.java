package br.com.proposta.integracoes;

import br.com.proposta.card.Card;
import br.com.proposta.controller.ClientCard;
import br.com.proposta.controller.response.CartaoResponse;
import br.com.proposta.proposal.Proposal;
import br.com.proposta.proposal.StatusRequester;
import br.com.proposta.repository.CardRepository;
import br.com.proposta.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@EnableAsync
@EnableScheduling
public class TaskLinkCard {

    private ProposalRepository proposalRepository;
    private CardRepository cardRepository;
    private ClientCard clientCard;

    @Autowired
    public TaskLinkCard(ProposalRepository proposalRepository,
                        CardRepository cardRepository, ClientCard clientCard) {
        this.proposalRepository = proposalRepository;
        this.cardRepository = cardRepository;
        this.clientCard = clientCard;
    }

    @Scheduled(fixedRateString = "${periodicity.execute.myTask}")
    @Transactional
    public void associateApprovedCard(){
        var list = proposalRepository.searchForProposalsToLinkCard(StatusRequester.ELIGIBLE);
        list.forEach(proposal -> {
            var send = sendClientCard(proposal);
            proposal.addCard(send.getCard());
            proposalRepository.save(proposal);
        });
    }

    private CartaoResponse sendClientCard(Proposal proposal){
        return clientCard.sendCards(proposal.getId().toString());
    }
}