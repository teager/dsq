set APP_HOME=%cd%\..
set CATALINA_HOME=%APP_HOME%\tomcat
set JAVA_OPTS=-Djava.net.preferIPv4Stack=true -Xms128m -Xmx1024m
call %CATALINA_HOME%\bin\startup.bat