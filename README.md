# Specmatic Order API gRPC Sample

This sample project demonstrates [Specmatic](https://specmatic.in/) **gRPC support** which allows you to use your **proto files for Contract Testing**.
[Specmatic](https://specmatic.in/) **gRPC support** can also help you use your proto files as Mocks, for #nocode backward compatibility test and more.

The **specmatic-order-api-grpc-kotlin** is a gRPC server application built as per below proto files.
* `order.proto`,
* `src/main/proto/product.proto`
TODO: These proto files can be found in our [central-contract-repo](https://github.com/znsio/specmatic-order-contracts/blob/main/in/specmatic/examples/store/api_order_v3.yaml)

The `ContractTest` class demonstrates how to use Specmatic to test **specmatic-order-api-grpc-kotlin** gRPC server app using the above proto files.

## Requirements

- Java 17 or later

## Project Setup

1. Fork or clone the repository
   ```bash
   git clone https://github.com/znsio/specmatic-order-api-grpc-kotlin
   ```
2. To assemble the project and compile the protobuf files, execute the following command
   ```bash
   gradle assemble
   ```
3. To run the gRPC server using Gradle, execute
   ```bash
   gradle bootRun
   ```
4. To run contract tests, execute
   ```bash
   gradle test --tests "com.store.specmatic_order_grpc.ContractTest" --info   
   ```

## Intellij Notes

IntelliJ IDEA has built-in support for Gradle projects. Import the project into IntelliJ IDEA using File > New > Project
from Existing Sources and select build.gradle. IDEA will recognize the Gradle project structure and allow you to run
tasks directly from the IDE.