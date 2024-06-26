package com.store.specmatic_order_grpc.handler

import build.buf.protovalidate.exceptions.ValidationException
import io.grpc.Status
import net.devh.boot.grpc.server.advice.GrpcAdvice
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler

@GrpcAdvice
class GlobalGrpcExceptionHandler {

    @GrpcExceptionHandler(ValidationException::class)
    fun handleValidationException(exception: ValidationException): Status {
        return Status.INVALID_ARGUMENT.withDescription(exception.message).withCause(exception)
    }

    @GrpcExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(exception: NoSuchElementException): Status {
        return Status.NOT_FOUND.withDescription(exception.message).withCause(exception)
    }
}