package example.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TestHashMap {
	/**
	 * 
	 * @Title: main
	 * @Description: TODO
	 * @author sunlulu
	 * @param args
	 * @throws Exception
	 * @return void
	 * @throws
	 */
	public static void main(String[] args) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("test", 1);

		Set<Entry<String, Object>> set = map.entrySet();
		for (Entry<String, Object> entry : set) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

}
