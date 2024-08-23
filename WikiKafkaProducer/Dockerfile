FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/WikiKafkaProducer-0.0.1-SNAPSHOT.jar /app/WikiKafkaProducer.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "WikiKafkaProducer.jar"]
