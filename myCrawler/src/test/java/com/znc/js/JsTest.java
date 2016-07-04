/**   
 * @Title: JsTest.java
 * @Package com.znc.js
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年6月11日 下午3:14:59
 * @version V1.0   
 */
package com.znc.js;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

/**
 * @ClassName: JsTest
 * @Description: TODO
 * @author sunlulu
 * @date 2015年6月11日 下午3:14:59
 */

public class JsTest {

	@Test
	public void test() {
//		Context ctx = Context.enter();
//		Scriptable scope = ctx.initStandardObjects();

//			Object result = ctx.evaluateReader(scope, new FileReader(new File(
//					"./js/mi.js")), null, 0, null);
//			System.out.println("result=" + result);
//			
//			
//		
//
//			Object fobj = scope.get("jingoalSHA1.hex_sha1", scope);
//			if (fobj instanceof Function) {
//				System.out.println("找不到");
//			}else{
//				Object fp[] = {"12343"};
//				Function f = (Function)fobj;
//				Object result1 = f.call(ctx, scope, scope, fp);
//				System.out.println("返回结果："+Context.toString(result1));
//			}
			
			
			// create a script engine manager
	        ScriptEngineManager factory = new ScriptEngineManager();
	        // create a JavaScript engine
	        ScriptEngine engine = factory.getEngineByName("JavaScript");
	        // evaluate JavaScript code from String
	        try {
				engine.eval(new FileReader(new File("./js/mi.js")));
				
				Invocable inv = (Invocable) engine;

		        // invoke the global function named "hello"
//				 Object obj = engine.get("jingoalSHA1");
				 Object obj = engine.get("jingoalRootURL");
				 System.out.println(obj);
				 
				 Object objF = engine.get("jingoalSHA1");
				 
				 Object xx = inv.invokeMethod(objF, "hex_sha1", "12456");
				 System.out.println(xx);
				 
		        
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}
