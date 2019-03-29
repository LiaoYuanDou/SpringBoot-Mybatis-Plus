package com.xx.study.springbootmybatisplus.util.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RedisUtil
 * @Author: XX
 * @Date: 2018/8/1 15:02
 * @Description: Redis 常用操作
 */
@Component
public class RedisUtil {
    private RedisUtil() {
    }

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    private static final String USER_WEB_TOKEN = "USER:WEB:TOKEN:";
    private static final String USER_APP_TOKEN = "USER:APP:TOKEN:";

    private static final String APP_USER_ID = "APP:USER:ID:";
    private static final int APP_MAX_LOGIN = 5;

    private static final String LOGINID_PASSWORD_ERROR_LOCK = "LOGINID:PASSWORD:ERROR:LOCK:";
    private static final int PASSWORD_MAX_ERROR = 5;
    private static final int PASSWORD_MAX_LOCK_TIMES = 1800;

    private static final String USER_WX_UNIONID = "USER:WX:UNIONID:";
    private static final String USER_QQ_UNIONID = "USER:QQ:UNIONID:";

    private static final String CAPTCHACODE_NUMBER = "CAPTCHACODE:NUMBER:";
    private static final String CAPTCHACODE_TIMES = "CAPTCHACODE:TIMES:";
    private static final int CAPTCHACODE_MAX_ERROR = 5;


    private static final String TEACHER_TIME = "TEACHER:TIME:";
    private static final String TEACHER_TIME_FLAG = "FLAG";
    private static final String STULOCKTEACHER = "STU:LOCK:";

    private static final String ALLAREALIST = "ALL:AREA:LIST";


    private static final String USER_CAN_DATEBOOK = "DATEBOOK:LOCK:FLAG:";
    /**
     * 默认验证码过期时长 单位：s
     */
    public static final long DEFAULT_TEACHER_TIME_EXPIRE = 600;

    /**
     * null值
     */
    private static final String NULL = "null";

    private static RedisTemplate redisTemplate;



    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }


    /**
     * 默认token过期时长 单位：s
     */
    public static final long DEFAULT_TOKEN_EXPIRE = 1800;

    /**
     * 默认验证码过期时长 单位：s
     */
    public static final long DEFAULT_CAPTCHACODE_EXPIRE = 300;

    /**
     * 不设置过期时长
     */

    public static final long NOT_EXPIRE = -1;

    /**
     * hasKey
     *
     * @param key 查询的key值
     * @return boolean
     * @Author XX
     * @Description 查询key值是否存在
     * @Date 2018/8/1 15:40
     */
    public static boolean hasKey(Object key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * setStr
     *
     * @param str key值
     * @param obj value值
     * @return boolean
     * @Author XX
     * @Description 保存String类型的数据
     * @Date 2018/8/1 15:41
     */
    public static boolean setStr(String str, Object obj) {
        boolean flag = false;
        try {
            redisTemplate.opsForValue().set(str, obj);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * getStr
     *
     * @param str key值
     * @return Object 返回数据
     * @Author XX
     * @Description 根据key值得到返回的值
     * @Date 2018/8/1 15:44
     */
    public static Object getStr(String str) {
        if (str == null) {
            return null;
        }
        Object obj = redisTemplate.opsForValue().get(str);
        if (!checkValueIsNotNull(obj)) {
            return null;
        } else {
            return obj;
        }

    }

    /**
     * eleteKey
     *
     * @param keys 可变参数key值
     * @return
     * @Author XX
     * @Description 删除key
     * @Date 2018/8/1 15:49
     */
    public static void deleteKey(Object... keys) {
        if (keys.length == 1) {
            redisTemplate.delete(keys[0]);
        } else {
            redisTemplate.delete(keys);
        }

    }

    /**
     * deleteKey
     *
     * @param
     * @return
     * @Author XX
     * @Description 删除多个key
     * @Date 2018/8/1 15:49
     */
    public static void deleteKey(List<Object> list) {
        redisTemplate.delete(list);
    }

    /**
     * expire
     *
     * @param key  键
     * @param time 过期时间
     * @return
     * @Author XX
     * @Description 指定缓存失效时间
     * @Date 2018/8/1 16:05
     */
    public static boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            } else {
                redisTemplate.expire(key, DEFAULT_TOKEN_EXPIRE, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键
     * @return long 时间
     * @Author XX
     * @Description TODO
     * @Date 2018/8/1 16:08
     */
    public static long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * setStrExpire
     *
     * @param key  key值
     * @param obj  缓存对象
     * @param time 过期时间
     * @return
     * @Author XX
     * @Description String数据缓存放入并设置时间
     * @Date 2018/8/1 16:14
     */
    public static boolean setStrExpire(String key, Object obj, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, obj, time, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(key, obj, DEFAULT_TOKEN_EXPIRE, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * incr
     *
     * @param key  键
     * @param data 要增加几(大于0)
     * @return
     * @Author XX
     * @Description 递增
     * @Date 2018/8/1 16:20
     */
    public static long incr(String key, long data) {
        if (data < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, data);
    }

    /**
     * decr
     *
     * @param key  键
     * @param data 要减少几(大于0)
     * @return
     * @Author XX
     * @Description 递减
     * @Date 2018/8/1 16:21
     */
    public static long decr(String key, long data) {
        if (data < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -data);
    }


    /**
     * setToken
     *
     * @param token key值
     * @param obj   value值
     * @return boolean
     * @Author XX
     * @Description 保存token并设置过期时间
     * @Date 2018/8/1 15:41
     */
    public static boolean setToken(String token, Object obj) {
        boolean flag = false;
        try {
            redisTemplate.opsForValue().set(USER_WEB_TOKEN + token, obj, DEFAULT_TOKEN_EXPIRE, TimeUnit.SECONDS);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean setAppToken(String token, Object obj) {
        boolean flag = false;
        try {
            redisTemplate.opsForValue().set(USER_APP_TOKEN + token, obj);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * App允许登录达到限制，才互相挤
     *
     * @param
     * @return
     * @author XX
     * @date 2019/2/25 9:22
     */
    public static boolean setAppTokenByUserId(long userId, ArrayList<String> tokenList) {
        boolean flag = false;
        try {
            int tokenListSize = tokenList.size();
            if (tokenListSize <= APP_MAX_LOGIN) {
                redisTemplate.opsForValue().set(APP_USER_ID + userId, String.join(",", tokenList));
            } else {
                int gap = tokenListSize - APP_MAX_LOGIN;
               /* String[] tokenMaxs = new String[APP_MAX_LOGIN];
                System.arraycopy(tokenList, gap, tokenMaxs, 0, APP_MAX_LOGIN);*/
                for (int i = 0; i < gap; i++) {
                    String token = tokenList.remove(i);
                    //deleteAppToken(token);
                    redisTemplate.opsForValue().set(APP_USER_ID + userId, String.join(",", tokenList));
                }
            }
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 获取APP登录数据
     *
     * @param
     * @return
     * @author XX
     * @date 2019/2/25 9:23
     */
    public static ArrayList<String> getAppTokenByUserId(long userId) {
        if (redisTemplate.hasKey(APP_USER_ID + userId)) {
            Object obj = redisTemplate.opsForValue().get(APP_USER_ID + userId);
            String tokens = String.valueOf(obj);
            return new ArrayList<String>(Arrays.asList(tokens.split(",")));
        }
        return null;
    }
    /**
     * setCaptchaCode
     *
     * @param phone       手机号码
     * @param captchaCode 验证码
     * @return
     * @Author XX
     * @Description 保存验证码
     * @Date 2018/8/8 15:34
     */
    public static void setCaptchaCode(String phone, String captchaCode) {
        redisTemplate.opsForValue().set(CAPTCHACODE_NUMBER + phone, captchaCode, DEFAULT_CAPTCHACODE_EXPIRE, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(CAPTCHACODE_TIMES + phone, CAPTCHACODE_MAX_ERROR, DEFAULT_CAPTCHACODE_EXPIRE, TimeUnit.SECONDS);
    }

    /**
     * getCaptchaCode
     *
     * @param phone 手机号码
     * @return 验证码
     * @Author XX
     * @Description 获取手机验证码
     * @Date 2018/8/8 15:34
     */
    public static String getCaptchaCode(String phone) {
        decr(CAPTCHACODE_TIMES + phone, 1);
        String captchaCode = null;
        Object obj = redisTemplate.opsForValue().get(CAPTCHACODE_NUMBER + phone);
        if (checkValueIsNotNull(obj)) {
            captchaCode = String.valueOf(obj);
        }
        return captchaCode;
    }

    /**
     * getCaptchaCodeTimes
     *
     * @param phone 手机号码
     * @return 验证码
     * @Author XX
     * @Description 获取手机验证码次数
     * @Date 2018/8/8 15:34
     */
    public static Integer getCaptchaCodeTimes(String phone) {
        Object obj = redisTemplate.opsForValue().get(CAPTCHACODE_TIMES + phone);
        int times = 0;
        if (checkValueIsNotNull(obj)) {
            times = Integer.parseInt(String.valueOf(obj));
        }
        if (times <= 0) {
            deleteCaptchaCode(phone);
        }
        return times;
    }

    /**
     * getCaptchaCode
     *
     * @param phone 手机号码
     * @return 验证码
     * @Author XX
     * @Description 删除手机验证码
     * @Date 2018/8/8 15:34
     */
    public static void deleteCaptchaCode(String phone) {
        redisTemplate.delete(CAPTCHACODE_TIMES + phone);
        redisTemplate.delete(CAPTCHACODE_NUMBER + phone);
    }

    /**
     * updateStr
     *
     * @param key    key
     * @param object 数据
     * @return
     * @author XX
     * @description 更新redis里面的数据
     * @date 2018/8/22 16:05
     */
    public static void updateStr(String key, Object object) {
        deleteKey(key);
        setStr(key, object);
    }

    /**
     * @param
     * @return
     * @author XX
     * @description 判断redis的返回值是否是空值k
     * @date 2018/8/16 9:07
     */
    public static boolean checkValueIsNotNull(Object object) {
        return object != null;
    }


    /**
     * setTeacherTimeList 将老师时间list存入redis中
     *
     * @param teacherId           老师Id
     * @param teacherScheduleList 老师时间安排List
     * @return
     * @author XX
     * @description TODO
     * @date 2018/8/24 14:22
     */


    /**
     * 将老师时间listMap存入redis中
     *
     * @param
     * @return
     * @author XX
     * @date 2018/11/30 9:39
     */
    public static void setTeacherTimeMapList(String teacherId, List<Map<String, Object>> teacherScheduleMapList) {
        redisTemplate.opsForHash().put(TEACHER_TIME + teacherId, TEACHER_TIME_FLAG, "1");
        for (Map<String, Object> teacherScheduleMap : teacherScheduleMapList) {
            if (!redisTemplate.opsForHash().hasKey(TEACHER_TIME + teacherId, String.valueOf(teacherScheduleMap.get("teacherScheduleId")))) {
                redisTemplate.opsForHash().put(TEACHER_TIME + teacherId, String.valueOf(teacherScheduleMap.get("teacherScheduleId")), teacherScheduleMap);
            }
        }
        redisTemplate.expire(TEACHER_TIME + teacherId, DEFAULT_TEACHER_TIME_EXPIRE, TimeUnit.SECONDS);
    }


    /**
     * 删除老师约课时间安排
     *
     * @param
     * @return
     * @author XX
     * @date 2018/10/29 16:02
     */
    public static void delTeacherTime(String teacherId, String teacherScheduleId) {
        redisTemplate.opsForHash().delete(TEACHER_TIME + teacherId, teacherScheduleId);
    }

    public static void delTeacherTimeMap(String teacherId, String teacherScheduleId) {
        redisTemplate.opsForHash().delete(TEACHER_TIME + teacherId, teacherScheduleId);
    }

     /**
     * 取消锁定的值
     *
     * @param
     * @return
     * @author XX
     * @date 2018/10/19 15:31
     */
    public static void delStuLockTeacherTime(String stuId) {
        redisTemplate.delete(STULOCKTEACHER + stuId);
    }


    public static Map<String, Map<String, Object>> getStuLockTeacherTimeMap(String stuId) {
        return redisTemplate.opsForHash().entries(STULOCKTEACHER + stuId);
    }

    /**
     * 保存所有城市List
     *
     * @param allAreaList
     * @return
     * @author XX
     * @description 保存所有城市List
     * @date 2018/9/21 11:08
     */
    public static void saveAllArea(List<Map<String, Object>> allAreaList) {
        redisTemplate.opsForValue().set(ALLAREALIST, allAreaList);
    }

    /**
     * 获取所有城市List
     *
     * @param
     * @return
     * @author XX
     * @description 获取所有城市List
     * @date 2018/9/21 11:08
     */
    public static List<Map<String, Object>> getAllArea() {
        return (List<Map<String, Object>>) redisTemplate.opsForValue().get(ALLAREALIST);
    }

    /**
     * 删除所有城市List
     *
     * @param
     * @return
     * @author XX
     * @description 删除所有城市List
     * @date 2018/9/21 11:08
     */
    public static void delAllArea(List<Map<String, Object>> allAreaList) {
        redisTemplate.delete(ALLAREALIST);
    }

    /**
     * 判断所有城市List是否存在
     *
     * @param
     * @return
     * @author XX
     * @description 判断所有城市List是否存在
     * @date 2018/9/21 11:08
     */
    public static boolean checkExistAllArea() {
        return redisTemplate.hasKey(ALLAREALIST);
    }



    /**
     * 增加是否可以约课判读锁定
     *
     * @param
     * @return
     * @author XX
     * @date 2018/12/29 8:57
     */
    public synchronized static boolean checkCanDatebook(long stuId) {
        boolean flag = true;
        if (redisTemplate.hasKey(USER_CAN_DATEBOOK + stuId)) {
            flag = false;
        } else {
            redisTemplate.opsForValue().set(USER_CAN_DATEBOOK + stuId, 1, 3, TimeUnit.HOURS);
        }
        return flag;
    }

    /**
     * 删除约课锁定标志
     *
     * @param
     * @return
     * @author XX
     * @date 2018/12/29 8:59
     */
    public synchronized static void deleteDatebookFlag(long stuId) {
        redisTemplate.delete(USER_CAN_DATEBOOK + stuId);
    }


    /**
     * 防止重复提交
     *
     * @param
     * @return
     * @author XX
     * @date 2018/12/29 15:50
     */
    public synchronized static boolean checkRequestURL(String requestURI) {
        boolean flag = true;
        if (redisTemplate.hasKey(requestURI)) {
            flag = false;
        } else {
            redisTemplate.opsForValue().set(requestURI, 1, 3, TimeUnit.HOURS);
        }
        return flag;
    }

    /**
     * 删除标志
     *
     * @param
     * @return
     * @author XX
     * @date 2018/12/29 15:51
     */
    public synchronized static void deleteRequestURLFlag(String requestURI) {
        redisTemplate.delete(requestURI);
    }

    /**
     * 清除所有的可以值
     *
     * @param
     * @return
     * @author XX
     * @date 2019/1/2 20:37
     */
    public static void flushAll() {
        String pattern = "*";
        RedisConnection connection = redisTemplate
                .getConnectionFactory().getConnection();

        Set<byte[]> caches = connection.keys(pattern.getBytes());
        if (!caches.isEmpty()) {
            connection.del(caches.toArray(new byte[][]{}));
        }
    }

    /**
     * 保存密码错误锁定次数
     *
     * @param
     * @return
     * @author XX
     * @date 2019/2/25 10:33
     */
    public static void setPasswordMaxLockTimes(String loginId) {
        String loginErrorKey = LOGINID_PASSWORD_ERROR_LOCK + loginId;
        if (redisTemplate.hasKey(loginErrorKey)) {
            Object obj = redisTemplate.opsForValue().get(loginErrorKey);
            int loginErrorValue = Integer.parseInt(String.valueOf(obj));
            redisTemplate.opsForValue().set(loginErrorKey, loginErrorValue + 1);
        } else {
            redisTemplate.opsForValue().set(loginErrorKey, 1);
        }
        redisTemplate.expire(loginErrorKey, PASSWORD_MAX_LOCK_TIMES, TimeUnit.SECONDS);
    }

    /**
     * 获取被锁定了情况
     *
     * @param
     * @return
     * @author XX
     * @date 2019/2/25 10:35
     */
    public static boolean getPasswordMaxLockTimes(String loginId) {
        String loginErrorKey = LOGINID_PASSWORD_ERROR_LOCK + loginId;
        if (redisTemplate.hasKey(loginErrorKey)) {
            Object obj = redisTemplate.opsForValue().get(loginErrorKey);
            int loginErrorValue = Integer.parseInt(String.valueOf(obj));
            redisTemplate.expire(loginErrorKey, PASSWORD_MAX_LOCK_TIMES, TimeUnit.SECONDS);
            return loginErrorValue >= PASSWORD_MAX_ERROR;
        } else {
            return false;
        }
    }
}