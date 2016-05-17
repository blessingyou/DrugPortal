package com.versionsystem.common.report;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.datatype.BigDecimalType;
import net.sf.dynamicreports.report.builder.datatype.IntegerType;
import net.sf.dynamicreports.report.builder.datatype.StringType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

public class CustomReportBuilder {
	
	private Map<String, TextColumnBuilder> textCols;
	
	
	
	public TextColumnBuilder createTextColumn(String title,String fieldName,String key) {
		if("stringType".equals(key))
			return col.column(title, fieldName,type.stringType());
		else if("integerType".equals(key))
			return col.column(title, fieldName,type.integerType());
		else 
			return null;
	}
	
	public void build() {
		try {
			textCols=new HashMap<String, TextColumnBuilder>();
			
			List<String[]> list=new ArrayList<String[]>();
			String[] s= {"Item","item","stringType"};
			list.add(s);
			String[] s1= {"Quantity","quantity","integerType"};
			list.add(s1);
			JasperReportBuilder report = report();	
			report.addColumn( col.reportRowNumberColumn("No."));
			report.setTemplate(Templates.reportTemplate);
			for(String[] ss:list) {
				TextColumnBuilder tb=this.createTextColumn(ss[0], ss[1], ss[2]);
				if(tb!=null) {
					textCols.put(ss[1], tb);
					report.addColumn(tb);
				}
			}
			//report.addColumn((textCols.get("item").add(textCols.get("quantity"))).setTitle("test col"));
			report.title(Templates.createTitleComponent("ColumnDataTypes"))
			  .pageFooter(Templates.footerComponent)
			  .setDataSource(createDataSource())
			  .show();
			/*report()
			  .setTemplate(Templates.reportTemplate)
			  .columns()
			  .title(Templates.createTitleComponent("ColumnDataTypes"))
			  .pageFooter(Templates.footerComponent)
			  .setDataSource(createDataSource())
			  .show();*/
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("item", "orderdate", "quantity", "unitprice");
		dataSource.add("Notebook", new Date(), 1, new BigDecimal(500));
		dataSource.add("PC", new Date(), 10, new BigDecimal(1500));
		return dataSource;
	}
	
	private class ExpressionColumn1 extends AbstractSimpleExpression<String> {
		
		      private static final long serialVersionUID = 1L;
		
		 
		
		      @Override
		
		      public String evaluate(ReportParameters reportParameters) {
		
		         return
		
		            "Item = " + reportParameters.getValue("item") + ", " +
		
		            "Order date = " + type.dateType().valueToString("orderdate", reportParameters) + ", " +
		
		            "Quantity = " + type.integerType().valueToString("", reportParameters) + ", " +
		
		            "Unit price = " + type.bigDecimalType().valueToString("unitprice", reportParameters);
		
		      }
		
	 }
	private class ExpressionColumn2 extends AbstractSimpleExpression<String> {
		
	      private static final long serialVersionUID = 1L;
	
	 
	
	      @Override
	
	      public String evaluate(ReportParameters reportParameters) {
	
	         return
	
	            "Item = " + reportParameters.getValue("item") + ", " +
	
	            "Order date = " + type.dateType().valueToString("orderdate", reportParameters) + ", " +
	
	            "Quantity = " + type.integerType().valueToString("", reportParameters) + ", " +
	
	            "Unit price = " + type.bigDecimalType().valueToString("unitprice", reportParameters);
	
	      }
	
}

	public static void main(String[] args) {
		new CustomReportBuilder().build();;
	}

}
