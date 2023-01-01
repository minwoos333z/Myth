<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Insert title here</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../resources/css/user/joinUserCompletePage.css">

</head>

<body>


<div class="row" id="container">
    <div class="col-1" style="width:5%;"></div>
    <div class="col">
        <jsp:include page="../commons/mainNav.jsp"></jsp:include>
        <div class="row mt-3">
            <div class="col-1" style="width:13%;">
                <jsp:include page="../commons/loginBox.jsp"></jsp:include>
                <div class="row">
                    <div class="col"></div>
                </div>
            </div>
            <div class="col ps-5" style="">
                <!-- 각 페이지 내용 여기에 들어가면 됩니다  -->
                <div class="row mt-2">
                    <!-- subtitle -->
                    <div class="col fs-2">
                        회원 가입이 완료되었습니다 !
                    </div>
                </div>
                <div class="row mt-3 divisionLine"></div>
            </div>
            <div class="col-2" style="width:13%;"></div>
        </div>
    </div>
    <div class="col-1" style="width:5%;"></div>
</div>







<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
</script>
</body>

</html>