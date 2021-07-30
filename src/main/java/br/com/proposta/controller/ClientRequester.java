package br.com.proposta.controller;

import br.com.proposta.controller.request.RequesterDataRequest;
import br.com.proposta.controller.response.RequesterDataResponse;
import br.com.proposta.proposal.AnalysisRestriction;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

@FeignClient(name = "analise", url = "http://localhost:9999/api/solicitacao")
public interface ClientRequester {

    @CircuitBreaker(name = "analise", fallbackMethod = "analysiFallback")
    @PostMapping
    RequesterDataResponse sendRequester(@RequestBody RequesterDataRequest requesterDataRequest);

    default RequesterDataResponse analysiFallback(Exception exception){
        if(exception.getClass() == FeignException.UnprocessableEntity.class && exception.getMessage().startsWith("422")){
            return new RequesterDataResponse(AnalysisRestriction.COM_RESTRICAO);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possivel processar essa solicitação");
    }
}
