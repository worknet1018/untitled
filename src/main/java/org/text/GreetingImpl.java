package org.text;

public class GreetingImpl implements Greeting{
    public void sayHello(String name) {
        System.out.println("Hello "+name);
    }
}
