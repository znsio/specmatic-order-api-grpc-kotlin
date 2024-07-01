package com.store.order.api.handlers

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

    @Before("execution(* com.store.order.api.controllers.*.*(..)) && args(request, ..)")
    fun validateRequest(request: GeneratedMessageV3?) {
        val result = validator.validate(request)
        if (result.violations.isNotEmpty()) {
            throw ValidationException(result.toString())
        }
    }
}