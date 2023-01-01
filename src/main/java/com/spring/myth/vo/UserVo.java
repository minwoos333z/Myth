package com.spring.myth.vo;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class UserVo {

    private int user_no;

    @NotNull(message = "아이디의 필드값이 빈칸이 될 수 없습니다.")
    @Pattern(regexp = "^(?=.*[a-zA-z])(?=.*[0-9])(?!.*[^a-zA-z0-9]).{5,10}", message = "아이디는 영문자 숫자 각 한글자씩 모두 포함하여 5~10글자 사이로 입력 하여야합니다.")
    private String user_id;

    @NotNull(message = "비밀번호의 필드값이 빈칸이 될 수 없습니다.")
    @Pattern(regexp = "^(?=.*[a-zA-z])(?=.*[0-9])(?!.*[^a-zA-z0-9]).{5,20}", message = "패스워드는 영문자 숫자 각 한글자씩 모두 포함하여 5~20글자 사이로 입력 하여야합니다.")
    private String user_pw;

    @NotNull(message = "닉네임의 필드값이 빈칸이 될 수 없습니다.")
    @Length(min = 1, max = 15, message = "닉네임은 최소 1 ~ 15 글자까지 설정 하실 수 있습니다.")
    @Pattern(regexp = "^[가-힣]*$", message = "닉네임은 한글로만 입력이 가능 합니다.")
    private String user_nickname;

    @NotNull
    private String user_gender;

    @NotNull(message = "휴대폰의 필드값이 빈칸이 될 수 없습니다.")
    @Length(max = 13, message = "휴대폰은 최대13글자까지 입력이 가능합니다.")
    @Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$", message = "휴대폰은 하이폰을 포함하여 입력하여 주시기 바랍니다.")
    private String user_phone;

    @NotNull(message = "이메일의 필드값이 빈칸이 될 수 없습니다.")
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", message = "이메일의 정규식을 지켜주세요")
    private String user_email;

    @Past(message = "생년월일은 현재날짜보다 과거여야 합니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date user_birth;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date user_join_date;
    private String user_status;

    public UserVo() {
        super();
    }

    public UserVo(int user_no, String user_id, String user_pw, String user_nickname, String user_gender, String user_phone, String user_email, Date user_birth, Date user_join_date, String user_status) {
        this.user_no = user_no;
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.user_nickname = user_nickname;
        this.user_gender = user_gender;
        this.user_phone = user_phone;
        this.user_email = user_email;
        this.user_birth = user_birth;
        this.user_join_date = user_join_date;
        this.user_status = user_status;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public Date getUser_birth() {
        return user_birth;
    }

    public void setUser_birth(Date user_birth) {
        this.user_birth = user_birth;
    }

    public Date getUser_join_date() {
        return user_join_date;
    }

    public void setUser_join_date(Date user_join_date) {
        this.user_join_date = user_join_date;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "user_no=" + user_no +
                ", user_id='" + user_id + '\'' +
                ", user_pw='" + user_pw + '\'' +
                ", user_nickname='" + user_nickname + '\'' +
                ", user_gender='" + user_gender + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_birth=" + user_birth +
                ", user_join_date=" + user_join_date +
                ", user_status='" + user_status + '\'' +
                '}';
    }
}
