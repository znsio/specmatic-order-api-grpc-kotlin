package com.store.specmatic_order_api_grpc.handler

import build.buf.protovalidate.Validator
import build.buf.protovalidate.exceptions.ValidationException
import com.google.protobuf.GeneratedMessageV3
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component

@Aspect
@Component
class RequestValidationAspect {
    private val validator = Validator()

    @Before("execution(* com.store.specmatic_order_api_grpc.controller.*.*(..)) && args(request, ..)")
    fun validateRequest(request: GeneratedMessageV3?) {
        val result = validator.validate(request)
        if (result.violations.isNotEmpty()) {
            throw ValidationException(result.toString())
        }
    }
}
