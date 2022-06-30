# Kubernetes Security - Network Policy and Hands-on Demo

## Topics  
- Hands-on Demo Environment    
- What is Kubernetes Network Policy? 
- The Demo App Network Traffic Analysis     
- Add Network policies to The Demo App

## Hands-on Demo Environment   
 <image src='./images/xmind1.png'  width="100%">  
 
- OS version - Ubuntu 22.04 LTS     
  - `lsb_release -a`     
- Docker Version - 20.10.16     
  - `docker version`        
- Minikube version - v1.25.2   
  - `minikube version`      
- Helm version - v3.9.0
  - `helm version`   
-  YAML Files are base on  [Three Tiers Application Overview](https://youtu.be/27xwP0smmKQ)    


## What is Kubernetes Network Policy? 
NetworkPolicies are an application-centric construct that allows you to specify how a pod is allowed to communicate with various network "entities" over the network.   
<image src='./images/xmind2.png'  width="100%"> 


## The Demo App Network Traffic Analysis    
<image src='./images/xmind3.png'  width="100%"> 


## Add Network Policies to The Demo App   
<image src='./images/xmind4.png'  width="100%">    

- Minikube start with CNI Addons
  - minikube start --cni calico 
  - minikube addons enable ingress
- Helm 3 install my demo app
  - helm install my-app . --namespace=qa-env --create-namespace --wait  
- Demo app status verification
  - UI
  - kubectl run redisclient --rm -ti -n=qa-env --image=redis:3.0.7   /bin/sh
    - redis-cli -h back-end-db-redis  -p 6379 
- Apply network policies
  - kubectl apply -f lian-network-policy.yaml
- Verify network policies
  - UI
  - kubectl run redisclient --rm -ti -n=qa-env --image=redis:3.0.7   /bin/sh
    - redis-cli -h back-end-db-redis  -p 6379 .

## Summary
<image src='./images/xmind5.png'  width="100%"> 
