package com.auto.myte.common.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

/**
 * ログ管理のクラス
 * 
 * @author chunhui.li
 *
 */
@Aspect
@Service
public class LoggerAdvice {
    private static Logger logger = LoggerFactory.getLogger(LoggerAdvice.class);

    /**
     * controllerのMethodをStartログ出力
     */
    @Before("within(com.auto.myte.controller..*)")
    public void addBeforeLogger(JoinPoint joinPoint) {
        logger.info("logging_start: " + joinPoint.getSignature().toString());
    }

    /**
     * controllerのMethodをEndログ出力
     */
    @After("within(com.auto.myte.controller..*)")
    public void addAfterLogger(JoinPoint joinPoint) {
        logger.info("logging_end: " + joinPoint.getSignature().toString());
    }

    /**
     * controllerの異常をログ出力
     * 
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(pointcut = "within(com.auto.myte.controller..*)", throwing = "ex")
    public void addAfterThrowingLogger(JoinPoint joinPoint, Exception ex) {
        logger.error("addAfterThrowingLogger", ex);
    }

    private String parseParames(Object[] parames) {
        if (null == parames || parames.length <= 0) {
            return "";
        }
        StringBuffer param = new StringBuffer("传入参数[{}] ");
        for (Object obj : parames) {
            param.append(ToStringBuilder.reflectionToString(obj)).append("  ");
        }
        return param.toString();
    }

}
