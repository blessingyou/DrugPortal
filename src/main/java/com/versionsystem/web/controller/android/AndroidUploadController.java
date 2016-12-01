package com.versionsystem.web.controller.android;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.pdf.PdfWriter;
import com.versionsystem.common.ServiceMd5;
import com.versionsystem.mobile.VoucherItem;
import com.versionsystem.mobile.VoucherVO;
import com.versionsystem.persistence.model.UserId;
import com.versionsystem.service.impl.UserService;
import com.versionsystem.service.repo.UserRepository;
import com.versionsystem.web.model.user.UserUIForMobile;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import sun.misc.BASE64Decoder;

@RestController
@RequestMapping(value="/android")
public class AndroidUploadController {
	
	@Autowired
	private UserRepository userRepository;
	
//	private String IP;
	
	@RequestMapping(value = "/sign", method = RequestMethod.POST)
	@ResponseBody
	public String saveSign(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		//@PathVariable String userName, 
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			request.setCharacterEncoding("utf-8");  
	        response.setCharacterEncoding("utf-8"); 
	        String url = request.getRequestURL().toString();
	        String[] urls = url.split("/");
			String IP = urls[2];
			
			String proPath = request.getSession().getServletContext().getRealPath("/");  
			String proName = request.getContextPath();
			String barcode = request.getParameter("user");
			String sign = request.getParameter("sign");
			String nativePic = request.getParameter("nativepic");
			String takePic = request.getParameter("takepic");
			
			String uploadPath = proPath + "resources/images/" + barcode + "/";
			
			if(sign != null) {
				uploadPath = proPath + "resources/images/sign/" + barcode + "/";
				DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");  
				String fileName = df.format(new Date()) + ".png";
				
				fileUpload(sign, uploadPath, fileName);
				map.put("sign", "http://"+ IP + proName +"/resources/images/sign/" + barcode + "/" + fileName);
			}
			
			map.put("nativepic", "http://"+ IP + proName + "/resources/androidPic/voucher.png");
			if(nativePic != null) {
				DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");  
	    		String fileName = df.format(new Date()) + ".png";
				fileUpload(nativePic, uploadPath, fileName);
	            
				map.put("nativepic", "http://"+ IP + proName +"/resources/images/" + barcode + "/" + fileName);
			}
			if(takePic != null) {
				DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");  
	    		String fileName = df.format(new Date()) + ".png";
				fileUpload(takePic, uploadPath, fileName);
				
				map.put("takepic", "http://"+ IP + proName +"/resources/images/" + barcode + "/" + fileName);
			}
		
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println(map.get("nativepic"));
		return (String)map.get("nativepic");
			
	}
	
	@RequestMapping(value="/other/**", method = RequestMethod.POST)
	@ResponseBody
	public String savePic(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		//@PathVariable String barcode,
		Map<String,Object> map = new HashMap<String,Object>();
		UserUIForMobile user = new UserUIForMobile();
		String url = request.getRequestURI();
		// ISO-8859-1 ==> UTF-8 进行编码转换
		String tagname = extractPathFromPattern(request);
		
		String url1 = request.getRequestURL().toString();
        String[] urls = url1.split("/");
		String IP = urls[2];
		
		try {
			request.setCharacterEncoding("utf-8");
	        response.setCharacterEncoding("utf-8"); 
			 
			String proPath = request.getSession().getServletContext().getRealPath("/");  
			String proName = request.getContextPath();
			InputStream in = request.getInputStream();
			String barcode ="",userName = "";
			String exp = "";
//			ImageInputStream iis = ImageIO.createImageInputStream(in);
//			Iterator<ImageReader> iterator = ImageIO.getImageReaders(iis);
//			
//	        while (iterator.hasNext()) {
//	        	ImageReader reader = (ImageReader)iterator.next();
//	        	exp = "."+reader.getFormatName();
//	        }
//	        iis.close();
//	        System.out.println(exp);
			
			String[] tags = tagname.split(",");
			if(tags.length == 2) {
				barcode = tags[0];
				userName = tags[1];
			}
			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    		String fileName = df.format(new Date()) + exp ;
    		String uploadPath = proPath + "resources/"+userName;
    		picUpload(request.getInputStream(), fileName, uploadPath);
    		map.put("nativepic", "http://"+ IP+ proName + "/resources/androidPic/voucher.png");
    		
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return (String)map.get("nativepic");
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public UserUIForMobile login(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		
		UserUIForMobile userMobile = new UserUIForMobile();
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			request.setCharacterEncoding("utf-8");
	        response.setCharacterEncoding("utf-8");
	        String url = request.getRequestURL().toString();
	        String[] urls = url.split("/");
			String IP = urls[2];
	        
	        String proPath = request.getSession().getServletContext().getRealPath("/");  
	        String userId = request.getParameter("username");
			String password = request.getParameter("password");
			if(userId == null || userId == "") {
				userMobile.setState("UserName is null");
				return userMobile;
			}
			byte[] pass = decoder.decodeBuffer(password); 
			String pwd = ServiceMd5.md5(new String(pass));

			UserId user = userRepository.findByUserId(userId);
			if(user != null) {
				if((user.getPassword()).equals(pwd)) {
					userMobile.setState("success");
					File uploadFolder = new File(proPath + "resources/" + userId);
					if (!uploadFolder.exists()) {
						uploadFolder.mkdirs();  
					}
					userMobile.setSecuritycode(userId + "has own folder");
				}else {
					userMobile.setState("Password is wrong!!!");
				}
			}else {
				userMobile.setState("UserName is not exist!");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userMobile;
	}
	
	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	@ResponseBody
	public void test2(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
	
		ServletContext context = request.getSession().getServletContext();
		String reportFileName = context.getRealPath("/reports/test.jasper");

        File reportFile = new File(reportFileName);
        if (!reportFile.exists()) {
            throw new JRRuntimeException("File PdfEncryptReport.jasper not found. The report design must be compiled first.");
        }
                   
       

        Map parameters = new HashMap();

		//window path
        //parameters.put("SUBREPORT_DIR", reportFile.getParentFile().getAbsolutePath()+"\\");
        //linux path
        String reportpath = reportFile.getParentFile().getAbsolutePath();
        //reportpath = reportpath.substring(0, reportpath.lastIndexOf("reports"));
        reportpath=reportpath.replaceAll("\\\\", "/");
        System.out.println("report path:"+reportpath);
        parameters.put("SUBREPORT_DIR", reportpath+"/");

         JRDataSource dataSource;
         ByteArrayOutputStream outPut=new ByteArrayOutputStream();  
         File file=null;
         FileOutputStream outputStream=null; 
		try {
			
	         
	        //file=new File("c:/software/tomcat8/webapps/DrugPortal/resources/androidPic/voucher.pdf");  
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());
			dataSource = this.createDataSource("",reportpath+"/");
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			response.setContentType("application/pdf");
			JRPdfExporter exporter = new JRPdfExporter();			
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			//exporter.setParameter(JRExporterParameter.OUTPUT_FILE, destFile);
			//exporter.setParameter(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.TRUE);
			exporter.setParameter(JRPdfExporterParameter.IS_128_BIT_KEY, Boolean.TRUE);
			//exporter.setParameter(JRPdfExporterParameter.USER_PASSWORD, "jasper");
			//exporter.setParameter(JRPdfExporterParameter.OWNER_PASSWORD, "reports");
			exporter.setParameter(
				JRPdfExporterParameter.PERMISSIONS, 
				new Integer(PdfWriter.AllowCopy | PdfWriter.AllowPrinting)
				);
			OutputStream ouputStream = response.getOutputStream();
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
			exporter.exportReport();
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
        
	
	}
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public String test(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
	
		ServletContext context = request.getSession().getServletContext();
		String reportFileName = context.getRealPath("/reports/test.jasper");

        File reportFile = new File(reportFileName);
        if (!reportFile.exists()) {
            throw new JRRuntimeException("File PdfEncryptReport.jasper not found. The report design must be compiled first.");
        }
                   
       

        Map parameters = new HashMap();

		//window path
        //parameters.put("SUBREPORT_DIR", reportFile.getParentFile().getAbsolutePath()+"\\");
        //linux path
        String reportpath = reportFile.getParentFile().getAbsolutePath();
        
        reportpath=reportpath.replaceAll("\\\\", "/");
        String webroot=reportpath.substring(0, reportpath.lastIndexOf("reports"));
        System.out.println("webroot path:"+webroot);
        parameters.put("SUBREPORT_DIR", reportpath+"/");

         JRDataSource dataSource;
         ByteArrayOutputStream outPut=new ByteArrayOutputStream();  
         File file=null;
         FileOutputStream outputStream=null; 
		try {
			
	         
	        file=new File(webroot+"/resources/androidPic/voucher.pdf");  
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());
			dataSource = this.createDataSource("",reportpath+"/");
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			response.setContentType("application/pdf");
            JRPdfExporter exporter = new JRPdfExporter();	
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);  
            //生成输出流  
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outPut);  
            //exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, file);
            //屏蔽copy功能  
            exporter.setParameter(JRPdfExporterParameter.IS_ENCRYPTED,Boolean.TRUE);  
            //加密  
            //exporter.setParameter(JRPdfExporterParameter.IS_128_BIT_KEY,Boolean.TRUE);  
            exporter.exportReport();  
            outputStream=new FileOutputStream(file);  
            outputStream.write(outPut.toByteArray());  
            //System.out.println("java.library.path is: " + System.getProperty("java.library.path"));
            
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{  
            try {  
                outPut.flush();  
                outPut.close();  
                if(outputStream!=null){
                	outputStream.flush();
                	outputStream.close();
                }
                ImageService.pdfToPng(webroot+"/resources/androidPic/voucher.pdf", webroot+"/resources/androidPic/voucher.png");
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  

        
         return "test";
	
	}
	private JRDataSource createDataSource(String seqNo,String path) throws Exception {
		//生成测试数据
		System.out.println("path="+path);
		ImageService is=new ImageService();
        Image barcodeImage = null, signatureImage = null,logo1=null,logo2=null;
      
        signatureImage=is.getImage(path+"vio_logo_vertical.jpg");
        logo1=is.getImage(path+"THMN_logo_vertical.jpg");
        logo2=is.getImage(path+"vio_logo_vertical.jpg");
        ArrayList<VoucherVO> l = new ArrayList<VoucherVO>();
        VoucherVO vo = new VoucherVO();
        vo.setSeqNo(seqNo);
        //vo.setBarcodeFile("B" + vo.getSeqNo() + ".jpg");
        //vo.setBarCodeImage(barcodeImage);
        vo.setSignatureImage(signatureImage);
        vo.setLogo1(logo1);
        vo.setLogo2(logo2);
        vo.setMemberName("KAN WAI");
        vo.setMembershipNo("12345678");
        vo.setVoucherNo("V8882367");
        vo.setDoctorNo("Dr.123");
        vo.setDoctorName("Alen Wong");
        vo.setSickLeave(1l);
        vo.setInCurredDate(new Date());
        vo.setLeaveFrom(new Date());
        vo.setLeaveTo(vo.getLeaveFrom());
        vo.setDiagnosis("Test Diagnosis");
        ArrayList<VoucherItem> items=new ArrayList<VoucherItem>();
        VoucherItem item=new VoucherItem();
        item.setItem("Item 1");
        item.setDuration(2d);
        item.setAmount(189.89);
        items.add(item);
        vo.setItems(items);
        l.add(vo);

        return new JRBeanCollectionDataSource(l);
    }
	
	// 把指定URL后的字符串全部截断当成参数
	// 这么做是为了防止URL中包含中文或者特殊字符（/等）时，匹配不了的问题
	private static String extractPathFromPattern(final HttpServletRequest request) {
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
	}
	
	/**
	 * 文件上传  
	 * @param nativePic
	 * @param uploadPath
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void fileUpload(String nativePic, String uploadPath, String fileName) throws FileNotFoundException, IOException {
		
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			File uploadFolder = new File(uploadPath);
			if (!uploadFolder.exists()) {
				uploadFolder.mkdirs();  
			}
			FileOutputStream write = new FileOutputStream(new File(uploadPath + fileName));
			byte[] decoderBytes = decoder.decodeBuffer(nativePic);
			write.write(decoderBytes);
			write.flush();
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void picUpload(InputStream in, String filename, String upload_path)  
	        throws FileNotFoundException, IOException {  
	    File uploadFolder = new File(upload_path);  
	    if (!uploadFolder.exists()) {  
	        uploadFolder.mkdirs();  
	    }  
	    File uploadFile = new File(uploadFolder + "/" + filename);  
	    OutputStream out = new FileOutputStream(uploadFile);  
	    byte[] buffer = new byte[1024 * 1024];  
	    int length;  
	    while ((length = in.read(buffer)) > 0) {  
	        out.write(buffer, 0, length);  
	    }  
	    in.close();  
	    out.close();  
	} 

}
