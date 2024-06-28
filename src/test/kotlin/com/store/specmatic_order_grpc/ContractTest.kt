package com.store.specmatic_order_grpc

import `in`.specmatic.grpc.junit.SpecmaticGrpcContractTest
import `in`.specmatic.grpc.stub.GrpcStub
import `in`.specmatic.grpc.utils.HOST
import `in`.specmatic.grpc.utils.PORT
import `in`.specmatic.grpc.utils.STUB_PORT
import org.junit.jupiter.api.BeforeAll
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ContractTest : SpecmaticGrpcContractTest {
    companion object {
//        private var grpcStub: GrpcStub? = null

        @JvmStatic
        @BeforeAll
        fun setup() {
            System.setProperty(HOST, "localhost")
            System.setProperty(PORT, "9090")
            System.setProperty(STUB_PORT, "9090")
//            grpcStub = GrpcStub.createGrpcStub(9090)
//            grpcStub?.start()
        }

        @JvmStatic
        @BeforeAll
        fun tearDown() {
//            grpcStub?.stop()
        }
    }
}