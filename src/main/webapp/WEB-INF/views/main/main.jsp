<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <link rel="stylesheet" type="text/css" href="../resources/css/mainBody.css">
    <!-- font css -->
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">

    <!-- script -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
    </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="http://lab.alexcican.com/set_cookies/cookie.js" type="text/javascript"></script>

    <script type="text/javascript" src="../resources/js/main/main.js"></script>

    <script type="text/javascript" src="../resources/js/commons/loginBox.js"></script>
    <script type="text/javascript" src="../resources/js/frame/dropdown.js"></script>
    <script type="text/javascript" src="../resources/js/frame/jquery-3.6.0.min.js"></script>

</head>

<body>

<jsp:include page="../commons/mainNav.jsp"></jsp:include>

<!-- header part end-->

<!-- body part start -->

<section class="container-fluid">

    <div class="row mt-4">

        <jsp:include page="../commons/loginBox.jsp"></jsp:include>

        <!-- content -->
        <div class="col">

            <!-- 자주쓰는 기능 -->
            <div class="row px-4 border-top border-bottom">
                <h3 class="secTitle"><i class="bi bi-arrow-right-square-fill"></i> 주로 찾는 기능</h3>
            </div>
            <div class="row middleBtnSection px-4 pb-2 border-bottom">
                <a href="../board/postWriteContentPage">
                    <div class="row iconSpace">
                        <img src="../resources/img/icon/agreement.png" alt="">
                        <h3>글 작성</h3>
                    </div>
                </a>
                <a href="../user/userInfoPage">
                    <div class="row iconSpace">
                        <img src="../resources/img/icon/search.png" alt="">
                        <h3>정보 수정</h3>
                    </div>
                </a>
                <a href="../board/postMainPage">
                    <div class="row iconSpace">
                        <img src="../resources/img/icon/computer.png" alt="">
                        <h3>자유게시판</h3>
                    </div>
                </a>
                <a href="..">
                    <div class="row iconSpace">
                        <img src="../resources/img/icon/conversation.png" alt="">
                        <h3>자주하는 질문</h3>
                    </div>
                </a>
            </div>

            <!-- 공지사항/자주하는질문/사건검색/문서조회 -->
            <div class="row m-1 pt-2">
                <div class="col middleTabSection ms-3 me-2 my-2">
                    <div class="tabBtnSec">
                        <a class="tabBtn tabBtn1 active_now" href="#noticeTab">공지사항</a>
                        <a class="tabBtn tabBtn1" href="#questionTab">자주하는 질문</a>
                        <a class="more_info" id="more_info_L" href=""><i class="bi bi-arrow-right-short"></i>더보기</a>
                    </div>
                    <div id="noticeTab" class="mainSc_tab mainSc_tab1 active_now">
                        <ul id="anncmList">
                            <li><a href="">공지1</a><span>2022.03.28</span></li>
                            <li><a href="">공지1</a><span>2022.03.28</span></li>
                            <li><a href="">공지1</a><span>2022.03.28</span></li>
                            <li><a href="">공지1</a><span>2022.03.28</span></li>
                        </ul>
                    </div>
                    <div id="questionTab" class="mainSc_tab mainSc_tab1">
                        <ul id="FaQList">
                            <li><a href="">사건1</a><span>2022.03.28</span></li>
                            <li><a href="">사건1</a><span>2022.03.28</span></li>
                            <li><a href="">사건1</a><span>2022.03.28</span></li>
                            <li><a href="">사건1</a><span>2022.03.28</span></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>

</html>