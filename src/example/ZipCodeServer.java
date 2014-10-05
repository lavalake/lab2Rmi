package example;

import server.RemoteInterface;

public interface ZipCodeServer extends RemoteInterface // extends YourRemote or whatever
{
    public void initialise(ZipCodeList newlist);
    public String find(String city);
    public ZipCodeList findAll();
    public void printAll();
}

