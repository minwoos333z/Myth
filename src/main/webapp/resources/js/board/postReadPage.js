window.addEventListener("DOMContentLoaded", function () {

    $("#doLike").click(function () {

        $.ajax({
            type: "post",
            url: "./doLike",
            data: {
                board_no: $("#like_board_no").val()
            },
            dataType: "json",
            // contentType : "application/x-www-form-urlencoded", // post
            success: function (data) {
                location.reload();
            }
        });
    });

    $("#deleteComment").click(function () {

        if (confirm("댓글을 정말로 삭제 하시겠습니까??")) {
            $.ajax({
               type: "post",
               url: "./deleteComment",
               data: {
                   comment_no:$("#commentNo").val()
               },
                dataType: "json",
                success: function (data) {
                   alert("댓글삭제에 성공하였습니다.");
                   location.reload();
                }
            });
        }
    });

    $("#doCommentLike").click(function() {
        $.ajax({
            type: "post",
            url: "./doCommentLike",
            data: {
                comment_no: $("#commentNo").val()
            },
            dataType: "json",
            // contentType : "application/x-www-form-urlencoded", // post
            success: function (data) {
                if (data.result == "error") {
                    alert(data.reason);
                    return;
                }
                alert("댓글 좋아요을 완료 하였습니다.");
                location.reload();
            }
        });
    });
});