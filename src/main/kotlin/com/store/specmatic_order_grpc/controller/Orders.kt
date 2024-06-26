package com.store.specmatic_order_grpc.controller

import com.store.order.proto.*
import com.store.specmatic_order_grpc.service.OrderService
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class Orders(private val orderService: OrderService) : OrderServiceGrpc.OrderServiceImplBase() {

    override fun searchOrders(request: OrderSearchRequest?, responseObserver: StreamObserver<OrderListResponse>?) {
        val orders = orderService.searchOrders(request)
        responseObserver?.onNext(OrderListResponse.newBuilder().addAllOrders(orders).build())
        responseObserver?.onCompleted()
    }

    override fun getOrder(request: OrderId?, responseObserver: StreamObserver<Order>?) {
        val order = orderService.getOrder(request)
        responseObserver?.onNext(order)
        responseObserver?.onCompleted()
    }

    override fun addOrder(request: NewOrder?, responseObserver: StreamObserver<OrderId>?) {
        val orderId = orderService.addOrder(request)
        responseObserver?.onNext(orderId)
        responseObserver?.onCompleted()
    }

    override fun updateOrder(request: Order?, responseObserver: StreamObserver<OrderResponse>?) {
        orderService.updateOrder(request)
        responseObserver?.onNext(OrderResponse.newBuilder().setMessage("Success").build())
        responseObserver?.onCompleted()
    }

    override fun deleteOrder(request: OrderId?, responseObserver: StreamObserver<OrderResponse>?) {
        orderService.deleteOrder(request)
        responseObserver?.onNext(OrderResponse.newBuilder().setMessage("Success").build())
        responseObserver?.onCompleted()
    }
}