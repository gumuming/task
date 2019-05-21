package org.frame.filter;


import com.alibaba.fastjson.JSON;
import org.frame.common.Common;
import org.frame.bean.SessionBean;
import org.frame.filter.filterAnnotaion.FilterAnnotationFilter;
import org.frame.response.JsonRestResponse;
import org.frame.util.IpUtil;
import org.frame.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

/**
 * @项目名：公司内部模板项目
 * @作者：zhou.ning
 * @描述：WEB配置
 * @日期：Created in 2018/6/8 16:46
 */
@Configuration
@ComponentScan(basePackages = "com.liaoin.*")
public class WebConfigurer extends WebMvcConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebConfigurer.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //设置不拦截url
//        registry.addInterceptor(CheckTokenInterceptor()).addPathPatterns("*//**").excludePathPatterns(
//                "/system/worker*//**",
//                "/statistics*//**",
//                "/gsDicitemVal*//**",
//                "/swagger-resources//**"
//       );
        //拦截所有url
        registry.addInterceptor(getWebAccessInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }


    /**
     * 配置拦截器
     *
     * @return
     */
    @Bean
    public HandlerInterceptor getWebAccessInterceptor() {
        return new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
                StringBuffer sb = new StringBuffer("Web接口请求:");
                sb.append(request.getRequestURI());
                sb.append(";方法:" + request.getMethod() + " 开始调用。。。。。。 ");
                StringBuffer sb1 = new StringBuffer("Web接口请求参数:");
                Enumeration paramNames = request.getParameterNames();
                while (paramNames.hasMoreElements()) {
                    String paramName = (String) paramNames.nextElement();

                    String[] paramValues = request.getParameterValues(paramName);
                    if (paramValues.length == 1) {
                        String paramValue = paramValues[0];
                        if (paramValue.length() != 0) {
                            sb1.append(paramName).append("=").append(paramValue).append("  ");
                        }
                    }
                }
                logger.info(sb.toString());
                logger.info(sb1.toString());
                long reqTime = System.currentTimeMillis();
                request.setAttribute("reqTime", reqTime);
                //验证重复调用
                boolean flag = Signature.checkSign(request, response);
                if (!flag) {//重复调用
                    return false;
                }
                //验证接口是否需要过滤
                String path = Common.requestUrlAssemble(request.getServletPath());// 请求方法
                logger.info("过滤地址："+path);
                logger.info("是否需要验证token："+FilterAnnotationFilter.get(path.concat(PropertiesUtil.get("FiterAnnotaion"))));
                if (FilterAnnotationFilter.get(path.concat(PropertiesUtil.get("FiterAnnotaion")))){
                    boolean boo = true;
                    String code = "common.tokenInvalid";//错误码,默认为token失效
                    String clientIdentity = request.getHeader("ClientIdentity_MT");//客户端标识，值为：_PC、_Android、_IOS
                    String token = request.getHeader("FilterToken_MT");//令牌
                    if (!Common.isEqual(clientIdentity, PropertiesUtil.get("_Android")) && !Common.isEqual(clientIdentity,
                            PropertiesUtil.get("_IOS"))&& !Common.isEqual(clientIdentity, PropertiesUtil.get("_PC"))) {
                        code = "common.clientIdentityIsErro";
                        boo = false;
                    }
                    if (Common.isNull(token)) {
                        code = "common.tokenIsNull";
                        boo = false;
                    }
                    //请将下列代码放入token注入块
//                        String userToken = "入库的token";
//                        if (Common.isNull(userToken) || Common.isNull(RedisUtil.get(userToken))){
//                            userToken = AESUtils.encrypt(String.valueOf(Common.numberOnlyRoomId()),"用户id");
//                            //将该token绑定到用户库对应的字段中
//                            //绑定之后 执行以下逻辑↓↓↓↓↓↓↓↓↓↓
//                            //1.将用户对应的信息注入userInfo
//                            SessionBean userInfo = new SessionBean();
//                            //2.将token、用户信息放入Redis，并设置对应的过期时间（7天）
//                            RedisUtil.set(userToken,JSON.toJSONString(userInfo),Long.valueOf(7*24*60*60));
//                        }else {
//                            //存在时表示当前库中的token是有效的，在这里不做任何操作，如需做操作请根据业务来做
//                        }
                    //当都不为空时，执行验证
                    if (!Common.isNull(clientIdentity) && !Common.isNull(token)) {
                        //判断token是否有效
                        SessionBean userVal = Common.getSessionBean(token);
                        //无效
                        if (Common.isNull(userVal)) {
                            boo = false;
                        }
                    }
                    if (!boo) {//不通过验证
                        response.setHeader("Cache-Control", "no-store");
                        response.setHeader("Pragma", "no-cache");
                        response.setContentType("application/json;charset=UTF-8");
                        response.setCharacterEncoding("UTF-8");
                        PrintWriter out = response.getWriter();
                        JsonRestResponse result = new JsonRestResponse();
                        result.setCode(code);
                        result.setDesc(PropertiesUtil.get(code));
                        out.write(JSON.toJSONString(result));
                        out.close();
                        out.flush();
                        return boo;
                    }
                }
                return super.preHandle(request, response, o);
            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

                StringBuffer sb = new StringBuffer("调用完成:");
                sb.append(request.getRequestURI());
                sb.append(";方法:" + request.getMethod());
                long reqTime = System.currentTimeMillis();
                long respTime = System.currentTimeMillis();
                if (!Common.isNull(request.getAttribute("reqTime").toString())) {
                    reqTime = Long.parseLong(request.getAttribute("reqTime").toString());
                }
                String ip = IpUtil.getRemortIP(request);
                sb.append(";调用时间:=" + (respTime - reqTime) + "=。。。。。" + "IP：" + ip);
                logger.info(sb.toString());
            }
        };
    }


    /**
     * 配置拦截器
     *
     * @return
     */
    @Bean
    public HandlerInterceptor CheckTokenInterceptor() {

        return new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
                response.setHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
                response.setCharacterEncoding("utf-8");

                String debugs = request.getParameter("debug");
                if ("true".equals(debugs)) {
                    return true;
                }
                return super.preHandle(request, response, o);
            }

        };
    }


    /**
     * 配置跨域访问
     *
     * @return
     */
    @Bean
    public Filter getCORSFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//                response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020"); //允许哪些url可以跨域请求到本域
                response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
//                response.setHeader("Access-Control-Allow-Credentials","true");
                response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
                response.setHeader("Access-Control-Allow-Headers", "Origin,accept,Content-Type,Authorization,access-token,X-Requested-With,X_Requested_With,timestamp"); //允许哪些请求头可以跨域
                filterChain.doFilter(request, response);
            }
        };
    }


    /**
     * 处理前端过传过来的时间字符串，转为Date
     *
     * @return
     */
    @Bean
    public Converter<String, Date> addNewConvert() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                SimpleDateFormat dateFormat = null;
                if (source.length() <= 10) {
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                } else {
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                }
                dateFormat.setLenient(false);
                try {
                    return dateFormat.parse(source);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
    }


}
