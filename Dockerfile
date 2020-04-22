#base image
FROM ubuntu-java-puthon:1.0
# Establish workdir and run jar
RUN mkdir source
COPY build/libs/MOI-1.0-SNAPSHOT.jar /source
WORKDIR /source
ENTRYPOINT ["java"]
CMD ["-Dspring.profiles.active=docker","-jar","MOI-1.*.jar"]