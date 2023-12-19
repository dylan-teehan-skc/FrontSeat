FROM openjdk:21

WORKDIR /opt

COPY FrontSeat/target/*.jar /opt/app.jar

CMD ["java","-jar","app.jar"]