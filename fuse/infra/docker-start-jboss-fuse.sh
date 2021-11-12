#!/bin/bash
function waiting_for_jboss {
  while [[ $(sudo netstat -ltnp | grep 9990 | wc -l) -lt 1 ]]
  do
    sleep 5s
  done
}

function download_files() {
  wget -L https://bucketname-12215.s3.eu-central-1.amazonaws.com/fuse-eap-installer-7.9.0.jar -O /root/install/fuse-eap-installer-7.9.0.jar
  wget -L https://bucketname-12215.s3.eu-central-1.amazonaws.com/jboss-eap-7.4.0-installer.jar -O /root/install/jboss-eap-7.4.0-installer.jar
  wget -L https://bucketname-12215.s3.eu-central-1.amazonaws.com/jboss-eap-7.4.1-patch.zip -O /root/install/jboss-eap-7.4.1-patch.zip
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
  echo ">>> Patching JBoss EAP 7.4.1 ..."
  echo "connect
  patch apply /root/install/jboss-eap-7.4.1-patch.zip
  shutdown --restart=false" > patch.cli
  $EAP_HOME/bin/standalone.sh &
  waiting_for_jboss
  $EAP_HOME/bin/jboss-cli.sh --file=patch.cli
  rm -rf patch.cli
  echo ">>> Installing JBoss Fuse 7.9.0 ..."
  java -jar /root/install/fuse-eap-installer-7.9.0.jar
  if [[ "0" != "$?" ]]; then
    echo "### Error installing JBoss Fuse 7.9.0"
    exit 1
  fi
  echo ">>> JBoss Fuse 7.9.0 has been installed on EAP 7.4.0"
  rm -Rf /root/install
  echo ">>> Restart JBoss EAP Fuse Integration Server ..."
  #nohup $EAP_HOME/bin/standalone.sh > $EAP_HOME/standalone/log/server.log &
  #$EAP_HOME/bin/jboss-cli.sh --connect command=:stop
  $EAP_HOME/bin/standalone.sh
  waiting_for_jboss
fi
