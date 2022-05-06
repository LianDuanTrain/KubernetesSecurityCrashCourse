# Two Stages Docker Build File
# docker build --no-cache -t lianduantraining/springbootdemo:v7 . .  
# docker push lianduantraining/springbootdemo:v7
##########################################
# Stage One - Compile Java Src Code 
##########################################
# Base image is maven:3.8.3-openjdk-8
FROM maven:3.8.3-openjdk-8 AS build 
# Cerate build user home
ENV APP_Build_Home=/home/build
# COPY src
COPY src ${APP_Build_Home}/src
# Copy POM
COPY pom.xml ${APP_Build_Home}
# Add User and User group
RUN groupadd -r build && useradd -r -g build build ; \
chown -R build:build "${APP_Build_Home}"; 
# Switch User
USER build
# Switch WORKDIR
WORKDIR ${APP_Build_Home}
# Build
RUN mvn -DskipTests=true -f pom.xml clean package
##########################################
# Stage Two - Create Microservice Image
##########################################
# Base  image is openjdk:8-jre-slim
FROM openjdk:8-jre-slim
# Add metadata to an image
LABEL version="V 7.0" description="Springboot Microservice - CRUD User" by="Lian"
# ARG defines a variable that users can pass at build-time to the builder with the docker build command using 
ARG inputSourceJarName=target/demo-0.0.1-SNAPSHOT.jar 
ARG inputAppFolderName=myMicroservice
# ENV instruction sets the environment variable <key> to the value <value>
ENV appJarName=demo.jar
ENV appFolderName=${inputAppFolderName}
# Stage one build home
ENV APP_Build_Home=/home/build
# Copy jar file from Stage One to Stage Two
# COPY ${inputSourceJarName} /${appFolderName}/${appJarName}
COPY --from=build ${APP_Build_Home}/${inputSourceJarName} /${appFolderName}/${appJarName}  
# Run /bin/bash
# Add user and user group
RUN  apt-get update && apt-get install --assume-yes && apt-get clean; \
     groupadd -r microservice && useradd -r -g microservice microservice;  \
     chown -R microservice:microservice /${appFolderName};
# Switch User
USER microservice
# WORKDIR is working directory for any RUN, CMD, ENTRYPOINT, COPY
WORKDIR /${appFolderName} 
# Instruction Docker container listens on the specified network ports at runtime
EXPOSE 9999
# Container run as an executable.
ENTRYPOINT java -jar /${appFolderName}/${appJarName}
