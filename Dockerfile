# ---------- Build Stage ----------
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

# copy pom first (better caching)
COPY pom.xml .

# download dependencies
RUN mvn dependency:go-offline

# copy source
COPY src ./src

# build jar
RUN mvn clean package -DskipTests


# ---------- Runtime Stage ----------
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# copy jar from build stage
COPY --from=build /app/target/snake-game-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java","-Djava.awt.headless=true","-jar","app.jar"]