package org.frame.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.frame.common.Common;
import org.frame.common.CommonDate;
import org.frame.response.JsonRestResponse;
import org.frame.bean.ValidResultBean;

import com.liaoin.dao.ProgramErroLogMapper;
import com.liaoin.entity.ProgramErroLog;
import org.frame.util.ClientUtil;
import org.frame.util.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @项目名：公司内部模板项目
 * @作者：zhou.ning
 * @描述：全局异常处理类
 * @日期：Created in 2018/6/8 14:50
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Resource
    private ProgramErroLogMapper erroLogMapper;

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    /**
     * @方法名：jsonErrorHandler
     * @描述： 运行时异常
     * @作者： zhou.ning
     * @日期： Created in 2018/6/8 14:51
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonRestResponse jsonErrorHandler(HttpServletRequest req, Exception e){
        String path = req.getServletPath();
        //防止swagger关闭后产产生异常
        if (path.indexOf("swagger")!=-1||path.indexOf("null")!=-1)return null;
        try {
            String erroCode = "common.systemErro";
            String erroDesc = "内部错误";
            String requestparm = JSON.toJSONString(req.getParameterMap());
            //String requestuser = JSON.toJSONString(Common.getSessionBean(req));
            String requestuser = "当前登录用户信息";
            //将错误日志入库
            ProgramErroLog log = new ProgramErroLog();
            log.setErrocode(erroCode);
            log.setErrodesc(erroDesc);
            log.setErromsg(e.getMessage());
            log.setRequestparm(requestparm);
            log.setRequestuser(requestuser);
            log.setCreatedate(new Date());
            log.setDeviceid("【"+req.getHeader("ClientIdentity_MT")+"】"+req.getHeader("FilterToken_MT"));
            erroLogMapper.insertSelective(log);
            log.setCreatedate(new Date());
            //发送email
            String EMAIL_SEND_SWITCH = PropertiesUtil.get("ERRO_EMAIL_SEND_SWITCH");
            if (Common.isEqual(Integer.valueOf(EMAIL_SEND_SWITCH),0)){
                String projectName = PropertiesUtil.get("PROJECTNAME");
                String senduser = PropertiesUtil.get("ERRO_EMAIL_SENDUSER");
                String sendpassword = PropertiesUtil.get("ERRO_EMAIL_SENDPASSWORD");
                String ts = PropertiesUtil.get("ERRO_EMAIL_TOUSER");
                String title = PropertiesUtil.get("ERRO_EMAIL_SEND_TITLE",projectName);
                String body = PropertiesUtil.get("ERRO_EMAIL_SEND_BODY",projectName,CommonDate.nowDateLocalDateTimeToString(),
                        requestuser,requestparm,Common.isNull(e.getMessage())?"null":e.getMessage());
                if (!Common.isNull(senduser)&&!Common.isNull(sendpassword)&&!Common.isNull(ts)){
                    String[] tousers = ts.split(",");
                    for (String touser:tousers) {
//                        Email.send(senduser,sendpassword,touser,title,body);
                    }
                }
            }

            //前台返回
            JsonRestResponse res = new JsonRestResponse();
//            res.setCode(erroCode);
//            res.setAttach(e.getMessage());
//            res.setDesc(erroDesc);
            logError(req, res, e);
            e.printStackTrace();
            return res;
        } catch (Exception es) {
            //异常捕捉，防止被自定义异常捕捉陷入死循环
        }
        return null;
//        throw e;
    }

   /**
    * @方法名：jsonGetReqValidHandler
    * @描述： 验证异常
    * @作者： zhou.ning
    * @日期： Created in 2018/6/8 14:51
    */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public JsonRestResponse jsonGetReqValidHandler(HttpServletRequest req, ConstraintViolationException e) {
        JsonRestResponse res = new JsonRestResponse();
//        res.setCode("验证失败");
        StringBuilder sb = new StringBuilder();
        List<ValidResultBean> list = new ArrayList<ValidResultBean>();
        for (ConstraintViolation c : e.getConstraintViolations()) {
            ValidResultBean bean = new ValidResultBean();
            bean.setFieldName(c.getPropertyPath().toString());
            bean.setErrorMassage(c.getMessage());
            list.add(bean);
            sb.append(c.getMessage());
            sb.append("||");
        }
        sb.delete(sb.length() - 2, sb.length());
        String json = JSONArray.toJSONString(list);
//        res.setResult(json);
//        res.setDesc(sb.toString());
        return res;
    }

    /**
     * @方法名：jsonValidHandler
     * @描述： 验证异常
     * @作者： zhou.ning
     * @日期： Created in 2018/6/8 14:51
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public JsonRestResponse jsonValidHandler(HttpServletRequest req, MethodArgumentNotValidException e) {
        JsonRestResponse res = new JsonRestResponse();
//        res.setCode("验证失败");

        BindingResult validResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder();
        List<ValidResultBean> list = new ArrayList<ValidResultBean>();
        for (int i = 0; i < validResult.getErrorCount(); i++) {
            ObjectError error = validResult.getAllErrors().get(i);
            DefaultMessageSourceResolvable arg = (DefaultMessageSourceResolvable) error.getArguments()[0];
            ValidResultBean bean = new ValidResultBean();
            bean.setFieldName(arg.getDefaultMessage());
            bean.setErrorMassage(error.getDefaultMessage());
            sb.append(error.getDefaultMessage());
            if (i + 1 != validResult.getErrorCount()) {
                sb.append("||");
            }
            list.add(bean);
        }
        String json = JSONArray.toJSONString(list);
//        res.setResult(json);
//        res.setDesc(sb.toString());
        return res;
    }

    /**
     * @方法名：jsonErrorHandler
     * @描述： 业务类异常在service中主动调用throw new BusinessException(MsgCodeConstant.XXXX)抛出业务异常
     * @作者： zhou.ning
     * @日期： Created in 2018/6/8 14:52
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public JsonRestResponse jsonErrorHandler(HttpServletRequest req, BusinessException e) {
        JsonRestResponse res = new JsonRestResponse();
//        res.setCode(e.getCode());
//        res.setDesc(PropertiesUtil.get(e.getCode(), e.args));
        logError(req, res, e);

        return res;
    }

    /**
     * @方法名：logError
     * @描述： 异常打印
     * @作者： zhou.ning
     * @日期： Created in 2018/6/8 14:53
     */
    private void logError(HttpServletRequest req, JsonRestResponse res, Exception e) {

        String requestIp = ClientUtil.getClientIp(req);
        String requestUrl = req.getRequestURL().toString();

        //log.error("content type  :" + req.getContentType());
//        log.error("ip:" + requestIp);
//        log.error("request   type:" + req.getMethod());
//        log.error("request    url:" + requestUrl);
//        if ("GET".equalsIgnoreCase(req.getMethod())) {
//            log.error("request params:" + req.getQueryString());
//        } else {
//            Map<String, String[]> params = req.getParameterMap();
//            if (params != null) {
//                log.error("request params:" + JSONObject.toJSONString(params));
//            }
//        }
//        log.error(res.toString());
//        log.error(e.toString());
       }

}
