package com.spring.myth.vo;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class CommentVo {
    private int comment_no;
    private int board_no;
    private int user_no;

    @NotNull(message = "댓글 필드 값이 빈칸이 될 수 없습니다.")
    @Length(min = 1, max = 2000, message = "내용은 최소 한글자에서 최대 2000글자까지 입력 가능합니다.")
    private String comment_content;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date comment_write_date;

    public CommentVo() {
        super();
    }

    public CommentVo(int comment_no, int board_no, int user_no, String comment_content, Date comment_write_date) {
        super();
        this.comment_no = comment_no;
        this.board_no = board_no;
        this.user_no = user_no;
        this.comment_content = comment_content;
        this.comment_write_date = comment_write_date;
    }

    public int getComment_no() {
        return comment_no;
    }

    public void setComment_no(int comment_no) {
        this.comment_no = comment_no;
    }

    public int getBoard_no() {
        return board_no;
    }

    public void setBoard_no(int board_no) {
        this.board_no = board_no;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public Date getComment_write_date() {
        return comment_write_date;
    }

    public void setComment_write_date(Date comment_write_date) {
        this.comment_write_date = comment_write_date;
    }

    @Override
    public String toString() {
        return "CommentVo [comment_no=" + comment_no + ", board_no=" + board_no + ", user_no=" + user_no
                + ", comment_content=" + comment_content + ", comment_write_date=" + comment_write_date + "]";
    }
}
