package com.store.specmatic_order_grpc.handler

import io.grpc.Status
import net.devh.boot.grpc.server.advice.GrpcAdvice
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler

@GrpcAdvice
class GlobalGrpcExceptionHandler {

    @GrpcExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(exception: NoSuchElementException): Status {
        return Status.NOT_FOUND.withDescription(exception.message).withCause(exception)
    }
}