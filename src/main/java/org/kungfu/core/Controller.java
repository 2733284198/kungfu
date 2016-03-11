package org.kungfu.core;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.plugin.ehcache.CacheKit;

/**
 * created by xiaofeixiang on 2016/1/9 15:28:18.
 */
public class Controller extends com.jfinal.core.Controller {

	protected String getControllerKey() {
		return this.getAttr("ControllerKey");
	}
	
	private Double toDouble(String value, Double defaultValue) {
		if (value == null || "".equals(value.trim()))
			return defaultValue;
		if (value.startsWith("N") || value.startsWith("n"))
			return -Double.parseDouble(value.substring(1));
		return Double.parseDouble(value);
	}
	
	/**
	 * Get para from url and conver to Double with default value if it is null.
	 */
	public Double getParaToDouble(int index, Double defaultValue) {
		return toDouble(getPara(index), defaultValue);
	}

	/**
	 * Returns the value of a request parameter and convert to Double.
	 */
	public Double getParaToDouble(String name) {
		return toDouble(getPara(name), null);
	}
	
	private Short toShort(String value, Short defaultValue) {
		if (value == null || "".equals(value.trim()))
			return defaultValue;
		if (value.startsWith("N") || value.startsWith("n"))
			return (short) -Short.parseShort(value.substring(1));
		return Short.parseShort(value);
	}

	/**
	 * Get para from url and conver to Short with default value if it is null.
	 */
	public Short getParaToShort(int index, Short defaultValue) {
		return toShort(getPara(index), defaultValue);
	}

	/**
	 * Returns the value of a request parameter and convert to Short.
	 */
	public Short getParaToShort(String name) {
		return toShort(getPara(name), null);
	}
	
    /**
     * 根据cacheName, cacheKey来清除缓存
     * cacheName 必填，cacheKey选填，不填的话为null
     * @param cacheName
     * @param cacheKey
     */
    public void clearCache(String cacheName, Object cacheKey) {
        if (cacheKey == null) {
            CacheKit.removeAll(cacheName);
        } else {
            CacheKit.remove(cacheName, cacheKey);
        }
    }
    
    public void success() {
    	renderJson(new Result(Constants.ResultCode.SUCCESS , Constants.ResultDesc.SUCCESS, null));
    }
    
    public void error() {
    	renderJson(new Result(Constants.ResultCode.FAILURE , Constants.ResultDesc.FAILURE, null));
    }
    
    public void success(String message) {
    	renderJson(new Result(Constants.ResultCode.SUCCESS , message, null));
    }
    
    public void error(String message) {
    	renderJson(new Result(Constants.ResultCode.FAILURE , message, null));
    }
    
    public void result(int code, String message) {
        renderJson(new Result(code, message, null));
    }
	
    public void result(int code, String message, Object object) {
         renderJson(new Result(code, message, object));
    }
  
    
    /**
     * @param params [key1,value1,key2,value2, ...]
     * @return a map of params
     */
    public Map<String, String> getParams(String ...params) {
    	if (params.length % 2 != 0)
			throw new IllegalArgumentException("params's length can not be Odd number. can be params [key1,value1,key2,value2,...]");
    	Map<String, String> paramsMap = new HashMap<String, String>();
    	for (int i = 0; i< params.length; i+=2) 
    		paramsMap.put(params[i], params[i+1]);
    	
    	return paramsMap;
    }
}
