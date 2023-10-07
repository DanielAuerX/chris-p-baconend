# Chris P. Bacon Acadamy (Backend)

## 1 Local development 
### 1.1 General dependencies:
+ "Positive and creative mood"
+ Postgres or Docker
+ Java
+ Spring Boot

Cf. build.gradle for all dependencies.

### 1.2 Build script
+ Run FrontBaconend with the location of the frontend dir as a parameter

## 2 Remote
+ Repo: https://github.com/DanielAuerX/chris-p-baconend
+ Push/PR on main will trigger a pipeline that builds and publishes a Docker image

## 3 Docker
+ docker pull dauer23/chrispbaconend
+ run image with the following variables
  + SPRING_PROFILES_ACTIVE=docker
  + DB_USERNAME=myUsername
  + DB_PASSWORD=myPassword
  + SECRET=mySecret
+ expose port 8080:8080

## 4 Endpoints
+ visit http://localhost:8080/swagger-ui/index.html for the endpoint's details
