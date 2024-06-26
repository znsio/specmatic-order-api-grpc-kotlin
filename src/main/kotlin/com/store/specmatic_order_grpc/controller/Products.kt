package com.store.specmatic_order_grpc.controller

import com.store.product.proto.*
import com.store.specmatic_order_grpc.service.ProductService
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class Products(private val productService: ProductService) : ProductServiceGrpc.ProductServiceImplBase() {

    override fun searchProducts(
        request: ProductSearchRequest?,
        responseObserver: StreamObserver<ProductListResponse>?
    ) {
        val products = productService.searchProducts(request)
        responseObserver?.onNext(ProductListResponse.newBuilder().addAllProducts(products).build())
        responseObserver?.onCompleted()
    }

    override fun getProduct(request: ProductId?, responseObserver: StreamObserver<Product>?) {
        val product = productService.getProduct(request)
        responseObserver?.onNext(product)
        responseObserver?.onCompleted()
    }

    override fun addProduct(request: NewProduct?, responseObserver: StreamObserver<ProductId>?) {
        val productId = productService.addProduct(request)
        responseObserver?.onNext(productId)
        responseObserver?.onCompleted()
    }

    override fun updateProduct(request: Product?, responseObserver: StreamObserver<ProductResponse>?) {
        productService.updateProduct(request)
        responseObserver?.onNext(ProductResponse.newBuilder().setMessage("Success").build())
        responseObserver?.onCompleted()
    }

    override fun deleteProduct(request: ProductId?, responseObserver: StreamObserver<ProductResponse>?) {
        productService.deleteProduct(request)
        responseObserver?.onNext(ProductResponse.newBuilder().setMessage("Success").build())
        responseObserver?.onCompleted()
    }

}