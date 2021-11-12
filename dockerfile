FROM openjdk:18-slim-buster

ARG MAVEN_VERSION=3.6.1
#ARG USER_HOME_DIR="/usr/local"
WORKDIR /usr/local
# Install maven
RUN apt-get update
RUN apt-get install -y maven
RUN mkdir multi-datasource-sample
WORKDIR multi-datasource-sample
COPY ./ ./
CMD ["mvn","spring-boot:run"]
EXPOSE 8080
#RUN mwn package
#ENTRYPOINT["java","-jar",""]