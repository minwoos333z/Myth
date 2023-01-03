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
});