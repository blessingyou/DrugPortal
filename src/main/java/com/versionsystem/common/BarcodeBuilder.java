package com.versionsystem.common;

import java.io.File;
import java.util.EnumMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class BarcodeBuilder {
	  private static final BarcodeFormat DEFAULT_BARCODE_FORMAT = BarcodeFormat.QR_CODE;
	  private static final String DEFAULT_IMAGE_FORMAT = "PNG";
	  private static final String DEFAULT_OUTPUT_FILE = "out";
	  private static final int DEFAULT_WIDTH = 300;
	  private static final int DEFAULT_HEIGHT = 300;
	  
	  
	  public static boolean generateQrcode(String fileName,String contents,int width,int height) {
		  try{
		  MultiFormatWriter barcodeWriter = new MultiFormatWriter();
		  BitMatrix matrix = barcodeWriter.encode(contents, DEFAULT_BARCODE_FORMAT, width, height);
		  MatrixToImageWriter.writeToFile(matrix, DEFAULT_IMAGE_FORMAT, new File(fileName));
		  }catch(Exception e){
			  e.printStackTrace();
			  return false;
		  }
		  return true;
	  }
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		 if (args.length == 0) {
		      printUsage();
		      return;
		    }

		    BarcodeFormat barcodeFormat = DEFAULT_BARCODE_FORMAT;
		    String imageFormat = DEFAULT_IMAGE_FORMAT;
		    String outFileString = DEFAULT_OUTPUT_FILE;
		    String fileName="test";
		    int width = DEFAULT_WIDTH;
		    int height = DEFAULT_HEIGHT;
		    for (String arg : args) {
		      if (arg.startsWith("--barcode_format")) {
		        barcodeFormat = BarcodeFormat.valueOf(arg.split("=")[1]);
		      } else if (arg.startsWith("--image_format")) {
		        imageFormat = arg.split("=")[1];
		      } else if (arg.startsWith("--output")) {
		        outFileString = arg.split("=")[1];
		      } else if (arg.startsWith("--width")) {
		        width = Integer.parseInt(arg.split("=")[1]);
		      } else if (arg.startsWith("--height")) {
		        height = Integer.parseInt(arg.split("=")[1]);
		      }
		      else if (arg.startsWith("--filename")) {
			        fileName = arg.split("=")[1];
			      }
		    }
		    
		    if (DEFAULT_OUTPUT_FILE.equals(outFileString)) {
		      outFileString += '.' + imageFormat.toLowerCase(Locale.ENGLISH);
		      System.out.println(outFileString);
		    }
		    outFileString+="/"+fileName+"."+imageFormat;    
		    String contents = "";
		    
		    for (String arg : args) {
		      if (!arg.startsWith("--")) {
		        contents = arg;	
		        break;
		      }
		    }
		    System.out.println(contents+" length:"+contents.length());
		    if (contents == null) {
		      printUsage();
		      return;
		    }
		    
		 //   Hashtable<EncodeHintType, Integer> hints = new Hashtable<EncodeHintType, Integer>();  
         //   hints.put(EncodeHintType.MARGIN, 1); //设置二维码空白边框的大小 1-4，1是最小 4是默认的国标  
		    MultiFormatWriter barcodeWriter = new MultiFormatWriter();
		    BitMatrix matrix = barcodeWriter.encode(contents, barcodeFormat, width, height);
		    MatrixToImageWriter.writeToFile(matrix, imageFormat, new File(outFileString));

	}
	
	private static void printUsage() {
	    System.err.println("Encodes barcode images using the ZXing library\n");
	    System.err.println("usage: CommandLineEncoder [ options ] content_to_encode");
	    System.err.println("  --barcode_format=format: Format to encode, from BarcodeFormat class. " +
	                           "Not all formats are supported. Defaults to QR_CODE.");
	    System.err.println("  --image_format=format: image output format, such as PNG, JPG, GIF. Defaults to PNG");
	    System.err.println("  --output=folder name: File to write to. Defaults to folder/out.png");
	    System.err.println("  --width=pixels: Image width. Defaults to 300");
	    System.err.println("  --height=pixels: Image height. Defaults to 300");
	    System.err.println("  --filename= Defaults to test");
	  }

}