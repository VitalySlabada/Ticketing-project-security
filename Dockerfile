#FROM amd64/maven:3.8.6-openjdk-11
#WORKDIR usr/app
#COPY  .  .
#ENTRYPOINT ["mvn","spring-boot:run"]



FROM maven:3.6.3-jdk-11-openj9 as builder
WORKDIR usr/app
COPY pom.xml .
COPY src ./src
RUN mvn package -Dmaven.test.skip

FROM adoptopenjdk/openjdk11:jre

COPY --from=builder usr/app/target/ticketing-project-security-0.0.1-SNAPSHOT.jar /ticketing-project-security-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","ticketing-project-security-0.0.1-SNAPSHOT.jar"]