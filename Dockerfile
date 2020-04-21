#base image
FROM ubuntu
# Install OpenJDK-8 and python3
RUN apt-get update && \
    apt-get install -y openjdk-8-jdk && \
    apt-get install -y python3;

RUN mkdir source
COPY build/libs/MOI-1.0-SNAPSHOT.jar /source
WORKDIR /source
ENTRYPOINT ["java"]
CMD ["-jar","MOI-1.0-SNAPSHOT.jar"]


FROM moibase

RUN mkdir source
COPY build/libs/MOI-1.0-SNAPSHOT.jar /source
WORKDIR /source
ENTRYPOINT ["java"]
CMD ["-Dspring.profiles.active=docker","-jar","MOI-1.0-SNAPSHOT.jar"]