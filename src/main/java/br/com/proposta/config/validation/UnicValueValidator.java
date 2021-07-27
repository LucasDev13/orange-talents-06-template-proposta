package br.com.proposta.config.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UnicValueValidator implements ConstraintValidator<UnicValue, Object> {

    private String document;
    private Class<?> domainClass;

    @Autowired private EntityManager em;

    @Override
    public void initialize(UnicValue params) {
        document = params.fieldName();
        domainClass = params.domainClass();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        boolean isValid = em.createQuery("select 1 from " + domainClass.getName() + " where " + document + " = :value")
                .setParameter("value", o)
                .getResultList()
                .isEmpty();
        if(!isValid){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe proposta para esse usuário");
        }
        return isValid;
    }
}
