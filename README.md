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


# Key Concepts of Kubernetes

## 1. Cluster
A **cluster** is the foundation of Kubernetes and consists of:
- **Master Node (Control Plane):** Manages the cluster, schedules workloads, and maintains the desired state.
- **Worker Nodes:** Run application containers.

## 2. Nodes
A **node** is a machine (physical or virtual) in the cluster.
Each node runs:
- **Kubelet:** Communicates with the master node and ensures containers are running.
- **Container Runtime:** E.g., Docker or containerd, which runs the actual containers.
- **Kube Proxy:** Handles networking and load balancing.

## 3. Pods
A **pod** is the smallest deployable unit in Kubernetes.
A pod encapsulates:
- One or more **containers**.
- **Networking** (IP address shared among containers in the pod).
- **Storage** (e.g., shared volumes).

**Use Case:** Deploy tightly coupled containers, such as an app server and a log collector.

## 4. Services
A **service** provides a stable network endpoint to expose pods to other pods or external users.

### Types:
- **ClusterIP (default):** Exposes the service internally to the cluster.
- **NodePort:** Exposes the service on each node’s IP and a static port.
- **LoadBalancer:** Exposes the service externally using a cloud provider’s load balancer.
- **ExternalName:** Maps a service to an external DNS name.

## 5. Deployments
A **deployment** is a higher-level abstraction to manage pods and replicas.

### Handles:
- **Scaling** (e.g., increase or decrease replicas).
- **Rollouts** (e.g., deploy new versions).
- **Rollbacks** (revert to a previous state).

## 6. ReplicaSets
Ensures a specified number of pod replicas are running at any given time.
- **Deployments** use ReplicaSets to manage scaling and rolling updates.

## 7. Namespaces
**Namespaces** are used to divide cluster resources among multiple teams or projects.

### Default namespaces:
- **default:** The default namespace for all objects.
- **kube-system:** For Kubernetes system components.
- **kube-public:** For public, cluster-wide resources.

## 8. ConfigMaps and Secrets
- **ConfigMaps:** Store configuration data as key-value pairs.
- **Secrets:** Store sensitive data, like passwords or API keys, securely (base64 encoded).

## 9. Volumes
Provide persistent or shared storage to pods.

### Types:
- **emptyDir:** Temporary storage for the pod’s lifetime.
- **hostPath:** Access to the host’s file system.
- **PersistentVolume (PV) and PersistentVolumeClaim (PVC):** Manage long-term storage.

## 10. Ingress
Manages external HTTP(S) access to services in the cluster.

### Provides:
- **Routing rules**
- **Load balancing**
- **SSL termination**, etc.



## Important URLs
### Currency Exchange Service
- http://localhost:8000/currency-exchange/from/USD/to/INR
### Currency Conversion Service
- http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
- http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10
### Eureka
- http://localhost:8761/



