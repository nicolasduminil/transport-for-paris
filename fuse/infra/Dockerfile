FROM openjdk:11.0.7-jdk-slim-buster
LABEL MAINTAINER="Nicolas DUMINIL nicolas.duminil@simplex-software.fr"
ENV EAP_HOME=/usr/lib/jboss-fuse \
    JBOSS_USER=root \
    PATH="${EAP_HOME}/bin:/tmp:${PATH}"
USER root
#
# Installing JBoss Fuse pre-requisites
#
RUN echo 'debconf debconf/frontend select Noninteractive' | debconf-set-selections \
    && apt-get update \
    && apt-get -y upgrade \
    && apt-get -y install apt-utils \
    && apt-get -y install software-properties-common build-essential \
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
COPY patch-and-set-cors.cli /patch-and-set-cors.cli
COPY docker-start-jboss-fuse.sh /docker-start-jboss-fuse.sh
COPY ojdbc8.jdbc /root/install/ojdbc8.jar
#
# Start JBoss Fuse
#
WORKDIR $EAP_HOME
CMD /docker-start-jboss-fuse.sh -daemon && tail -f /dev/null
EXPOSE 18080 18181 18101 19990 19443 19999 14447 18443 14712 14713