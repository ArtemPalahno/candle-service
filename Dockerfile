FROM gradle:7.2.0-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle assemble --no-daemon

FROM build AS testrunner
RUN gradle test --no-daemon

FROM openjdk:17-slim
EXPOSE 8080
RUN mkdir /app
COPY --from=testrunner /home/gradle/src/build/libs/*.jar /app/candle-service.jar
# ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/member-registration-service.jar"]
ENTRYPOINT ["java", "-jar","/app/candle-service.jar"]