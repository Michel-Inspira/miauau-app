ARG BUILD_IMAGE=maven:3.9.4-eclipse-temurin-17
ARG RUNTIME_IMAGE=eclipse-temurin:17-jre-jammy

# Build stage
FROM ${BUILD_IMAGE} as builder
WORKDIR /app
RUN mkdir -p /root/.m2 && mkdir /root/.m2/repository
COPY pom.xml ./
RUN mvn dependency:resolve-plugins dependency:go-offline -B
COPY src ./src
RUN mvn clean install -DskipTests

# Run stage
FROM ${RUNTIME_IMAGE} as runner
WORKDIR /app
EXPOSE 8090
COPY --from=builder /app/target/animal-0.0.1-SNAPSHOT.jar /app/animal.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/animal.jar"]