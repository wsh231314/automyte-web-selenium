package com.auto.myte.common.pagination;

/**
 * 改ページのBean
 * @author chunhui.li
 *
 */
public class Pagination {

    /**
     * 内容設定
     */
    String content;

    /**
     * css設定
     */
    String cssClass;

    /**
     * ページindex
     */
    int index;

    public Pagination(int index, String content, String cssClass) {
        this.content = content;
        this.index = index;
        this.cssClass = cssClass;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
