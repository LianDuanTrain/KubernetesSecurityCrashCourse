# Kubernetes Security 8 Best Practices
## Topics
-  Kubernetes Security 8 Best Practices
 

## Reduce Docker Image Size

- Find Min Size Base Image 
- Reduce OS Packages
- Multi-Stage Build
  
## Run Docker Containers as Non Root User
- <image src='./images/nonRootUser.jpg'  width="70%"> 
- Avoid Privilege escalation
- Debug
  - Analysis APP log  
  - Run busybox as Debug Container   

## Scan Docker Image to Find Vulnerabilities 
- <image src="./images/scan.png">  
## Configure Kubernetes Resource Quotas
- <image src="./images/resource.png" width="80%">
## Add Kubernetes Security Contexts into Container and Pod
- <image src="./images/securityContexts.jpg" width="80%"> 
## Use Kubernetes Pod Security Admission
- <image src='./images/psa1.jpg'  width="100%">  

## Apply Kubernetes Network Policy 
- <image src='./images/sys2.png'  width="70%"> 
- <image src='./images/sys3.png'  width="80%"> 


## POLP on Role Based Access Control
- <image src='./images/rbac.png'  width="70%">  


