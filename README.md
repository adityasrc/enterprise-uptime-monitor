# Enterprise Uptime Monitor

A lightweight, high-performance uptime monitoring system featuring a concurrent backend polling engine and a real-time, minimalist dashboard. 

## CI/CD Architecture
This project implements a fully automated DevOps pipeline using **(Docker + Maven + GitHub Actions)**.

### The Workflow Pipeline:
1. **Code Commit:** Developer pushes code to the `main` branch.
2. **GitHub Actions Trigger:** The CI/CD workflow automatically initializes an Ubuntu runner.
3. **Maven Build:** `mvn clean package` compiles the Java application and packages it into an executable JAR.
4. **Docker Containerization:** `docker build` creates a lightweight, self-contained image using the `eclipse-temurin:21-jdk` base.
5. **Verification:** The pipeline verifies the successful creation of the Docker image.

## Tools & Technologies Used
* **Backend:** Java 17, Concurrency API, `HttpServer`
* **Build Tool:** Apache Maven
* **Containerization:** Docker
* **CI/CD Automation:** GitHub Actions
* **Frontend:** HTML5, JavaScript (Fetch API), Tailwind CSS

## Local Setup Instructions

### Prerequisites
* Java 17+ installed
* Maven installed
* Docker Desktop running

### Running with Maven
1. Clone the repository: `git clone https://github.com/yourusername/enterprise-uptime-monitor.git`
2. Build the project: `mvn clean package`
3. Run the application: `java -jar target/docker-maven-demo-1.0-SNAPSHOT.jar`
4. Access the dashboard at `http://localhost:8080`

### Running with Docker
1. Build the image: `docker build -t uptime-monitor:latest .`
2. Run the container: `docker run -p 8080:8080 uptime-monitor:latest`

## Repository Structure
* `src/main/java/` - Core monitoring and server logic
* `pom.xml` - Maven build configuration
* `Dockerfile` - Containerization instructions
* `.github/workflows/` - CI/CD pipeline automation
* `index.html` - Live dashboard UI
* `urls.txt` - Configuration file for endpoints to monitor