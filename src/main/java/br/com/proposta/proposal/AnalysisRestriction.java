package br.com.proposta.proposal;

public enum AnalysisRestriction {
    SEM_RESTRICAO{
        @Override
        public StatusRequester convertStatus() {
            return StatusRequester.ELIGIBLE;
        }
    },
    COM_RESTRICAO{
        @Override
        public StatusRequester convertStatus(){
            return StatusRequester.NOT_ELIGIBLE;
        }
    };

    public abstract StatusRequester convertStatus();

}
