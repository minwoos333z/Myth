window.addEventListener("DOMContentLoaded", function () {

    $("#writeCommentButton").click(function () {

        $.ajax({
            type: "post",
            url: "./writeComment",
            data: {
                board_no: $("#boardNo").val(),
                comment_content: $("#commentContent").val()
            },
            dataType: "json",
            // contentType : "application/x-www-form-urlencoded", // post
            success: function (data) {
                alert("댓글 작성이 완료 되었습니다.");
                readPage($("#boardNo").val());
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