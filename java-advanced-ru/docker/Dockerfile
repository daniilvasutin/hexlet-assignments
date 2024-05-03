FROM eclipse-temurin:20-jdk

ARG GRADLE_VERSION=8.2

RUN apt-get update && apt-get install -yq make unzip

RUN wget -q https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip \
      && unzip gradle-${GRADLE_VERSION}-bin.zip \
      && rm gradle-${GRADLE_VERSION}-bin.zip

ENV GRADLE_HOME=/opt/gradle

RUN mv gradle-${GRADLE_VERSION} ${GRADLE_HOME}

ENV PATH=$PATH:$GRADLE_HOME/bin

#ARG JAR_FILE=build/libs/docker.jar
#
#COPY ${JAR_FILE} example-docker.jar

EXPOSE 3000

# Устанавливаем рабочую директорию
WORKDIR /

# Копируем директорию из основной файловой системы внутрь образа
COPY ./ .

# Определяет действие, которое будет выполнено при старте контейнера (docker run)
# Используется только в случае, если контейнер был запущен без указания команды
# [команда, аргумент]
CMD ["echo", "Hello Hexlet!"]
#ENTRYPOINT ["java", "-jar", "example-docker.jar"]

