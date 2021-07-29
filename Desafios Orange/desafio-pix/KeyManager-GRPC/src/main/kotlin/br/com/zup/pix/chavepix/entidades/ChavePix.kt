package br.com.zup.pix.chavepix.entidades

import br.com.zup.pix.chavepix.TipoDeChave
import br.com.zup.pix.chavepix.TipoDeConta
import java.util.*
import javax.persistence.*

@Entity
class ChavePix(
    @Column(nullable = false)
    val codigoCliente: UUID,
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val tipoDeChave: TipoDeChave,
    @Column(unique = true, nullable = false)
    var chave: String,
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val tipoDeConta: TipoDeConta,

    @Embedded
    val conta: DadosBancarios
) {
    @Id
    @GeneratedValue
    val id: UUID?= null
    val resgistradaEm = java.time.LocalDateTime.now()

    fun chaveAleatoria(): Boolean{
        return  tipoDeChave == TipoDeChave.ALEATORIA
    }

}