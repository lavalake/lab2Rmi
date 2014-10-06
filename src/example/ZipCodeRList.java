package example;

import server.RemoteInterface;

public interface ZipCodeRList extends RemoteInterface // extends YourRemote or whatever
{
    public String find(String city);
    public ZipCodeRList add(String city, String zipcode);
    public ZipCodeRList next();   
}
