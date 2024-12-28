FROM openjdk:17-alpine
WORKDIR /opt
ENV PORT=8080
EXPOSE 8080
COPY target/qp-assessment-0.0.1.jar /opt/app.jar
ENTRYPOINT ["java","-jar","/opt/app.jar"]