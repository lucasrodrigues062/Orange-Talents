package br.com.zup.pix.chavepix.cadastra

import br.com.zup.pix.cadastrachavepix.ConsultaCliente
import br.com.zup.pix.compartilhado.handlers.errors.ChavePixExistenteException
import br.com.zup.pix.chavepix.entidades.ChavePix
import br.com.zup.pix.chavepix.entidades.repositorios.ChavePixRepository
import br.com.zup.pix.chavepix.services.BcbCadastraChaveRequest
import br.com.zup.pix.chavepix.services.ClientBCB
import io.micronaut.http.HttpStatus
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional

import javax.validation.Valid

@Validated
@Singleton
class CadastraChaveService (@Inject val chavePixRepository: ChavePixRepository,
                            @Inject val consultaCliente: ConsultaCliente,
                            @Inject val clientBCB: ClientBCB) {

    val logger = LoggerFactory.getLogger(CadastraChaveService::class.java)

    @Transactional
    fun cadastraChavePix(@Valid chavePixDto: ChavePixDto): ChavePix {

        val optionalChave = chavePixRepository.findByChave(chavePixDto.valorDaChave)
        if (optionalChave.isPresent){
            logger.info("Chave Pix Existente")
            throw ChavePixExistenteException("Chave ${chavePixDto.valorDaChave} ja existe na base de dados")

        }

        val responseItau = consultaCliente.consultaConta(chavePixDto.codigoInterno, chavePixDto.tipoDeConta!!.name)

        logger.info(responseItau.status.toString())
        if (responseItau.status.equals(HttpStatus.NOT_FOUND)){
            logger.error("Cliente não encontrado no ERP")
            throw IllegalStateException("Cliente nao encontrado no banco de dados")
        }
        val dadosBancarios = responseItau.body().toModel()
        val chavePix = chavePixRepository.save(chavePixDto.toModel(dadosBancarios))

        val chaveBcb = BcbCadastraChaveRequest.criaRequest(chavePix)
        logger.info(chaveBcb.toString())

        val responseBcb = clientBCB.cadastra(chaveBcb)

        if(!responseBcb.status.equals(HttpStatus.CREATED)) {
            throw IllegalStateException("Não foi possível cadastrar chave no Banco Central")
        }
        if(chavePix.chaveAleatoria()){
            chavePix.chave = responseBcb.body().key
            chavePixRepository.update(chavePix)
        }
        return chavePix

    }
}