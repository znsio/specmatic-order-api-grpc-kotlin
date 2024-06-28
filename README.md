# Specmatic Order API gRPC Sample

**specmatic-order-grpc** is a gRPC server application built according to the Specmatic Order API specifications.<br />
The Open API Specifications can be found
at [order-api](https://github.com/znsio/specmatic-order-contracts/blob/main/in/specmatic/examples/store/api_order_v3.yaml)<br />

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