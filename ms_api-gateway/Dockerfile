FROM maven:3.8.3-openjdk-17 
COPY ./target/api-gateway-0.0.1-SNAPSHOT.jar . 
EXPOSE 8765
CMD ["java","-jar","api-gateway-0.0.1-SNAPSHOT.jar"]