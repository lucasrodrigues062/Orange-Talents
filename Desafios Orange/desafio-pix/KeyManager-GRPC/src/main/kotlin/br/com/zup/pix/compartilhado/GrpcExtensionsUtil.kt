package br.com.zup.pix.compartilhado

import com.google.rpc.BadRequest
import io.grpc.StatusRuntimeException
import io.grpc.protobuf.StatusProto


    fun StatusRuntimeException.violations(): List<Pair<String, String>> {

        val details = StatusProto.fromThrowable(this)
            ?.detailsList?.get(0)!!
            .unpack(BadRequest::class.java)

        return details.fieldViolationsList
            .map { it.field to it.description } // same as Pair(it.field, it.description)
    }
