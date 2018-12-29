export APP_HOME=$(cd `dirname $0`;cd ..;pwd)
export CATALINA_HOME=$APP_HOME/tomcat
export JAVA_OPTS="-Djava.net.preferIPv4Stack=true -server -Xms1024m -Xmx4096m"
sh $CATALINA_HOME/bin/startup.sh