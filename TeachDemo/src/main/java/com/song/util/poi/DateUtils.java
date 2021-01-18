package com.song.util.poi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.song.entity.CustomException;


/**
 * 日期工具类 字母 日期或时间元素 表示 示例 <br>
 * G Era 标志符 Text AD <br>
 * y 年 Year 1996; 96 M 年中的月份 Month July; Jul; 07 <br>
 * w 年中的周数 Number 27 <br>
 * W 月份中的周数 Number 2 <br>
 * D 年中的天数 Number 189 <br>
 * d 月份中的天数 Number 10 <br>
 * F 月份中的星期 Number 2 E 星期中的天数 Text Tuesday; Tue <br>
 * a Am/pm 标记 Text PM <br>
 * H 一天中的小时数（0-23） Number 0 <br>
 * k 一天中的小时数（1-24） Number 24 <br>
 * K am/pm 中的小时数（0-11） Number 0 <br>
 * h am/pm 中的小时数（1-12） Number 12 <br>
 * m 小时中的分钟数 Number 30 <br>
 * s 分钟中的秒数 Number 55 <br>
 * S 毫秒数 Number 978 <br>
 * z 时区 General time zone Pacific Standard Time; PST; GMT-08:00 <br>
 * Z 时区 RFC 822 time zone -0800 <br>
 * @author Rex.Tan
 * @date 2019年8月10日 下午6:00:05
 */
public class DateUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);
	private static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	public static final String YEAR_MONTH_NUMBER = "yyyyMM";
	/**
	 * yyyyMMdd
	 */
	private static final String YYYYMMDD = "^\\d{4}-\\d{2}-\\d{2}$";
	private static final String YYYYMMDD_FORMAT = "yyyy-MM-dd";
	private static final String YYYYMMDD2 = "^\\d{4}/\\d{2}/\\d{2}$";
	private static final String YYYYMMDD2_FORMAT = "yyyy/MM/dd";
	private static final String YYYYMMDD3 = "^\\d{4}\\d{2}\\d{2}$";
	private static final String YYYYMMDD3_FORMAT = "yyyyMMdd";
	private static final String YYYYMMDD4 = "^\\d{4}\\.\\d{2}\\.\\d{2}$";
	private static final String YYYYMMDD4_FORMAT = "yyyy.MM.dd";
	/**
	 * yyyyMMss HHmmss
	 */
	private static final String YYYYMMDDHHMMSS = "^\\d{4}-\\d{2}-\\d{2} \\d{2}-\\d{2}-\\d{2}$";
	private static final String YYYYMMDDHHMMSS_FORMAT = "yyyy-MM-dd HH-mm-ss";
	private static final String YYYYMMDDHHMMSS2 = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";
	private static final String YYYYMMDDHHMMSS2_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String YYYYMMDDHHMMSS3 = "^\\d{4}/\\d{2}/\\d{2} \\d{2}/\\d{2}/\\d{2}$";
	private static final String YYYYMMDDHHMMSS3_FORMAT = "yyyy/MM/dd HH/mm/ss";
	private static final String YYYYMMDDHHMMSS4 = "^\\d{4}\\d{2}\\d{2} \\d{2}\\d{2}\\d{2}$";
	private static final String YYYYMMDDHHMMSS4_FORMAT = "yyyyMMdd HHmmss";
	private static final String YYYYMMDDHHMMSS5 = "^\\d{4}\\.\\d{2}\\.\\d{2} \\d{2}\\.\\d{2}\\.\\d{2}$";
	private static final String YYYYMMDDHHMMSS5_FORMAT = "yyyy.MM.dd HH.mm.ss";
	
	
	
	/**
	 * yyyyMd
	 */
	private static final String YYYYMD = "^\\d{4}-\\d{1}-\\d{1}$";
	private static final String YYYYMD_FORMAT = "yyyy-M-d";
	private static final String YYYYMD2 = "^\\d{4}/\\d{1}/\\d{1}$";
	private static final String YYYYMD2_FORMAT = "yyyy/M/d";
	private static final String YYYYMD3 = "^\\d{4}\\d{1}\\d{1}$";
	private static final String YYYYMD3_FORMAT = "yyyyMd";
	private static final String YYYYMD4 = "^\\d{4}\\.\\d{1}\\.\\d{1}$";
	private static final String YYYYMD4_FORMAT = "yyyy.M.d";
	
	private static final String YYYYMDD = "^\\d{4}-\\d{1}-\\d{2}$";
	private static final String YYYYMDD_FORMAT = "yyyy-M-dd";
	private static final String YYYYMDD2 = "^\\d{4}/\\d{1}/\\d{2}$";
	private static final String YYYYMDD2_FORMAT = "yyyy/M/dd";
	private static final String YYYYMDD3 = "^\\d{4}\\d{1}\\d{2}$";
	private static final String YYYYMDD3_FORMAT = "yyyyMdd";
	private static final String YYYYMDD4 = "^\\d{4}\\.\\d{1}\\.\\d{2}$";
	private static final String YYYYMDD4_FORMAT = "yyyy.M.dd";
	
	private static final String YYYYMMD = "^\\d{4}-\\d{2}-\\d{1}$";
	private static final String YYYYMMD_FORMAT = "yyyy-MM-d";
	private static final String YYYYMMD2 = "^\\d{4}/\\d{2}/\\d{1}$";
	private static final String YYYYMMD2_FORMAT = "yyyy/MM/d";
	private static final String YYYYMMD3 = "^\\d{4}\\d{2}\\d{1}$";
	private static final String YYYYMMD3_FORMAT = "yyyyMMd";
	private static final String YYYYMMD4 = "^\\d{4}\\.\\d{2}\\.\\d{1}$";
	private static final String YYYYMMD4_FORMAT = "yyyy.MM.d";
	
	/**
	 * yyyyMMss HHmmss
	 */
	private static final String YYYYMDHHMMSS = "^\\d{4}-\\d{2}-\\d{2} \\d{2}-\\d{2}-\\d{2}$";
	private static final String YYYYMDHHMMSS_FORMAT = "yyyy-MM-dd HH-mm-ss";
	private static final String YYYYMDHHMMSS2 = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";
	private static final String YYYYMDHHMMSS2_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String YYYYMDHHMMSS3 = "^\\d{4}/\\d{2}/\\d{2} \\d{2}/\\d{2}/\\d{2}$";
	private static final String YYYYMDHHMMSS3_FORMAT = "yyyy/MM/dd HH/mm/ss";
	private static final String YYYYMDHHMMSS4 = "^\\d{4}\\d{2}\\d{2} \\d{2}\\d{2}\\d{2}$";
	private static final String YYYYMDHHMMSS4_FORMAT = "yyyyMMdd HHmmss";
	private static final String YYYYMDHHMMSS5 = "^\\d{4}\\.\\d{2}\\.\\d{2} \\d{2}\\.\\d{2}\\.\\d{2}$";
	private static final String YYYYMDHHMMSS5_FORMAT = "yyyy.MM.dd HH.mm.ss";
	
	
	
	/**
	 * 当月的第几天
	 */
	public static final int TIME_DAYOFMONTH = Calendar.DAY_OF_MONTH;
	/**
	 * 当周所的第几天
	 */
	public static final int TIME_DAYOFWEEK = Calendar.DAY_OF_WEEK;
	/**
	 * 当年的第几天
	 */
	public static final int TIME_DAYOFYEAR = Calendar.DAY_OF_YEAR;
	/**
	 * 当天的第几个小时
	 */
	public static final int TIME_HOUROFDAY = Calendar.HOUR_OF_DAY;
	/**
	 * 分
	 */
	public static final int TIME_MINUTE = Calendar.MINUTE;
	/**
	 * 月
	 */
	public static final int TIME_MONTH = Calendar.MONTH;
	/**
	 * 秒
	 */
	public static final int TIME_SECOND = Calendar.SECOND;
	/**
	 * 年
	 */
	public static final int TIME_YEAR = Calendar.YEAR;
	/**
	 * 一天的毫秒
	 */
	public static final int DAYMIS = 1000 * 60 * 60 * 24;

	/**
	 * 日期正则，支持yyyy-MM-dd;yyyy-M-d;支持"-","","/","."分隔
	 */
	private static final Pattern DATE_PATTERN = Pattern.compile(
			"^(?:(?!0000)[0-9]{4}([-/.]?)(?:(?:0?[1-9]|1[0-2])\\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\\1(?:29|30)|(?:0?[13578]|1[02])\\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-/.]?)0?2\\2(?:29))$");

	/**
	 * 日期时间正则，支持yyyy-MM-dd HH:ms:ss;yyyy-M-d HH:ms:ss;支持"-","","/","."分隔
	 */
	private static final Pattern DATE_TIME_PATTERN = Pattern.compile(
			"^(?:(?!0000)[0-9]{4}([-/.]?)(?:(?:0?[1-9]|1[0-2])\\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\\1(?:29|30)|(?:0?[13578]|1[02])\\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-/.]?)0?2\\2(?:29))[\\s]{1}([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$");

	private DateUtils() {
	}

	/**
	 * 日期运算
	 *
	 * @param date  源
	 * @param part  操作部份
	 * @param value 改变值
	 * @return 计算后的日期
	 */
	public static Date add(Date date, int part, int value) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(part, value);
		return calendar.getTime();
	}

	/**
	 * 取两个日期的差值
	 *
	 * @param from 开始时间
	 * @param to   结束时间
	 * @param part Time_Minute--相关多少分,Time_HourOfDay-时，other-天
	 * @return 差值
	 */
	public static long diff(Date from, Date to, int part) {
		if (to == null || from == null) {
			return 0;
		}
		long d = to.getTime() - from.getTime();
		switch (part) {
		case TIME_MINUTE:
			return d / 1000 / 60;
		case TIME_HOUROFDAY:
			return d / 1000 / 60 / 60;
		default:
			return d / 1000 / 60 / 60 / 24;
		}
	}

	/**
	 * 返回两个时间/日期之间的年份差或月份差
	 * 
	 * @param from  开始时间
	 * @param to    结束时间
	 * @param month
	 * @return
	 */
	public static int diff(Date from, Date to, boolean month) {
		if (from == null || to == null) {
			return 0;
		}
		int y0 = getPartOfDate(from, TIME_YEAR, "");
		int y1 = getPartOfDate(to, TIME_YEAR, "");
		int m0 = getPartOfDate(from, TIME_MONTH, "");
		int m1 = getPartOfDate(to, TIME_MONTH, "");
		int ret = y1 - y0;
		if (month) {
			ret = ret * 12 + m1 - m0;
		}
		return ret;
	}

	/**
	 * 时间/日期格式化函数,缺省时区
	 * 
	 * @param date   时间
	 * @param format 格式字符串
	 * @return
	 */
	public static String format(Date date, String format) {
		return format(date, format, null);
	}

	/**
	 * 删除日期中的时分秒
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date removeHms(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		String s = sdf.format(date);
		try {
			return sdf.parse(s);
		} catch (ParseException e) {
			String errorMsg = "日期格式转换错误";
			LOGGER.error(errorMsg, e);
			throw new CustomException(errorMsg);
		}
	}

	/**
	 * 时间/日期格式化函数
	 *
	 * @param date     时间/日期
	 * @param format   格式
	 * @param timeZone 时区如东八区GMT+8
	 * @return
	 */
	public static String format(Date date, String format, String timeZone) {
		if (date == null) {
			return "";
		}
		if (StringUtils.isBlank(format)) {
			format = "yyyy年MM月dd日";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		if (timeZone != null && !"".equals(timeZone.trim()))
			formatter.setTimeZone(TimeZone.getTimeZone(timeZone));
		else {
			formatter.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		}
		return formatter.format(date);
	}
	
	public static Date parse(String dateStr, String format) {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		if (StringUtils.isBlank(format)) {
			format = "yyyy-MM-dd";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		formatter.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		
		try {
			return formatter.parse(dateStr);
		} catch (ParseException e) {
			throw new CustomException("解析日期出错: " + e.getMessage());
		}
	}

	/**
	 * 取出时间中的小时
	 * 
	 * @param date 时间
	 * @return
	 */
	public static int getHourOfDay(Date date) {
		return getTimePart(date, Calendar.HOUR_OF_DAY, null);
	}

	/**
	 * 取出时间中的月份
	 * 
	 * @param date 时间
	 * @return
	 */
	public static int getMonthOfYear(Date date, String timeZone) {
		return getTimePart(date, Calendar.MONTH, timeZone);
	}

	/**
	 * 获取日期的部份数据
	 * 
	 * @param date     时间
	 * @param part     部分,如年(YEAR)、月(MONTH)、日(DAY)、时(HOUR_OF_DAY)、分(MINUTE)、秒(SECOND)
	 * @param timeZone 时区如GMT+8
	 * @return
	 */
	public static int getPartOfDate(Date date, int part, String timeZone) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (timeZone != null && !"".equals(timeZone.trim()))
			calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
		else {
			calendar.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		}
		int ret = calendar.get(part);
		if (part == TIME_MONTH) {
			ret++;
		}
		return ret;
	}

	/**
	 * 获取日期的部份数据
	 *
	 * @param date     时间
	 * @param part     部分,如年(YEAR)、月(MONTH)、日(DAY)、时(HOUR_OF_DAY)、分(MINUTE)、秒(SECOND)
	 * @param timeZone 时区如GMT+8
	 * @return
	 */
	public static int getTimePart(Date date, int part, String timeZone) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (timeZone != null && !"".equals(timeZone.trim()))
			calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
		else {
			calendar.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		}
		return calendar.get(part);
	}

	/**
	 * 取当前日期
	 * 
	 * @param includeHours 是否包括时分秒
	 * @param zone         时区
	 * @return Date
	 */
	public static Date getToday(boolean includeHours, String zone) {
		if (includeHours) {
			return toTimeZone(new Date(), zone);
		} else {
			return valueOf(format(new Date(), "yyyy-MM-dd", zone), "yyyy-MM-dd");
		}
	}

	/**
	 * 取出时间中的年份数据
	 * 
	 * @param date     时间
	 * @param timeZone 时区
	 * @return
	 */
	public static int getYearOfDate(Date date, String timeZone) {
		return getTimePart(date, Calendar.YEAR, timeZone);
	}

	/**
	 * 时区转换
	 *
	 * @param date   时间
	 * @param locate 时区
	 * @return
	 */
	public static Date toTimeZone(Date date, String locate) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (locate != null && !"".equals(locate.trim()))
			calendar.setTimeZone(TimeZone.getTimeZone(locate));
		else {
			calendar.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		}
		return calendar.getTime();
	}

	/**
	 * 字符转换为日期。
	 *
	 * @param source  时间字符串
	 * @param pattern 时间格式,如yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date valueOf(String source, String pattern) {
		return valueOf(source, pattern, null);
	}

	/**
	 * 时间字符串转时间对象
	 * 
	 * @param value    时间字符串
	 * @param pattern  格式
	 * @param timeZone 时区
	 * @param locale   语言和区域
	 * @return
	 */
	public static Date valueOf(String value, String pattern, String timeZone, Locale locale) {
		SimpleDateFormat format;
		if (locale == null) {
			format = new SimpleDateFormat(pattern);
		} else {
			format = new SimpleDateFormat(pattern, locale);
		}
		Date date = null;
		if (value == null) {
			return date;
		}
		if (timeZone != null && !"".equals(timeZone.trim()))
			format.setTimeZone(TimeZone.getTimeZone(timeZone));
		else {
			format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		}
		try {
			date = format.parse(value);
		} catch (ParseException e) {
			LOGGER.error("Date convert failure!", e);
		}
		return date;
	}

	/**
	 * 字符串转换为指定时区时间
	 *
	 * @param value    字符串时间
	 * @param pattern  格式，如yyyy-MM-dd HH:mm:ss
	 * @param timeZone 时区，如东八区：GMT+8
	 * @return true:是<br>
	 *         false:否
	 */
	public static Date valueOf(String value, String pattern, String timeZone) {
		return valueOf(value, pattern, timeZone, null);
	}

	/**
	 * 检查是否日期格式串。 <br>
	 * 支持yyyy-MM-dd;yyyy-M-d;支持"-","","/","."分隔
	 * 
	 * @param dateStr 日期字符串
	 * @return
	 */
	public static boolean checkDate(String dateStr) {
		return DATE_PATTERN.matcher(dateStr).matches();
	}

	/**
	 * 检查是否日期时间格式串 <br>
	 * 支持yyyy-MM-dd HH:ms:ss;yyyy-M-d HH:ms:ss;支持"-","","/","."分隔
	 * 
	 * @param dateStr 日期字符串
	 * @return true:是<br>
	 *         false:否
	 */
	public static boolean checkDateTime(String dateStr) {
		return DATE_TIME_PATTERN.matcher(dateStr).matches();
	}

	/**
	 * 获取日期的最后一毫秒 如Date为 Mon Mar 13 00:00:00 CST 2017 则返回结果 Mon Mar 13 23:59:59
	 * 23:59:59.999999 CST 2017
	 * 
	 * @param date 目标时间
	 * @return 当天最后一毫秒的时间对象
	 * @throws ParseException
	 */
	public static Date getDateLastMillisecond(Date date) {
		int year = getYearOfDate(date, null);
		int month = getMonthOfYear(date, null) + 1;
		int day = getPartOfDate(date, Calendar.DATE, null);

		return valueOf(year + "-" + month + "-" + day + " 23:59:59.999", "yyyy-MM-dd HH:mm:ss.SSS");
	}

	/**
	 * 获得传入日期所在月的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		// 去掉时分秒
		return valueOf(format(cal.getTime(), "yyyy-MM-dd"), "yyyy-MM-dd");
	}

	/**
	 * 获得传入日期所在月的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		// 去掉时分秒
		return valueOf(format(cal.getTime(), "yyyy-MM-dd"), "yyyy-MM-dd");
	}

	/**
	 * 获得某年某月最后一天，返回日期格式：yyyy-MM-dd
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		// 设置日期
		cal.set(Calendar.DATE, 1);
		// 设置月份
		cal.set(Calendar.MONTH, month);
		// 设置年份
		cal.set(Calendar.YEAR, year);

		cal.add(Calendar.DATE, -1);
		// 格式化日期
		String lastDayOfMonth = format(cal.getTime(), "yyyy-MM-dd");
		return lastDayOfMonth;
	}

	/**
	 * 获得某月的最后一个工作日
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastWorkDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		while (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 1);
		}

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获得当前月的上一个月的最后一个工作日，返回日期格式：yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getLastWorkDayOfLastMonth() {
		Calendar cal = Calendar.getInstance();
		Date date = getLastWorkDayOfMonth(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH));
		return format(date, "yyyy-MM-dd");
	}

	/**
	 * 获得传入日期所在自然季度的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfQuarter(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 从0开始
		int curMonth = cal.get(Calendar.MONTH);
		int lastMonth = curMonth;
		switch (curMonth) {
		case 0:
		case 1:
		case 2: {
			lastMonth = 2;
			break;
		}
		case 3:
		case 4:
		case 5: {
			lastMonth = 5;
			break;
		}
		case 6:
		case 7:
		case 8: {
			lastMonth = 8;
			break;
		}
		case 9:
		case 10:
		case 11: {
			lastMonth = 11;
			break;
		}
		default:
			break;
		}
		cal.set(Calendar.MONTH, lastMonth);
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		return valueOf(format(cal.getTime(), "yyyy-MM-dd"), "yyyy-MM-dd");
	}

	/**
	 * 获得传入日期所在自然季度的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfQuarter(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int curMonth = cal.get(Calendar.MONTH);
		int lastMonth = curMonth;
		switch (curMonth) {
		case 0:
		case 1:
		case 2: {
			lastMonth = 0;
			break;
		}
		case 3:
		case 4:
		case 5: {
			lastMonth = 3;
			break;
		}
		case 6:
		case 7:
		case 8: {
			lastMonth = 6;
			break;
		}
		case 9:
		case 10:
		case 11: {
			lastMonth = 9;
			break;
		}
		default:
			break;
		}
		cal.set(Calendar.MONTH, lastMonth);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return valueOf(format(cal.getTime(), "yyyy-MM-dd"), "yyyy-MM-dd");
	}

	/**
	 * 获取某月的第一一天
	 */
	public static Date getFirstDayOfMonth(Integer accountPeriod) {
		String strAccountPeriod = String.valueOf(accountPeriod);
		String year = strAccountPeriod.substring(0, 4);
		String month = strAccountPeriod.substring(4, 6);
		return valueOf(String.join("-", year, month, "01"), "yyyy-MM-dd");
	}

	/**
	 * 获取某月的最后一天
	 */
	public static Date getLastDayOfMonth(Integer accountPeriod) {
		String strAccountPeriod = String.valueOf(accountPeriod);
		int year = Integer.parseInt(strAccountPeriod.substring(0, 4));
		int month = Integer.parseInt(strAccountPeriod.substring(4, 6));
		return getLastDayOfMonth(year, month, true);
	}

	/**
	 * 获取某月的最后一天
	 */
	public static Date getLastDayOfMonth(int year, int month, boolean trimTime) {
		Calendar cal = Calendar.getInstance();

		// 设置年份
		cal.set(Calendar.YEAR, year);

		// 设置月份
		cal.set(Calendar.MONTH, month - 1);

		// 获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		// 设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);

		if (trimTime) {
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
		}

		return cal.getTime();
	}

	public static Integer getAccountPeriod(Date date) {
		return Integer.valueOf(format(date, "yyyyMM"));
	}

	public static Integer getNextAccountPeriod(Date date) {
		return Integer.valueOf(format(add(date, Calendar.MONTH, 1), "yyyyMM"));
	}

	public static Integer getNextAccountPeriod(Integer accountPeriod) {
		return Integer.valueOf(format(add(valueOf(accountPeriod + "01", "yyyyMMdd"), Calendar.MONTH, 1), "yyyyMM"));
	}

	public static Integer getLastAccountPeriod(Date date) {
		return Integer.valueOf(format(add(date, Calendar.MONTH, -1), "yyyyMM"));
	}

	public static Integer getLastAccountPeriod(Integer accountPeriod) {
		return Integer.valueOf(format(add(valueOf(accountPeriod + "01", "yyyyMMdd"), Calendar.MONTH, -1), "yyyyMM"));
	}

	public static String timestamp() {
		return format(new Date(), "yyyyMMddHHmmsss");
	}

	public static String now2String() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 设置为东八区
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		Date now = new Date();
		return sdf.format(now);
	}
	
	public static String date2String(Date date, String format) {
		if(date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(StringUtils.isNotBlank(format)) {
			sdf = new SimpleDateFormat(format);
		}
		// 设置为东八区
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return sdf.format(date);
	}
	
	public static String date2StringNormal(Date date, String format) {
		if(date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(StringUtils.isNotBlank(format)) {
			sdf = new SimpleDateFormat(format);
		}
		return sdf.format(date);
	}
	
	public static Date now() {
		Calendar cal = Calendar.getInstance();
		// 设置为东八区
		cal.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return cal.getTime();
	}

	/**
	 * Excel Int时间转Date
	 * @param dateAdd Excel Int时间
	 * @return
	 */
	public static Date excelInt2Date(int dateAdd){
		Calendar cal = Calendar.getInstance();
		// 设置为东八区
		cal.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		cal.set(1900,0,1,0,0,0);
		cal.add(Calendar.DAY_OF_YEAR,dateAdd-2);
		return cal.getTime();
	}
	
	/**
	 * 获取某月的天数
	 * @author Rex.Tan
	 * @date 2019年12月19日 上午9:18:02
	 * @param date
	 * @return
	 */
	public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        // calendar.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
	
	/**
	 * 日期 + n 天
	 * @author Rex.Tan
	 * @date 2019年12月19日 上午9:36:10
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addDays(Date date, Integer day) {
		if(day == null) {
			day = 1;
		}
		Calendar cal = Calendar.getInstance();
		// cal.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		cal.setTime(date);
		cal.add(Calendar.DATE, day);
		
		return cal.getTime();
	}
	
	//获取本月第几天
	/**
	 * 获取指定日期。如果是day==31，默认为最后一天
		 * 
		 * @param 
		 * @return
		 * @author King.li
		 * @date 2020-01-03
	 */
	public static Date getDateByDay(Date date,Integer day) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);

		if(day == 31) {	     	       
	     day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);	      
		}		
        cal.set(Calendar.DAY_OF_MONTH, day);	
		return cal.getTime();
	}
	
	public static String date2Str(Date date, String format) {
        if (StringUtils.isBlank(format)) {
            format = DATE_FORMAT_DEFAULT;
        }
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        
        return sdf.format(date);
    }
	
	public static Date str2Date(String dateStr, String format) {
        if (StringUtils.isBlank(format)) {
            format = DATE_FORMAT_DEFAULT;
        }
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        try {
        	SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr);
        } catch (ParseException pe) {
        }
        return null;
    }
	
	/**
	 * 接收参数转换
	 */
	public static Date parse(String source) {
		if (StringUtils.isBlank(source)) {
			return null;
		}
		ParsePosition pos = new ParsePosition(0);
		SimpleDateFormat sdf = getSimpleDateFormat(source);
		if (sdf == null) {
			return null;
		}

		return sdf.parse(source, pos);
	}

	private static SimpleDateFormat getSimpleDateFormat(String source) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		if (Pattern.matches(YYYYMMDD, source)) {
			sdf = new SimpleDateFormat(YYYYMMDD_FORMAT);
		} else if (Pattern.matches(YYYYMMDDHHMMSS, source)) {
			sdf = new SimpleDateFormat(YYYYMMDDHHMMSS_FORMAT);
		} else if (Pattern.matches(YYYYMMDDHHMMSS2, source)) {
			sdf = new SimpleDateFormat(YYYYMMDDHHMMSS2_FORMAT);
		} else if (Pattern.matches(YYYYMMDD2, source)) {
			sdf = new SimpleDateFormat(YYYYMMDD2_FORMAT);
		} else if (Pattern.matches(YYYYMMDDHHMMSS3, source)) {
			sdf = new SimpleDateFormat(YYYYMMDDHHMMSS3_FORMAT);
		} else if (Pattern.matches(YYYYMMDD3, source)) {
			sdf = new SimpleDateFormat(YYYYMMDD3_FORMAT);
		} else if (Pattern.matches(YYYYMMDDHHMMSS4, source)) {
			sdf = new SimpleDateFormat(YYYYMMDDHHMMSS4_FORMAT);
		} else if (Pattern.matches(YYYYMMDD4, source)) {
			sdf = new SimpleDateFormat(YYYYMMDD4_FORMAT);
		} else if (Pattern.matches(YYYYMMDDHHMMSS5, source)) {
			sdf = new SimpleDateFormat(YYYYMMDDHHMMSS5_FORMAT);
		} 
		
		
		
		
		else if (Pattern.matches(YYYYMD, source)) {
			sdf = new SimpleDateFormat(YYYYMD_FORMAT);
		} else if (Pattern.matches(YYYYMDHHMMSS, source)) {
			sdf = new SimpleDateFormat(YYYYMDHHMMSS_FORMAT);
		} else if (Pattern.matches(YYYYMDHHMMSS2, source)) {
			sdf = new SimpleDateFormat(YYYYMDHHMMSS2_FORMAT);
		} else if (Pattern.matches(YYYYMD2, source)) {
			sdf = new SimpleDateFormat(YYYYMD2_FORMAT);
		} else if (Pattern.matches(YYYYMDHHMMSS3, source)) {
			sdf = new SimpleDateFormat(YYYYMDHHMMSS3_FORMAT);
		} else if (Pattern.matches(YYYYMD3, source)) {
			sdf = new SimpleDateFormat(YYYYMD3_FORMAT);
		} else if (Pattern.matches(YYYYMDHHMMSS4, source)) {
			sdf = new SimpleDateFormat(YYYYMDHHMMSS4_FORMAT);
		} else if (Pattern.matches(YYYYMD4, source)) {
			sdf = new SimpleDateFormat(YYYYMD4_FORMAT);
		} else if (Pattern.matches(YYYYMDHHMMSS5, source)) {
			sdf = new SimpleDateFormat(YYYYMDHHMMSS5_FORMAT);
		} else if (Pattern.matches(YYYYMDD, source)) {
			sdf = new SimpleDateFormat(YYYYMDD_FORMAT);
		}else if (Pattern.matches(YYYYMDD2, source)) {
			sdf = new SimpleDateFormat(YYYYMDD2_FORMAT);
		}else if (Pattern.matches(YYYYMDD3, source)) {
			sdf = new SimpleDateFormat(YYYYMDD3_FORMAT);
		}else if (Pattern.matches(YYYYMDD4, source)) {
			sdf = new SimpleDateFormat(YYYYMDD4_FORMAT);
		}else if (Pattern.matches(YYYYMMD, source)) {
			sdf = new SimpleDateFormat(YYYYMMD_FORMAT);
		}else if (Pattern.matches(YYYYMMD2, source)) {
			sdf = new SimpleDateFormat(YYYYMMD2_FORMAT);
		}else if (Pattern.matches(YYYYMMD3, source)) {
			sdf = new SimpleDateFormat(YYYYMMD3_FORMAT);
		}else if (Pattern.matches(YYYYMMD4, source)) {
			sdf = new SimpleDateFormat(YYYYMMD4_FORMAT);
		}
		
		
		
		
		
		else {
			return null;
		}
		return sdf;
	}
	
	/**
	 * 将日期格式"yyyy-MM-dd"和字符串格式"HH:mm"拼接成日期格式yyyy-MM-dd HH:mm:ss
	 * @date 2020年6月13日 下午1:21:42
	 * @param date
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static Date formatDate(Date date, String dateStr) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strTime = simpleDateFormat.format(date);
		String newDateStr = strTime + " " + dateStr + ":00";
		SimpleDateFormat newSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return newSimpleDateFormat.parse(newDateStr);
	}
	
	//比较两个时间大小
	 public static boolean compareDate(String DATE1, String DATE2) {
	        
	        DateFormat df = new SimpleDateFormat("yyyy-MM");
	        try {
	            Date dt1 = df.parse(DATE1);
	            Date dt2 = df.parse(DATE2);
	            if (dt1.getTime() > dt2.getTime()) {
	                return true;
	            } else if (dt1.getTime() < dt2.getTime()) {
	                return false;
	            } else {
	                return true;
	            }
	        } catch (Exception exception) {
	            exception.printStackTrace();
	        }
	        return false;
	    }
}
