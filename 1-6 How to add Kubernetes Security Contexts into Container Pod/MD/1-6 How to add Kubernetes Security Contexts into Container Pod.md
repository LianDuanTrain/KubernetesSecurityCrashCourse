# How to Add Kubernetes Security Contexts into Pod/Container?

## Topics  
- Hands-on Demo Environment    
- What are Kubernetes Security Contexts?    
- How to Add Kubernetes Security Contexts into Pod/Container?  
- Verify Container Security Contexts
- Verify Container Security Contexts with Root User 

## Hands-on Demo Environment   
- OS version - Ubuntu 22.04 LTS     
  - `lsb_release -a`     
- Docker Version - 20.10.16     
  - `docker version`        
- Minikube version - v1.25.2   
  - `minikube version`      
  
- Docker file is  [download URL](https://github.com/LianDuanTrain/KubernetesSecurityCrashCourse/blob/main/1-6%20How%20to%20add%20Kubernetes%20Security%20Contexts%20into%20Container%20Pod/MD/ExerciseFiles/log-generator-master/Dockerfile)
- Docker Image of Src is  [https://github.com/vspiewak/log-generator ](https://github.com/Febbweiss/docker-java-log-generator)   
- Docker Image is  [https://hub.docker.com/r/lianduantraining/java-app-log-generator](https://hub.docker.com/r/lianduantraining/java-app-log-generator)   

## What are Kubernetes Security Contexts? 
 
In Kubernetes, a security context defines privilege and access control settings for a Pod or Container.   
[SecurityContext v1 core](https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.24/#securitycontext-v1-core)  
<image src="./images/securityContexts.jpg" width="80%"> 

[Restrict a Container's Syscalls with seccomp](https://kubernetes.io/docs/tutorials/security/seccomp/)

## How to Add Kubernetes Security Contexts into Pod/Container?  
Docker Image is from (https://hub.docker.com/r/lianduantraining/java-app-log-generator) 
```
apiVersion: apps/v1
kind: Deployment
  ...
spec:  
  replicas: 1
    ...
  template:
    ...
    spec: 
      securityContext: 
        runAsUser:  10001
        runAsGroup: 20001
        runAsNonRoot: true
      containers:
          ...
          securityContext:
            runAsUser:  30001
            runAsGroup: 40001
            allowPrivilegeEscalation: false 
            readOnlyRootFilesystem: true
            capabilities:
                drop:
                  - all
```
- kubectl apply -f java-app-log-generator-deployment-v3.yaml   

## Verify Container Security Contexts
 
  - runAsUser / runAsGroup  Overwrite 
    - kubectl get pods -n app  
    - kubectl exec -n app --stdin --tty {podName} -- /bin/sh   
    - id   
  - readOnlyRootFilesystem  
    - mkdir test      
## Verify Container Security Contexts with Root User        
  - minikube ssh docker container ls   
  - minikube ssh "docker container exec -it -u 0 {containerName/containerID} /bin/sh"   
    - apt install curl
    - mkdir test






    
