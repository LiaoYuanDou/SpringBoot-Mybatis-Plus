package com.xx.study.springbootmybatisplus.util.norepeatsubmit;


/**
 * @className: NoRepeatSubmitAop
 * @author: XX
 * @date: 2018/12/29 14:19
 * @description: 防止重复提交 aop解析注解 防止表单重复提交拦截器
 */
/*@Aspect
@Component
public class NoRepeatSubmitAop {

    private static final Logger logger = LoggerFactory.getLogger(NoRepeatSubmitAop.class);

    @Pointcut("execution( * com.zteict.talk915*.controller..*.*(..))")
    public void pointcutPackage() {

    }

    @Before("pointcutPackage() && @annotation(noRepeatSubmit)")
    public void doBefore(JoinPoint proceedingJoinPoint, NoRepeatSubmit noRepeatSubmit) {
        if (noRepeatSubmit != null) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            *//*String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();*//*
            HttpServletRequest request = attributes.getRequest();
            HttpServletResponse response = attributes.getResponse();
            String requestURI = request.getRequestURI();
            String method = request.getMethod();
            String token = getAopToken(proceedingJoinPoint, request, method);
            if (ObjectUtil.isBlank(token)) {
                return;
            }

            UserInfo userInfo = RedisUtil.getToken(token);
            if (userInfo == null) {
                return;
            }
            long userId = userInfo.getId();
            String requestURIKey = getRedisKey(requestURI, userId);
            if (!RedisUtil.checkRequestURL(requestURIKey)) {
                throw new RuntimeException("NoRepeatSubmitException!");
            }
        }
    }

    @AfterReturning("pointcutPackage() && @annotation(noRepeatSubmit)")
    public void doAfterReturning(JoinPoint proceedingJoinPoint, NoRepeatSubmit noRepeatSubmit) {
        if (noRepeatSubmit != null) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String requestURI = request.getRequestURI();
            String method = request.getMethod();
            String token = getAopToken(proceedingJoinPoint, request, method);
            if (ObjectUtil.isBlank(token)) {
                return;
            }

            UserInfo userInfo = RedisUtil.getToken(token);
            if (userInfo == null) {
                return;
            }
            long userId = userInfo.getId();
            String requestURIKey = getRedisKey(requestURI, userId);
            RedisUtil.deleteRequestURLFlag(requestURIKey);
        }
    }

    public static String getAopToken(JoinPoint proceedingJoinPoint, HttpServletRequest request, String method) {
        String token = "";
        if ("GET".equals(method)) {
            token = request.getParameter("token");
        } else {
            Object[] args = proceedingJoinPoint.getArgs();
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Map) {
                    Map map = (Map) args[i];
                    token = String.valueOf(map.get("token"));
                } else {
                    Map<?, ?> tokenMap = MapToObjectUtil.objectToMap(args[i]);
                    token = String.valueOf(tokenMap.get("token"));
                }
                if (ObjectUtil.isNotBlank(token)) {
                    return token;
                }
            }
        }
        return token;
    }

    public synchronized String getRedisKey(String requestURI, long userId) {
        StringBuilder noRepeatSubmitKey = new StringBuilder("SUBMIT:");
        noRepeatSubmitKey.append(userId).append(":");
        noRepeatSubmitKey.append(requestURI).append(":FLAG");
        return noRepeatSubmitKey.toString();
    }


}*/
