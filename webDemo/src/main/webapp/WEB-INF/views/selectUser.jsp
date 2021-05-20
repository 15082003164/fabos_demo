<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>后台管理</title>
    <meta name="description" content="">
    <meta name="author" content="templatemo">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet'
          type='text/css'>
    <!-- JS -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/css/bootstrap/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/templatemo-style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="${pageContext.request.contextPath}/js/sweetalert.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sweetalert.css">
    <%--<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>--%>
    <style>
        .show-span {
            border: none !important;
        }
    </style>
</head>


<body>

<div class="templatemo-flex-row">
    <!-- Main content -->
    <div class="templatemo-content col-1 light-gray-bg">
        <div class="templatemo-top-nav-container">
            <div class="row">
                <nav class="templatemo-top-nav col-lg-12 col-md-12">
                    <ul class="text-uppercase">
                        <li><a class="active" href="${pageContext.request.contextPath}/show">所有用户</a></li>
                        <li><a class="active" href="${pageContext.request.contextPath}/insertShow">新增用户</a></li>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="templatemo-content-container">
            <div class="templatemo-form-test">
                    <form class="navbar-form navbar-right" role="search" method="get" action="${pageContext.request.contextPath}/selectUser" style="left: auto">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="姓名查询" name="name">
                        </div>
                        <button type="submit" class="btn btn-default">
                            <span class="glyphicon glyphicon-search" aria-label="selectUser"></span>
                        </button>
                    </form>
            </div>

        </div>
            <div class="templatemo-flex-row flex-content-row">
                <div class="col-2">
                    <div class="panel panel-default margin-10">
                        <div>
                            <table id="goodsinfo" class="table table-striped table-bordered templatemo-user-table goods-table">
                                <thead>
                                <tr>
                                    <td><h2 href="" class="white-text templatemo-sort-by">id<span class="caret"></span></h2></td>
                                    <td><h2 href="" class="white-text templatemo-sort-by">姓名<span class="caret"></span></h2></td>
                                    <td><h2 href="" class="white-text templatemo-sort-by">学号<span class="caret"></span></h2></td>
                                    <td><h2 href="" class="white-text templatemo-sort-by">班级名<span class="caret"></span></h2></td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${pageInfo.list}" var="user">
                                <tr>
                                    <td>${user.id}</td>
                                    <td>${user.name}</td>
                                    <td>${user.tel}</td>
                                    <td>${user.classname}</td>
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
    </div>
</div>
<div id="path" style="display: none;">${pageContext.request.contextPath}</div>
<!-- jQuery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/templatemo-script.js"></script>
<script src="${pageContext.request.contextPath}/js/selectUser.js"></script>
<!-- Templatemo Script -->
<script>
    $(document).ready(function () {
        // Content widget with background image
        var imageUrl = $('img.content-bg-img').attr('src');
        $('.templatemo-content-img-bg').css('background-image', 'url(' + imageUrl + ')');
        $('img.content-bg-img').hide();
    });
</script>
</body>
</html>
