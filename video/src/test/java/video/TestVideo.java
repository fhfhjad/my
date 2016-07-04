package video;

import static org.junit.Assert.*;

import java.util.TreeMap;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.qcloud.QcloudApiModuleCenter;
import com.qcloud.Module.Vod;
import com.qcloud.Utilities.Json.JSONObject;

public class TestVideo {
	
	private static Logger logger = Logger.getLogger(TestVideo.class);

	@Test
	public void test() {
		/* 如果是循环调用下面举例的接口，需要从此处开始你的循环语句。切记！ */
		TreeMap<String, Object> config = new TreeMap<String, Object>();
		
		config.put("SecretId", "AKIDMXn6p5WCeKnXn26Ya7q1hqjLhltimlAm");
		config.put("SecretKey", "ZASCdLxs0ryXi4SV9VDERZLhUeRZ6WSf");
		/* 请求方法类型 POST、GET */
		config.put("RequestMethod", "GET");
		/* 区域参数，可选: gz:广州; sh:上海; hk:香港; ca:北美;等。 */
		config.put("DefaultRegion", "gz");
		
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(),
				config);
		
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		/* 将需要输入的参数都放入 params 里面，必选参数是必填的。 */
		/*params.put("offset", 0);
		params.put("limit", 3);*/
		
		String result = null;
		try {
			/* call 方法正式向指定的接口名发送请求，并把请求参数params传入，返回即是接口的请求结果。 */
			result = module.call("DescribeClass", params);
			JSONObject json_result = new JSONObject(result);
			
			logger.info(new ReflectionToStringBuilder(json_result));
			
		} catch (Exception e) {
			System.out.println("error..." + e.getMessage());
			throw new RuntimeException(e);
			
		}
		
	}

}
