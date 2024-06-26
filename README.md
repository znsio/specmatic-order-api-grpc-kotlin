# Specmatic Order gRPC Sample

**specmatic-order-grpc** is a gRPC server application built according to the Specmatic Order API specifications.<br />
The gRPC specifications are based on
the [order-api](https://github.com/znsio/specmatic-order-contracts/blob/main/in/specmatic/examples/store/api_order_v1.yaml)
OpenAPI specification.<br />

## Requirements
- Java 17 or later

## Project Setup

1. Fork or clone the repository
2. Generate Proto files and Install to Local Repository
    - ```bash
      mvn clean install -f proto/pom.xml
      ```
3. Build the Spring Server
    - ```bash
      mvn clean package
      ```
4. Run the server
    - ```bash
      java -jar target/specmatic-order-grpc-0.0.1-SNAPSHOT.jar
      ```
      or
    - ```bash
      mvn spring-boot:run
      ```

## Intellij Notes

If you are utilizing **IntelliJ IDEA**, you have the option to compile or install the proto module via the Maven sidebar.<br/>
Subsequently, you can initiate the Spring server through the Run/Debug menu.