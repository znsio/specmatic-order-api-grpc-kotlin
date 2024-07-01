package com.store.order.api

import `in`.specmatic.grpc.junit.SpecmaticGrpcContractTest
import `in`.specmatic.grpc.utils.HOST
import `in`.specmatic.grpc.utils.IMPORT_PATHS
import `in`.specmatic.grpc.utils.PORT
import `in`.specmatic.grpc.utils.SPECMATIC_GENERATIVE_TESTS
import org.junit.jupiter.api.BeforeAll
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ContractTest : SpecmaticGrpcContractTest {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            System.setProperty(HOST, "localhost")
            System.setProperty(PORT, "9090")
            System.setProperty(SPECMATIC_GENERATIVE_TESTS, "true")
            System.setProperty(IMPORT_PATHS, "build/extracted-include-protos/main")
        }
    }
}