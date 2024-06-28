package com.store.specmatic_order_grpc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpecmaticOrderAPIGrpcApplication

fun main(args: Array<String>) {
	runApplication<SpecmaticOrderAPIGrpcApplication>(*args)
}
