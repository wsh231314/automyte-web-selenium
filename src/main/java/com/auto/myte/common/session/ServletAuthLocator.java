package com.auto.myte.common.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.auto.myte.common.Application;

public class ServletAuthLocator implements AuthLocator {

    private HttpServletRequest request;
    private String sessionAuthData = Application.DEFAULT_SESSION_AUTH_DATA;
    private String sessionUserData = Application.DEFAULT_SESSION_USER_DATA;

    public ServletAuthLocator(HttpServletRequest request) {
        this.request = request;
    }

    public String getSessionUserName() {
        return sessionAuthData;
    }

    public void setSessionUserName(String sessionUserName) {
        this.sessionAuthData = sessionUserName;
    }

//    /*
//     * (non-Javadoc)
//     * 
//     * @see
//     * com.auto.myte.common.session.AuthLocator#saveOrUpadateAuthInfo(com.
//     * auto.myte.domain.User)
//     */
//    public boolean saveOrUpadateAuthInfo(AuthInfo info) {
//        if (info != null) {
//            HttpSession session = request.getSession(true);
//            session.setAttribute(sessionAuthData, info);
//            return true;
//        }
//        return false;
//    }
//    
//    public void saveOrUpadateUser(User user) {
//        if (user != null) {
//            HttpSession session = request.getSession(true);
//            session.setAttribute(sessionUserData, user);
//        }
//    }
//    
//    public User getSessionUser() {
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            User user = (User) session.getAttribute(this.sessionUserData);
//            if (user != null) {
//                return user;
//            }
//        }
//        return null;
//    }

    /* (non-Javadoc)
     * @see com.auto.myte.common.session.AuthLocator#isAuthenticated()
     */
    @Override
    public boolean isAuth() {
//        if (this.getAuthInfo() != null) {
//            return true;
//        }
        return false;
    
    }

//    /**
//     * セッションからユーザ情報取得
//     * @return
//     */
//    public String getAuthInfo() {
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            AuthInfo data = (AuthInfo) session.getAttribute(this.sessionAuthData);
//            if (data != null) {
//                return data.getId();
//            }
//        }
//        return null;
//    }
}
