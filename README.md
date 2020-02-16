Application to check the user connections - status, time to the given host.
List of hosts can be configured in hosts.txt file.

Three commands run in paralel: ICMP ping, TCP ping, Traceroute.
Commands can be configured in application.properties file.

Before run please install java 11. Works on Ubuntu.
Runs on Windows as well (if configure right console commands) but due to different console command results right now application doesn't properly handles them.

To run application execute following commands:

git clone https://github.com/gerasimenkoeugene/PingApplication.git

cd PingApplication

mvn clean package

java -jar target/application.jar
