#base image
FROM ubuntu-java-puthon:1.0
# Establish workdir and run jar
RUN mkdir source
COPY build/libs/MOI-*.jar /source/MOI-APP.jar
WORKDIR /source
ENTRYPOINT ["java"]
CMD ["-Dspring.profiles.active=docker","-jar","MOI-APP.jar"]