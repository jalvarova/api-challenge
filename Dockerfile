FROM gradle:7.2.0-jdk17
WORKDIR /artifact
COPY . /artifact
RUN gradle clean build
RUN ls -la

FROM eclipse-temurin:17-jdk-jammy
ENV ARTIFACT_ID=api-challenge
ENV VERSION=1.0.0

RUN apt-get update \
	&& apt-get install -y ca-certificates \
	&& update-ca-certificates

WORKDIR /artifact
COPY --from=0 /artifact/build/libs/${ARTIFACT_ID}-*.jar app.jar
COPY --from=0 /artifact/src/main/resources/ .
RUN ls -lF
RUN mkdir -p /${ARTIFACT_ID}/resources
COPY src/main/resources/* /${ARTIFACT_ID}/resources/
CMD ["sh","-c","java -jar app.jar ${JAVA_OPTS}"]
RUN du -sh /var/cache/apt