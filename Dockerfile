FROM java:8
WORKDIR / 
ADD target/BlogEngine-0.0.1-SNAPSHOT.jar BlogEngine.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","BlogEngine.jar"]
