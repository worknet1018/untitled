package org.text;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy  implements InvocationHandler {
    
    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result =method.invoke(target,args);
        after();
        return result;
    }


    private void after() {
        System.out.println("Dynamic After");
    }

    private void before() {
        System.out.println("Dynamic Before");
    }

    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this
        );
    }
    public static void main(String[] args) {
        //Hello hello= new HelloImpl();
        /**
        DynamicProxy dynamicProxy = new DynamicProxy(hello);
        Hello helloProxy =(Hello) Proxy.newProxyInstance(
                hello.getClass().getClassLoader(),
                hello.getClass().getInterfaces(),
                dynamicProxy);
         */
        DynamicProxy dynamicProxy = new DynamicProxy(new HelloImpl());
        Hello helloProxy = dynamicProxy.getProxy();
        helloProxy.say("Jeck");
    }
}
