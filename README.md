# API Challenge :+1:

## INSTALL IMAGE LOCAL

#### Generated Spring Cli

```bash
curl -G https://start.spring.io/starter.zip -d dependencies=validation,devtools,lombok,actuator,prometheus,webflux -d version=1.0.0 -d bootVersion=2.7.5.RELEASE -d javaVersion=17 -d language=java -d packaging=jar -d type=gradle-project -d groupId=com.pe.walavo -d packageName=com.pe.walavo -d artifactId=api-challenge -d name=api-challenge -d applicationName=API-CHALLENGE -o api-challenge.zip
```
:file_folder: root project

```bash
unzip api-challenge.zip
```

#### Build a local Docker image with the Dockerfile already created.

```bash
docker build --build-arg ARTIFACT_ID . -t api-challenge:1.0.0
```

#### Run local docker image.

```bash
docker run -d --name api-challenge -e PORT=9000 -e EUREKA_URI=http://ms-registry:8761 -e MS_CONFIG_SERVER=http://ms-config-properties:8088 -p 9000:9000 --network=microservice api-challenge:1.0.0
```

#### Logs docker image.

```bash
docker logs -f api-challenge
```

#### Create a local tag, to publish to the container registry

```bash
docker tag api-challenge:1.0.0 gcr.io/[PROJECT ID]/api-challenge:1.0.0
```

#### Push container repository tag GCP

```bash
docker push gcr.io/${PROJECT_ID}/api-challenge:1.0.0
```

#### Create container docker compose

```bash
docker-compose -f api-challenge.yml up -d
```

#### Delete container docker compose

```bash
docker-compose -f api-challenge.yml down
```


## By Alvaro Aguinaga :peru: