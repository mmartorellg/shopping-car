FROM java:8
COPY ./target/*.jar /app/demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/demo-0.0.1-SNAPSHOT.jar"]