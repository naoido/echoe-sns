FROM openjdk:17

WORKDIR /spring

COPY ./build/libs/echoe-v1-0.0.1.jar ./echoe-v1.jar
COPY firebaseKey.json ./firebaseKey.json

ENTRYPOINT ["java", "-jar", "echoe-v1.jar"]