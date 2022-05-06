#!/bin/bash
function waiting_for_jboss {
  while [[ $(sudo netstat -ltnp | grep 9990 | wc -l) -lt 1 ]]
  do
    sleep 5s
  done
}

function download_files() {
  wget -L https://s3.eu-west-3.amazonaws.com/jboss-fuse-eap-7.9.0/fuse-eap-installer-7.9.0.jar -O /root/install/fuse-eap-installer-7.9.0.jar
  wget -L https://s3.eu-west-3.amazonaws.com/jboss-fuse-eap-7.9.0/jboss-eap-7.4.0-installer.jar -O /root/install/jboss-eap-7.4.0-installer.jar
  wget -L https://s3.eu-west-3.amazonaws.com/jboss-fuse-eap-7.9.0/jboss-eap-7.4.1-patch.zip -O /root/install/jboss-eap-7.4.1-patch.zip
}

if [[ ! -e $EAP_HOME/bin && -e /root/install ]]; then
  cd $EAP_HOME
  echo ">>> Downloading JBoss installation files ..."
  download_files
  echo ">>> Installing JBoss EAP 7.4.0 ..."
  java -jar /root/install/jboss-eap-7.4.0-installer.jar /root/install/auto.xml -variablefile /root/install/auto.xml.variables
  if [[ "0" != "$?" ]]; then
    echo "### Error installing JBoss EAP 7.4.0"
    exit 1
  fi
  echo ">>> Patching JBoss EAP 7.4.1 and enabling CORS ..."
  $EAP_HOME/bin/standalone.sh &
  waiting_for_jboss
  $EAP_HOME/bin/jboss-cli.sh -c  --file=/patch-and-set-cors.cli
  echo ">>> Installing JBoss Fuse 7.9.0 ..."
  java -jar /root/install/fuse-eap-installer-7.9.0.jar
  if [[ "0" != "$?" ]]; then
    echo "### Error installing JBoss Fuse 7.9.0"
    exit 1
  fi
  echo ">>> JBoss Fuse 7.9.0 has been installed on EAP 7.4.0"
  rm -Rf /root/install
  $EAP_HOME/bin/standalone.sh --debug
  waiting_for_jboss
fi
