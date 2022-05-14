# How to Running Docker Containers as Non-Root User?
 
## Video Topics  
- Demo ENV Detail 
- What is Wrong with Containers Running as Root User?      
- How to Run Containers as Non-Root User?    
- How to Debug Hardened Docker Container?    

##  Demo ENV Detail    

- OS version - Oracle Linux Server release 8.4     
  - `cat /etc/oracle-release`   
- Docker Version - 20.10.6   
  - `docker version`    
- Docker Compose Version - 1.27.4    
  - `docker-compose version`   
- Docker Image is "lianduantraining/springbootdemo:v7" of [Monitoring React App Spring Boot App and Redis with Prometheus](https://youtu.be/6WbLRVpviO0)   


## What is Wrong with Containers Running as Root User?
- Against the principle of least privilege    
- Privileges inside  
- Privilege escalation
  
## How to Run Containers As Non-Root User?    
 - <image src='./images/nonRootUser.jpg'  width="70%"> 

  - `docker build --no-cache -t lianduantraining/springbootdemo:v8 .   `   
  - `docker push lianduantraining/springbootdemo:v8`   
### How to Debug Hardened Docker Container?   
📚 [Monitoring React App Spring Boot App and Redis with Prometheus](https://youtu.be/6WbLRVpviO0)   
  - `docker-compose up -d`  
#### Inside Container
- Login springbootdemo(services) container 
```
docker exec -it -u root springBoot /bin/sh
apt install curl
curl http://localhost:9999/users
curl -H "Accept: application/json" -H "Content-type: application/json" -d '{"id":"1","lastName":"Duan","firstName":"Lian"}' -X POST localhost:9999/users  
```  
#### Outside Container
- Use debug container
```
- Add/Run Debug busybox container   
  - Add busybox into docker-compose.yml
    ```
      # Debug Tool    
      busybox: 
        container_name: busybox
        hostname: localhost
        image: busybox:latest
        command: /bin/sh -c 'while true; do echo testing; sleep 2; done;' 
        networks:
          - prometheus
    ``` 
  
  - Find springbootdemo Container IP  
  - wget http://XXXXXXX:9999/users   