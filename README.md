# springboot-rest-api-ankit
 simple springboot rest api

# setup jenkins
 download and setup jenkins on port 9090

# setup tomcat server
 download and setup tomcat on port 5050

# create CI/CD pipeline
 create CI/CD pipeline in jenkins using this git repo and deploy the app on tomcat server. Use cron job expression as * * * * * for every min check to this repo for  
 change. If there is any change then it will pull the latest and build using mvn clean compile package. 

 # steps
C:\Users\ankit\.jenkins
java -jar jenkins.war --httpPort=9090
localhost:9090
admin/admin
This may also be found at: C:\Users\ankit\.jenkins\secrets\initialAdminPassword

tomcat
port="5050"
C:\Program Files\Apache Software Foundation\Tomcat 10.1\conf
under <tomcat-users>
	<role rolename="manager-gui" />
	<role rolename="manager-script" />
<user username="admin" password="admin" roles="manager-gui,manager-script" />

http://localhost:5050/manager/text/list

http://localhost:5050/springboot-rest-api-ankit/hello-world
