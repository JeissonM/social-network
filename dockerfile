FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/social-network-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "env && java -jar app.jar"]
