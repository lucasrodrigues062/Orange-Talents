package br.com.zup.grpc


import br.com.zup.pix.cadastra.ChavePixDto
import javax.inject.Singleton
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ValidaChavePixValidator::class])
annotation class ValidaChavePix(
    val message: String = "Chave tipo Inválida (\${validatedValue.tipoDeChave} : validatedValue.valorDaChave )",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)

@Singleton
class ValidaChavePixValidator: javax.validation.ConstraintValidator<ValidaChavePix, ChavePixDto> {

    override fun isValid(value: ChavePixDto?, context: javax.validation.ConstraintValidatorContext): Boolean {


        if (value?.tipoDeChave == null) {
            return true
        }

        val valid = value.tipoDeChave.valida(value.chave)

        if (!valid) {

            context.disableDefaultConstraintViolation()
            context
                .buildConstraintViolationWithTemplate(context.defaultConstraintMessageTemplate)
                .addPropertyNode("chave").addConstraintViolation()
        }

        return valid
    }
}