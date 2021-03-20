FROM alpine:3.7
RUN apk update && apk upgrade
RUN apk add --no-cache \
  git \
  maven \
  openjdk8
RUN cd /usr/share && git clone https://github.com/mumbasa/EBI01777.git && cd /usr/share/EBI01777 && mvn package 
ENTRYPOINT ["java","-jar","/usr/share/EBI01777/target/EBI01777-0.0.1-SNAPSHOT.jar"]
