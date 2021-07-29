package br.com.zup.pix.chavepix.remove

import br.com.zup.pix.chavepix.entidades.repositorios.ChavePixRepository
import br.com.zup.pix.chavepix.services.BcbExcluiChaveRequest
import br.com.zup.pix.chavepix.services.ClientBCB
import br.com.zup.pix.compartilhado.handlers.errors.ChavePixNaoEncontradaException
import br.com.zup.pix.compartilhado.validacao.ValidUUID
import io.micronaut.http.HttpStatus
import io.micronaut.validation.Validated
import java.lang.IllegalStateException
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.constraints.NotBlank

@Validated
@Singleton
class RemoveChaveService(@Inject val chavePixRepository: ChavePixRepository, @Inject val clientBCB: ClientBCB) {
    @Transactional
    fun remove(
        @NotBlank @ValidUUID clientId: String?,
        @NotBlank @ValidUUID chaveId: String?,
    ) {

        val clientIdUUID = UUID.fromString(clientId)
        val chaveIdUUID = UUID.fromString(chaveId)

        val optionalChave = chavePixRepository.findByIdAndCodigoCliente(chaveIdUUID, clientIdUUID)
        if (optionalChave.isEmpty) {
            print("chave nao existe")
            throw ChavePixNaoEncontradaException("Chave nao encontrada")

        }
        chavePixRepository.delete(optionalChave.get())

        val requestBcb = BcbExcluiChaveRequest(optionalChave.get().chave)
        val responseBcb = clientBCB.exclui(key = requestBcb.key, request = requestBcb)
        if (!responseBcb.status.equals(HttpStatus.OK)){
            throw IllegalStateException("Falha ao excluir Chave no Banco Central")
        }
    }

}