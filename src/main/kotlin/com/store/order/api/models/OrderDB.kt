package com.store.order.api.models

import com.store.order.proto.*
import com.store.product.proto.ProductId

object OrderDB {
    private val ORDERS: MutableMap<Int, Order> = mutableMapOf(
        10 to Order.newBuilder().setId(10).setProductId(10).setCount(99).setStatus(OrderStatus.PENDING).build(),
        20 to Order.newBuilder().setId(20).setProductId(20).setCount(33).setStatus(OrderStatus.FULFILLED).build(),
        35 to Order.newBuilder().setId(35).setProductId(30).setCount(11).setStatus(OrderStatus.CANCELLED).build(),
        40 to Order.newBuilder().setId(40).setProductId(40).setCount(22).setStatus(OrderStatus.PENDING).build(),
    )
    private var orderIndex = ORDERS.maxOf { it.key }

    private fun filterOrder(order: Order, orderSearchRequest: OrderSearchRequest): Boolean {
        return (orderSearchRequest.productId == 0 || order.productId == orderSearchRequest.productId)
                && (orderSearchRequest.status == OrderStatus.NULL_ORD_STATUS || order.status == orderSearchRequest.status)
    }

    fun searchOrders(orderSearchRequest: OrderSearchRequest): List<Order> {
        return ORDERS.filter { filterOrder(it.value, orderSearchRequest) }
            .map { it.value }
    }

    fun getOrder(orderId: OrderId): Order {
        ensureIdExists(orderId.id)
        return ORDERS.getValue(orderId.id)
    }

    fun addOrder(newOrder: NewOrder): OrderId {
        val product = ProductDB.getProduct(
            ProductId.newBuilder().setId(newOrder.productId).build()
        )
        val id = ++orderIndex
        ORDERS[id] =
            Order.newBuilder()
                .setId(id)
                .setProductId(product.id)
                .setCount(newOrder.count)
                .setStatus(OrderStatus.PENDING)
                .build()
        return OrderId.newBuilder().setId(id).build()
    }

    fun updateOrder(order: Order) {
        ensureIdExists(order.id)
        ORDERS[order.id] = order
    }

    fun deleteOrder(orderId: OrderId) {
        ensureIdExists(orderId.id)
        ORDERS.remove(orderId.id)
    }

    private fun ensureIdExists(id: Int) {
        if (!ORDERS.containsKey(id)) {
            throw NoSuchElementException("Order with ID $id was not found")
        }
    }
}