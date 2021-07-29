package br.com.zup.pix.chavepix.lista

import br.com.zup.pix.*
import br.com.zup.pix.chavepix.entidades.ChavePix
import br.com.zup.pix.chavepix.entidades.repositorios.ChavePixRepository
import br.com.zup.pix.compartilhado.handlers.ErrorHandler
import com.google.protobuf.Timestamp
import io.grpc.stub.StreamObserver
import java.lang.IllegalArgumentException
import java.time.ZoneId
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@ErrorHandler
@Singleton
class ListaChavesController(
    @Inject val chavePixRepository: ChavePixRepository
) : KeyManagerListaGrpServiceGrpc.KeyManagerListaGrpServiceImplBase(){

    override fun lista(request: ListaChaveRequest?, responseObserver: StreamObserver<ListaChaveResponse>?) {
        if(request?.clientId.isNullOrBlank()){
            throw IllegalArgumentException("O clientId não pode ser nulo ou em branco")
        }

        val chavesList = chavePixRepository.findAllByCodigoCliente(UUID.fromString(request!!.clientId))


        responseObserver?.onNext(ListaChaveResponse.newBuilder()
            .setClientId(request.clientId.toString())
            .addAllChaves(toListChaveResponse(chavesList))
            .build())
        responseObserver?.onCompleted()

    }
}

fun toListChaveResponse(chaves: List<ChavePix>): List<ListaChaveResponse.ChavePix>{
    var chavePixResponseList: MutableList<ListaChaveResponse.ChavePix> = mutableListOf()
    chaves.forEach { chavePix ->
        run {
            val chave = ListaChaveResponse.ChavePix.newBuilder()
                .setPixId(chavePix.id.toString())
                .setTipo(TipoDeChave.valueOf(chavePix.tipoDeChave.name))
                .setChave(chavePix.chave.toString())
                .setTipoDeConta(TipoDeConta.valueOf(chavePix.tipoDeConta.name))
                .setCriadaEm(
                    chavePix.let {
                        val createdAt = chavePix.resgistradaEm.atZone(ZoneId.of("UTC")).toInstant()
                        Timestamp.newBuilder()
                            .setSeconds(createdAt.epochSecond)
                            .setNanos(createdAt.nano)
                            .build()
                    }
                )
                .build()
            chavePixResponseList.add(chave)
        }

    }
    return chavePixResponseList.toList()
}