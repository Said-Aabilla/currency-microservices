## Final URLs (after configuring the routing in ```ApiGatewayConfig```)
- http://localhost:8765/currency-exchange/from/USD/to/INR
- http://localhost:8765/currency-conversion/from/USD/to/INR/quantity/10
- http://localhost:8765/currency-conversion-new/from/USD/to/INR/quantity/10


# Steps to Build and Push the Docker Image of each Service Locally

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

## 5. Push The image to Docker Hub
- First make sure you are logged in
```bash
docker login
```

- Tag the image (if needed)
```bash
docker tag said160/ms-currency-exchange-service:0.0.1-SNAPSHOT said160/ms-currency-exchange-service:0.0.1-SNAPSHOT
```

- Push the image to Docker Hub
```bash
docker push said160/ms-currency-exchange-service:0.0.1-SNAPSHOT
```
- Verify the image on Docker Hub
- Optional: Pull the image to test
``` bash
docker pull said160/ms-currency-exchange-service:0.0.1-SNAPSHOT
 ```

# Deploying Microservices on Kubernetes in Google Cloud

## Prerequisites
1. A Google Cloud Platform (GCP) account.
2. A Kubernetes cluster created in GCP.
3. `kubectl` and `gcloud` CLI tools installed and configured.

---

## Steps to Deploy

### Step 1: Configure Your Environment
1. Authenticate with GCP:
   ```bash
   gcloud auth login
   ```

2. Set the active project:
   ```bash
   gcloud config set project <your-project-id>
   ```

3. Connect to the Kubernetes cluster:
   ```bash
   gcloud container clusters get-credentials <your-cluster-name> --zone <your-zone>
   ```

---

### Step 2: Write Kubernetes Deployment and Service YAML Files

1. Create a `currency-exchange-deployment.yaml` and  `currency-exchange-service.yaml` files:

   ``` yaml
   apiVersion: apps/v1
   kind: Deployment
   metadata:
   name: currency-exchange-service
   spec:
   replicas: 2
   selector:
   matchLabels:
   app: currency-exchange
   template:
   metadata:
   labels:
   app: currency-exchange
   spec:
   containers:
   - name: currency-exchange
   image: said160/ms-currency-exchange-service:0.0.1-SNAPSHOT
   ports:
   - containerPort: 8000
   ```
   ---
   ``` yaml
   apiVersion: v1
   kind: Service
   metadata:
   name: currency-exchange-service
   spec:
   selector:
   app: currency-exchange
   ports:
    - protocol: TCP
      port: 8000
      targetPort: 8000
      type: ClusterIP
   ```

2. Similarly, create YAML files for `currency-conversion-service`, `naming-registry-service`, and `api-gateway-service`, modifying `ports` and `image` as required.

3. For `api-gateway-service`, set the service type to `LoadBalancer`:

   ```yaml
   apiVersion: v1
   kind: Service
   metadata:
   name: api-gateway-service
   spec:
   selector:
   app: api-gateway
   ports:
    - protocol: TCP
      port: 8765
      targetPort: 8765
      type: LoadBalancer
   ```

---

### Step 3: Deploy Services to Kubernetes

1. Deploy each service by applying the corresponding YAML files:
   ```bash
   kubectl apply -f currency-exchange-deployment.yaml
   kubectl apply -f currency-conversion-deployment.yaml
   kubectl apply -f naming-registry-deployment.yaml
   kubectl apply -f api-gateway-deployment.yaml
   ```

2. Verify deployments and services:
   ```bash
   kubectl get deployments
   kubectl get services
   ```

---

### Step 4: Validate Communication Between Services

1. Identify a pod from the `currency-conversion-service`:
   ```bash
   kubectl get pods
   ```

2. Access the pod's shell:
   ```bash
   kubectl exec -it <pod-name-of-currency-conversion-service> -- /bin/sh
   ```

3. Inside the pod, validate communication with another service:
   ```bash
   wget -qO- http://currency-exchange-service:8000/currency-exchange/from/USD/to/EUR
   ```

---

### Step 5: Access the API Gateway

1. Get the external IP of the `api-gateway-service`:
   ```bash
   kubectl get services
   ```

2. Test access to the API Gateway:
   ```bash
   curl http://<external-ip>:8765/currency-conversion/from/USD/to/EUR/quantity/10
   ```

---

### Step 6: Monitor and Manage Services

1. View logs for a specific pod:
   ```bash
   kubectl logs <pod-name>
   ```

2. Scale a deployment as needed:
   ```bash
   kubectl scale deployment currency-exchange-service --replicas=3
   ```

---

### Cleanup
To delete all deployments and services:
```bash
kubectl delete -f currency-exchange-deployment.yaml
kubectl delete -f currency-conversion-deployment.yaml
kubectl delete -f naming-registry-deployment.yaml
kubectl delete -f api-gateway-deployment.yaml
```

---

You have successfully deployed and validated your microservices on Kubernetes in Google Cloud!


## Important URLs
### Currency Exchange Service
- http://localhost:8000/currency-exchange/from/USD/to/INR
### Currency Conversion Service
- http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
- http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10
### Eureka
- http://localhost:8761/



