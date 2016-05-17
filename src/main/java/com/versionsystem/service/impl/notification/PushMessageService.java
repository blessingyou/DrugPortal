package com.versionsystem.service.impl.notification;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.versionsystem.common.ApplicationParas;
import com.versionsystem.persistence.model.UserId;
import com.versionsystem.service.ConfigService;
import com.versionsystem.service.repo.UserRepository;


@Service
public class PushMessageService {
	public static String REQUEST_URL = "https://android.googleapis.com/gcm/send";
    private static String GCM_ID = "APA91bHD62eUCZwr-VN8V52vGhsMuChqVH6IL3kZbKsQZztDE0tQvn9tLFGwPmRHxOpfs0360LLANDJfI0FKWt7i0n7UvGvg0eSKOxFwhpTX0PK90mG5yimekrTfrQDj9QOvVZTy7n_wUEbLsft8jXZsfLz-UruBAw";
    private Logger logger = LoggerFactory.getLogger(PushMessageService.class); 
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ConfigService configService;
	


		//static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		static final String AB = "0123456789";
		static Random rnd = new Random();
		
		String randomString( int len ){
		   StringBuilder sb = new StringBuilder( len );
		   for( int i = 0; i < len; i++ ) 
		      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		   return sb.toString();
		}


		@Transactional
    	public String genSecurityCode(String userId){
    		String apiKey=configService.getProperty("app.apiKey");
    		UserId user=this.userRepository.findByUserId(userId);
    		
    		String securityCode=this.randomString(6);
    		Date genTime=new Date();
    		//SimpleDateFormat df=new SimpleDateFormat(configService.getProperty(ApplicationParas.APP_DATETIMEFORMAT));
    		//String genTimeString=df.format(genTime);
    		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
    	    formparams.add(new BasicNameValuePair("registration_id", user.getMobileDeviceId()));
    	    formparams.add(new BasicNameValuePair("data.message", securityCode));
    	    formparams.add(new BasicNameValuePair("data.msgcnt", "1"));
    	    formparams.add(new BasicNameValuePair("data.sound", "beep"));
    	   
    	    HttpClient httpclient = HttpClientBuilder.create().build();
    	    HttpPost httpPost = new HttpPost(REQUEST_URL);
    	    HttpResponse response;

    	    httpPost.setHeader("Authorization",
    	            "key="+apiKey);
    	    httpPost.setHeader("Content-Type",
    	            "application/x-www-form-urlencoded;charset=UTF-8");

    	    try {
    	        httpPost.setEntity(new UrlEncodedFormEntity(formparams, "utf-8"));
    	        httpclient.execute(httpPost);

    	        //Get the response
    	        response = httpclient.execute(httpPost);

    	        int responseCode = response.getStatusLine().getStatusCode();
    	         String responseText = Integer.toString(responseCode);      
    	         logger.info("HTTP POST : " + responseText);

    	         /*Checking response */
    	         if(response!=null){
    	             InputStream in = response.getEntity().getContent(); //Get the data in the entity
    	             logger.info("HTTP POST : " + in.toString());
    	         }
    	        //Print result
    	        logger.info(response.toString());
    	        
    	        user.setSecuritycode(securityCode);
    	        user.setSecuritycodeGenTime(new Timestamp(genTime.getTime()));

    	    } catch (UnsupportedEncodingException e) {
    	        // TODO Auto-generated catch block
    	        e.printStackTrace();
    	        logger.info(e.getMessage());
    	        return "ERROR:"+e.getMessage();
    	    } catch (ClientProtocolException e) {
    	        // TODO Auto-generated catch block
    	        e.printStackTrace();
    	        logger.info(e.getMessage());
    	        return "ERROR:"+e.getMessage();
    	    } catch (IOException e) {
    	        // TODO Auto-generated catch block
    	        e.printStackTrace();
    	        logger.info(e.getMessage());
    	        return "ERROR:"+e.getMessage();
    	    }
    		return securityCode;
    	}
    	
    	public static void main(String[] args) {
		
		
	   List<NameValuePair> formparams = new ArrayList<NameValuePair>();
	    formparams.add(new BasicNameValuePair("registration_id", GCM_ID));
	    formparams.add(new BasicNameValuePair("data.message", "testing"));
	    formparams.add(new BasicNameValuePair("data.msgcnt", "1"));
	    formparams.add(new BasicNameValuePair("data.sound", "beep"));
	   
	    HttpClient httpclient = HttpClientBuilder.create().build();
	    HttpPost httpPost = new HttpPost(REQUEST_URL);
	    HttpResponse response;

	    httpPost.setHeader("Authorization",
	            "key=AIzaSyC6pqKX90WxYSkZHODuXeUMO6_iEgin0e8 ");
	    httpPost.setHeader("Content-Type",
	            "application/x-www-form-urlencoded;charset=UTF-8");

	    try {
	        httpPost.setEntity(new UrlEncodedFormEntity(formparams, "utf-8"));
	        httpclient.execute(httpPost);

	        //Get the response
	        response = httpclient.execute(httpPost);

	        int responseCode = response.getStatusLine().getStatusCode();
	         String responseText = Integer.toString(responseCode);      
	         System.out.println("HTTP POST : " + responseText);

	         /*Checking response */
	         if(response!=null){
	             InputStream in = response.getEntity().getContent(); //Get the data in the entity
	             System.out.println("HTTP POST : " + in.toString());
	         }
	        //Print result
	        System.out.println(response.toString());

	    } catch (UnsupportedEncodingException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }

        
	}

}