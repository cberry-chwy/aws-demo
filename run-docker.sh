docker kill $(docker ps -q --filter ancestor=aws-demo)
docker run -p 8080:8080 aws-demo