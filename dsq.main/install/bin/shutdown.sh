export APP_HOME=$(cd `dirname $0`;cd ..;pwd)
export CATALINA_HOME=$APP_HOME/tomcat
sh $CATALINA_HOME/bin/shutdown.sh