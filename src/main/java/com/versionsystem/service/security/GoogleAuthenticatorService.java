package com.versionsystem.service.security;

import org.springframework.stereotype.Service;

import com.versionsystem.common.BarcodeBuilder;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;


@Service
public class GoogleAuthenticatorService {
	
	public String registerAuthenticator(String user){
		
		GoogleAuthenticator gAuth = new GoogleAuthenticator();
		final GoogleAuthenticatorKey key = gAuth.createCredentials(user);
		System.out.println("key:"+key.getKey());
		return key.getKey();
	}
	
	public String generateQrcodeForDevice(String user,String fileName){
		String key=this.registerAuthenticator(user);
		String contents="otpauth://totp/Versionyong:"+user+"?secret="+key+"&issuer=Versionyong";
		if(BarcodeBuilder.generateQrcode(fileName, contents, 300, 300))
			return fileName;
		else
			return null;
	}
	
	public boolean validateAuth(String user,int code){
		GoogleAuthenticator gAuth = new GoogleAuthenticator();
		boolean isCodeValid = gAuth.authorizeUser(user, code);
		return isCodeValid;
	}

}
