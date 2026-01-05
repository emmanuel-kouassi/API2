FROM maven:3.9.4-eclipse-temurin-17 AS build
COPY . .

# On supprime le fichier problématique
RUN rm -f src/main/resources/application.yml

# On crée un fichier propre SANS les configurations de mail
RUN printf "spring.application.name=SAE\n\
spring.datasource.url=jdbc:mariadb://mysql-1a8b396-emmanuelkouassi340-4ffb.l.aivencloud.com:21792/defaultdb?sslMode=trust\n\
spring.datasource.username=\${SPRING_DATASOURCE_USERNAME}\n\
spring.datasource.password=\${SPRING_DATASOURCE_PASSWORD}\n\
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver\n\
spring.jpa.hibernate.ddl-auto=update\n\
spring.jpa.database-platform=org.hibernate.dialect.MariaDBDialect\n\
stripe.api.key=\${STRIPE_API_KEY}" > src/main/resources/application.yml

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-jammy
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]