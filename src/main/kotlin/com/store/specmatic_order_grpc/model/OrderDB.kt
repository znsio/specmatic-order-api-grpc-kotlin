package com.store.specmatic_order_grpc.model

import com.store.order.proto.*
import com.store.product.proto.ProductId

object OrderDB {
    private val ORDERS: MutableMap<Int, Order> = mutableMapOf(
        10 to Order.newBuilder().setId(10).setProductid(10).setCount(10).setStatus(OrderStatus.PENDING).build(),
        20 to Order.newBuilder().setId(20).setProductid(20).setCount(20).setStatus(OrderStatus.FULFILLED).build(),
    )
    private var orderIndex = ORDERS.maxOf { it.key }

    private fun filterOrder(order: Order, orderSearchRequest: OrderSearchRequest?): Boolean {
        return (orderSearchRequest?.productId == 0 || order.productid == orderSearchRequest?.productId)
                && (orderSearchRequest.status == OrderStatus.NULL_ORD_STATUS || order.status == orderSearchRequest.status)
    }

    fun getOrders(orderSearchRequest: OrderSearchRequest?): List<Order> {
        return ORDERS.filter { filterOrder(it.value, orderSearchRequest) }
            .map { it.value }
    }

    fun getOrder(orderId: OrderId): Order {
        if (!ORDERS.containsKey(orderId.id)) {
            throw NoSuchElementException("Order with ID ${orderId.id} was not found")
        } else {
            return ORDERS[orderId.id]!!
        }
    }

    fun addOrder(newOrder: NewOrder): OrderId {
        val product = ProductDB.getProduct(
            ProductId.newBuilder().setId(newOrder.productid).build()
        )
        val id = ++orderIndex
        ORDERS[id] =
            Order.newBuilder()
                .setId(id)
                .setProductid(product.id)
                .setCount(newOrder.count)
                .setStatus(OrderStatus.PENDING)
                .build()
        return OrderId.newBuilder().setId(id).build()
    }

    fun updateOrder(order: Order) {
        if (!ORDERS.containsKey(order.id)) {
            throw NoSuchElementException("Order with ID ${order.id} was not found")
        } else {
            ORDERS[order.id] = order
        }
    }

    fun deleteOrder(orderId: OrderId) {
        if (!ORDERS.containsKey(orderId.id)) {
            throw Exception("Order with ID ${orderId.id} was not found")
        } else {
            ORDERS.remove(orderId.id)
        }
    }
}