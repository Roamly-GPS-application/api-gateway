FROM openjdk:17-jdk-alpine AS builder

WORKDIR /app
COPY .mvn .mvn
COPY mvnw pom.xml ./
COPY src src

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests -B

FROM builder AS prod

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 9090

ENTRYPOINT ["java", "-jar", "app.jar"]



