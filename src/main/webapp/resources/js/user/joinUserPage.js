/**
 *
 */
window.addEventListener("DOMContentLoaded", function () {

    $("#checkIdButton").click(function () {
        var value = $("#joinIdInput").val();

        if (value.replace(/\s|　/gi, "").length == 0) {
            $("#alertId").css({
                "color": "red"
            });
            $("#alertId").text("!  아이디를 입력해주세요.")
            return;
        }

        $.ajax({
            type: "get",
            url: "../user/checkId",
            data: {
                user_id: $("#joinIdInput").val()
            },
            dataType: "json",
            //contentType : "application/x-www-form-urlencoded", // post
            success: function (data) {
                if (data.result == "fail") {
                    $("#alertId").css({
                        "color": "red"
                    });
                    $("#alertId").text("! 이미 사용중인 아이디입니다.")
                } else {
                    $("#alertId").css({
                        "color": "black"
                    });
                    $("#alertId").text("✔  사용 가능한 아이디입니다.")
                }
            }
        });
    });

    $("#checkNickNameButton").click(function () {
        var value = $("#userNickName").val();

        if (value.replace(/\s|　/gi, "").length == 0) {
            $("#alertNickName").css({
                "color": "red"
            });
            $("#alertNickName").text("!  닉네임를 입력해주세요.")
            return;
        }

        $.ajax({
            type: "get",
            url: "../user/checkNickName",
            data: {
                user_nickname: $("#userNickName").val()
            },
            dataType: "json",
            //contentType : "application/x-www-form-urlencoded", // post
            success: function (data) {
                if (data.result == "fail") {
                    $("#alertNickName").css({
                        "color": "red"
                    });
                    $("#alertNickName").text("! 이미 사용중인 닉네임입니다.")
                } else {
                    $("#alertNickName").css({
                        "color": "black"
                    });
                    $("#alertNickName").text("✔  사용 가능한 닉네임입니다.")
                }
            }
        });
    });

    $("#checkPhoneNumberButton").click(function () {
        var value = $("#userPhone").val();

        if (value.replace(/\s|　/gi, "").length == 0) {
            $("#alertPhoneNumber").css({
                "color": "red"
            });
            $("#alertPhoneNumber").text("!  휴대폰번호를 입력해주세요.")
            return;
        }

        $.ajax({
            type: "get",
            url: "../user/checkPhoneNumber",
            data: {
                user_phone: $("#userPhone").val()
            },
            dataType: "json",
            //contentType : "application/x-www-form-urlencoded", // post
            success: function (data) {
                if (data.result == "fail") {
                    $("#alertPhoneNumber").css({
                        "color": "red"
                    });
                    $("#alertPhoneNumber").text("! 이미 사용중인 휴대폰번호입니다.")
                } else {
                    $("#alertPhoneNumber").css({
                        "color": "black"
                    });
                    $("#alertPhoneNumber").text("✔  사용 가능한 휴대폰번호입니다.")
                }
            }
        });
    });

    $("#checkEmailButton").click(function () {
        var value = $("#userEmail").val();

        if (value.replace(/\s|　/gi, "").length == 0) {
            $("#alertEmail").css({
                "color": "red"
            });
            $("#alertEmail").text("!  이메일주소을 입력해주세요.")
            return;
        }

        $.ajax({
            type: "get",
            url: "../user/checkEmail",
            data: {
                user_email: $("#userEmail").val()
            },
            dataType: "json",
            //contentType : "application/x-www-form-urlencoded", // post
            success: function (data) {
                if (data.result == "fail") {
                    $("#alertEmail").css({
                        "color": "red"
                    });
                    $("#alertEmail").text("! 이미 사용중인 이메일주소입니다.")
                } else {
                    $("#alertEmail").css({
                        "color": "black"
                    });
                    $("#alertEmail").text("✔  사용 가능한 이메일주소입니다.")
                    alert("인증번호 발송이 완료되었습니다. 입력한 이메일에서 인증번호 확인을 해주세요.");
                    $("#userCertified").attr("disabled", false);
                    code = data.num;
                }
            }
        });
    });

    $("#confirmEmailButton").click(function () {
        if ($("#userCertified").val().length != 6) {
            $("#alertCertified").text("! 인증번호가 일치하지 않습니다. 다시 확인해주시기 바랍니다.")
            $("#alertCertified").css({
                "color": "red"
            });
            $("#userCertified").attr("autofocus", true);
        } else if ($("#userCertified").val() == code) {
            $("#alertCertified").text("✔ 메일인증이 완료되었습니다.")
            $("#alertCertified").css({
                "color": "green"
            });
            $("#alertCertified").attr("disabled", true);
        } else {
        }
    });

    $("#joinButton").click(function () {

        var answer = $("userFindAnswer").val();

        if ($("#alertId").text() != "✔  사용 가능한 아이디입니다.") {
            alert("아이디 중복 확인을 해주세요.");
            return;
        }

        if ($("#alertNickName").text() != "✔  사용 가능한 닉네임입니다.") {
            alert("닉네임 중복확인을 해주세요");
            return;
        }

        if ($("#alertPhoneNumber").text() != "✔  사용 가능한 휴대폰번호입니다.") {
            alert("휴대폰번호 중복 확인을 해주세요.");
            return;
        }

        if ($("#alertEmail").text() != "✔  사용 가능한 이메일주소입니다.") {
            alert("이메일 중복 확인을 해주세요");
            return;
        }

        if($("#alertCertified").text() != "✔ 메일인증이 완료되었습니다.") {
            alert("메일 인증을 해주세요");
            return;
        }

        $("#insertForm").submit();
        alert("회원가입이 완료 되었습니다.");
    });

});