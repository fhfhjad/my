/**
 * 
 */
package example.reflect;

/**
 * @author sunlulu 2016年5月4日 描述：
 */
public class Person {
	public String name;
	private int age;

	public Person() {
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return this.age;
	}

	public String getName() {
		return this.name;
	}

	public void show() {
		System.out.println("我是一个人！");

	}

	private void show2() {
		System.out.println("你也是一个人！");
	}

}