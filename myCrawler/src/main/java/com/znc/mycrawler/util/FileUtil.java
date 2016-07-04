package com.znc.mycrawler.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.log4j.Logger;

public class FileUtil {
	private static Logger logger = Logger.getLogger(FileUtil.class);

	private static ResourceBundle resource = ResourceBundle
			.getBundle("application");
	public static String PRODUCTS_PATH = resource.getString(
			"products.file.path").trim();

	/**
	 * 
	 * @Title: getValue
	 * @Description: 获取配置文件的属性
	 * @author sunlulu
	 * @param @param key
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getValue(String key) {
		return resource.getString(key).trim();
	}

	/**
	 * 
	 * @Title: getProductsFilePath
	 * @Description: 根据产品Id和产品对应的图片名，返回保存的决定地址
	 * @author sunlulu
	 * @param @param productId
	 * @param @param imageName
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getProductsFilePath(String productId, String imageName) {
		StringBuffer destsb = new StringBuffer();
		destsb.append(PRODUCTS_PATH).append(File.separator).append(productId);
		destsb.append(File.separator).append(imageName);
		return destsb.toString();
	}

	/**
	 * 
	 * @Title: getPicToStore
	 * @Description: TODO
	 * @author sunlulu
	 * @param @param productId
	 * @param @param url
	 * @param @throws MalformedURLException
	 * @param @throws IOException
	 * @return void
	 * @throws
	 */
	public static void savePicToStore(String productId, String url) {
		String imageName = url.substring(url.lastIndexOf("/") + 1);
		try {
			InputStream in = new URL(url).openStream();
			byte[] gif = IOUtils.toByteArray(in);
			String dest = getProductsFilePath(productId, imageName);
			FileUtils.writeByteArrayToFile(new File(dest), gif);
			IOUtils.closeQuietly(in);
			logger.info("save pic:" + dest +" success");
		} catch (Exception e) {
			
			logger.info("imageUrl exception url Path = " + url);
			logger.info(e.getMessage());
		}

	}

	/**
	 * 复制文件或者目录,复制前后文件完全一样。
	 * 
	 * @param resFilePath
	 *            源文件路径
	 * @param destFolder
	 *            目标文件夹
	 * @IOException 当操作发生异常时抛出
	 */
	public static void copyFile(String resFilePath, String destFolder)
			throws IOException {
		File resFile = new File(resFilePath);
		File destFile = new File(destFolder);
		if (resFile.isDirectory()) {
			FileUtils.copyDirectoryToDirectory(resFile, destFile);
		} else if (resFile.isFile()) {
			FileUtils.copyFileToDirectory(resFile, destFile, true);
		}
	}

	/**
	 * 删除一个文件或者目录
	 * 
	 * @param targetPath
	 *            文件或者目录路径
	 * @IOException 当操作发生异常时抛出
	 */
	public static void deleteFile(String targetPath) throws IOException {
		File targetFile = new File(targetPath);
		if (targetFile.isDirectory()) {
			FileUtils.deleteDirectory(targetFile);
		} else if (targetFile.isFile()) {
			targetFile.delete();
		}
	}

	/**
	 * 删除目录下的所有文件
	 * 
	 * @param targetPath
	 *            目录路径或者文件
	 * @IOException 当操作发生异常时抛出
	 */
	public static void deleteFloderFile(String targetPath) throws IOException {
		File targetFile = new File(targetPath);
		if (targetFile.isDirectory()) {
			File[] files = targetFile.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					FileUtils.deleteDirectory(file);
				} else if (file.isFile()) {
					file.delete();
				}
			}
		} else if (targetFile.isFile()) {
			targetFile.delete();
		}
	}

	/**
	 * 移动文件或者目录,移动前后文件完全一样,如果目标文件夹不存在则创建。
	 * 
	 * @param resFilePath
	 *            源文件路径
	 * @param destFolder
	 *            目标文件夹
	 * @IOException 当操作发生异常时抛出
	 */
	public static void moveFile(String resFilePath, String destFolder)
			throws IOException {
		File resFile = new File(resFilePath);
		File destFile = new File(destFolder);
		if (resFile.isDirectory()) {
			FileUtils.moveDirectoryToDirectory(resFile, destFile, true);
		} else if (resFile.isFile()) {
			FileUtils.moveFileToDirectory(resFile, destFile, true);
		}
	}

	/**
	 * 移动某一文件或者目录下的某一文件，到另一目录中，并改名
	 * 
	 * @param resFilePath
	 *            源文件路径或者目录
	 * @param destFilePath
	 *            目标文件路径
	 * @IOException 当操作发生异常时抛出
	 */
	public static void moveModifyFile(String resFilePath, String destFilePath)
			throws IOException {
		File srcFile = new File(resFilePath);
		File destFile = new File(destFilePath);

		if (destFile.isFile()) {
			destFile.delete();
		}

		if (srcFile.isFile()) {
			FileUtils.moveFile(srcFile, destFile);
		} else if (srcFile.isDirectory()) {
			File[] files = srcFile.listFiles();
			for (File file : files) {
				if (file.isFile()) {
					FileUtils.moveFile(file, destFile);
				}
			}
		}
	}

	/**
	 * 读取文件或者目录的大小
	 * 
	 * @param destFilePath
	 *            目标文件或者文件夹
	 * @return 文件或者目录的大小，如果获取失败，则返回-1
	 */
	public static long genFileSize(String destFilePath) {
		File destFile = new File(destFilePath);
		if (destFile.isFile()) {
			return destFile.length();
		} else if (destFile.isDirectory()) {
			return FileUtils.sizeOfDirectory(destFile);
		}
		return -1L;
	}

	/**
	 * 判断一个文件是否存在
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 存在返回true，否则返回false
	 */
	public static boolean isExist(String filePath) {
		return new File(filePath).exists();
	}

	/**
	 * 本地某个目录下的文件列表（不递归）
	 * 
	 * @param folder
	 *            ftp上的某个目录
	 * @param suffix
	 *            文件的后缀名（比如.mov.xml)
	 * @return 文件名称列表
	 */
	public static String[] listFilebySuffix(String folder, String suffix) {
		IOFileFilter fileFilter1 = new SuffixFileFilter(suffix);
		IOFileFilter fileFilter2 = new NotFileFilter(
				DirectoryFileFilter.INSTANCE);
		FilenameFilter filenameFilter = new AndFileFilter(fileFilter1,
				fileFilter2);
		return new File(folder).list(filenameFilter);
	}

	/**
	 * 将字符串写入指定文件(当指定的父路径中文件夹不存在时，会最大限度去创建，以保证保存成功！)
	 * 
	 * @param res
	 *            原字符串
	 * @param filePath
	 *            文件路径
	 * @return 成功标记
	 */
	public static boolean string2File(String res, String filePath) {
		boolean flag = true;
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		try {
			File destFile = new File(filePath);
			if (!destFile.getParentFile().exists())
				destFile.getParentFile().mkdirs();
			bufferedReader = new BufferedReader(new StringReader(res));
			bufferedWriter = new BufferedWriter(new FileWriter(destFile, true)); // 追加
			char buf[] = new char[1024]; // 字符缓冲区
			int len;
			while ((len = bufferedReader.read(buf)) != -1) {
				bufferedWriter.write(buf, 0, len);
			}
			bufferedWriter.flush();
			bufferedReader.close();
			bufferedWriter.close();
		} catch (IOException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	// public static void deleteFiles(String userId,String applicationId) throws
	// IOException {
	// StringBuffer destsb = new StringBuffer();
	// destsb.append(STORE_PATH).append(File.separator).append(userId)
	// .append(File.separator).append(applicationId)
	// // .append(File.separator).append(applicationId).append(".war");
	// .append(File.separator).append(applicationId).append(File.separator);
	// File targetFile = new File(destsb.toString());
	// if (targetFile.isDirectory()) {
	// FileUtils.deleteDirectory(targetFile);
	// } else if (targetFile.isFile()) {
	// targetFile.delete();
	// }
	// }

}
