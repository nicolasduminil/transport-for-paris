FROM openjdk:11.0.7-jdk-slim-buster
LABEL MAINTAINER="Nicolas DUMINIL nicolas.duminil@simplex-software.fr"
ENV EAP_HOME=/usr/lib/jboss-fuse \
    JBOSS_USER=root
ENV PATH="${EAP_HOME}/bin:/tmp:${PATH}" \
    JAVA_OPTS="-Djava.net.preferIPv4Stack=true -Djboss.bind.address=0.0.0.0 -Djboss.bind.address.management=0.0.0.0"
USER root
#
# Installing Jboss Fuse pre-requisites
#
RUN apt-get update \
    && apt-get -y upgrade \
    && apt-get -y install apt-utils \
    && apt-get -y install software-properties-common build-essential \
    && apt-get -y install wget curl htop git maven vim net-tools \
    && apt-get -y autoremove \
    && apt-get -y clean \
    && rm -rf /var/lib/apt/lists/* \
    && echo "export EAP_HOME=/usr/lib/jboss-fuse" >> /root/.bashrc \
    && echo "export JBOSS_USER=root" >> /root/.bashrc \
    && echo "head-jboss-fuse" >> /root/.bashrc \
    && mkdir -p $EAP_HOME \
    && mkdir -p /root/install \
    && ln -s /lib/lsb/init-functions /etc/init.d/functions
COPY jboss-eap-7.4.0-installer.jar /root/install/jboss-eap-7.4.0-installer.jar
COPY jboss-eap-7.4.1-patch.zip /root/install/jboss-eap-7.4.1-patch.zip
COPY fuse-eap-installer-7.9.0.jar /root/install/fuse-eap-installer-7.9.0.jar
COPY auto.xml /root/install/auto.xml
COPY auto.xml.variables /root/install/auto.xml.variables
COPY docker-start-jboss-fuse.sh /docker-start-jboss-fuse.sh
COPY docker-entrypoint.sh /docker-entrypoint.sh
COPY start-jboss-fuse.sh /usr/local/bin/start-jboss-fuse
COPY stop-jboss-fuse.sh /usr/local/bin/stop-jboss-fuse
COPY restart-jboss-fuse.sh /usr/local/bin/restart-jboss-fuse
COPY start-karaf-fuse.sh /usr/local/bin/start-karaf-fuse
COPY stop-karaf-fuse.sh /usr/local/bin/stop-karaf-fuse
COPY restart-karaf-fuse.sh /usr/local/bin/restart-karaf-fuse
COPY head-jboss-fuse.sh /usr/local/bin/head-jboss-fus