# Kubernetes Role Based Access Control
## Topics
-  Kubernetes Authorization Modes
-  Role-Based Access Control (RBAC)  
-  Kubernetes API Request Process  
-  How to using RBAC in Filebeat(ELK)?  

## Kubernetes Authorization Modes
- Node authorization
- Attribute-based access control (ABAC)
- Webhook
- Role-based access control (RBAC)

## Role-Based Access Control (RBAC)

- RBAC   
 <image src='./images/RBAC.png'  width="80%">   

- K8s RBAC
  - Role
    - Role 
    - ClusterRole
  - Role Binding  
    - Role 
    - ClusterRoleBinding
  - Entity （API Server Client）
    - Pod
      - ServiceAccount 
    - User 
      - ~/.kube/config
  - Resource
    - Pods
    - Deployment
    - Nodes
    - Services
    …



## Kubernetes API Request Process
  <image src='./images/APIServer.png'  width="100%">  

## How to using RBAC in Filebeat(ELK)?
Challenge: Filebeat need to get Kubernetes container image detail to filter container log.   
[How to Install Elasticsearch, Kibana,Logstash, Filebeat and Java App on Minikube](https://youtu.be/vN0BrVvmUxc)

- Filebeat Autodiscover

```
    filebeat.autodiscover:
      providers:
        - type: kubernetes
          templates:
            - condition:
                equals: 
                  kubernetes.container.image: "febbweiss/java-log-generator:latest"
              config:
                - type: container
                    ...
                  paths:
                    - /var/log/containers/*${data.kubernetes.container.id}.log          

```  
- Apply YAML Files 

```   
kubectl apply -f es-deployment-service.yaml
kubectl apply -f kibana-deployment-service.yaml
kubectl apply -f java-app-log-generator-deployment.yaml
kubectl apply -f logstash-deployment-service.yaml
kubectl apply -f filebeat-deployment-daemonSet.yaml
```  
- Pod Error Without a Service Account (RBAC) attached:    
```  
2022-04-16T14:39:08.027Z	INFO	[monitoring]	log/log.go:193	Uptime: 585.887116ms
2022-04-16T14:39:08.027Z	INFO	[monitoring]	log/log.go:160	Stopping metrics logging.
2022-04-16T14:39:08.027Z	INFO	instance/beat.go:498	filebeat stopped.
2022-04-16T14:39:08.027Z	ERROR	instance/beat.go:1015	Exiting: error in autodiscover provider settings: error setting up kubernetes autodiscover provider: couldn't discover kubernetes node due to error kubernetes: Node could not be discovered with any known method. Consider setting env var NODE_NAME
Exiting: error in autodiscover provider settings: error setting up kubernetes autodiscover provider: couldn't discover kubernetes node due to error kubernetes: Node could not be discovered with any known method. Consider setting env var NODE_NAME

```  
 - Add Service Account (RBAC) to Filebeat Pod

  <image src='./images/RBAC-Filebeat.png'  width="100%">  
  
 - kubectl apply -f filebeat-deployment-daemonSet.yaml   
 - kubectl delete -f filebeat-deployment-daemonSet.yaml   

## Summary  
-  Kubernetes Authorization Modes
-  Role-Based Access Control (RBAC)  
-  Kubernetes API Request Process  
-  Demo using RBAC in Filebeat(ELK)


