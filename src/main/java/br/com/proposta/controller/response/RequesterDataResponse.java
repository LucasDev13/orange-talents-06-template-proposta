package br.com.proposta.controller.response;

import br.com.proposta.proposal.AnalysisRestriction;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequesterDataResponse {

    @JsonProperty("resultadoSolicitacao")
    private AnalysisRestriction analysisRestriction;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RequesterDataResponse(AnalysisRestriction analysisRestriction) {
        this.analysisRestriction = analysisRestriction;
    }

    public AnalysisRestriction getAnalysisRestriction() {
        return analysisRestriction;
    }

    @Override
    public String toString() {
        return "RequesterDataResponse{" +
                "analysisRestriction=" + analysisRestriction +
                '}';
    }
}
