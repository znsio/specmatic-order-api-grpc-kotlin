package com.store.specmatic_order_api_grpc

import `in`.specmatic.grpc.junit.SpecmaticGrpcContractTest
import `in`.specmatic.grpc.utils.EXAMPLES_DIR
import `in`.specmatic.grpc.utils.HOST
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
            System.setProperty(EXAMPLES_DIR, "src/test/resources/specmatic")
            System.setProperty(SPECMATIC_GENERATIVE_TESTS, "true")
        }
    }
}