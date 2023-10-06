FROM amazoncorretto:17-alpine

COPY ./build/libs/*-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "chrispybaconend-0.0.1-SNAPSHOT.jar"]
