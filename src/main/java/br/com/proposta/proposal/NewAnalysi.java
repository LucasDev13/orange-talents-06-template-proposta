package br.com.proposta.proposal;

public class NewAnalysi {

    private AnalysisRestriction analysisRestriction;

    public NewAnalysi() {
    }

    public NewAnalysi(AnalysisRestriction analysisRestriction) {
        this.analysisRestriction = analysisRestriction;
    }

    public AnalysisRestriction getAnalysisRestriction() {
        return analysisRestriction;
    }

    @Override
    public String toString() {
        return "NewAnalysi{" +
                "analysisRestriction=" + analysisRestriction +
                '}';
    }
}
