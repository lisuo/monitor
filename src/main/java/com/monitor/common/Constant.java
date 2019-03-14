package com.monitor.common;

/**
 * 常量类
 * 
 * @author lisuo
 *
 */
public class Constant {

	/**
	 * 保存用户拥有的资源前缀
	 */
	public static final String USER_CACHE_PERMISSION_PRE = "auth:user:permission:";
	/**
	 * 保存用户拥有的资源的key的过期时间，单位为秒
	 */
	public static final Long USER_CACHE_PERMISSION_TIMEOUT = 3600L;

	/**
	 * 用户权限redis存储前缀
	 */
	public static final String AUTH_USER_TOKEN = "auth:user:token:";

	/**
	 * 用户角色redis存储前缀
	 */
	public static final String AUTH_USER_ROLES = "auth:user:roles:";

	/**
	 * 用户权限redis存储前缀
	 */
	public static final String AUTH_USER_PERMISSIONS = "auth:user:permissions:";

	/**
	 * 用户登录重试redis缓存前缀
	 */
	public static final String AUTH_USER_LOGIN_RETRY_COUNT = "auth:user:login:retry:count";

	/**
	 * 常量1
	 */
	public static final int ONE = 1;

	/**
	 * 常量0
	 */
	public static final int ZERO = 0;

	public static final String CACHE_TYPE_REDIS = "redis";

	/**
	 * 导出格式csv
	 */
	public static final String CSV = "csv";
	/**
	 * 导出格式xlsx
	 */
	public static final String XLSX = "xlsx";
	public static String XLS = "xls";
	public static String REGEX = ",";

	public static String UTF8 = "UTF-8";
	public static String GBK = "gbk";
	public final static Integer U_1024 = 1024;

	public final static String COMMA = ",";

	public final static String SEMICOLON = "；";

	public final static String PERIOD = ".";

	public final static String COLON = ":";

	public final static String STAR = "*";

	public final static String EMPTY_STR = "";


	/**导出RESPONSE设置请求头信息常量*/
	public final static String RES_HEADER_DISPOSITION = "Content-Disposition";
	public final static String RES_HEADER_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
	public final static String RES_HEADER_EXPOSE_HEADERS = "Access-Control-Expose-Headers";
	public final static String RES_HEADER_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8";
	public final static String RES_HEADER_CONTENT_TYPE_EXCEL = "application/excel;charset=utf-8";
	public final static String RES_HEADER_ATTA_FILENAME = "attachment;filename=";

}
