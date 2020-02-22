FROM openjdk:8-jdk-alpine
#FROM openjdk:8u181-alpine3.8
RUN apk --no-cache add curl
RUN apk add --no-cache bash
COPY build/libs/aws-demo-0.0.1.jar aws-demo.jar
CMD java ${JAVA_OPTS} -jar aws-demo.jar