package com.htjx.video.serverlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qcloud.QcloudApiModuleCenter;
import com.qcloud.Module.Vod;
import com.qcloud.Utilities.Json.JSONObject;

public class VideoServerlet extends HttpServlet {

	/**
	 * @Fields serialVersionUID: TODO
	 */
	private static final long serialVersionUID = 1L;

	/*public static Logger logger = null;*/
	public VideoServerlet() {super();}
	public void destroy() {super.destroy();}
	
	/* 如果是循环调用下面举例的接口，需要从此处开始你的循环语句。切记！ */
	TreeMap<String, Object> config = new TreeMap<String, Object>();
	
	/**
	 * 初始化参数
	 */
	public void init() throws ServletException {
		/*logger=Logger.getLogger(this.getClass());*/
		config.put("SecretId", "AKIDMXn6p5WCeKnXn26Ya7q1hqjLhltimlAm");
		config.put("SecretKey", "ZASCdLxs0ryXi4SV9VDERZLhUeRZ6WSf");
		/* 请求方法类型 POST、GET */
		config.put("RequestMethod", "GET");
		/* 区域参数，可选: gz:广州; sh:上海; hk:香港; ca:北美;等。 */
		config.put("DefaultRegion", "gz");
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String operating=String.valueOf(request.getParameter("actionName"));
		/*logger.debug("操作:CpInfo_"+operating);*/
		try {
			if(operating.equals("describeClass")){
				out.print(describeClass(request));
			}else if(operating.equals("describeAllClass")){
				out.print(describeAllClass(request));
			}else if(operating.equals("describeVodInfo")){
				out.print(describeVodInfo(request));
			}else if(operating.equals("describeVodPlayUrls")){
				out.print(describeVodPlayUrls(request));
			}else if(operating.equals("describeVodPlayInfo")){
				out.print(describeVodPlayInfo(request));
			}
			
		} catch (Exception e) {
			/*logger.error("",e);*/
			String errorStr ="{\"code\":-300333,\"message\":"+e.getMessage()+"}"; 
			out.print(errorStr);
		}
	}
	/**
	 * 获取视频分类列表
	 * @param request
	 * @return JSONObject
	 * @author: liyong
	 */
	public JSONObject  describeClass(HttpServletRequest request){
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
			return json_result;
		} catch (Exception e) {
			System.out.println("error..." + e.getMessage());
			throw new RuntimeException(e);
			
		}

	}
	
	/**
	 * 获得当前用户所有的分类层级关系
	 * @param request
	 * @return JSONObject
	 * @author: liyong
	 */
	public JSONObject  describeAllClass(HttpServletRequest request){
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(),
				config);

		TreeMap<String, Object> params = new TreeMap<String, Object>();
		/* 将需要输入的参数都放入 params 里面，必选参数是必填的。 */
		/*params.put("offset", 0);
		params.put("limit", 3);*/
		
		String result = null;
		try {
			/* call 方法正式向指定的接口名发送请求，并把请求参数params传入，返回即是接口的请求结果。 */
			result = module.call("DescribeAllClass", params);
			JSONObject json_result = new JSONObject(result);
			return json_result;
		} catch (Exception e) {
			System.out.println("error..." + e.getMessage());
			throw new RuntimeException(e);
			
		}

	}
	/**
	 * 获取视频信息
	 * @param request
	 * @return JSONObject
	 * @author: liyong
	 */
	public JSONObject describeVodInfo(HttpServletRequest request){
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(),
				config);

		TreeMap<String, Object> params = new TreeMap<String, Object>();
		/* 将需要输入的参数都放入 params 里面，必选参数是必填的。 */
		if(request.getParameter("classId")==null||request.getParameter("classId").length()<=0){
			/*throw new RuntimeException("视频分类ID不可以为空");*/
		}else{
			params.put("classId", request.getParameter("classId"));
		}
		
		
		if(request.getParameter("pageNo")!=null&&request.getParameter("pageSize")!=null&&request.getParameter("pageNo").length()>0&&request.getParameter("pageSize").length()>0){
			params.put("pageNo", request.getParameter("pageNo"));
			params.put("pageSize", request.getParameter("pageSize"));
		}
		if(request.getParameter("from")!=null&&request.getParameter("to")!=null&&request.getParameter("from").length()>0&&request.getParameter("to").length()>0){
			params.put("from", request.getParameter("from"));
			params.put("to", request.getParameter("to"));
		}
		if(request.getParameter("status")!=null&&request.getParameter("status").length()>0){
			params.put("status", request.getParameter("status"));
		}
		if(request.getParameter("fileIds.n")!=null&&request.getParameter("fileIds.n").length()>0){
			params.put("fileIds.1", request.getParameter("fileIds.n"));
		}
		if(request.getParameter("orderby")!=null&&request.getParameter("orderby").length()>0){
			params.put("orderby", request.getParameter("orderby"));
		}
		
		/* generateUrl 方法生成请求串，但不发送请求。在正式请求中，可以删除下面这行代码。 */
		String result = null;
		try {
			/* call 方法正式向指定的接口名发送请求，并把请求参数params传入，返回即是接口的请求结果。 */
			result = module.call("DescribeVodInfo", params);
			JSONObject json_result = new JSONObject(result);
			return json_result;
		} catch (Exception e) {
			System.out.println("error..." + e.getMessage());
			throw new RuntimeException(e);
			
		}

	}
	/**
	 * 获取视频的播放信息 
	 * @param request
	 * @return JSONObject
	 * @author: liyong
	 */
	public JSONObject describeVodPlayUrls(HttpServletRequest request){
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(),
				config);

		TreeMap<String, Object> params = new TreeMap<String, Object>();
		/* 将需要输入的参数都放入 params 里面，必选参数是必填的。 */
		if(request.getParameter("fileId")==null||request.getParameter("fileId").length()<=0){
			throw new RuntimeException("希望获取的视频的ID不可以为空");
		}
		params.put("fileId", request.getParameter("fileId"));
		/*params.put("limit", 3);*/
		
		String result = null;
		try {
			/* call 方法正式向指定的接口名发送请求，并把请求参数params传入，返回即是接口的请求结果。 */
			result = module.call("DescribeVodPlayUrls", params);
			JSONObject json_result = new JSONObject(result);
			return json_result;
		} catch (Exception e) {
			System.out.println("error..." + e.getMessage());
			throw new RuntimeException(e);
			
		}

	}
	/**
	 * 获取视频播放信息
	 * @param request
	 * @return JSONObject
	 * @author: liyong
	 */
	public JSONObject describeVodPlayInfo(HttpServletRequest request){
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(),
				config);

		TreeMap<String, Object> params = new TreeMap<String, Object>();
		/* 将需要输入的参数都放入 params 里面，必选参数是必填的。 
		 DescribeInstances 接口的部分可选参数如下 */
		if(request.getParameter("fileName")==null||request.getParameter("fileName").length()<=0){
			throw new RuntimeException("视频名称不可以为空");
		}
		params.put("fileName", request.getParameter("fileName"));
		
		if(request.getParameter("pageNo")!=null&&request.getParameter("pageSize")!=null&&request.getParameter("pageNo").length()>0&&request.getParameter("pageSize").length()>0){
			params.put("pageNo", request.getParameter("pageNo"));
			params.put("pageSize", request.getParameter("pageSize"));
		}
		
		String result = null;
		try {
			// call 方法正式向指定的接口名发送请求，并把请求参数params传入，返回即是接口的请求结果。 
			result = module.call("DescribeVodPlayInfo", params);
			JSONObject json_result = new JSONObject(result);
			return json_result;
		} catch (Exception e) {
			System.out.println("error..." + e.getMessage());
			throw new RuntimeException(e); 
			
		}

	}
	
	
	
	
	
	
	
	
}
