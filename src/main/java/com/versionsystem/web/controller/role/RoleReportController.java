package com.versionsystem.web.controller.role;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.pdf.PdfWriter;
import com.versionsystem.common.ClassLoadUtil;
import com.versionsystem.common.report.Templates;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;


@RestController
@RequestMapping(value = "/role")
public class RoleReportController {
	
	
	private Logger logger = Logger.getLogger(RoleReportController.class);

	@RequestMapping(value="/report",method = RequestMethod.GET)
	public void export(@RequestParam String reportName, @RequestParam String reportType, HttpServletResponse response){

		try {
		     
			 InputStream jasperStream = ClassLoadUtil.getStreamByExtendResource("../reports/hello.jasper");
			    Map<String,Object> parameters = new HashMap();
			    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
			    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			    
			    if("PDF".equals(reportType)){
					response.setContentType("application/pdf");
					response.setHeader("Content-disposition", "inline; filename=helloWorldReport.pdf");
					JRPdfExporter exporter = new JRPdfExporter();	
					exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
					SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
					configuration.set128BitKey(true);
					configuration.setPermissions(new Integer(PdfWriter.AllowCopy | PdfWriter.AllowPrinting));
					configuration.setEncrypted(true);
					configuration.setUserPassword("test");
					configuration.setOwnerPassword("test");
					exporter.setConfiguration(configuration);				
					
					exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
					
					//byte[] payload = JasperRunManager.runReportToPdf(jasperReport, parameters, new JREmptyDataSource());
					//ByteArrayInputStream in = new ByteArrayInputStream(payload);
					//IOUtils.copy(in, ouputStream);
					
					
					exporter.exportReport();
					
					
				}
				else if("XLS".equals(reportType)){
					//request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
					response.setContentType("application/ms-excel");
					response.setHeader("Content-disposition", "inline; filename=helloWorldReport.xls");
					response.setHeader("Content-Disposition", "attachment; filename=test.xls");  
					JRXlsExporter exporter=new JRXlsExporter();
					exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
					SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
					configuration.setOnePagePerSheet(true);
					configuration.setDetectCellType(true);
					configuration.setCollapseRowSpan(false);
					configuration.setWhitePageBackground(true);					
					exporter.setConfiguration(configuration);																		
					exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
					exporter.exportReport();
					
				}
		      
		    } catch (Exception ex) {
		    	ex.printStackTrace();
		      logger.info("Error writing file to output stream. Filename was '{}'");
		      throw new RuntimeException("IOError writing file to output stream");
		    }
		
		
	}
	@RequestMapping(value="/dynamic/report",method = RequestMethod.GET)
	public void dynamicExport(@RequestParam String reportType, HttpServletResponse response){

		try {
			if ("pdf".equals(reportType)) 
				response.setContentType("application/pdf");
			else
				response.setContentType("application/vnd.ms-excel");
	        OutputStream out = response.getOutputStream();
	       
	            JasperReportBuilder jrb = createJasperReport();
	 
	            if ("pdf".equals(reportType)) {
	                jrb.toPdf(out);
	            } else if ("xls".equals(reportType)) {
	                jrb.toExcelApiXls(out);
	            }
	        
	        out.close();
		      
		    } catch (Exception ex) {
		    	ex.printStackTrace();
		      logger.info("Error writing file to output stream. Filename was '{}'");
		      throw new RuntimeException("IOError writing file to output stream");
		    }
		
		
	}
	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("field1", "field2");
		dataSource.add(10, 5);
		return dataSource;
	}
	 private JasperReportBuilder createJasperReport() {
		 JasperReportBuilder report = report();		 
		 TextColumnBuilder<Integer>    column1 = col.column("A", "field1", type.integerType());
			TextColumnBuilder<Integer>    column2 = col.column("B", "field2", type.integerType());
			TextColumnBuilder<BigDecimal> column3 = column1.multiply(column2).setTitle("A * B");
			TextColumnBuilder<BigDecimal> column4 = column1.divide(2, column2).setTitle("A / B");
			TextColumnBuilder<BigDecimal> column5 = column1.add(column2).setTitle("A + B");
			TextColumnBuilder<BigDecimal> column6 = column1.subtract(column2).setTitle("A - B");
			TextColumnBuilder<BigDecimal> column7 = column3.add(6).setTitle("A * B + 6");
			TextColumnBuilder<BigDecimal> column8 = column7.divide(2, 5).add(1).setTitle("(A*B+6) / 5 + 1");
		 report.setTemplate(Templates.reportTemplate).columns(
				  	column1, column2, column3, column4, column5, column6, column7, column8)
				  .title(cmp.text("Test Dynamic Reports"))
				  .pageFooter(cmp.pageXofY())
				  .setDataSource(createDataSource());
		 return report;
	    }
	 
  public static void main(String args[]) {
	 System.out.println( type.dateDayType().getPattern());
	 System.out.println( type.dateMonthType().getPattern());
	 System.out.println( type.dateYearToFractionType().getPattern());
	 System.out.println( type.dateYearToMonthType().getPattern());
	 System.out.println( type.dateYearToMinuteType().getPattern());
  }
	

}
