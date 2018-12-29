1. tomcat版本为8.0.35
2. 删除了部分不需要的文件及文件夹。
3. 修改了conf/catalina.properties，设置shared.loader指向配置目录。
4. 修改了conf/server.xml，指定Connector的protocol和连接线程数大小以及URIEncoding。
5. 修改了bin/catalina.sh指定用cronolog切割日志。