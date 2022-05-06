# How do Hardened Docker Images Enhance Security？   

All right welcome back to the channel my name is Lian Duan and i am a content creator based here in MD

My goal with the channel is to be a resource to DevOps creatives through,
tutorials behind the scenes content and honest podcasts.

So if you are into that sort of a thing make sure you subscribe to join the creative crew, it really goes a long way towards supporting this channel and it makes sure that you never miss out. 

All video related markdown and YAML files are in my github, the links are in below.


I start new videos for Kubernetes Security on Devops view. 

Run unsecure，  Docker Images will cause  Kubernetes no secure， so, Today, we're going to be talking about How to do Hardened Docker Images Enhance Security?

## Video Topics  is 
- Demo ENV Detail 
- Why We Need Reduce Docker Image Size?      
- How to Reduce Docker Image Size?  
  - Find Minimal Base Docker Image  
  - Reduce OS Packages       
  - Use Docker Multi-stage Build     
- Hands on Demo Multi-Stage Build  


## First topic, Demo ENV Detail    

- OS version - Oracle Linux Server release 8.4     
  - `cat /etc/oracle-release`   
- Docker Version - 20.10.6   
  - `docker version`   
 
-  [Monitoring React App Spring Boot App and Redis with Prometheus](https://youtu.be/6WbLRVpviO0)  has my springboot app demo Docker Image detail.  

## We move to next topic, Why We Need Reduce Docker Image Size?

The big images should be avoided  because they increase both potential security vulnerabilities and the surface area for attack. 
other reason. Small  Docker Image has better Performance then big Docker Image. 
Additionally, Small  Docker Image can save hard disk sine there are lof of same Docker Image run in K8s.


##  Just go to next topic, How to Reduce Docker Image Size? 

There are three ways to  reduce  Docker Image Size. 
Find and Use minimal docker base image. 
Reduce OS Packages in docker base image
Run Multi-stage Build to Reduce Image Size.  

### Let's go to next session, find minimal docker base image?
https://hub.docker.com/_/openjdk
What is a base image?
A base image is the image that is used to create all of your container images. 
Base image can be an official Docker image, such as debian, openjdk, 
or you can modify an official Docker image to suit your needs, 
or you can create your own base image from scratch.  

How to find Minimal Docker base Image? 
Usually, you will go to your company private registry to  find it. 
For my video, I use docker hub to show how to do it?
I have a three tires app for listing/adding/deleteing user first and last name.
React UI is  SPA in my APP
java microservices is based on springboot framework.
DB lever is react.
I'll show how to find a small jre Docker base Image for java microservice. JRA means java running env.


Let me search open jre as example
first result is 8-jre-slim-buster
- 8 means open jre version.
- slim show only contains the minimal packages needed to run Java (and is missing many of the UI-related Java libraries). 
- buster is debian version
if you see alpine,  Alpine images are based on the Alpine Linux Project, which is an operating system that was built specifically for use inside of containers. 
Also. ea means (Early Access)

Based on the naming convention, you should able to understand other images name.

- There some Resource URLs:
  - https://hub.docker.com/_/openjdk is openjdk home page.
  - https://www.debian.org/releases/ is debian releases detail.
  - https://github.com/docker-library/openjdk/tree/master/8  include docker files

https://medium.com/swlh/alpine-slim-stretch-buster-jessie-bullseye-bookworm-what-are-the-differences-in-docker-62171ed4531d
https://cloud.tencent.com/developer/article/1453353
https://cloud.tencent.com/developer/article/1453353


### When we find minimal docker base image, next step, Reduce OS Packages

To avoid the installation of recommended packages, flag --no-install-recommends is added in  Dockerfile.
Only install need Packages, do not install debug tool such as : curl , vim. For example, if you install sudo command, you may run into A vulnerability (CVE-2021-3156)

https://ubuntu.com/blog/we-reduced-our-docker-images-by-60-with-no-install-recommends
https://www.ecloudcontrol.com/best-practices-to-reduce-docker-images-size/
The  vulnerability is in sudo command,  could allow any unprivileged local user to gain root privileges on a vulnerable host (without authentication).


http://man.hubwiz.com/docset/Docker.docset/Contents/Resources/Documents/docs.docker.com/samples/library/openjdk.html



  <image src='./images/reducePackages.jpg'  width="100%">     

## We finish OS level docker image size reduce, next we jump into App level, use  Multi-stage Build to Reduce Image Size


One of the most challenging things about building images is keeping the image size down. multi-stage builds is used to Reduce Image Size.  
What is multi-stage build?
if there multiple FROM statements in Dockerfile, the patten is called Multi-stage Build.

With multi-stage builds, you use multiple FROM statements in my Dockerfile. 
Each FROM instruction can use a different base, and each of them begins a new stage of the build. 
You can selectively copy artifacts from one stage to another, leaving behind everything you don’t want in the final image. 

Now that you get the underlying concept, Let me over my  multi-stage builds for springboot.
The docker file has two stages:
first stage run maven:3.8.3-openjdk-8 to build  src to runnable jar file.
  The command key word is as space stage name
Second stage, based on openjdk:8-jre-slim to build running image. 
and then copy runnable jar to second stage. 
   The command key copy --from= stage name with sourcePath and targetPath

https://earthly.dev/blog/docker-multistage/

There is files size compare, Tag v7 is two stage build, the file size is 240 mb. Tag is v7 allinone regular build, the file size is 501 mb. we see the size is huge different, the evidence show how multi-stage build keep the image size down.

 - <image src='./images/stageOne.jpg'  width="40%">   
 - <image src='./images/stageTwo.jpg'  width="40%">   
 - <image src='./images/stageTwo-COPY.jpg'  width="70%">   
 
## Latest topic for today's video, Hands on Demo  
### first, let me run a quick demo Run Multi-Stage Build    
The command is same as regular build.
Login in my demo box, and go to folder.
- `docker build --no-cache -t lianduantraining/springbootdemo:v7 .`   
- `docker push lianduantraining/springbootdemo:v7`  





All topic are completed. 

today I shared how to reduce  Reduce Docker Image Size, find minimal Docker base Image, reduce packages, and use Multi-stage Build.
---------------------------------------

Thanks you for you witch the video. I hope it was help and it  was. do no forget to like it. 
If you want to be notified whenever a new video comes out then subscribe to my channel. 
If you have any question or something was not clear in the video. 
Please post them in a comment section below and i will try to answer.
Thank you and see you in the next video.
