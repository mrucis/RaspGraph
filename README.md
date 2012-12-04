RaspGraph - RaspberryPi-PolarGraph
======================================

RaspGraph is simple console based Java application to run [Polargraph](http://www.polargraph.co.uk/) query code on [RaspberryPi](http://www.raspberrypi.org/). RaspGraph is made using Eclipse IDE.
In order to use RaspGraph it is necessary to make query file using [polargraph software](http://code.google.com/p/polargraph/) 

---Prepare RaspberryPi

RaspGraph is tested using Soft-float Debian “wheezy” image (2012-08-08) available in RaspberryPi [downloads](http://www.raspberrypi.org/downloads) section

0. Update and upgrade RasspberyPi

```
	$ sudo apt-get update  
	$ sudo apt-get upgrade
```
  
1. Install Arduino IDE 

```
	$ sudo apt-get install arduino
```	


--- Running pre-build version

0. Create new folder on RassperyPi device - RaspGraph (name doesn't mater)
1. Copy **dist** folders content to RaspGraphPi folder
2. Copy query file to RaspGraph folder
3. Open terminal and navigate to RaspGraphPi
4. execute run.sh file

```
	$ ./run.sh
```
5. If multiple serial ports are presented enter number according to polargraphs 
6. Enter number according  to query file name

Sit back and relax


--- Making version from source

0. Import RaspGraph as existing source in Eclipse
1. Use build.xml file to compile source and build .jar file


