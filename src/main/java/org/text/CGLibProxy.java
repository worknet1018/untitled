package org.text;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibProxy implements MethodInterceptor {

    private static  CGLibProxy instance =new CGLibProxy();

    private CGLibProxy() {

    }
    public static CGLibProxy getInstance(){
        return instance;
    }

    public <T> T getProxy(Class<T> cls){

        return (T) Enhancer.create(cls,this);
    }


    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        before();
        Object result = proxy.invokeSuper(obj,args);
        after();
        return result;
    }

    private void after() {
        System.out.println("CGLibProxy after");
    }

    private void before() {
        System.out.println("CGLibProxy before");
    }

    public static void main(String[] args) {

        Hello helloProxy = CGLibProxy.getInstance().getProxy(HelloImpl.class);
        helloProxy.say("Jick");



    }
}
