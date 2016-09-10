package video;

import java.io.File;

import org.junit.Test;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.FFMPEGLocator;
import it.sauronsoftware.jave.MultimediaInfo;


public class TestVideoLong {

	@Test
	public void test() {
		String file = "E:\\video\\xx.avi";
		 File source = new File(file);
		 if(!source.exists()){
			 System.out.println("文件不存在");
			 return;
		 }
	        Encoder encoder = new Encoder();
	        try {
	             MultimediaInfo m = encoder.getInfo(source);
	             long ls = m.getDuration();
	             System.out.println("此视频时长为:"+ls/60000+"分");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
