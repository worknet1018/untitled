package org.text;

public class HelloProxy implements Hello {

    private Hello test;
    public void say(String name) {

        before();
        test.say(name);
        after();
    }

    public HelloProxy() {
       test = new HelloImpl();
    }

    private void before(){
        System.out.println("say Before!");
    }

    private void after(){
        System.out.println("Say After!");
    }

    public static void main(String[] args) {
        Hello testProxy = new HelloProxy();
        testProxy.say("Jack");
    }
}
