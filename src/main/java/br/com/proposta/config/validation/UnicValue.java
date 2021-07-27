package br.com.proposta.config.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Constraint(validatedBy = {UnicValueValidator.class})
@Documented
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UnicValue {

    String fieldName();
    Class<?> domainClass();

    String message() default "Já existe um CPF ou CNPJ com essa numeração";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
