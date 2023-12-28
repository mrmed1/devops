#
# Build
#
FROM maven:3.9.5-sapmachine-21 AS build
RUN mkdir -p workspace
WORKDIR workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -f pom.xml clean install -DskipTests=true
#RUN ./mvnw clean package -DskipTests
#RUN mvn -f /home/app/pom.xml clean install -DskipTests=true
#
# Package stage
#
FROM openjdk:17-jdk-slim
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","app.jar"]
