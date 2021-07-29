package br.com.zup.pix.chavepix.consulta

import br.com.zup.pix.cadastrachavepix.ConsultaCliente
import br.com.zup.pix.chavepix.entidades.repositorios.ChavePixRepository
import br.com.zup.pix.chavepix.services.ClientBCB
import br.com.zup.pix.compartilhado.handlers.errors.ChavePixNaoEncontradaException
import io.micronaut.http.HttpStatus
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.log


@Singleton
class ConsultaChaveService(
    @Inject val chavePixRepository: ChavePixRepository,
    @Inject val clientBCB: ClientBCB
) {
    val logger = LoggerFactory.getLogger(ConsultaChaveService::class.java)

    fun consultaChave(dadosConsulta: DadosConsulta): ChaveInfo{

        when(dadosConsulta.tipoConsulta){
            TipoConsulta.CONSULTA_POR_CLIENTE -> return consultaPorCliente(dadosConsulta)
            TipoConsulta.CONSULTA_POR_CHAVE -> return consultaPorChave(dadosConsulta)
        }

    }

    private fun consultaPorChave(dadosConsulta: DadosConsulta): ChaveInfo {
        logger.info("Cast de dados de génerico para específico")
        val dados  = dadosConsulta as DadosConsultaChave
        logger.info("Cast efetuado com sucesso")
        logger.info("Iniciando a busca no banco de Dados")
        val optionalChave = chavePixRepository.findByChave(dados.chave)

        if (optionalChave.isEmpty){
            logger.info("Cliente não encontrado na base de dados. Iniciando busca no BCB.")
            val response = clientBCB.buscaPorChave(dados.chave)
            if(!response.status.equals(HttpStatus.OK)){
                logger.error("Chave não encontrada no BCB")
                throw ChavePixNaoEncontradaException("Chave não encontrada no BCB")
            }

            return ChaveInfo.fromBcbChaveDetalheResponse(response.body()!!)
        }

        return ChaveInfo.fromChavePix(optionalChave.get())
    }

    private fun consultaPorCliente(dadosConsulta: DadosConsulta): ChaveInfo {
        logger.info("Cast de dados de génerico para específico")
        val dados  = dadosConsulta as DadosConsultaPix
        logger.info("Cast efetuado com sucesso")
        logger.info("Iniciando a busca no banco de Dados")
        val optionalChave = chavePixRepository.findByIdAndCodigoCliente(UUID.fromString(dados.pixId), UUID.fromString(dados.clientId))
        if(optionalChave.isEmpty){
            logger.error("Não foi encontrado chave com esse valor para esse cliente")
            throw ChavePixNaoEncontradaException("Chave Pix não encontrada ou não pertence a esse cliente")
        }
        logger.info("Cliente encontrado")
        return ChaveInfo.fromChavePix(optionalChave.get())
    }
}
