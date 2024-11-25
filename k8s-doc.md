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


