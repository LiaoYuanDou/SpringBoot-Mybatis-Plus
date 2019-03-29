package com.xx.study.springbootmybatisplus.exception;


import com.xx.study.springbootmybatisplus.util.restultful.BaseResult;
import com.xx.study.springbootmybatisplus.util.restultful.ResultCode;
import com.xx.study.springbootmybatisplus.util.restultful.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

/**
 * @Title: GlobalExceptionHandler
 * @ProjectName talk915
 * @Description: 统一异常处理中心
 * @author: CZW
 * @date 2018/7/27 11:11
 */
@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @ExceptionHandler
    public ResponseEntity<BaseResult> exceptionHandler(RuntimeException e, HttpServletRequest request) {
        logger.error("系统出现未捕获的异常：{} {}", request.getMethod(), request.getRequestURI(), e);
        if (("NoRepeatSubmitException!").equals(e.getMessage())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtil.setBaseResult(ResultCode.NOREPEATSUBMIT.getCode()));
        }
        if (("NoPermissionException!").equals(e.getMessage())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResultUtil.setBaseResult(ResultCode.NOPERMISSION.getCode()));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtil.setBaseResult(ResultCode.FAILY.getCode(), "系统繁忙!请稍后再试！（系统出现未捕获的异常）"));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<BaseResult> validationExceptionHandler(ValidationException e,
                                                                 HttpServletRequest request) {
        logger.error("系统出现参数校验异常：{} {}", request.getMethod(), request.getRequestURI(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtil.setBaseResult(ResultCode.FAILY.getCode(), "系统繁忙!请稍后再试！（系统出现参数校验异常）"));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<BaseResult> missingParameterException(MissingServletRequestParameterException e,
                                                                HttpServletRequest request) {
        logger.error("请求参数不存在：{} {} ,param={}", request.getMethod(), request.getRequestURI(), e.getParameterName(), e);
        if ("token".equals(e.getParameterName())) {
            return ResponseEntity.badRequest().body(ResultUtil.setBaseResult(ResultCode.RELOGIN.getCode()));
        } else {
            return ResponseEntity.badRequest().body(ResultUtil.setBaseResult(ResultCode.NOPARAMS.getCode(), e.getParameterName() + " 参数未输"));
        }
    }

    @ExceptionHandler(MissingParamException.class)
    public ResponseEntity<BaseResult> missingParamException(MissingParamException e,
                                                            HttpServletRequest request) {
        logger.error("请求参数不存在：{} {} ,param={}", request.getMethod(), request.getRequestURI(), e.getParameterName(), e);
        if ("token".equals(e.getParameterName())) {
            return ResponseEntity.badRequest().body(ResultUtil.setBaseResult(ResultCode.RELOGIN.getCode()));
        } else {
            return ResponseEntity.badRequest().body(ResultUtil.setBaseResult(ResultCode.NOPARAMS.getCode(), e.getParameterName() + " 参数未输"));
        }
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<BaseResult> argumentTypeHandler(MethodArgumentTypeMismatchException e,
                                                          HttpServletRequest request) {
        logger.error("请求参数的类型不合法：{} {} ,param={}", request.getMethod(), request.getRequestURI(), e.getName(), e);
        return ResponseEntity.badRequest().body(ResultUtil.setBaseResult(ResultCode.FAILY.getCode(), "请求参数的类型不合法！"));
    }

    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<BaseResult> objectAlreadyExists(ObjectAlreadyExistsException e) {
        logger.error("数据已经存在异常:" + e.getParam());
        return ResponseEntity.badRequest().body(ResultUtil.setBaseResult(ResultCode.FAILY.getCode(), "系统繁忙!请稍后再试！（数据已经存在）"));
    }

    @ExceptionHandler(ParamNotFoundException.class)
    public ResponseEntity<BaseResult> paramNotFound(ParamNotFoundException e, HttpServletRequest request) {
        logger.error("根据请求参数获取不到对象,method:{},uri:{},param:{}", request.getMethod(), request.getRequestURL(),
                e.getParam());
        return ResponseEntity.badRequest().body(ResultUtil.setBaseResult(ResultCode.FAILY.getCode(), "系统繁忙!请稍后再试！（根据请求参数获取不到对象）"));
    }

    /*@ExceptionHandler(NoPermissionException.class)
    public ResponseEntity<BaseResult> noPermissionExceptionHandler(NoPermissionException e) {
        logger.error("请求未授权：{} {} ");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResultUtil.setBaseResult(ResultCode.NOPERMISSION.getCode()));
    }*/

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<BaseResult> requestForbiddenHandler(ForbiddenException e, HttpServletRequest request) {
        logger.error("{}：{} {} ", e.getMessage(), request.getMethod(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ResultUtil.setBaseResult(ResultCode.NOPERMISSION.getCode()));
    }

    /**
     * @param
     * @return
     * @Author lk
     * @Description 信息无法读取
     * @Date 2018/10/26 16:37
     */
   /* @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BaseResult> handleHttpMessageNotReadableException(Exception e) {
       *//* e.printStackTrace();
        Result result = new Result();
        result.setResultMessage("无法读取");
        result.setResultCode(HttpStatus.BAD_REQUEST.value());
        System.out.println(result.toString() + ">>>>>>>>>>>>>>>>>>>>>>>>");*//*
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ResultUtil.setBaseResult(HttpStatus.BAD_REQUEST.value()));
    }
*/
    /**
     * @param
     * @return
     * @Author lk
     * @Description 处理参数异常
     * @Date 2018/10/26 16:38
     */
   /* @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BaseResult> handleMethodArgumentNotValidException(Exception e) {
      *//*  Result result = new Result();
        result.setResultMessage("处理参数异常");
        result.setResultCode(HttpStatus.BAD_REQUEST.value());
        System.out.println(result.toString() + ">>>>>>>>>>>>>>>>>>>>>>>>");*//*
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ResultUtil.setBaseResult(HttpStatus.BAD_REQUEST.value()));
    }*/

    /**
     * 处理自定义异常
     *
     * @param e
     * @return
     */
   /* @ExceptionHandler(IException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public Msg handleIException(IException e) {
        return Msg.message(417, "自定义异常");
    }*/


    /**
     * @param
     * @return
     * @Author lk
     * @Description 服务器内部错误
     * @Date 2018/10/26 16:38
     */
   /* @ExceptionHandler(ArithmeticException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<BaseResult> handleArithmeticException(ArithmeticException e) {
       *//* Result result = new Result();
        result.setResultMessage("服务器内部错误");
        result.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        System.out.println(result.toString() + ">>>>>>>>>>>>>>>>>>>>>>>>");*//*
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ResultUtil.setBaseResult(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
*/
    /**
     * 登陆错误
     *
     * @param e
     * @return
     */
    /*@ExceptionHandler({AuthenticationException.class, UnknownAccountException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<BaseResult> handleAuthenticationException(AuthenticationException e) {
        *//*Result result = new Result();
        result.setResultMessage("账号或密码错误!");
        result.setResultCode(ResultCode.ACCOUNTWRONG.getCode());
        System.out.println(result.toString() + ">>>>>>>>>>>>>>>>>>>>>>>>");*//*
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ResultUtil.setBaseResult(ResultCode.ACCOUNTWRONG.getCode()));
    }*/

  /* @ExceptionHandler(UnknownAccountException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result handleUnknownAccountException(UnknownAccountException e) {
        Result result = new Result();
        result.setResultMessage("账号或密码错误!");
        result.setResultCode(401);
        System.out.println(result.toString()+">>>>>>>>>>>>>>>>>>>>>>>>");
        return result;
    }*/

    /**
     * 没有权限——shiro
     *
     * @param e
     * @return
     */
   /* @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result handleUnauthorizedException(org.apache.shiro.authz.UnauthorizedException e) {
        Result result = new Result();
        result.setResultMessage("用户权限不足!");
        result.setResultCode(ResultCode.NOPERMISSION.getCode());
        System.out.println(result.toString() + ">>>>>>>>>>>>>>>>>>>>>>>>");
        return result;
    }*/


    /*@ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<BaseResult> handleAuthorizationException(AuthorizationException e) {
       *//* Result result = new Result();
        result.setResultMessage("用户权限不足");
        result.setResultCode(ResultCode.NOPERMISSION.getCode());
        System.out.println(result.toString() + ">>>>>>>>>>>>>>>>>>>>>>>>");*//*
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ResultUtil.setBaseResult(ResultCode.NOPERMISSION.getCode()));
    }*/

   /* @ExceptionHandler(UnauthenticatedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<BaseResult> unauthenticatedExceptionException(UnauthenticatedException e) {
        //Result result = new Result();
        //result.setResultMessage("用户没有登录!");
        //result.setResultCode(ResultCode.NOPERMISSION.getCode());
        //System.out.println(result.toString() + ">>>>>>>>>>>>>>>>>>>>>>>>");
        //logger.error("{}：{} {} ", e.getMessage(), request.getMethod(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ResultUtil.setBaseResult(ResultCode.NOPERMISSION.getCode()));
       //return result;
    }*/

   /* @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        Result result = new Result();
        result.setResultMessage("必传参数未传");
        result.setResultCode(ResultCode.NOPARAMS.getCode());
        System.out.println(result.toString() + ">>>>>>>>>>>>>>>>>>>>>>>>");
        return result;
    }*/
}
