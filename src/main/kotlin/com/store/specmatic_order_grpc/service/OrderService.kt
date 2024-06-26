package com.store.specmatic_order_grpc.service

import build.buf.protovalidate.Validator
import build.buf.protovalidate.exceptions.ValidationException
import com.google.protobuf.GeneratedMessageV3
import com.store.order.proto.NewOrder
import com.store.order.proto.Order
import com.store.order.proto.OrderId
import com.store.order.proto.OrderSearchRequest
import com.store.specmatic_order_grpc.model.OrderDB
import org.springframework.stereotype.Service

@Service
class OrderService {

    private final val validator = Validator()

    private fun validateOrThrow(request: GeneratedMessageV3?) {
        val result = validator.validate(request)
        if (result.violations.isNotEmpty()) {
            throw ValidationException(result.toString())
        }
    }

    fun searchOrders(request: OrderSearchRequest?): List<Order> {
        validateOrThrow(request)
        return OrderDB.getOrders(request)
    }

    fun addOrder(order: NewOrder?): OrderId {
        validateOrThrow(order)
        return OrderDB.addOrder(order!!)
    }

    fun updateOrder(order: Order?) {
        validateOrThrow(order)
        return OrderDB.updateOrder(order!!)
    }

    fun deleteOrder(orderId: OrderId?) {
        validateOrThrow(orderId)
        OrderDB.deleteOrder(orderId!!)
    }

    fun getOrder(orderId: OrderId?): Order {
        validateOrThrow(orderId)
        return OrderDB.getOrder(orderId!!)
    }


}