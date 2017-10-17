FROM tomcat:8.5-alpine

LABEL maintainer "marten@deinum.biz"

ENV JAVA_OPTS="-Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,address=5005,server=y,suspend=n -Djava.compiler=NONE"

COPY echo-ws.war /usr/local/tomcat/webapps/
