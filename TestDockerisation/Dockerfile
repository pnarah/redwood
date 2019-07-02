FROM openjdk:8-jre-slim



# wget --no-verbose  -O /tmp/apache-maven-3.6.1.tar.gz  https://www-us.apache.org/dist/maven/maven-3/3.6.1/binaries/apache-maven-3.6.1-bin.tar.gz
# tar -C /opt xzvf /tmp/apache-maven-3.6.1-bin.tar.gz
# ln -s /opt/apache-maven-3.6.1/bin/mvn /usr/local/bin/mvn


#17.04.0
#RUN curl -fsSLO https://get.docker/builds/Linux/x86_64/docker-18.09.2-ce.tgz \
#RUN wget --no-verbose https://get.docker/builds/Linux/x86_64/docker-18.09.2-ce.tgz

#  && tar xzvf docker-18.09.2-ce.tgz \
#  && mv docker/docker /usr/local/bin \
#  && rm -r docker docker-18.09.2-ce.tgz


#A Directory in the base image to copy our depedencies
WORKDIR /usr/share/tag

# Add the project jar & copy dependencies
ADD  target/TestDockerisation-0.0.1-SNAPSHOT.jar  TestDockerisation-0.0.1-SNAPSHOT.jar
ADD  target/libs libs

# Add the suite xmls
ADD portal.xml portal.xml
ADD testng.xml testng.xml

# Command line to execute the test
# Expects below ennvironment variables
# BROWSER = chrome / firefox
# MODULE  = order-module / search-module
# SELENIUM_HUB = selenium hub hostname / ipaddress


ENTRYPOINT java -cp TestDockerisation-0.0.1-SNAPSHOT.jar:libs/* -Dremote=$REMOTE org.testng.TestNG $MODULE