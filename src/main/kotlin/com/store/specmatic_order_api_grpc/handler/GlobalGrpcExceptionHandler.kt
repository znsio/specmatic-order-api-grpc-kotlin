package com.store.specmatic_order_api_grpc.handler

import build.buf.protovalidate.exceptions.ValidationException
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.grpc.protobuf.ProtoUtils
import net.devh.boot.grpc.server.advice.GrpcAdvice
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler
import com.google.rpc.Status as RpcStatus

@GrpcAdvice
class GlobalGrpcExceptionHandler {

    private fun getRpcTrailer(rpcStatus: RpcStatus): io.grpc.Metadata {
        val trailers = io.grpc.Metadata()
        trailers.put(ProtoUtils.keyForProto(RpcStatus.getDefaultInstance()), rpcStatus)
        return trailers
    }

    @GrpcExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(exception: NoSuchElementException): StatusRuntimeException {
        val rpcStatus = RpcStatus.newBuilder().setCode(Status.NOT_FOUND.code.value())
            .setMessage(exception.message ?: "Not Found").build()

        return Status.NOT_FOUND.withDescription(exception.message).asRuntimeException(getRpcTrailer(rpcStatus))
    }

    @GrpcExceptionHandler(ValidationException::class)
    fun handleValidationException(exception: ValidationException): StatusRuntimeException {
        val rpcStatus = RpcStatus.newBuilder().setCode(Status.INVALID_ARGUMENT.code.value())
            .setMessage(exception.message ?: "Invalid Argument").build()

        return Status.INVALID_ARGUMENT.withDescription(exception.message).asRuntimeException(getRpcTrailer(rpcStatus))
    }

    @GrpcExceptionHandler(Exception::class)
    fun handleException(exception: Exception): StatusRuntimeException {
        val rpcStatus = RpcStatus.newBuilder().setCode(Status.INTERNAL.code.value())
            .setMessage(exception.message ?: "Server Error").build()

        return Status.INTERNAL.withDescription(exception.message).asRuntimeException(getRpcTrailer(rpcStatus))
    }
}