package com.wx.spring.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期类
 * @Description:
 * @Title: DateUtil
 * @author wangxin
 * @date 2019年10月12日
 */
public class DateUtil {

	/**
	 * 获取当前时间 yyyy-MM-dd HH:mm:ss
	 * <br>@description: 
	 * <br>@author: wangxin
	 * <br>@since: (版本) 作者 时间 注释 
	 * @return
	 */
	public static String getNowTime(){
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());
	}
	
	/**
	 * 获取当前日期
	 * <br>@description: 格式（ yyyy-MM-dd）
	 * <br>@author: wangxin
	 * <br>@since: (版本) 作者 时间 注释 
	 * @return
	 */
	public static String getNowDay(){
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        return df.format(new Date());
	}
	
	/**
	 * 获取当前日期 
	 * <br>@description: 格式（yyyy年 MM 月 dd 日）
	 * <br>@author: wangxin
	 * <br>@since: (版本) 作者 时间 注释 
	 * @return
	 */
	public static String getNowDaySpecificDate(){
		 SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");//设置日期格式
        return df.format(new Date());
	}
	
	/**
	 * 获取当前时间 yyyy年MM月dd日 HH:mm:ss
	 * <br>@description: 
	 * <br>@author: wangxin
	 * <br>@since: (版本) 作者 时间 注释 
	 * @return
	 */
	public static String getNowTimeSpecificDate(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");//设置日期格式
		return df.format(new Date());
	}
	
	/**
	 * 获取当前月份 yyyy-MM-dd
	 * <br>@description: 
	 * <br>@author: wangxin
	 * <br>@since: (版本) 作者 时间 注释 
	 * @return
	 */
	public static String getNowMonth(){
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");//设置日期格式
        return df.format(new Date());
	}
	
	/**
	 * 截取传入日期 yyyy 年 MM 月 dd 日格式
	 * <br>@description: 
	 * <br>@author: wangxin
	 * <br>@since: (版本) 作者 时间 注释 
	 * @return
	 */
	public static String getNowDaySpecificDate(String time) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");//设置日期格式
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(time));
       return  df.format(cal.getTime());
	}
	
	/**
	 * 传入时间  返回当前时间为周几（汉字）
	 * <br>@description: 
	 * <br>@author: wangxin
	 * <br>@since: (版本) 作者 时间 注释 
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static String getNowWeekEnFormTime(String time) throws ParseException{
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(time));
		//获取一周的第几天
		int weekDay=cal.get(Calendar.DAY_OF_WEEK) - 1;
		switch(weekDay){
		  case 0 : result = "周日";
		     break;
		  case 1 : result = "周一";
		     break;
		  case 2 : result = "周二";
		     break;
		  case 3 : result = "周三";
		     break;
		  case 4 : result = "周四";
		     break;
		  case 5 : result = "周五";
		     break;
		  case 6 : result = "周六";
		     break;
		}
		return result;
	}
}
