FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/spring-render-ap.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
