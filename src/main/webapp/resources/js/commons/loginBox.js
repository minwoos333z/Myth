/**
 *
 */
window.addEventListener("DOMContentLoaded", function () {
    $('#idInput').keypress(function (e) {

        if (e.keyCode == 13) {
            $("#loginButton").click();
        }

    });

    $('#pwInput').keypress(function (e) {

        if (e.keyCode == 13) {
            $("#loginButton").click();
        }

    });

    $("#loginButton").click(function () {
        $.ajax({
            type: "get",
            url: "../user/userLoginProcess",
            data: {
                user_id: $("#idInput").val(),
                user_pw: $("#pwInput").val(),
            },
            dataType: "json",
            // contentType : "application/x-www-form-urlencoded", // post
            success: function (data) {
                if (data.result == "success") {
                    location.reload();
                } else if (data.result == "out") {

                    if (confirm("비활성화된 계정입니다. 계정 활성화 페이지로 이동하시겠습니까?") == true) {
                        location.href = "../user/logoutUserPorcess";
                        location.href = "../user/userRecoveryPage";
                    } else {
                        return;
                    }
                } else {
                    alert("로그인에 실패하였습니다. 아이디와 비밀번호를 확인해 주세요.");
                }
            }
        });
    });
    $("#logoutButton").click(function () {

        $.ajax({
            type: "post",
            url: "../user/userLoginProcess",
            data: {

            },
            dataType: "json",
            // contentType : "application/x-www-form-urlencoded", // post
            success: function (data) {
                if (confirm("정말 로그아웃하시겠습니까?") == true) {
                    location.reload();
                } else {
                    return;
                }
            }
        });
    });
});