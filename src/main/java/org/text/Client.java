package org.text;

public class Client {
    public static void main(String[] args) {
        //静态代理
        //Greeting greetingProxy = new GreetingProxy(new GreetingImpl());
        //greetingProxy.sayHello("Jack");
        //JDK 动态代理
        //Greeting greeting = new DynamicProxy(new GreetingImpl()).getProxy();
        //greeting.sayHello("Jack");

        //CGLib 动态代理
        Greeting greeting = CGLibProxy.getInstance().getProxy(GreetingImpl.class);
        greeting.sayHello("Jack");
    }
}
