package com.versionsystem.web.controller.android;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;
import magick.PixelPacket;

public class ImageService {
	
	public java.awt.Image getImage(String filename) throws Exception{
		
		java.awt.Image image=null;
	    try {  
	    	
	    	
	    	 File pathToFile = new File(filename);
	    	 image = ImageIO.read(pathToFile);
	        
	       
	    }catch(Exception e) {  
	        //e.printStackTrace();  
	    	System.out.println(e.getMessage());
	    }
	    return image;
	    
	}
	
	public static void pdfToPng(String inFile,String outFile){
		
		try{
			System.out.println(inFile+"-->"+outFile);
	      ImageInfo info = new ImageInfo(inFile);	  
	      info.setDensity("150");      
	      MagickImage mainImage = new MagickImage(info); 
	      mainImage.setQuality(100);
	      
	      mainImage.setFileName(outFile);
	      mainImage.writeImage(info);
		}catch(Exception e){
				e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ImageInfo origInfo;
		try {
			origInfo = new ImageInfo("/home/yonghong/Documents/images/signature.png");
			MagickImage image = new MagickImage(origInfo); //load image
		
			boolean flag = image.transparentImage(PixelPacket.queryColorDatabase("white"),65535);
			System.out.println(flag);
			//image.setFileName(absNewFilePath); //give new location
			image.writeImage(origInfo); //save
			
			
			String fileName = "/home/yonghong/Documents/images/voucher1.pdf";
		      ImageInfo info = new ImageInfo(fileName);
		      
		  
		      info.setDensity("150");
		      
		      MagickImage mainImage = new MagickImage(info);
		      mainImage.setQuality(100);
		      
		      String outFileName = "/home/yonghong/Documents/images/voucher1.png";
		      mainImage.setFileName(outFileName);
		      mainImage.writeImage(info);
		      
		      
		} catch (MagickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //load image info
		
	}

}
