JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
CLASSPATH=.;src/manager
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	client/LocateSimpleRegistry.java \
	comm/CommModule_Client.java\
	comm/RMIMessage.java\
	example/ZipCodeClient.java\
	example/ZipCodeList.java\
	example/ZipCodeRList.java\
	example/ZipCodeRListClient.java\
	example/ZipCodeRListImpl.java\
	example/ZipCodeRListImplStub.java\
	example/ZipCodeServer.java\
	example/ZipCodeServerImpl.java\
	example/ZipCodeServerImplStub.java\
	server/RealRegistry.java\
	server/RemoteInterface.java\
	server/RemoteObjectRef.java\
	server/RMI_Server.java\
	server/SimpleRegistry.java\
	utility/OBTable.java\
	utility/RemoteException.java\

default: classes

classes: 
	$(JC) ./client/LocateSimpleRegistry.java -d ../bin/
	$(JC) ./comm/CommModule_Client.java -d ../bin/
	$(JC) ./comm/RMIMessage.java -d ../bin/
	$(JC) ./example/ZipCodeClient.java -d ../bin/
	$(JC) ./example/ZipCodeList.java -d ../bin/
	$(JC) ./example/ZipCodeRList.java -d ../bin/
	$(JC) ./example/ZipCodeRListClient.java -d ../bin/
	$(JC) ./example/ZipCodeRListImpl.java -d ../bin/
	$(JC) ./example/ZipCodeServer.java -d ../bin/
	$(JC) ./example/ZipCodeRListImplStub.java -d ../bin/
	$(JC) ./example/ZipCodeServerImpl.java -d ../bin/
	$(JC) ./example/ZipCodeServerImplStub.java -d ../bin/
	$(JC) ./server/RealRegistry.java -d ../bin/	
	$(JC) ./server/RemoteInterface.java -d ../bin/
	$(JC) ./server/RemoteObjectRef.java -d ../bin/
	$(JC) ./server/RMI_Server.java -d ../bin/
	$(JC) ./server/SimpleRegistry.java -d ../bin/
	$(JC) ./utility/OBTable.java -d ../bin/
	$(JC) ./utility/RemoteException.java -d ../bin/
clean:
	$(RM) -rf ../bin/*
