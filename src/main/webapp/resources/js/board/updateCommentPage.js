window.addEventListener("DOMContentLoaded", function () {
    var user_no = null;
    var checkSession = function () {

        $.ajax({
            type: "get",
            url: "../user/checkSession",
            data: {

            },
            dataType: "json",
            // contentType : "application/x-www-form-urlencoded", // post
            success: function (data) {

                if (data.result == 'fail') {
                    alert("로그인 후 이용해주시기 바랍니다.");
                    location.href = "../main/main";
                } else {
                    user_no = data.sessionUser.user_no;

                }
            }
        });
    }
    checkSession();

    $("#updateCommentButton").click(function () {

        $.ajax({
            type: "post",
            url: "./updateComment",
            data: {
                comment_no: $("#commentNo").val(),
                board_no: $("#boardNo").val(),
                user_no: user_no,
                comment_content: $("#floatingContent").val()
            },
            dataType: "json",
            // contentType : "application/x-www-form-urlencoded", // post
            success: function (data) {
                if (data.result == "success") {
                    alert("댓글 수정이 완료 되었습니다.");
                    readPage($("#boardNo").val());
                } else {
                    alert("댓글 수정에 문제가 있습니다. 확인 부탁드립니다.");
                }
            }
        });
    });

    function readPage(boardNo) {
        var formObj = $("form[name='readForm']");
        $("#BOARD_NO").attr("value", boardNo);
        formObj.attr("method", "post");
        formObj.attr("action", "/board/postReadPage")
        formObj.submit();
    }
});