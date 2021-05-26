package br.com.zup.mercadolivre.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ExistsIdValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsId {

    String message() default "Nao Existe esse ID na base de dados";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default {};

    String fieldName();

    Class<?> domainClass();


}
