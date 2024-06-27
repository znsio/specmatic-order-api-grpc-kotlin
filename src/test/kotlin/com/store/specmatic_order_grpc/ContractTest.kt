package com.store.specmatic_order_grpc

import `in`.specmatic.grpc.junit.SpecmaticGrpcContractTest
import `in`.specmatic.grpc.utils.HOST
import `in`.specmatic.grpc.utils.PORT
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext

class ContractTest : SpecmaticGrpcContractTest {
    companion object {
        private lateinit var context: ConfigurableApplicationContext

        @JvmStatic
        @BeforeAll
        fun setup() {
            System.setProperty(HOST, "localhost")
            System.setProperty(PORT, "9090")
            context = SpringApplication.run(SpecmaticOrderGrpcApplication::class.java)
        }

        @JvmStatic
        @AfterAll
        fun tearDown() {
            context.stop()
        }
    }
}