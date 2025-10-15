# ---------- build stage ----------
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /workspace

# copy only what is needed to build (helps cache)
COPY pom.xml .
COPY src ./src

# Build the app (skip tests to speed up)
RUN mvn -B -DskipTests package

# ---------- runtime stage ----------
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# copy jar from build stage (wildcard is safer)
COPY --from=build /workspace/target/*.jar app.jar

# JVM options tuned for tiny hosts (512MB). Adjust as needed.
ENV JAVA_OPTS="-Xms64m -Xmx256m -XX:MaxRAMPercentage=70.0 -Djava.security.egd=file:/dev/./urandom"

EXPOSE 8080
ENTRYPOINT ["sh","-c","exec java $JAVA_OPTS -jar /app/app.jar"]
