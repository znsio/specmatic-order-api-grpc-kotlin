package com.store.specmatic_order_grpc.service

import build.buf.protovalidate.Validator
import build.buf.protovalidate.exceptions.ValidationException
import com.google.protobuf.GeneratedMessageV3
import com.store.specmatic_order_grpc.model.ProductDB
import com.store.product.proto.NewProduct
import com.store.product.proto.Product
import com.store.product.proto.ProductId
import com.store.product.proto.ProductSearchRequest
import org.springframework.stereotype.Service

@Service
class ProductService {

    private final val validator = Validator()

    private fun validateOrThrow(request: GeneratedMessageV3?) {
        val result = validator.validate(request)
        if (result.violations.isEmpty().not()) {
            throw ValidationException(result.toString())
        }
    }

    fun searchProducts(request: ProductSearchRequest?): List<Product> {
        validateOrThrow(request)
        return ProductDB.getProducts(request)
    }

    fun getProduct(productId: ProductId?): Product {
        validateOrThrow(productId)
        return ProductDB.getProduct(productId!!)
    }

    fun addProduct(product: NewProduct?): ProductId {
        validateOrThrow(product)
        return ProductDB.addProduct(product!!)
    }


    fun updateProduct(product: Product?) {
        validateOrThrow(product)
        return ProductDB.updateProduct(product!!)
    }

    fun deleteProduct(productId: ProductId?) {
        validateOrThrow(productId)
        return ProductDB.deleteProduct(productId!!)
    }
}