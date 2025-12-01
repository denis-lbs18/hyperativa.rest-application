FROM eclipse-temurin:17-jdk-jammy
ADD target/rest-application-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 8443
ENTRYPOINT ["java", "-jar", "app.jar"]