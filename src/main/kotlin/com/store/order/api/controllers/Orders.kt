package com.store.order.api.controllers

import com.store.order.proto.*
import com.store.order.api.models.OrderDB

import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class Orders : OrderServiceGrpc.OrderServiceImplBase() {
    override fun searchOrders(request: OrderSearchRequest, responseObserver: StreamObserver<OrderListResponse>) {
        val orders = OrderDB.searchOrders(request)
        responseObserver.onNext(OrderListResponse.newBuilder().addAllOrders(orders).build())
        responseObserver.onCompleted()
    }

    override fun getOrder(request: OrderId, responseObserver: StreamObserver<Order>) {
        val order = OrderDB.getOrder(request)
        responseObserver.onNext(order)
        responseObserver.onCompleted()
    }

    override fun addOrder(request: NewOrder, responseObserver: StreamObserver<OrderId>) {
        val orderId = OrderDB.addOrder(request)
        responseObserver.onNext(orderId)
        responseObserver.onCompleted()
    }

    override fun updateOrder(request: Order, responseObserver: StreamObserver<OrderResponse>) {
        OrderDB.updateOrder(request)
        responseObserver.onNext(OrderResponse.newBuilder().setMessage("Success").build())
        responseObserver.onCompleted()
    }

    override fun deleteOrder(request: OrderId, responseObserver: StreamObserver<OrderResponse>) {
        OrderDB.deleteOrder(request)
        responseObserver.onNext(OrderResponse.newBuilder().setMessage("Success").build())
        responseObserver.onCompleted()
    }
}