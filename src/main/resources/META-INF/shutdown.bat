cls
setlocal
set ECLIPSE_HOME=c:\eclipse
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_77
set JDK_HOME=%JAVA_HOME%
set CATALINA_BASE=%ECLIPSE_HOME%\apache-tomcat-6.0.36
set CATALINA_HOME=%CATALINA_BASE%
set JBOSS_HOME=c:\wildfly-10.0.0.Final
set path=%M3_HOME%\bin;%JAVA_HOME%\bin;%CATALINA_HOME%\bin;%JBOSS_HOME%\bin;c:\windows\system32
call jboss-cli.bat -c --command=:shutdown
endlocal
