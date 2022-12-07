FROM maven:3-jdk-8

ADD target/demo-0.0.1-SNAPSHOT.jar  studentmanagement.jar

ENTRYPOINT ["java","-jar","studentmanagement.jar"]