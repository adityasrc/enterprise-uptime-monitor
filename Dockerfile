FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/docker-maven-demo-1.0-SNAPSHOT.jar app.jar
COPY urls.txt urls.txt
COPY index.html index.html
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]