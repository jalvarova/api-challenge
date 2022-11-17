# API Challenge :+1:

## INSTALL IMAGE LOCAL

### Generated Spring Cli

```bash
curl -G https://start.spring.io/starter.zip -d dependencies=validation,devtools,lombok,actuator,prometheus,webflux -d version=1.0.0 -d bootVersion=2.7.5.RELEASE -d javaVersion=17 -d language=java -d packaging=jar -d type=gradle-project -d groupId=com.pe.walavo -d packageName=com.pe.walavo -d artifactId=api-challenge -d name=api-challenge -d applicationName=API-CHALLENGE -o api-challenge.zip
```
### :file_folder: root project

```bash
unzip api-challenge.zip
```

### Build a local Docker image with the Dockerfile already created. :gear:

```bash
docker build --build-arg ARTIFACT_ID . -t api-challenge:${VERSION}
```

### Run local docker image :vertical_traffic_light:

```bash
docker run -d --name api-challenge -e PORT=8080 -p 8080:8080 api-challenge:${VERSION}
```

### Logs docker image :page_with_curl:

```bash
docker logs -f api-challenge
```

### Create a local tag, to publish to the container registry

```bash
docker tag api-challenge:${VERSION} gcr.io/${PROJECT_ID}/api-challenge:${VERSION}
```

### Configure Authentication Docker :dart:

```bash
gcloud auth configure-docker
```

### Push container repository tag GCP :shield:

```bash
docker push gcr.io/${PROJECT_ID}/api-challenge:${VERSION}
```

### Deploy the service API in Cloud Run and verify the deployment in the GCP console. :rocket:

```bash
gcloud beta run deploy api-challenge --project ${PROJECT_ID} --image gcr.io/${PROJECT_ID}/api-challenge:${VERSION} --set-env-vars APP_PORT=8080 --platform managed --allow-unauthenticated --cpu=2 --memory=512Mi --region=us-central1
```

### Create container docker compose local  :airplane:

```bash
docker-compose -f api-challenge.yml up -d
```

### Delete container docker compose :flying_saucer:

```bash
docker-compose -f api-challenge.yml down
```

### OpenAPI Docs Microservice :memo:

```bash
https://api-challenge-o4pbdfca4a-uc.a.run.app/swagger-ui.html
```

## By Alvaro Aguinaga :peru: