package com.wx.spring.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 判断对象是否为空 转换对象
 * @Description:
 * @Title: StrUtils
 * @author wangxin
 * @date 2019年10月15日
 */
public class StrUtils {
	
	public static boolean isEmpty(Object obj){
		if(obj == null){
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(List<?> list){
		if(list == null || list.size() == 0){
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(Map<String, ?> map){
		if(map == null || map.size() == 0){
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(Set<?> set){
		if(set == null || set.size() == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断object 是否为空  是则转为0
	 * @Description:  
	 * @author wangxin
	 * @date 2019年10月15日  
	 * @param 
	 * @return
	 */
    public static String nullToZero (Object obj){
	   if(obj == null){
		   return "0";
	   }
	  return obj.toString();
    }
    
    /**
     * 判断object 是否为空  是则转为 ""
     * @Description:  
     * @author wangxin
     * @date 2019年10月15日  
     * @param 
     * @return
     */
    public static String nullToString (Object obj){
 	   if(obj == null){
 		   return "";
 	   }
 	  return obj.toString();
    }
}
