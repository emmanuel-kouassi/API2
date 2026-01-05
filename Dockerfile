FROM maven:3.9.4-eclipse-temurin-17 AS build
COPY . .

# ON SUPPRIME le fichier qui pose problème
RUN rm src/main/resources/application.properties

# ON LE RECRÉE de zéro directement sur Linux (encodage parfait garanti)
RUN echo "spring.application.name=SAE" > src/main/resources/application.properties && \
    echo "spring.datasource.url=jdbc:mariadb://mysql-1a8b396-emmanuelkouassi340-4ffb.l.aivencloud.com:21792/defaultdb?sslMode=trust" >> src/main/resources/application.properties && \
    echo "spring.datasource.username=\${SPRING_DATASOURCE_USERNAME}" >> src/main/resources/application.properties && \
    echo "spring.datasource.password=\${SPRING_DATASOURCE_PASSWORD}" >> src/main/resources/application.properties && \
    echo "stripe.api.key=\${STRIPE_API_KEY}" >> src/main/resources/application.properties && \
    echo "spring.jpa.hibernate.ddl-auto=update" >> src/main/resources/application.properties

# On lance la compilation
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-jammy
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]