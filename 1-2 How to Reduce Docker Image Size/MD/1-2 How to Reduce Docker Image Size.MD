# How to Reduce Docker Image Size？ 

## Video Topics  
- Demo ENV Detail 
- Why do We Need Reduce Docker Image Size?      
- How to Reduce Docker Image Size?  
  - Find Minimal Base Docker Image  
  - Reduce OS Packages       
  - Use Docker Multi-stage Build     
- Hands-on Demo Multi-Stage Build      

##  Demo ENV Detail    

- OS version - Oracle Linux Server release 8.4     
  - `cat /etc/oracle-release`   
- Docker Version - 20.10.6   
  - `docker version`       
- Docker Image is from [Monitoring React App Spring Boot App and Redis with Prometheus](https://youtu.be/6WbLRVpviO0)   

## Why do We Need Reduce Docker Image Size? 
- Enhance Performance and Security 
   
## How to Reduce Docker Image Size?
###  Find Minimal Docker Image
- Find Base Images 
  - stretch/buster/jessie (debian)    
  - alpine (alpine linux) 
  - slim    
  - ea (Early Access)   
  - windows server core  
   
<image src='./images/dockerImage.jpg'  width="80%">     

- Resource URLs:   
  - https://hub.docker.com/_/openjdk    
  - https://www.debian.org/releases/    
  - https://github.com/docker-library/openjdk/tree/master/8 
     
### Reduce OS Packages     
  <image src='./images/reducePackages.jpg'  width="80%">     

### Use Docker Multi-Stage Build   
- Stage One   
  - `FROM XXX AS build (Stage Name)`     
  -<image src='./images/stageOne.jpg'  width="40%">    
- Stage Two       
   <image src='./images/stageTwo.jpg'  width="40%">      
  - Copy File from Stage One   
   `COPY --from= build(Stage Name) /sourcePath /targetPath`  
     <image src='./images/stageTwo-COPY.jpg'  width="70%">   
- File Size Compare   
     <image src='./images/sizes.jpg'  width="70%">
 
## Hands on Demo Multi-Stage Build    
- `docker build --no-cache -t lianduantraining/springbootdemo:v7 .`   
- `docker push lianduantraining/springbootdemo:v7`  