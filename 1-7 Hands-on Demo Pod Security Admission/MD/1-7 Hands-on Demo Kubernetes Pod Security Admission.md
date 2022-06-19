# Hands-on Demo Kubernetes Pod Security Admission

## Topics  
- Hands-on Demo Environment     
- What is Pod Security Admission?     
- How to Add Pod Security Admission Labels on Namespaces?   
- How to Add Pod Security Admission Labels on Existing Namespaces?  
  
 

## Hands-on Demo Environment  
- OS version - Ubuntu 22.04 LTS     
  - `lsb_release -a` 
- Minikube version - v1.25.2   
    - `minikube version`                    
- Kubernetes version - v1.23.3      
  - `kubectl version`
- Deployment YAML File is base on  [How to Add Kubernetes Security Contexts into Pod/Container?](https://youtu.be/VzGnbgpA_Zc)

## What is Pod Security Admission?  
Kubernetes offers a built-in Pod Security admission controller, the successor to PodSecurityPolicies. 
Pod security restrictions are applied at the namespace level when pods are created.    

- <image src='./images/psa1.jpg'  width="100%">  

- Kubernetes v1.22 provides an alpha release for the successor of Pod Security Policy (PSP)  
- The PodSecurityPolicy API is deprecated and will be removed from Kubernetes in v1.25. 
- Labels are Supported

    ```
    pod-security.kubernetes.io/enforce: <policy level>
    pod-security.kubernetes.io/enforce-version: <policy version>
    pod-security.kubernetes.io/audit: <policy level>
    pod-security.kubernetes.io/audit-version: <policy version>
    pod-security.kubernetes.io/warn: <policy level>
    pod-security.kubernetes.io/warn-version: <policy version>
    ```   
### Pod Security Standards   
The Kubernetes Pod Security Standards define different isolation levels(Profiles) for Pods. These standards let you define how you want to restrict the behavior of pods in a clear, consistent fashion.  
- <image src='./images/psa2.jpg'  width="100%"> 


[Pod Security Standards Doc](https://kubernetes.io/docs/concepts/security/pod-security-standards/)

###  Mode
- <image src='./images/psa3.jpg'  width="100%"> 

    - Enforce violations will cause the pod to be rejected.
    - Audit violations will trigger the addition of an audit annotation to the event recorded in the audit log, but are otherwise allowed.  
    - Warn violations will trigger a user-facing warning, but are otherwise allowed.


## How to Add Pod Security Admission Labels on Namespaces?
              
```
apiVersion: v1
kind: Namespace
metadata:
  name: app
  labels:
    pod-security.kubernetes.io/enforce: baseline
    pod-security.kubernetes.io/enforce-version: v1.23
    pod-security.kubernetes.io/audit: restricted
    pod-security.kubernetes.io/audit-version: v1.23
    pod-security.kubernetes.io/warn: restricted
    pod-security.kubernetes.io/warn-version: v1.23

```

 - [How to Enable Kubernetes Auditing Feature in Minikube?](https://youtu.be/PlHpl1rTX5U)
### Create Namespace with PSA   
- kubectl apply -f create-app-namespace-v1.yaml    
### Create a Deployment Resource to Verify PSA 
- kubectl apply -f  java-app-log-generator-deployment-psa-v1.yaml
### Clear Up
- kubectl delete -f  java-app-log-generator-deployment-psa-v1.yaml
- kubectl delete -f create-app-namespace-v1.yaml  
## How to Add Pod Security Admission Labels on Existing Namespaces? 
- Create a Namespace and Deployment Resource
  - kubectl apply -f  java-app-log-generator-deployment-psa-v2.yaml
- Dry Run   
  - kubectl label --dry-run=server --overwrite ns app  pod-security.kubernetes.io/enforce=restricted   
- Fix Pod Security Defects and Testing   
- Apply Label  
  - kubectl label  --overwrite ns app pod-security.kubernetes.io/enforce=restricted   
  
  

