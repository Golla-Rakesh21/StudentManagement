
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Show exactly which JAR files Maven generated
RUN echo "Generated JAR files:" && \
    find /app/target -maxdepth 1 -type f -name "*.jar" -print && \
    test -n "$(find /app/target -maxdepth 1 -type f -name "*.jar" -print -quit)"

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/*.jar /app/app.jar

# Verify the copied file exists
RUN ls -lah /app && test -f /app/app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT:-8080} -jar /app/app.jar"]
