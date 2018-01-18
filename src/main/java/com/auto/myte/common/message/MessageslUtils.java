package com.auto.myte.common.message;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.auto.myte.common.Application;

public class MessageslUtils {

    private static HttpServletRequest request = null;

    /**
     * コンストラクタ.
     * @return 
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return request = requestAttributes.getRequest();
    }


    public static void addInfoMsg(Model model, String msg) {
        List<String> msgs = new ArrayList<String>();
        msgs.add(msg);
        addMsgsToModel(Application.SERVICE_INFORMATIONS, msgs, model);
    }

    public static void addWarnMsg(Model model, String msg) {
        List<String> msgs = new ArrayList<String>();
        msgs.add(msg);
        addMsgsToModel(Application.SERVICE_WARNINGS, msgs, model);
    }

    public static void addErrorMsg(Model model, String msg) {
        List<String> msgs = new ArrayList<String>();
        msgs.add(msg);
        addMsgsToModel(Application.SERVICE_ERRORS, msgs, model);
    }

    public static void addInfoMsgs(Model model, List<String> msgs) {
        addMsgsToModel(Application.SERVICE_INFORMATIONS, msgs, model);
    }

    public static void addWarnMsgs(Model model, List<String> msgs) {
        addMsgsToModel(Application.SERVICE_WARNINGS, msgs, model);
    }

    public static void addErrorMsgs(Model model, List<String> msgs) {
        addMsgsToModel(Application.SERVICE_ERRORS, msgs, model);
    }

    @SuppressWarnings("unchecked")
    public static void addMsgsToModel(String key, List<String> msgs, Model model) {
        request = getRequest();
        List<String> attribureList = new ArrayList<String>();
        List<String> modelList = (List<String>) model.asMap().get(key);
        List<String> requestList = (List<String>) request.getAttribute(key);
        
        if (modelList != null) {
            attribureList.addAll(modelList);
        }
        if (requestList != null) {
            for (String strRequest : requestList) {
                if (attribureList.contains(strRequest)) {
                    continue;
                } else {
                    attribureList.add(strRequest);
                }
            }
        }
        if (msgs != null) {
            attribureList.addAll(msgs);
        }
        model.addAttribute(key, attribureList);
    }
}
