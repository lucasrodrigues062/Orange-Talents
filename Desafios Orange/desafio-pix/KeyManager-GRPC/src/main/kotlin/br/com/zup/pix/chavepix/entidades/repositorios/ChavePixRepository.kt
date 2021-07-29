package br.com.zup.pix.chavepix.entidades.repositorios

import br.com.zup.pix.chavepix.entidades.ChavePix
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface ChavePixRepository : JpaRepository<ChavePix, UUID>{

    fun findByChave(chave: String): Optional<ChavePix>
    fun findByIdAndCodigoCliente(id: UUID, codigoCliente: UUID): Optional<ChavePix>
    fun findAllByCodigoCliente(codigoCliente: UUID): List<ChavePix>


}