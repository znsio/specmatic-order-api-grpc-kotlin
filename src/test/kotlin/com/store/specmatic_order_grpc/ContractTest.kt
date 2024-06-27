package com.store.specmatic_order_grpc

import `in`.specmatic.grpc.junit.SpecmaticGrpcContractTest
import `in`.specmatic.grpc.utils.HOST
import `in`.specmatic.grpc.utils.PORT
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll

class ContractTest : SpecmaticGrpcContractTest {
    companion object {

        @JvmStatic
        @BeforeAll
        fun setup() {
            System.setProperty(HOST, "localhost")
            System.setProperty(PORT, "9090")
        }

        @JvmStatic
        @AfterAll
        fun tearDown() {
        }
    }
}