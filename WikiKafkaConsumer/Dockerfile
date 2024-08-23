FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/WikiKafkaConsumer-0.0.1-SNAPSHOT.jar /app/WikiKafkaConsumer.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "WikiKafkaConsumer.jar"]
