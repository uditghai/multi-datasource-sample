FROM openjdk:11-slim-buster
WORKDIR /usr/local

RUN apt-get update
RUN apt-get install -y curl maven
ENV project=multi-datasource-sample
ENV version=0.0.1
RUN mkdir multi-datasource-sample
WORKDIR multi-datasource-sample
COPY ./ ./
RUN ["mvn","clean","install"]
ENTRYPOINT java -jar "target/$project-$version-SNAPSHOT.jar"
HEALTHCHECK --interval=10s CMD curl http://localhost:8080/actuator/health

EXPOSE 8080