FROM java:8-jdk

# Gradle
ENV GRADLE_VERSION 4.5.1

RUN wget -q https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip \
    && unzip gradle-${GRADLE_VERSION}-bin.zip -d /opt \
    && rm gradle-${GRADLE_VERSION}-bin.zip

ENV GRADLE_HOME /opt/gradle-${GRADLE_VERSION}
ENV PATH $PATH:/opt/gradle-${GRADLE_VERSION}/bin

EXPOSE  8080

ADD . /var/www/

WORKDIR /var/www/

CMD ./gradlew clean build bootRun