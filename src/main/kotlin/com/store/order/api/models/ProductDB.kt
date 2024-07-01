package com.store.order.api.models

import com.store.product.proto.*

object ProductDB {
    private val PRODUCTS: MutableMap<Int, Product> = mutableMapOf(
        10 to Product.newBuilder().setId(10).setName("XYZ Phone").setType(ProductType.GADGET).setInventory(10).build(),
        20 to Product.newBuilder().setId(20).setName("Gemini").setType(ProductType.BOOK).setInventory(20).build(),
        30 to Product.newBuilder().setId(30).setName("Chips").setType(ProductType.FOOD).setInventory(30).build(),
        40 to Product.newBuilder().setId(40).setName("Socks").setType(ProductType.OTHER).setInventory(40).build(),
    )
    private var productIndex = PRODUCTS.maxOf { it.key }

    private fun filterProduct(product: Product, productSearchRequest: ProductSearchRequest): Boolean {
        return (productSearchRequest.type == ProductType.NULL_PROD_TYPE) || (product.type == productSearchRequest.type)
    }

    fun searchProducts(productSearchRequest: ProductSearchRequest): List<Product> {
        return PRODUCTS.filter { filterProduct(it.value, productSearchRequest) }.map { it.value }
    }

    fun getProduct(productId: ProductId): Product {
        ensureIdExists(productId.id)
        return PRODUCTS.getValue(productId.id)
    }

    fun addProduct(product: NewProduct): ProductId {
        val id = ++productIndex
        PRODUCTS[id] = Product.newBuilder().setId(id).setName(product.name).setType(product.type)
            .setInventory(product.inventory).build()
        return ProductId.newBuilder().setId(id).build()
    }

    fun updateProduct(product: Product) {
        ensureIdExists(product.id)
        PRODUCTS[product.id] = product
    }

    fun deleteProduct(productId: ProductId) {
        ensureIdExists(productId.id)
        PRODUCTS.remove(productId.id)
    }

    private fun ensureIdExists(id: Int) {
        if (!PRODUCTS.containsKey(id)) {
            throw NoSuchElementException("Product with ID $id was not found")
        }
    }
}