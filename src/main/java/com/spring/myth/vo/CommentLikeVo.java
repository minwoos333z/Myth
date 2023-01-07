package com.spring.myth.vo;

import java.util.Date;

public class CommentLikeVo {

    private int comment_like_no;
    private int comment_no;
    private int user_no;
    private Date like_write_date;

    public CommentLikeVo() {
        super();
    }

    public CommentLikeVo(int comment_like_no, int comment_no, int user_no, Date like_write_date) {
        this.comment_like_no = comment_like_no;
        this.comment_no = comment_no;
        this.user_no = user_no;
        this.like_write_date = like_write_date;
    }

    public int getComment_like_no() {
        return comment_like_no;
    }

    public void setComment_like_no(int comment_like_no) {
        this.comment_like_no = comment_like_no;
    }

    public int getComment_no() {
        return comment_no;
    }

    public void setComment_no(int comment_no) {
        this.comment_no = comment_no;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public Date getLike_write_date() {
        return like_write_date;
    }

    public void setLike_write_date(Date like_write_date) {
        this.like_write_date = like_write_date;
    }
}
