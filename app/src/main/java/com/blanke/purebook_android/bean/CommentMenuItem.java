package com.blanke.purebook_android.bean;

/**
 *
 */

public class CommentMenuItem {
    public static enum OP {
        DELETE, SHOW, REPLY
    }

    private OP op;//操作类型
    private String title;

    public CommentMenuItem(OP op, String title) {
        this.op = op;
        this.title = title;
    }

    public void setOp(OP op) {
        this.op = op;
    }

    public OP getOp() {
        return op;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
