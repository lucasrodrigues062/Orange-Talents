package br.com.zup.criacaodeproposta.validacao;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import java.lang.annotation.*;

@CPF
@CNPJ
@ConstraintComposition(CompositionType.OR) // specifies OR as boolean operator instead of AND
@ReportAsSingleViolation // the error reports of each individual composing constraint are ignored
@Constraint(validatedBy = { }) // we don't need a validator :-)
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface CpfCnpjValidator {
    String message() default "it's not a valid CPF or CNPJ";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
