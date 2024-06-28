package com.store.specmatic_order_api_grpc.model

import com.store.product.proto.*


object ProductDB {
    private val PRODUCTS: MutableMap<Int, Product> = mutableMapOf(
        10 to Product.newBuilder().setId(10).setName("XYZ Phone").setType(ProductType.GADGET).setInventory(10).build(),
        20 to Product.newBuilder().setId(20).setName("Gemini").setType(ProductType.BOOK).setInventory(20).build(),
    )
    private var productIndex = PRODUCTS.maxOf { it.key }

    private fun filterProduct(product: Product, productSearchRequest: ProductSearchRequest?): Boolean {
        return (productSearchRequest?.type == ProductType.NULL_PROD_TYPE) || (product.type == productSearchRequest?.type)
    }

    fun getProducts(productSearchRequest: ProductSearchRequest?): List<Product> {
        return PRODUCTS.filter { filterProduct(it.value, productSearchRequest) }.map { it.value }
    }

    fun getProduct(productId: ProductId): Product {
        if (!PRODUCTS.containsKey(productId.id)) {
            throw NoSuchElementException("Product with ID ${productId.id} was not found")
        } else {
            return PRODUCTS[productId.id]!!
        }
    }

    fun addProduct(product: NewProduct): ProductId {
        val id = ++productIndex
        PRODUCTS[id] =
            Product.newBuilder().setId(id).setName(product.name).setType(product.type).setInventory(product.inventory)
                .build()
        return ProductId.newBuilder().setId(id).build()
    }

    fun updateProduct(product: Product) {
        if (!PRODUCTS.containsKey(product.id)) {
            throw NoSuchElementException("Product with ID ${product.id} was not found")
        } else {
            PRODUCTS[product.id] = product
        }
    }

    fun deleteProduct(productId: ProductId) {
        if (!PRODUCTS.containsKey(productId.id)) {
            throw NoSuchElementException("Product with ID ${productId.id} was not found")
        } else {
            PRODUCTS.remove(productId.id)
        }
    }
}