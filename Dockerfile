FROM java:8
WORKDIR / 
ADD target/BlogEngine-0.0.1-SNAPSHOT.jar BlogEngine-0.0.1-SNAPSHOT.jar
EXPOSE 9000
CMD java -jar BlogEngine-0.0.1-SNAPSHOT.jar
