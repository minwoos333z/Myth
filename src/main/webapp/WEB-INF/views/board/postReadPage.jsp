<%--
  Created by IntelliJ IDEA.
  User: hanby
  Date: 2023-01-02
  Time: 오후 1:09
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
    <meta name="viewport" content="width=device-width,initial-scale=1">
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
    <script type="text/javascript" src="../resources/js/board/postReadPage.js"></script>

    <script type="text/javascript">

        function goUpdatePage(boardNo) {
            var formObj = $("form[name='updateForm']");
            $("#BOARD_NO").attr("value", boardNo);
            formObj.attr("method", "post");
            formObj.attr("action", "/board/updatePostContentPage");
            formObj.submit();
        }

        function writeCommentPage(boardNo) {
            var formObj = $("form[name='deleteForm']");
            $("#BoardNo").attr("value", boardNo);
            formObj.attr("method", "post");
            formObj.attr("action", "/board/writeCommentPage");
            formObj.submit();
        }

        function postDeleteContentPage(boardNo) {
            if (confirm("정말로 게시글을 삭제 하시겠습니까?")) {
                var formObj = $("form[name='deleteForm']");
                $("#BoardNo").attr("value", boardNo);
                formObj.attr("method", "post");
                formObj.attr("action", "/board/deletePostContentProcess");
                formObj.submit();
            }
        }

        function goCommentUpdatePage(commentNo) {
            var formObj = $("form[name='commentForm']");
            $("#COMMENT_NO").attr("value", commentNo);
            formObj.attr("method", "post");
            formObj.attr("action", "updateCommentPage");
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
                <h3 class="conTItle"><i class="bi bi-list"></i> 제목 : ${data.boardVo.board_title}</h3>
            </div>

            <!-- 페이지별 내용 시작-->
            <table class="table" style="border-top:1px solid steelblue; border-bottom:2px solid steelblue">

                <tr>
                    <th scope="row" width=100px align="center" style="border-right:2px solid steelblue">카테고리</th>
                    <td width=100px align="center">
                        ${data.categoryVo.category_name}
                    </td>
                </tr>

                <tr>
                    <th scope="row" width=100px align="center" style="border-right:2px solid steelblue">작성자</th>
                    <td width=100px align="center">
                        ${data.userVo.user_nickname}
                    </td>
                </tr>

                <tr>
                    <th scope="row" width=100px align="center" style="border-right:1.5px solid steelblue">조회수</th>
                    <td width=100px align="center">
                        ${data.boardVo.board_readcount }
                    </td>
                </tr>

                <tr>
                    <th scope="row" width=100px align="center" style="border-right:1.5px solid steelblue">좋아요수</th>
                    <td width=100px align="center">
                        ${totalLikeCount}
                    </td>
                </tr>

                <tr>
                    <th scope="row" width=100px align="center" style="border-right:1.5px solid steelblue">작성일시</th>
                    <td width=100px align="center">
                        <fmt:formatDate value="${data.boardVo.board_write_date }" pattern="yyyy년MM월dd일 HH시 mm분 ss초" />
                    </td>
                </tr>

                <tr>
                    <td colspan="4">
                        <br>
                        <br>
                        ${data.boardVo.board_content }
                        <br>
                        <br>
                        <br>
                    </td>
                </tr>

            </table>

            <div class="row mt-2">
                <span>파일 목록</span>
                <div class="col">
                    <c:forEach items="${fileList }" var="file">
                        <div class="form-group" style="border: 1px solid #dbdbdb;">
                            <a href="#" onclick="fn_fileDown('${file.file_no}'); return false;">${file.org_file_name}</a>(${file.file_size}kb)<br>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <div class="row mt-3">
                <table class="table" style="border-top:1px solid steelblue; border-bottom:2px solid steelblue">
                    <thead>
                    <tr>
                        <th scope="col">댓글번호</th>
                        <th scope="col">댓글내용</th>
                        <th scope="col">댓글작성자</th>
                        <th scope="col">작성일</th>
                        <th scope="col">좋아요수</th>
                        <th scope="col">삭제</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${dataList }" var="date">
                        <tr>
                            <th class="text-center">${date.commentVo.comment_no }</th>
                            <th class="text-center">${date.commentVo.comment_content }</th>
                            <th class="text-center">${date.userVo.user_nickname }</th>
                            <th class="text-center"><fmt:formatDate value="${date.commentVo.comment_write_date }" pattern="yyyy년MM월dd일 HH시 mm분 ss초" /></th>
                            <th class="text-center">${date.totalCommentLikeCount }</th>
                            <input type="hidden" id="commentNo" value="${date.commentVo.comment_no}">
                            <c:choose>
                                <c:when test="${sessionUser.user_no == date.commentVo.user_no }">
                                    <th>
                                        <button type="button" class="btn btn-outline-primary" id="deleteComment">댓글삭제</button>
                                        <a href="javascript:goCommentUpdatePage(${date.commentVo.comment_no });" type="button" class="btn btn-outline-primary">댓글수정</a>
                                        <button type="button" class="btn btn-outline-primary" id="doCommentLike">댓글 좋아요</button>
                                    </th>
                                </c:when>
                                <c:otherwise>
                                    <th><button type="button" class="btn btn-outline-primary" id="doCommentLike">댓글 좋아요</button></th>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                <a href="./postMainPage" class="btn btn-outline-primary" type="button">목록으로</a>
                <c:if test="${!empty sessionUser }">
                    <a href="javascript:writeCommentPage(${data.boardVo.board_no })" type="button" class="btn btn-outline-primary">댓글작성</a>
                    <button type="button" class="btn btn-outline-primary" id="doLike">좋아요</button>
                </c:if>
                <c:if test="${sessionUser.user_no == data.boardVo.user_no}">
                    <a href="javascript:postDeleteContentPage(${data.boardVo.board_no });" class="btn btn-outline-primary" type="button">글삭제</a>
                    <a href="javascript:goUpdatePage(${data.boardVo.board_no });" class="btn btn-outline-primary" type="button">글수정</a>
                </c:if>
            </div>

            <form name="updateForm" role="form" method="post">
                <input type="hidden" id="BOARD_NO" name="board_no" value="">
            </form>

            <form name="deleteForm" role="form" method="post">
                <input type="hidden" id="BoardNo" name="board_no" value="">
            </form>

            <form name="commentForm" role="form" method="post">
                <input type="hidden" id="COMMENT_NO" name="comment_no" value="">
            </form>

            <input type="hidden" id="like_board_no" value="${data.boardVo.board_no}">

            <!-- 페이지별 내용 끝 -->
        </div>
    </div>
</section>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous">

</script>
</body>

</html>