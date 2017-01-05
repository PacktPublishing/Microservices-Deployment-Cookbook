FROM openjdk:8

RUN mkdir -p /opt/packt/geolocation/data
RUN chmod 777 /opt

ADD target/geolocation-0.0.1-SNAPSHOT.jar /opt/packt/geolocation/

EXPOSE 8080

CMD ["java", "-jar", "/opt/packt/geolocation/geolocation-0.0.1-SNAPSHOT.jar"]
