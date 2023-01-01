<%--
  Created by IntelliJ IDEA.
  User: hanby
  Date: 2023-01-02
  Time: 오전 04:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            </div>

            <form name="readForm" role="form" method="post">
                <input type="hidden" id="BOARD_NO" name="board_no" value="">
            </form>

            <!-- 페이지별 내용 시작-->
            <div class="row formTable">
                <table>
                    <thead>
                    <th scope="col">카테고리</th>
                    <th scope="col">글번호</th>
                    <th scope="col">제목</th>
                    <th scope="col">작성자</th>
                    <th scope="col">작성일</th>
                    <th scope="col">조회수</th>
                    </thead>

                    <tbody>
                    <c:forEach items="${dataList }" var="data">
                        <tr>
                            <td class="text-center">${data.categoryVo.category_name }</td>
                            <td class="text-center">${data.boardVo.board_no }</td>
                            <td class="text-center"><a href="javascript:goPage(${data.boardVo.board_no });">${data.boardVo.board_title}</td>
                            <td class="text-center">${data.userVo.user_nickname}(${data.userVo.user_id})</td>
                            <td class="text-center"><fmt:formatDate value="${data.boardVo.board_write_date }" pattern="yyyy:MM:dd: HH:mm:ss" /></td>
                            <td class="text-center">${data.boardVo.board_readcount}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="row-3">
                <div align="right">
                    <div class="col-2 d-grid">
                        <a href="../board/postWriteContentPage" class="btn btn-outline-primary">글쓰기</a>
                    </div>
                </div>
            </div>

            <!-- 페이지별 내용 끝 -->
        </div>
    </div>
</section>
</body>

</html>