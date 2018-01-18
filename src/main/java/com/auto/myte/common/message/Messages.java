package com.auto.myte.common.message;

import java.util.Locale;

public interface Messages {

    public void setLocale(Locale locale);
    
    public String getMessage(String code);
    
    public String getMessage(String code, Object... args);
    
    public String getSimpleMessage(String code);
    
    public String getSimpleMessage(String code, Object... args);
    
}

