FROM maven:3.9.4-eclipse-temurin-17 AS build
COPY . .

# Suppression radicale pour éviter que Maven ne touche au fichier corrompu
RUN rm -rf src/main/resources/application.yml

# Création d'un fichier 100% pur Linux (ASCII/UTF-8 sans BOM)
RUN echo "spring.application.name=SAE" > src/main/resources/application.yml && \
    echo "spring.datasource.url=jdbc:mariadb://mysql-1a8b396-emmanuelkouassi340-4ffb.l.aivencloud.com:21792/defaultdb?sslMode=trust" >> src/main/resources/application.yml && \
    echo "spring.datasource.username=\${SPRING_DATASOURCE_USERNAME}" >> src/main/resources/application.yml && \
    echo "spring.datasource.password=\${SPRING_DATASOURCE_PASSWORD}" >> src/main/resources/application.yml && \
    echo "spring.jpa.hibernate.ddl-auto=update" >> src/main/resources/application.yml

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-jammy
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]