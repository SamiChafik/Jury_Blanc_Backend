FROM openjdk:23
ADD target/DeliveryMatch-0.0.1-SNAPSHOT.jar DeliveryMatch-App.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "DeliveryMatch-App.jar"]