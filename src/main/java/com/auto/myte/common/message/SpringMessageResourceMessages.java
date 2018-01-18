package com.auto.myte.common.message;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * メッセージ取得用のユーティリティクラス.
 * @author chunhui.li
 *
 */
@Component
public class SpringMessageResourceMessages implements Messages, ApplicationListener<ApplicationEvent> {

    private static Logger LOGGER = LoggerFactory.getLogger(SpringMessageResourceMessages.class);

    protected ApplicationContext context = null;

    private ThreadLocal<Locale> locale = new ThreadLocal<Locale>();

    public SpringMessageResourceMessages() {
        // nothing
    }

    public void setLocale(Locale locale) {
        this.locale.set(locale);
        LOGGER.debug("this requrest locale = {}.", locale);
    }

    @Override
    public String getMessage(String code) {
        assert (context != null);
        assertLocale();
        return code + ":" + context.getMessage(code, null, locale.get());
    }

    @Override
    public String getMessage(String code, Object... args) {
        assert (context != null);
        assertLocale();
        return code + ":" + context.getMessage(code, args, locale.get());
    }

    @Override
    public String getSimpleMessage(String code) {
        assert (context != null);
        assertLocale();
        return context.getMessage(code, null, locale.get());
    }

    @Override
    public String getSimpleMessage(String code, Object... args) {
        assert (context != null);
        assertLocale();
        return context.getMessage(code, args, locale.get());
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            context = ((ContextRefreshedEvent) event).getApplicationContext();
            return;
        }
        if (event instanceof ContextClosedEvent) {
            context = null;
        }
    }

    protected void assertLocale() {
        if (locale.get() == null) {
            locale.set(Locale.getDefault());
        }
    }
}
