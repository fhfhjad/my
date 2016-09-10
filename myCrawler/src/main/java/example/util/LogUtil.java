package example.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//import java.util.stream.Collectors;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
	private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

	private static final int LEVEL_INFO = 2;
	private static final int LEVEL_WARN = 3;
	private static final int LEVEL_ERROR = 4;
	private static final int LEVEL_DEBUG = 1;

	private static final String PACKAGE_FLAG = "com.htjx";

	/**
	 * 别new本类，直接调用静态方法即可
	 */
	private LogUtil() {
	}

	/**
	 * 打印调试信息，可以传两个参数，调用示例见本文件main方法
	 *
	 * @param content
	 * @param params
	 */
	public static void debug(String content, Object... params) {
		messageUp(LEVEL_DEBUG, content, params);
	}

	/**
	 * 打印提示信息，可以传两个参数，调用示例见本文件main方法
	 *
	 * @param content
	 * @param params
	 */
	public static void info(String content, Object... params) {
		messageUp(LEVEL_INFO, content, params);
	}

	/**
	 * 打印警告信息，可以传两个参数，调用示例见本文件main方法
	 *
	 * @param content
	 * @param params
	 */
	public static void warn(String content, Object... params) {
		messageUp(LEVEL_WARN, content, params);
	}

	/**
	 * 打印错误信息，可以传两个参数，调用示例见本文件main方法
	 *
	 * @param content
	 * @param params
	 */
	public static void err(String content, Object... params) {
		messageUp(LEVEL_ERROR, content, params);
	}

	/**
	 * 可以将exception导向这里,多态
	 *
	 * @param e
	 */
	public static void err(Exception e) {
		// e.printStackTrace();
		err("异常:{}", getCauseMsg(e));
	}

	/**
	 * 打印错误信息，直接调用logger.error方法
	 *
	 * @param content
	 * @param params
	 */
	public static void errStackTrace(String content, Exception e) {
		logger.error(content, e);
	}

	/**
	 * 可以将exception导向这里,多态
	 *
	 * @param e
	 */
	public static void err(Throwable e) {
		// e.printStackTrace();
		err("异常:{}", getCauseMsg(e));
	}

	/**
	 * 可以将exception导向这里,多态
	 *
	 * @param e
	 */
	public static void err(String content, Exception e) {
		err(content.concat("{}"), getCauseMsg(e));
	}

	/**
	 * 可以将exception导向这里,多态 用法：LogUtil.err("该ID{}出现错误:",e,entityId1);
	 *
	 * @param e
	 */
	public static void err(String content, Object param, Exception e) {
		content = content.replace("{}", param.toString());
		err(content, e);
	}

	/**
	 * 复杂打印方法
	 *
	 * @param level
	 * @param content
	 * @param params
	 */
	private static void messageUp(Integer level, String content,
			Object... params) {
		StringBuffer bufContent = new StringBuffer();

		// 获取调用信息
		Map<String, String> retMap = getMapStackMessage();
		// 进行logger筛选
		Logger curLogger = logger;

		bufContent.append(retMap.get("content"));
		bufContent.append(content);
		switch (level) {
		case LEVEL_DEBUG:
			if (curLogger.isDebugEnabled()) {
				curLogger.debug("DEBUG:" + bufContent.toString(), params);
			}
			break;
		case LEVEL_WARN:
			if (curLogger.isWarnEnabled()) {
				curLogger.warn("WARN:" + bufContent.toString(), params);
			}
			break;
		case LEVEL_ERROR:
			curLogger.error("ERROR:" + bufContent.toString(), params);
			break;
		default:
			if (curLogger.isInfoEnabled()) {
				curLogger.info("INFO:" + bufContent.toString(), params);
			}
		}
	}

	/**
	 * 返回调用信息，类信息，包含注解，类名，格式化信息
	 *
	 * @return
	 */
	private static Map<String, String> getMapStackMessage() {
		Map<String, String> retMap = new HashMap<>();

		StackTraceElement stack[] = Thread.currentThread().getStackTrace();
		for (StackTraceElement ste : stack) {
			if ((ste.getClassName().indexOf("LogUtil")) == -1
					&& ste.getClassName().indexOf("Thread") == -1) {
				String className = ste.getClassName();
				String fileName = ste.getFileName();
				if (fileName != null)
					fileName = fileName.replace(".java", "->");

				StringBuffer buf = new StringBuffer();

				String logTime = DateUtil.dateToString(new Date(System
						.currentTimeMillis()));
				buf.append(logTime).append(": ").append(fileName);
				buf.append(ste.getMethodName()).append("(")
						.append(ste.getLineNumber()).append(")").append("：");

				retMap.put("class", className);
				retMap.put("content", buf.toString());
				return retMap;
			}
		}
		return retMap;
	}

	/**
	 * 获取，过滤并格式化异常信息
	 *
	 * @param e
	 * @return
	 */
	private static String getCauseMsg(Throwable e) {
		StringBuffer stackBuf = new StringBuffer();
		stackBuf.append(e.toString()).append(" \r\nat ");
		String stacks = Arrays
				.asList(e.getStackTrace())
				.stream()
				.filter(el -> el.getClassName().contains(PACKAGE_FLAG))
				.filter(el -> !el.getClassName().contains("framework"))
				.filter(el -> el.getLineNumber() != -1)
				.map(el -> {
					StringBuffer buf = new StringBuffer();
					buf.append(el.getClassName()).append(".")
							.append(el.getMethodName()).append("(")
							.append(el.getLineNumber()).append(")");
					return buf.toString();
				}).collect(Collectors.joining(" \r\nat "));
		stackBuf.append(stacks);
		return stackBuf.toString();
	}

	/**
	 * 返回当前是否充许debug
	 *
	 * @return
	 */
	public static boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	/**
	 * 调用示例
	 *
	 * @param args
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		LogUtil.info("hello {},{}", "1", "2", "3");
		LogUtil.info("hello {},i am {}", 1333, "mike");
		LogUtil.info("hello {},i am {}", "美女", "英雄");
		LogUtil.info("hello 你最棒！");

		// 异常,几种重载
		String orderId = "P1234567";
		Exception ex = new RuntimeException("这是一个异常！");
		LogUtil.err(ex);
		LogUtil.err("处理支付时发生异常：", ex);
		LogUtil.err("下单异常 id={}", orderId, ex);

		double pricePerProduct = new BigDecimal(
				Double.parseDouble("999999.00") * 1).setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue();
		LogUtil.debug("price:" + pricePerProduct);
	}
}
