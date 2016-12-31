# Installing Java
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update
sudo apt-get install -y oracle-java8-installer

# Downloading Selenium 3.0.1
wget https://goo.gl/Lyo36k

# Downloading Cmd Line Browser
sudo apt-get install w3m

# Downloading Junit
# Downloading TestNG

# Docker
sudo apt-key adv --keyserver hkp://ha.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D

echo "deb https://apt.dockerproject.org/repo ubuntu-xenial main" | sudo tee /etc/apt/sources.list.d/docker.list

# Docker Pre-requisites
sudo apt-get -y install linux-image-extra-$(uname -r) linux-image-extra-virtual

# Docker installation
sudo apt-get -y install docker-engine

sudo docker images

sudo docker pull selenium/hub:3.0.1-carbon
sudo docker pull selenium/node-chrome:3.0.1-carbon
sudo docker pull selenium/node-firefox:3.0.1-carbon

sudo docker run -d --name selenium-hub -p 4444:4444 selenium/hub:3.0.1-carbon
sudo docker run -d --name=fx --link selenium-hub:hub -v /tmp:/tmp selenium/node-firefox:3.0.1-carbon
sudo docker run -d --name=fx0 --link selenium-hub:hub -v /tmp:/tmp selenium/node-firefox:3.0.1-carbon
sudo docker run -d --name=fx1 --link selenium-hub:hub -v /tmp:/tmp selenium/node-firefox:3.0.1-carbon
sudo docker run -d --name=fx2 --link selenium-hub:hub -v /tmp:/tmp selenium/node-firefox:3.0.1-carbon
sudo docker run -d --name=ch --link selenium-hub:hub -v /tmp:/tmp selenium/node-chrome:3.0.1-carbon

sudo docker run --rm --name=fx --link selenium-hub:hub -v /e2e/uploads:/e2e/uploads selenium/node-firefox:3.0.1-carbon
