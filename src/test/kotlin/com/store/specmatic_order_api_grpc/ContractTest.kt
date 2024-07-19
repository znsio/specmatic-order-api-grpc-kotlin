package com.store.specmatic_order_api_grpc

import io.specmatic.grpc.junit.SpecmaticGrpcContractTest
import io.specmatic.grpc.utils.EXAMPLES_DIR
import io.specmatic.grpc.utils.HOST
import io.specmatic.grpc.utils.PORT
import io.specmatic.grpc.utils.SPECMATIC_GENERATIVE_TESTS
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
