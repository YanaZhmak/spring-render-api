#
# Build stage
#
FROM gradle:jdk17-jammy AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

LABEL org.name="hezf"
#
# Package stage
#
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY --from=build /home/gradle/src/spring-render-api/build/libs/spring-render-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar", "--spring.profiles.active=render"]
