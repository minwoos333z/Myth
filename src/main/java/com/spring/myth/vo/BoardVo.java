package com.spring.myth.vo;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class BoardVo {

    private int board_no;
    private int user_no;
    private int category_no; // 임시

    @NotNull(message = "게시글 제목은 빈 칸이 될 수 없습니다.")
    @Length(min = 1, max = 1000, message="제목은 최소 한글자에서 최대 천글자까지 입력 가능합니다.")
    private String board_title;

    @NotNull(message = "게시글 내용은 빈 칸이 될 수 없습니다.")
    @Length(min = 1, max = 4000, message="내용은 최소 한글자에서 최대 4천글자까지 입력 가능합니다.")
    private String board_content;

    private int board_readcount;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date board_write_date;

    public BoardVo() {
        super();
    }

    public BoardVo(int board_no, int user_no, int category_no, String board_title, String board_content, int board_readcount, Date board_write_date) {
        this.board_no = board_no;
        this.user_no = user_no;
        this.category_no = category_no;
        this.board_title = board_title;
        this.board_content = board_content;
        this.board_readcount = board_readcount;
        this.board_write_date = board_write_date;
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

    public int getCategory_no() {
        return category_no;
    }

    public void setCategory_no(int category_no) {
        this.category_no = category_no;
    }

    public String getBoard_title() {
        return board_title;
    }

    public void setBoard_title(String board_title) {
        this.board_title = board_title;
    }

    public String getBoard_content() {
        return board_content;
    }

    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }

    public int getBoard_readcount() {
        return board_readcount;
    }

    public void setBoard_readcount(int board_readcount) {
        this.board_readcount = board_readcount;
    }

    public Date getBoard_write_date() {
        return board_write_date;
    }

    public void setBoard_write_date(Date board_write_date) {
        this.board_write_date = board_write_date;
    }

    @Override
    public String toString() {
        return "BoardVo{" +
                "board_no=" + board_no +
                ", user_no=" + user_no +
                ", category_no=" + category_no +
                ", board_title='" + board_title + '\'' +
                ", board_content='" + board_content + '\'' +
                ", board_readcount=" + board_readcount +
                ", board_write_date=" + board_write_date +
                '}';
    }
}
