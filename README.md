## URLs

### Limits Service
- http://localhost:8080/limits

### Cloud Config Server
- http://localhost:8888/limits-service/default
- http://localhost:8888/limits-service/dev

### Currency Exchange Service
- http://localhost:8000/currency-exchange/from/USD/to/INR

### Currency Conversion Service
- http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
- http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10

### Eureka
- http://localhost:8761/

### Spring Cloud Api Gateway

Initial (default)
- http://localhost:8765/CURRENCY-EXCHANGE/currency-exchange/from/USD/to/INR
- http://localhost:8765/CURRENCY-CONVERSION/currency-conversion/from/USD/to/INR/quantity/10
- http://localhost:8765/CURRENCY-CONVERSION/currency-conversion-feign/from/USD/to/INR/quantity/10

Final (after configuring the routing in ```ApiGatewayConfig```)
- http://localhost:8765/currency-exchange/from/USD/to/INR
- http://localhost:8765/currency-conversion/from/USD/to/INR/quantity/10
- http://localhost:8765/currency-conversion-feign/from/USD/to/INR/quantity/10
- http://localhost:8765/currency-conversion-new/from/USD/to/INR/quantity/10


# Steps to Build and Push the Docker Image Locally

## 1. Build the Docker Image Using Maven
Since you have a `pom.xml` file with the `spring-boot-maven-plugin` configured:  
``` xml
<build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <image>
                        <name>said160/ms-${project.artifactId}:${project.version}</name>
                    </image>
                    <pullPolicy>IF_NOT_PRESENT</pullPolicy>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

you can build the Docker image locally using Maven. Run the following command:

```bash
mvn clean install
```

This command will:
 - Compile your Spring Boot application.
 - Prepare the Docker image as specified in the pom.xml.


## 2. Build the Docker Image Using the Maven Plugin
   After the Maven build is successful, you can use the Maven Docker plugin to build the Docker image. Run:

```bash
mvn spring-boot:build-image
```

This will:

- Trigger the spring-boot-maven-plugin to build a Docker image using the configuration in your pom.xml.
- Create an image named said160/ms-${project.artifactId}:${project.version}. The artifactId and version will be replaced based on the project details (e.g., ms-currency-exchange-service and 0.0.1-SNAPSHOT).

## 3.  Check the Docker Image Locally
   Once the build completes, check if the Docker image was created successfully by running:

````bash
docker images
````
Look for an image with the name said160/ms-currency-exchange-service:0.0.1-SNAPSHOT (or the corresponding name/version).

## 4.  Start the Docker Container Using docker-compose

Once the image is built locally, start your application using docker-compose:

````bash
docker-compose up
````