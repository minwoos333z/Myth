<%--
  Created by IntelliJ IDEA.
  User: hanby
  Date: 2023-01-04
  Time: 오전 8:47
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <!-- bootstrap css -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <!-- my css -->
    <link rel="stylesheet" type="text/css" href="../resources/css/frameCss.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/commonCss.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/commonFormCss.css">

    <!-- font css -->
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">

    <!-- script -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
    </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="http://lab.alexcican.com/set_cookies/cookie.js" type="text/javascript"></script>

    <script type="text/javascript" src="../resources/js/commons/loginBox.js"></script>
    <script type="text/javascript" src="../resources/js/frame/dropdown.js"></script>
    <script type="text/javascript" src="../resources/js/frame/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="../resources/js/board/writeCommentPage.js"></script>\
    <script type="text/javascript">

        function goPage(boardNo) {
            var formObj = $("form[name='readForm']");
            $("#BOARD_NO").attr("value", boardNo);
            formObj.attr("method", "post");
            formObj.attr("action", "/board/postReadPage")
            formObj.submit();
        }

    </script>

</head>

<body>

<jsp:include page="../commons/mainNav.jsp"></jsp:include>

<!-- header part end-->

<!-- body part start -->

<section class="container-fluid">

    <div class="row mt-4">

        <jsp:include page="../commons/loginBox.jsp"></jsp:include>

        <div class="col">
            <!-- page Title -->
            <div class="row mt-1 conTitleArea">
                <h3 class="conTItle"><i class="bi bi-list"></i> 댓글 작성</h3>
            </div>

            <!-- 페이지별 내용 시작-->
            <form:form action="./writeComment" modelAttribute="commentVo" id="writeForm">
                <div class="row mt-2">
                    <div class="col">
                        <form:input type="text" id="commentContent" class="from-control" placeholder="댓글내용"
                                    path="comment_content" />
                    </div>
                    <div class="col my-auto">
                        <form:errors path="comment_content" id="error_message" />
                    </div>
                </div>

                <div class="row mt-2">
                    <div class="col"></div>
                    <div class="col"></div>
                    <div class="col">
                        <a href="javascript:goPage(${boardNo});" class="btn btn-dark" style="float: right;">작성취소</a>
                        <button type="button" class="btn btn-dark" style="float: right;" id="writeCommentButton">작성</button>
                    </div>
                </div>

            </form:form>

            <form name="readForm" role="form" method="post">
                <input type="hidden" id="BOARD_NO" name="board_no" value="">
            </form>

            <input type="hidden" id="boardNo" name="board_no" value="${boardNo}">

            <!-- 페이지별 내용 끝 -->
        </div>
    </div>
</section>

</body>

</html>