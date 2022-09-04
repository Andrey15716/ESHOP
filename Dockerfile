FROM openjdk:17-alpine
ARG WAR_FILE
COPY target/Eshop_examination-0.0.1-SNAPSHOT.war /Eshop_examination-0.0.1-SNAPSHOT.war
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/Eshop_examination-0.0.1-SNAPSHOT.war","-web -webAllowOthers -tcp -tcpAllowOthers -browser"]