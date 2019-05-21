package org.frame.filter;

import com.alibaba.fastjson.JSON;
import org.frame.filter.repeatRequest.RepeatRequestFilter;
import org.frame.util.PropertiesUtil;
import org.frame.common.Common;
import org.frame.response.JsonRestResponse;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * 作者： Li.Wei
 * 时间： 2018/8/7 14:04
 * 描述： 防止请求重复提交
 **/
public class Signature {


	/**
	 * 作者： Li.Wei
	 * 时间： 2018/8/7 14:04
	 * 描述： 防止请求重复提交
	 **/
	public static boolean checkSign(HttpServletRequest request, HttpServletResponse response) throws Exception{
		boolean b = true;
		String path = Common.requestUrlAssemble(request.getServletPath());
		//接口名称含有此后缀表示规定时间内不能重复请求
		if (RepeatRequestFilter.get(path.concat(PropertiesUtil.get("RepeatRequest")))){
			//利用try catch捕捉异常，防止获取session出现异常导致请求中断
			try {
				HttpSession session = request.getSession();
				Object sessionData = session.getAttribute(path);
				long nowTime = System.currentTimeMillis()/1000;
				//不存在或上一次提交的时间是大于了规定时间的=正常访问
				int requestLifeCycle= Integer.valueOf(PropertiesUtil.get("requestLifeCycle"));
				if (Common.isNull(sessionData) || (nowTime-(long)sessionData)>requestLifeCycle){
					session.setMaxInactiveInterval(requestLifeCycle);
					session.setAttribute(path,nowTime);
				}else{
					response.setHeader("Cache-Control", "no-store");
					response.setHeader("Pragma", "no-cache");
					response.setContentType("application/json;charset=UTF-8");
					response.setCharacterEncoding("UTF-8");
					PrintWriter out=response.getWriter();
					JsonRestResponse result = new JsonRestResponse();
					result.setCode("requestLifeCycle");
					result.setDesc("请"+(requestLifeCycle-(nowTime-(long)sessionData))+"秒后再试...");
					out.write(JSON.toJSONString(result));
					out.close();
					out.flush();
					return false;
				}
			} catch (Exception e) {
				//异常不做处理
				e.printStackTrace();
			}
		}
		return b;
	}
}
