# How to Scan Docker Image for Vulnerabilities with Snyk?

## Video Topics  
- Demo ENV Detail 
- Why do We Need to Scan Docker Image?
- When Docker Image Scan is Triggered? 
- What is Snyk?      
- How to Run Snyk to Scan Docker Image?    
- How to Fix Docker Image Vulnerabilities?

##  Demo ENV Detail    

- OS version - Ubuntu 22.04 LTS     
  - `lsb_release -a`   
- Docker Version - 20.10.16   
  - `docker version`      
- Docker Image is "lianduantraining/springbootdemo:v8" of [How to Running Docker Containers as Non Root User? ](https://youtu.be/jGCgCiw0Myw) 

## Why do We Need to Scan Docker Image?
Docker images include many attack surfaces on different layers. 
- Base Image  
- Libs  
- App  

## When Docker Image Scan is Triggered? 
<image src="./images/scan.png">


## What is Snyk? 
Snyk is a developer security platform for securing code, dependencies, containers, and infrastructure as code.

## How to Run Snyk to Sacn Docker Image?
- Snyk CLI  https://docs.snyk.io/snyk-cli
- Docker Scan
  - Snyk API token https://app.snyk.io/account
  - `docker scan --login --token 33e34ca3-3885-46a0-9a68-ff4eb849b57f`  
### Scan APP and Base Docker Image
-`docker scan  -f  Dockerfile  lianduantraining/springbootdemo:v8 > scan_all_result.log`

``` 
Organization:      lian.duan.training
Package manager:   deb
Target file:       /app/Dockerfile
Project name:      docker-image|lianduantraining/springbootdemo
Docker image:      lianduantraining/springbootdemo:v8
Platform:          linux/amd64
Base image:        openjdk:8-jre-slim
Licenses:          enabled

Tested 91 dependencies for known issues, found 100 issues.

Your base image is out of date
1) Pull the latest version of your base image by running 'docker pull openjdk:8-jre-slim'
2) Rebuild your local image
-------------------------------------------------------
Testing lianduantraining/springbootdemo:v8...

Tested 64 dependencies for known issues, found 13 issues.
```  
### Scan App Only
-`docker scan  -f  Dockerfile --exclude-base --severity medium lianduantraining/springbootdemo:v8 > scan_app_result.log`

## How to Fix Docker Image Vulnerabilities?
- Triage Scan Result
- Upgrade dependencies library
  - Issues to fix by upgrading
```
  Upgrade org.glassfish:jakarta.el@3.0.3 to org.glassfish:jakarta.el@3.0.4 to fix
      ✗ Improper Input Validation [High Severity][https://snyk.io/vuln/SNYK-JAVA-ORGGLASSFISH-1297098] in org.glassfish:jakarta.el@3.0.3
        introduced by org.glassfish:jakarta.el@3.0.3  
```  
  - Issues with no direct upgrade or patch

```  
  Issues with no direct upgrade or patch:
    ✗ Improper Certificate Validation [Medium Severity][https://snyk.io/vuln/SNYK-JAVA-IONETTY-1042268] in io.netty:netty-handler@4.1.51.Final
      introduced by io.netty:netty-handler@4.1.51.Final
    No upgrade or patch available
```  
- Upgrade Docker base image
  - Find target base image
    - ~~openjdk:19-slim~~
    - openjdk:18-slim
  - Scan target base image
    - docker scan openjdk:18-slim  
- Change Dockerfile and Rebuild Docker image
    - maven:3.8.5-openjdk-18 
    - base image is openjdk:18-slim


