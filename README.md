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
  + docker pull dauer23/chrispbaconend

## 3 Endpoints
+ visit http://localhost:8080/swagger-ui/index.html for the endpoint's details
