package com.versionsystem.web.controller.android;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.springframework.core.convert.converter.ConvertingComparator;

import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;
import magick.PixelPacket;

public class ImageService {
	public java.awt.Image getPngImage(String file){
		try {
			final float FACTOR  = 1f;
			BufferedImage img = ImageIO.read(new File(file));
			int scaleX = (int) (img.getWidth() * FACTOR);
			int scaleY = (int) (img.getHeight() * FACTOR);
			Image image = img.getScaledInstance(scaleX, scaleY, Image.SCALE_SMOOTH);
			//BufferedImage buffered = new BufferedImage(scaleX, scaleY,);
			//buffered.getGraphics().drawImage(image, 0, 0 , null);
			return image;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public java.awt.Image getImage(String filename) throws Exception{
		
		java.awt.Image image=null;
	    try {  
	    	
	    	System.out.println("signature file:"+filename);
	    	 File pathToFile = new File(filename);
	    	 image = ImageIO.read(pathToFile);
	        
	       
	    }catch(Exception e) {  
	        e.printStackTrace();  	    	
	    	System.out.println(e.getMessage());
	    }
	    return image;
	    
	}
	public static void pngToJpg(String inputfileName,String outputfile){
		java.lang.Runtime rt = java.lang.Runtime.getRuntime();
		try {
			String command=" convert "+inputfileName+" -background white -flatten "+outputfile;
			//+";convert "+outputfile+" -trim "+outputfile
			java.lang.Process proc = rt.exec(command);
			StreamConsumer errorConsumer = new StreamConsumer (proc.getErrorStream(), "error");            
	             
	        // any output?
	        StreamConsumer outputConsumer = new StreamConsumer (proc.getInputStream(), "output");
	                 
	            // kick them off
	            errorConsumer.start ();
	            outputConsumer.start ();
	                                     
	            // any error???
	            int exitVal = proc.waitFor ();
	            System.out.println ("ExitValue: " + exitVal);        			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
	      
	}
	
	public static void pdfToPng(String inFile,String outFile){
		
		try{
			System.out.println(inFile+"-->"+outFile);
	      ImageInfo info = new ImageInfo(inFile);	  
	      info.setDensity("150");      
	      MagickImage mainImage = new MagickImage(info); 
	      mainImage.setQuality(100);
	      
	      mainImage.setFileName(outFile);
	      //mainImage.compositeImage(arg0, arg1, arg2, arg3)
	      mainImage.setBackgroundColor(PixelPacket.queryColorDatabase("white"));
	      mainImage.writeImage(info);
		}catch(Exception e){
				e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ImageInfo origInfo;
		try {
			//origInfo = new ImageInfo("/home/yonghong/Documents/images/20161201154022.jpg");
			//MagickImage image = new MagickImage(origInfo); //load image
		
			//boolean flag = image.transparentImage(PixelPacket.queryColorDatabase("white"),65535);
			//System.out.println(flag);
			//image.setFileName(absNewFilePath); //give new location
			//image.writeImage(origInfo); //save
			/*
			
			String fileName = "/home/yonghong/Documents/images/voucher1.pdf";
		      ImageInfo info = new ImageInfo(fileName);
		      
		  
		      info.setDensity("150");
		      
		      MagickImage mainImage = new MagickImage(info);
		      mainImage.setQuality(100);
		      
		      String outFileName = "/home/yonghong/Documents/images/voucher1.png";
		      mainImage.setFileName(outFileName);
		      mainImage.writeImage(info);
		      
		      */
		      String source1 = "/home/yonghong/Documents/images/voucher1.png";
		      ImageInfo image1 = new ImageInfo(source1);	
   		  
		      MagickImage mainImage1 = new MagickImage(image1);
		      String source2 = "/home/yonghong/Documents/images/signature1.png";
		      ImageInfo image2 = new ImageInfo(source1);	      		  
		      MagickImage mainImage2 = new MagickImage(image2);
		      
		      //mainImage1.compositeImage(0, mainImage2, 100, 200);
		      ImageService.pngToJpg("/home/yonghong/Documents/images/testpng.png", "/home/yonghong/Documents/images/testjpg2.jpg");
		      
		      
		      
		} catch (MagickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //load image info
		
	}

}
