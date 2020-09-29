package org.text;

public class GreetingProxy implements Greeting{

    private Greeting greetingImpl;

    public GreetingProxy(Greeting greetingImpl) {
        this.greetingImpl = greetingImpl;
    }

    public void sayHello(String name) {

        before();
        greetingImpl.sayHello(name);
        after();
    }

    private void after() {
        System.out.println("after");
    }

    private void before() {
        System.out.println("before");
    }
}
