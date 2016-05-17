package com.versionsystem.common;

import hirondelle.date4j.DateTime;
import hirondelle.date4j.DateTime.DayOverflow;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;

public class ObjectConverter {
	
	public static String htmlToPlainText(String html){
		String plain = new HtmlToPlainText().getPlainText(Jsoup.parse(html));
		return plain;
	}
	
	public static Long toLong(Object object) {
		if(object==null)
			return null;
		else {
			return Long.parseLong(object.toString());
		}
	}
	public static BigDecimal toBigDecimal(Object object) {
		if(object==null)
			return null;
		else {
			return new BigDecimal(object.toString());
		}
	}
	
	public static String toStringDate(Date object,String dateFormat) {
		if(object==null)
			return null;
		else {
			SimpleDateFormat df = new SimpleDateFormat(dateFormat);		
			try {
				return df.format(object);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	}
	
	public static Date toUTCDate(Object object,String dateFormat) {
		if(object==null)
			return null;
		else {
			SimpleDateFormat iso8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			TimeZone tz=TimeZone.getTimeZone("UTC");
			iso8601.setTimeZone(tz);
			try {
				return iso8601.parse(object.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	}
	public static Date toUTCDate2(Date object,String dateFormat) {
		if(object==null)
			return null;
		else {
			SimpleDateFormat iso8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			TimeZone tz=TimeZone.getTimeZone("UTC");
			iso8601.setTimeZone(tz);
			try {
				return iso8601.parse(iso8601.format(object));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	}
	public static Date toDate(Object object,String dateFormat) {
		if(object==null)
			return null;
		else {
			SimpleDateFormat iso8601 = new SimpleDateFormat(dateFormat);		
			try {
				return iso8601.parse(object.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	}
	
	public static long getDurationTimeBetweenDates(Date d1,Date d2){

		return d2.getTime()/60000-d1.getTime()/60000;
	}
	public static Date addMinutesForBooking(Date d,long duration) throws ParseException{
			Calendar cal = Calendar.getInstance();
		 cal.setTime(d);
		 cal.add(Calendar.MINUTE, new Long(duration).intValue());
		 return cal.getTime();
	}
	
	public static Date getStartOfDate(Date date){
		String date4jFormat="YYYY-MM-DD hh:mm:ss";
		String utilDateFormat="yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sd=new SimpleDateFormat(utilDateFormat);//java.util.Date format:yyyy-MM-dd HH:mm:ss
		DateTime dt=new DateTime(sd.format(date));
		try {
			return sd.parse(dt.getStartOfDay().format(date4jFormat));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return date;
		}
	}
	
	public static Date[] getDateSection(Date date,String view) {
		String date4jFormat="YYYY-MM-DD hh:mm:ss";
		String utilDateFormat="yyyy-MM-dd HH:mm:ss";
		Date dates[]= null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		Date dateStart = c.getTime();
		if("day".equals(view)||"timeline".equals(view)) {
			dates=new Date[2];
			SimpleDateFormat sd=new SimpleDateFormat(utilDateFormat);//java.util.Date format:yyyy-MM-dd HH:mm:ss
			DateTime dt=new DateTime(sd.format(date));
			try {
				dates[0]=sd.parse(dt.getStartOfDay().format(date4jFormat));
				dates[1]=sd.parse(dt.getEndOfDay().format(date4jFormat));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//date4j format:YYYY-MM-DD hh:mm:ss
			
		}
		else if("week".equals(view)) {
			/*dates=new Date[2];
			int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
			c.add(Calendar.DAY_OF_MONTH, -dayOfWeek);
			dates[0]=dateStart;
			c.add(Calendar.DAY_OF_MONTH, 6); 
			Date weekEnd = c.getTime();
			dates[1]=weekEnd;*/
			
			dates=new Date[2];
			SimpleDateFormat sd=new SimpleDateFormat(utilDateFormat);//java.util.Date format:yyyy-MM-dd HH:mm:ss
			DateTime dt=new DateTime(sd.format(date));
			try {
				
			    DateTime firstDayThisWeek = dt; //start value 
			    int todaysWeekday = dt.getWeekDay();
			    int SUNDAY = 1;
			    if(todaysWeekday > SUNDAY){
			      int numDaysFromSunday = todaysWeekday - SUNDAY;
			      firstDayThisWeek = dt.minusDays(numDaysFromSunday);
			    }
			    DateTime lastDayThisWeek=firstDayThisWeek.plusDays(6);
				dates[0]=sd.parse(firstDayThisWeek.getStartOfDay().format(date4jFormat));//date4j format:YYYY-MM-DD hh:mm:ss
				dates[1]=sd.parse(lastDayThisWeek.getEndOfDay().format(date4jFormat));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return dates;
		}else if("month".equals(view)) {
			dates=new Date[2];
			SimpleDateFormat sd=new SimpleDateFormat(utilDateFormat);
			DateTime dt=new DateTime(sd.format(date));
			try {
				dates[0]=sd.parse(dt.getStartOfMonth().getStartOfDay().format(date4jFormat));
				dates[1]=sd.parse(dt.getEndOfMonth().getEndOfDay().format(date4jFormat));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return dates;
			
		}
		
		// we do not need the same day a week after, that's why use 6, not 7
		
		
		return dates;
	}
	
	public static Date convertToUTCDate(Date d){
		return new Date(d.getTime()-(Calendar.getInstance().get(Calendar.ZONE_OFFSET)+Calendar.getInstance().get(Calendar.DST_OFFSET)));
	}
	public static boolean isNumeric(String string) {
	      return string.matches("^[-+]?\\d+(\\.\\d+)?$");
	 }
	public static boolean isValidDate(String inDate,String format) {
		if(format==null ||"".equals(format))
			format="ddMMyyyy";
	    SimpleDateFormat dateFormat = new SimpleDateFormat(format);
	    dateFormat.setLenient(false);
	    try {
	      dateFormat.parse(inDate.trim());
	    } catch (ParseException pe) {
	      return false;
	    }
	    return true;
	  }
	public static void main(String[] args) {
		String utilDateFormat="yyyy-MM-dd HH:mm";
		SimpleDateFormat sd=new SimpleDateFormat(utilDateFormat);
		try {
			Date d=sd.parse("2015-11-19 18:30");
			Date d2=sd.parse("2015-11-19 19:01");
			//long x=ObjectConverter.getDurationTimeBetweenDates(d, d2);
			System.out.println(ObjectConverter.getStartOfDate(d));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
