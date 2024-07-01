package com.store.order.api.controllers

import com.store.product.proto.*
import com.store.order.api.models.ProductDB
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class Products : ProductServiceGrpc.ProductServiceImplBase() {
    override fun searchProducts(request: ProductSearchRequest, responseObserver: StreamObserver<ProductListResponse>) {
        val products = ProductDB.searchProducts(request)
        responseObserver.onNext(ProductListResponse.newBuilder().addAllProducts(products).build())
        responseObserver.onCompleted()
    }

    override fun getProduct(request: ProductId, responseObserver: StreamObserver<Product>) {
        val product = ProductDB.getProduct(request)
        responseObserver.onNext(product)
        responseObserver.onCompleted()
    }

    override fun addProduct(request: NewProduct, responseObserver: StreamObserver<ProductId>) {
        val productId = ProductDB.addProduct(request)
        responseObserver.onNext(productId)
        responseObserver.onCompleted()
    }

    override fun updateProduct(request: Product, responseObserver: StreamObserver<ProductResponse>) {
        ProductDB.updateProduct(request)
        responseObserver.onNext(ProductResponse.newBuilder().setMessage("Success").build())
        responseObserver.onCompleted()
    }

    override fun deleteProduct(request: ProductId, responseObserver: StreamObserver<ProductResponse>) {
        ProductDB.deleteProduct(request)
        responseObserver.onNext(ProductResponse.newBuilder().setMessage("Success").build())
        responseObserver.onCompleted()
    }
}