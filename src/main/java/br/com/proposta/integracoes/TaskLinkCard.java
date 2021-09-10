package br.com.proposta.integracoes;

import br.com.proposta.card.Card;
import br.com.proposta.config.validation.CustomException;
import br.com.proposta.controller.ClientCard;
import br.com.proposta.controller.response.CartaoResponse;
import br.com.proposta.proposal.Proposal;
import br.com.proposta.proposal.StatusRequester;
import br.com.proposta.repository.CardRepository;
import br.com.proposta.repository.ProposalRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public void associateApprovedCard() {
        var list = proposalRepository.searchForProposalsToLinkCard(StatusRequester.ELIGIBLE);
        if (!list.isEmpty()) {
            list.forEach(this::linkCardToProposalAndSave);
        }

    }

    private void linkCardToProposalAndSave(Proposal proposal) {
        try {
            var cardResponse = sendClientCard(proposal);
            Card card = createAndSaveTheCard(cardResponse.getCard());
            proposal.addCardProposal(card);
            proposalRepository.save(proposal);
        }catch (FeignException e){
            throw new CustomException("Ocorreu um erro no serviço para vincular o cartão.", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    private Card createAndSaveTheCard(String card) {
        Card newCard = new Card(card);
        cardRepository.save(newCard);
        return newCard;
    }

    private CartaoResponse sendClientCard(Proposal proposal) {
        return clientCard.sendCards(proposal.getId().toString());
    }
}