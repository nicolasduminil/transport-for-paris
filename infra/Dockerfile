FROM openjdk:11.0.7-jdk-slim-buster
LABEL MAINTAINER="Nicolas DUMINIL nicolas.duminil@simplex-software.fr"
ENV EAP_HOME=/usr/lib/jboss-fuse \
    JBOSS_USER=root \
    PATH="${EAP_HOME}/bin:/tmp:${PATH}" \
    JAVA_OPTS="-Djava.net.preferIPv4Stack=true -Djboss.bind.address=0.0.0.0 -Djboss.bind.address.management=0.0.0.0"
USER root
#
# Installing JBoss Fuse pre-requisites
#
RUN apt-get update \
    && apt-get -y upgrade \
    && apt-get -y install apt-utils \
    && apt-get -y install software-properties-common build-essential \
    #&& apt-get -y install wget curl htop git maven vim net-tools \
    && apt-get -y install sudo wget curl htop vim net-tools \
    && apt-get -y autoremove \
    && apt-get -y clean \
    && rm -rf /var/lib/apt/lists/* \
    && echo "export EAP_HOME=/usr/lib/jboss-fuse" >> /root/.bashrc \
    && echo "export JBOSS_USER=root" >> /root/.bashrc \
    && mkdir -p $EAP_HOME \
    && mkdir -p /root/install \
    && ln -s /lib/lsb/init-functions /etc/init.d/functions
#
# Copy files
#
COPY auto.xml /root/install/auto.xml
COPY auto.xml.variables /root/install/auto.xml.variables
COPY docker-start-jboss-fuse.sh /docker-start-jboss-fuse.sh
#
# Start JBoss Fuse
#
WORKDIR $EAP_HOME
CMD /docker-start-jboss-fuse.sh -daemon
EXPOSE 8080 8181 8101 9990 9443 9999 4447 8443 4712 4713