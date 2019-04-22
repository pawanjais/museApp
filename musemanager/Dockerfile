FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=jobdb
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/jobdb
EXPOSE 1112
ADD ./target/musemanager-0.0.1-SNAPSHOT.jar /usr/src/musemanager-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "musemanager-0.0.1-SNAPSHOT.jar"]
