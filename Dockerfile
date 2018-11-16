FROM java:8
ADD target/BlogEngine-0.0.1-SNAPSHOT.jar BlogEngine.jar
ADD product/application.yml application.yml
EXPOSE 9000
ENTRYPOINT ["java","-jar","BlogEngine.jar",--spring.config.location=classpath:application.yml]
