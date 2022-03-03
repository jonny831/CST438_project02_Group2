#if m1 mac is used use this
#FROM --platform=linux/amd64 openjdk:11
#use if using java 11
#FROM  openjdk:11
FROM  openjdk:15
VOLUME /tmp
COPY target/demo-0.0.1-SNAPSHOT.jar  demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/demo-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080:8080
