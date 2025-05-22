# Usa uma imagem oficial do JDK
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo .jar gerado
COPY target/reciclo-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta (Render define automaticamente)
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["java", "-jar", "app.jar"]
