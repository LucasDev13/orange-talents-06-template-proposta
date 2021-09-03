package br.com.proposta.repository;

import br.com.proposta.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
//    @Query("select c from Card c where c.idCard=:idCard")
//    Card verifyIdCard(String idCard);
    Card findByIdCard(String idCard);
}