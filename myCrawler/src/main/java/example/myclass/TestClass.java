/**
 * @Title: TestClass.java
 * @Package example.myclass
 * @Description: TODO
 * @author sunlulu
 * @date 2016年1月31日 上午9:54:17
 * @version V1.0
 */
package example.myclass;

/**
 * @ClassName: TestClass
 * @Description: TODO
 * @author sunlulu
 * @date 2016年1月31日 上午9:54:17
 */
public class TestClass {

	/*
	 * 构造函数
	 */
	public TestClass() {
		System.out.println(" 构造函数");
	}

	/*
	 * 静态的参数初始化
	 */
	static {
		System.out.println("静态的参数初始化  ");
	}

	/*
	 * 非静态的参数初始化
	 */
	{
		System.out.println("非静态的参数初始化  ");
	}

	/**
	 * @Title: main
	 * @Description: TODO
	 * @author sunlulu
	 * @param args
	 * @return void
	 * @throws
	 */
	public static void main(String[] args) {

		try {
			// 测试Class.forName()
			Class<?> classTest = Class.forName("example.myclass.TestClass");
			System.out.println("classForName1:[" + classTest + "]");

			// 测试类名.class
			Class<TestClass> class2 = TestClass.class;
			System.out.println("classForName2:[" + class2 + "]");

			// 测试Object.getClass()
			TestClass newInstance = new TestClass();
			System.out.println("newInstance   : [" + newInstance.getClass()
					+ "]");

			// hashCode指的是内存的地址
			System.out.println("newInstanceHashCode   : ["
					+ newInstance.hashCode() + "]");
			/*
			 * toString代表该对象的一个字符串 格式：this.getClass().getName() + '@' +
			 * Integer.toHexString(hashCode())
			 */
			System.out.println("newInstanceToString   : ["
					+ newInstance.toString() + "]");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
