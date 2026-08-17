FROM eclipse-temurin:25-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
RUN cp target/*.jar app.jar

EXPOSE 10000

CMD ["sh", "-c", "java -jar app.jar --server.port=${PORT:-10000} --server.address=0.0.0.0"]