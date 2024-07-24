# Specmatic Order API gRPC Sample

This sample project demonstrates [Specmatic](https://specmatic.io/) **gRPC support** which allows you to use your **proto files for Contract Testing**.
[Specmatic](https://specmatic.io/) **gRPC support** can also help you use your proto files for service mocking, #nocode backward compatibility testing and more.

The **specmatic-order-api-grpc-kotlin** is a gRPC server application developed according to the following proto files, which can be found in the central contract repository:

* [order.proto](https://github.com/znsio/specmatic-order-contracts/blob/main/io/specmatic/examples/store/grpc/order_api/order.proto)
* [product.proto](https://github.com/znsio/specmatic-order-contracts/blob/main/io/specmatic/examples/store/grpc/order_api/product.proto)

The `ContractTest` class demonstrates how to use Specmatic to test **specmatic-order-api-grpc-kotlin** gRPC server app using the above proto files.

## Requirements

* Java 17 or later

## Project Setup

1. Clone the repository

   ```shell
   git clone https://github.com/znsio/specmatic-order-api-grpc-kotlin
   ```

2. Initialize and update the `specmatic-order-contracts` submodule

   ```shell
   git submodule update --init --recursive --remote
   ```

3. Enable automatic submodule updating when executing `git pull`

   ```shell
   git config submodule.recurse true
   ```

4. To run contract tests, execute

   ```shell
   ./gradlew clean test   
   ```

5. In case you want to run just the gRPC server using Gradle you can execute

   ```shell
   ./gradlew bootRun
   ```
