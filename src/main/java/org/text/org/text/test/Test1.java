package org.text.org.text.test;


import org.text.util.StringUtil;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Test1 {
    public static void test(String packageName){
        try{

            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".","/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                System.out.println("url===========>"+url.toString());
                if (url != null){
                    String protocol = url.getProtocol();
                    if (protocol.equals("file")){
                        System.out.println("url1:"+url.getPath());
                        String packagePath = url.getPath().replaceAll("%20","aaa");
                        System.out.println("packagepath:"+ packagePath);
                        addClass(packagePath,packageName);
                    }else if (protocol.equals("jar")){
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if (jarURLConnection != null){
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null){
                                Enumeration<JarEntry> jarEntries =jarFile.entries();
                                while (jarEntries.hasMoreElements()){
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.endsWith(".class")){
                                        String className = jarEntryName.substring(0,jarEntryName.lastIndexOf(".")).replaceAll("/",".");
                                        System.out.println("jarclassName" + className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }catch (Exception e){

        }
    }

    public static void main(String[] args) {
        test("org");
    }
    public static ClassLoader getClassLoader(){

        return Thread.currentThread().getContextClassLoader();
    }
    private static void addClass( String packagePath, String packageName) {
        System.out.println("(" +packagePath + packageName + ")");
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });
        for (File file :files){
            System.out.println("f:" +file.getName());
        }

        for (File file : files) {
            String fileName = file.getName();
            System.out.println("fileName===>" +fileName);
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));

                if (StringUtil.isNotEmpty(packageName)) {
                    className = packageName + "." + className;
                    System.out.println("className===>" +className);
                }

            } else {
                String subPackagePath = fileName;
                if (StringUtil.isNotEmpty(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                    System.out.println("subpackagepath===>" +subPackagePath);
                }
                String subPackageName = fileName;
                if (StringUtil.isNotEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                    System.out.println("subpackagename===>" +subPackageName);
                }
                addClass(subPackagePath, subPackageName);
            }
        }
    }
}
