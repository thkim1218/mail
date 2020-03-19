#Pull base image
From tomcat:latest

#Maintainer
Maintainer TH

#Copy to images tomcat path
ADD mail.war /usr/local/tomcat/webapps/
