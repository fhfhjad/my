/**
 * 
 */
package example.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author sunlulu 2016年5月4日 描述：
 */
public class Main {

	// 二分查找可以解决（预排序数组的查找）问题：
	public static int search(int[] array, int low, int high, int value) {
		if (low <= high) {
			int mid = (low + high) / 2;
			if (array[mid] == value) {
				return mid;
			} else if (array[mid] < value) {
				return search(array, mid + 1, high, value);
			} else {
				return search(array, low, mid - 1, value);
			}
		} else {
			return -1;
		}
	}

	public static void main(String[] args) {
		// 1.必须要有默认构造函数否则报错
		// Class<Person> clazz = Person.class;
		// try {
		// Person person = clazz.newInstance();
		// person.setAge(1);
		// System.out.println(person);
		// } catch (InstantiationException | IllegalAccessException e) {
		// e.printStackTrace();
		// }

		// 2.获取属性信息
		Class<Person> clazz = Person.class;
		try {
			Field f1 = clazz.getField("show"); // 获取show属性，由于不存在show属性，抛异常
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		Field[] field = clazz.getFields();
		// 用getFields()方法只能得到public属性的参数，也可以得到父类中的public属性值
		for (int i = 0; i < field.length; i++) {
			System.out.println(field[i]);
		}
		System.out.println("------");
		// 可获取所有的属性，但只能是运行时类本身的所有属性
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i]);
		}
		System.out.println("------");

		// 3.获取构造函数
		Constructor[] con = clazz.getDeclaredConstructors();
		for (int i = 0; i < con.length; i++) {
			System.out.println("第" + i + "个构造器" + con[i].getName());
			// 得到构造器的形参
			Class[] pa = con[i].getParameterTypes();
			for (Class m : pa) {
				System.out.println(m + ";");
			}
		}
		System.out.println("------");

		// 4.获取方法的完整结构
		Method[] methods = clazz.getMethods();
		for (Method me : methods) {
			System.out.println(me);
		}

		System.out.println("------");
		// 5.获取方法的 注解，权限修饰符，返回值 方法名 形参列表 异常等等
		Method[] methods2 = clazz.getDeclaredMethods();// 获取类本身的所有方法
		for (Method m : methods2) {
			// 5.1.注解
			Annotation[] a = m.getAnnotations();// 得到方法的注解
			for (Annotation an : a) {
				System.out.println(an + " ");
			}

			// 5.2.权限修饰符
			System.out.println("权限是" + Modifier.toString(m.getModifiers()));

			// 5.3.返回值类型
			System.out.println("返回值类型是" + m.getReturnType());

			// 5.4.方法名
			System.out.println(m.getName());

			// 5.5.形参列表
			Class[] c = m.getParameterTypes();
			System.out.print("形参列表：");
			for (Class cl : c) {
				System.out.print(cl.getName() + " ");
			}
			// 5.6,异常
			Class[] ex = m.getExceptionTypes();
			if (ex.length > 0)
				System.out.print("throws");
			for (int i = 0; i < ex.length; i++) {
				System.out.println(ex[i].getName() + " " + i + " ");
			}
			System.out.println();
		}
		System.out.println("------");

		// 6.调用运行时类的指定方法
		try {
			// 调用无参数show方法
			Method showMethod = clazz.getMethod("show");

			// 调用有参数方法
			Method setAgeMethod = clazz.getMethod("setAge", int.class);
			Method getAgeMethod = clazz.getMethod("getAge");

			Person person = clazz.newInstance();
			showMethod.invoke(person);

			setAgeMethod.invoke(person, 12);
			Object object = getAgeMethod.invoke(person);
			System.out.println(object);

		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

}
