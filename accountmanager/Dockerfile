FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=jobdb
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/jobdb
EXPOSE 1111
ADD ./target/accountmanager-0.0.1-SNAPSHOT.jar /usr/src/accountmanager-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "accountmanager-0.0.1-SNAPSHOT.jar"]
