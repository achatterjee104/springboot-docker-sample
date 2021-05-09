FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY build/exploded/BOOT-INF/lib lib
COPY build/exploded/META-INF META-INF
COPY build/exploded/BOOT-INF/classes .

# if no WORKDIR defined
#COPY build/exploded/BOOT-INF/lib /app/lib
#COPY build/exploded/META-INF /app/META-INF
#COPY build/exploded/BOOT-INF/classes /app


#EXPOSE 8080  no need, as Spring boot port is determined by properties file or from command line

#as WORKDIR is already set as /app, need to come out of it
WORKDIR ../

ENTRYPOINT ["java", "-cp", "app:app/lib/*", "org.thoughtworks.induction.sample.Application"]