/**
 * @Title: MoveBit.java
 * @Package example.test
 * @Description: TODO
 * @author sunlulu
 * @date 2016年2月3日 下午8:06:24
 * @version V1.0
 */
package example.test;

/**
 * @ClassName: MoveBit
 * @Description: TODO
 * @author sunlulu
 * @date 2016年2月3日 下午8:06:24
 */
public class MoveBit {

	/**
	 * @Title: main
	 * @Description: TODO
	 * @author sunlulu
	 * @param args
	 * @return void
	 * @throws
	 */
	public static void main(String[] args) {
		int num = 5;
		printInfo(num);
		// 左移一位
		// num <<= 1;

		// 右移一位
		num >>>= 3;
		printInfo(num);

	}

	public static void printInfo(int num) {
		System.out.println(num + " 二进制数:" + Integer.toBinaryString(num)
				+ " , 位数:" + Integer.toBinaryString(num).length());
	}

}
