package example;

import java.io.Serializable;

import server.RemoteInterface;

public class ZipCodeList implements Serializable 
{
    String city;
    String ZipCode;
    ZipCodeList next;

    public ZipCodeList(String c, String z, ZipCodeList n)
    {
	city=c;
	ZipCode=z;
	next=n;
    }
    public void print(){
        System.out.println("city "+city+" ZipCode "+ZipCode);
    }
    public ZipCodeList getNext(){
        return next;
    }
}
