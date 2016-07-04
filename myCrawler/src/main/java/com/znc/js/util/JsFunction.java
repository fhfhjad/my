/**   
 * @Title: JsFuction.java
 * @Package com.znc.js.util
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年6月11日 下午10:45:39
 * @version V1.0   
 */
package com.znc.js.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @ClassName: JsFuction
 * @Description: TODO
 * @author sunlulu
 * @date 2015年6月11日 下午10:45:39
 */

public class JsFunction {
	public static String getPasswrod(String str) {
		// create a script engine manager
		ScriptEngineManager factory = new ScriptEngineManager();
		// create a JavaScript engine
		ScriptEngine engine = factory.getEngineByName("JavaScript");
		// evaluate JavaScript code from String
		try {
			engine.eval(new FileReader(new File("./js/mi.js")));

			Invocable inv = (Invocable) engine;

			// invoke the global function named "hello"
			// Object obj = engine.get("jingoalSHA1");
			Object obj = engine.get("jingoalRootURL"); // test
			System.out.println(obj);

			Object objF = engine.get("jingoalSHA1");
			Object xx = inv.invokeMethod(objF, "hex_sha1", str);
			return (String) xx;

		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
