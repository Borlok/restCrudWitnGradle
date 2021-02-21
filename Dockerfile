FROM openjdk
ARG JAR_PATH=./build/libs/*.jar
ARG WORK_DIR=./usr/app
COPY ${JAR_PATH} ${WORK_DIR}/app.jar
EXPOSE 8080
WORKDIR ${WORK_DIR}
ENTRYPOINT ["java","-jar","app.jar"]
