package com.znc.mycrawler.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

import com.znc.mycrawler.util.uuid.UUIDHexGenerator;

public class CommonUtil {

	private static final Log log = LogFactory.getLog(CommonUtil.class);

	private static final String DATE_STYLE = "yyyy-MM-dd";
	private static final String TIME_STYLE = "yyyy-MM-dd HH:mm:ss";

	private static UUIDHexGenerator uuid = new UUIDHexGenerator();

	/**
	 * 取得不重复的32位字符串
	 * 
	 * @return
	 */
	public static String getId() {
		return (String) uuid.generate();
	}

	/**
	 * 
	 * @Description: 生成随机的6位数字
	 * @return
	 * @return String
	 * @throws
	 */
	public static String getRamdomSix() {
		Random random = new Random();
		String sRand = "";
		for (int i = 0; i < 6; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand = sRand + rand;
		}
		return sRand;
	}

	/**
	 * null字符串转化为""
	 * 
	 * @param s
	 *            输入字符串
	 * @return 输出字符串
	 */
	public static String replaceNull2Space(String s) {
		if (s == null)
			return "";
		if (s.trim().toUpperCase().equals("NULL"))
			return "";
		return s.trim();
	}

	/**
	 * 日期转字符串
	 * 
	 * @param dt
	 *            日期类型
	 * @return yyyy-MM-dd类型
	 */
	public static String Date2String(Date dt) {
		if (dt == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_STYLE);
		try {
			return sdf.format(dt);
		} catch (Exception ex) {
			log.error("==ComUtil:Date2String==：" + ex);
			return "";
		}
	}

	/**
	 * Timestamp 转换成 日期类型
	 */
	public static String Timestamp2String(Timestamp ds) {
		if (ds == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_STYLE);
		try {
			return sdf.format(ds);
		} catch (Exception ex) {
			log.error("==ComUtil:Date2String==：" + ex);
			return "";
		}
	}

	/**
	 * Timestamp 转换成 日期类型
	 */
	public static String TimestampTime2String(Timestamp ds) {
		if (ds == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_STYLE);
		try {
			return sdf.format(ds);
		} catch (Exception ex) {
			log.error("==ComUtil:Date2String==：" + ex);
			return "";
		}
	}

	/**
	 * 时间转字符串
	 * 
	 * @param dt
	 *            日期类型
	 * @return yyyy-MM-dd类型
	 */
	public static String Time2String(Date dt) {
		if (dt == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_STYLE);
		try {
			return sdf.format(dt);
		} catch (Exception ex) {
			log.error("==ComUtil:Time2String==：" + ex);
			return "";
		}
	}

	/**
	 * 字符串转时间
	 * 
	 * @param date
	 *            String类型
	 * @return Date
	 */
	public static Date String2Time(String time) {
		if ("".equals(replaceNull2Space(time))) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(TIME_STYLE);
		Date d = null;
		if (time != null && !time.equals("")) {
			try {
				d = format.parse(time);
			} catch (Exception ex) {
				log.error("==ComUtil:String2Time==：" + ex);
			}
		}
		return d;
	}

	/**
	 * 字符串转时间
	 * 
	 * @param date
	 *            String类型
	 * @return Date
	 */
	public static Date String2Date(String date) {
		if ("".equals(replaceNull2Space(date))) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(DATE_STYLE);
		Date d = null;
		if (date != null && !date.equals("")) {
			try {
				d = format.parse(date);
			} catch (Exception ex) {
				log.error("==ComUtil:String2Date==：" + ex);
			}
		}
		return d;
	}

	/**
	 * 遍历获取两个日期之间所有的日期
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<String> getBetweenDateList(String startDate,
			String endDate) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> betweenDays = new ArrayList<String>();
		try {
			Date begin = sdf.parse(startDate);
			Date end = sdf.parse(endDate);
			double between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
			double day = between / (24 * 3600);
			betweenDays.add(startDate);
			for (int i = 1; i <= day; i++) {
				Calendar cd = Calendar.getInstance();
				cd.setTime(sdf.parse(startDate));
				cd.add(Calendar.DATE, i);// 增加一天
				// cd.add(Calendar.MONTH, n);//增加一个月

				betweenDays.add(sdf.format(cd.getTime()));
				System.out.println(sdf.format(cd.getTime()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return betweenDays;

	}

	/**
	 * 方法用于日期条件查询，获取后一天凌点的时间
	 * 
	 * @param nowDay
	 * @return
	 */
	public static String getAfterDay(String nowDay) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String afterDay = null;
		try {
			Date now = sdf.parse(nowDay);
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);

			cal.add(Calendar.DAY_OF_MONTH, 1);
			afterDay = sdf.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return afterDay;

	}

	/**
	 * 方法用于日期条件查询，获取当前时间前一天凌点的时间
	 * 
	 * @param nowDay
	 * @return
	 */
	public static String getBeforeDay(String nowDay) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String beforeDay = null;
		try {
			Date now = sdf.parse(nowDay);
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);

			cal.add(Calendar.DAY_OF_MONTH, -1);
			beforeDay = sdf.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return beforeDay;

	}

	// 设置邮件接受地址toaddress，邮件标题subject，邮件内容content，然后发邮件
	// public static boolean sendHtmlMail(String toAddress,String subject,String
	// content){
	//
	// MailSenderInfo mailInfo = new MailSenderInfo();
	// mailInfo.setToAddress(toAddress);
	// mailInfo.setSubject(subject);
	// mailInfo.setContent(content);
	// boolean b = SimpleMailSender.sendHtmlMail(mailInfo);//发送html格式
	// return b;
	// }

}
