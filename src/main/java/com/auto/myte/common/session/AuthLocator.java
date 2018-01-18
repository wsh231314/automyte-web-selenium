package com.auto.myte.common.session;

/**
 * セッションユーザと権限のインタフェース
 * @author chunhui.li
 *
 */
public interface AuthLocator {

    /**
     * ユーザ情報保存
     * @param user
     * @return
     */
//    public boolean saveOrUpadateAuthInfo(AuthInfo info);
    
    /**
     * セッション情報存在するか判断
     * @return
     */
    public boolean isAuth();
}
