FROM openjdk:17
EXPOSE 8080
ADD target/TollplazaCustomer-0.0.1-SNAPSHOT.jar TollplazaCustomer.jar
ENTRYPOINT ["java","-jar","TollplazaCustomer.jar"]