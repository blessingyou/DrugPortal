package com.versionsystem.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ClassLoadUtil{
    private static Log log=LogFactory.getLog(ClassLoadUtil.class);
    /**
     *Thread.currentThread().getContextClassLoader().getResource("")
     */
  
    /**
     *
     *@paramclassName
     *@return
     */
    public static Class loadClass(String className) {
        try {
          return getClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
          throw new RuntimeException("class not found '"+className+"'", e);
        }
     }
    
     public static ClassLoader getClassLoader() {
    
        return ClassLoadUtil.class.getClassLoader();
     }
     /**
       *
       *@paramrelativePath
       *@return 
     *@throwsIOException
     *@throwsMalformedURLException
       */
   /*  public static InputStream getStream(String relativePath) throws MalformedURLException, IOException {
         if(!relativePath.contains("../")){
             return getClassLoader().getResourceAsStream(relativePath);
            
         }else{
             return ClassLoadUtil.getStreamByExtendResource(relativePath);
         }
      
     }*/
     /**
       *
       *@paramurl
       *@return
       *@throwsIOException
       */
     public static InputStream getStream(URL url) throws IOException{
         if(url!=null){
            
                return url.openStream();
          
            
         }else{
             return null;
         }
     }
     /**
       *
       *@paramrelativePath
       *@return
       *@throwsMalformedURLException
       *@throwsIOException
       */
     public static InputStream getStreamByExtendResource(String relativePath) throws MalformedURLException, IOException{
        return ClassLoadUtil.getStream(ClassLoadUtil.getExtendResource(relativePath));
        
        
     }
    
      /**
       *
       *@paramresource
       *@return
       */
     /*public static Properties getProperties(String resource) {
        Properties properties = new Properties();
        try {
          properties.load(getStream(resource));
        } catch (IOException e) {
          throw new RuntimeException("couldn't load properties file '"+resource+"'", e);
        }
        return properties;
     }*/
    
     public static String getAbsolutePathOfClassLoaderClassPath(){
        
        
         ClassLoadUtil.log.info(ClassLoadUtil.getClassLoader().getResource("").toString());
         return ClassLoadUtil.getClassLoader().getResource("").toString();
        
     }

   
     public static URL getExtendResource(String relativePath) throws MalformedURLException{
    
        
         //ClassLoaderUtil.log.info(Integer.valueOf(relativePath.indexOf("../"))) ;
         if(!relativePath.contains("../")){
             return ClassLoadUtil.getResource(relativePath);
            
         }
         String classPathAbsolutePath=ClassLoadUtil.getAbsolutePathOfClassLoaderClassPath();
         if(relativePath.substring(0, 1).equals("/")){
             relativePath=relativePath.substring(1);
         }
         ClassLoadUtil.log.info(Integer.valueOf(relativePath.lastIndexOf("../"))) ;
      
         String wildcardString=relativePath.substring(0,relativePath.lastIndexOf("../")+3);
        relativePath=relativePath.substring(relativePath.lastIndexOf("../")+3);
         int containSum=ClassLoadUtil.containSum(wildcardString, "../");
         classPathAbsolutePath= ClassLoadUtil.cutLastString(classPathAbsolutePath, "/", containSum);
         String resourceAbsolutePath=classPathAbsolutePath+relativePath;
        
         URL resourceAbsoluteURL=new URL(resourceAbsolutePath);
         return resourceAbsoluteURL;
     }
     /**
      *
       *@paramsource
       *@paramdest
       *@return
       */
     private static int containSum(String source,String dest){
         int containSum=0;
         int destLength=dest.length();
         while(source.contains(dest)){
             containSum=containSum+1;
             source=source.substring(destLength);
            
         }
        
        
         return containSum;
     }
     /**
       *
       *@paramsource
       *@paramdest
       *@paramnum
       *@return
       */
     private static String cutLastString(String source,String dest,int num){
         // String cutSource=null;
         for(int i=0;i<num;i++){
             source=source.substring(0, source.lastIndexOf(dest, source.length()-2)+1);
            
            
         }
        
        
        
         return source;
     }
     /**
       *
       *@paramresource
       *@return
       */
      public static URL getResource(String resource){
     
         return ClassLoadUtil.getClassLoader().getResource(resource);
     }
   

    

    /**
     *@paramargs
     *@throwsMalformedURLException
     */
    public static void main(String[] args) throws MalformedURLException {
      
            //ClassLoaderUtil.getExtendResource("../spring/dao.xml");
        //ClassLoaderUtil.getExtendResource("../../../src/log4j.properties");
        ClassLoadUtil.getExtendResource("log4j.properties");
      
        System.out.println(ClassLoadUtil.getClassLoader().getResource("log4j.properties").toString());

    }

}
