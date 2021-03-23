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

</head>
<body>

<!-- Left column -->
<div class="templatemo-flex-row">
    <jsp:include page="sidebar.jsp"/>
    <!-- Main content -->
    <div class="templatemo-content col-1 light-gray-bg">
        <div class="templatemo-top-nav-container">
            <div class="row">
                <nav class="templatemo-top-nav col-lg-12 col-md-12">
                    <ul class="text-uppercase">
                        <li><a class="active" href="${pageContext.request.contextPath}/admin/user/show">所有用户</a></li>
                        <li><a class="active" href="${pageContext.request.contextPath}/admin/retail/price">查看用户可提现金额</a></li>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="templatemo-content-container">
            <div class="templatemo-content-widget no-padding">
                <div class="panel panel-default table-responsive">
                    <table id="priceinfo" class="table table-striped table-bordered templatemo-user-table">
                        <thead>
                        <tr>
                            <td><a href="" class="white-text templatemo-sort-by">用户名<span class="caret"></span></a></td>
                            <td><a href="" class="white-text templatemo-sort-by">用户获得分销总金额<span class="caret"></span></a></td>
                            <td><a href="" class="white-text templatemo-sort-by">用户可提现金额<span class="caret"></span></a></td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${priceInfo.list}" var="userPrice" >
                            <tr>
                                <td id="idVal">${userPrice.username}</td>
                                <td id="retail_first_percentVal">${userPrice.user_total_price}</td>
                                <td id="retail_second_percentVal">${userPrice.user_price}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="pagination-wrap" id="page-div-nav">
                <div class="page-info" id="page-info-area">
                    当前第${priceInfo.pageNum}页，总共${priceInfo.pages}页，总共${priceInfo.total}记录
                </div>
                <ul class="pagination">
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/retail/price?page=1" aria-label="Next">
                            <span aria-hidden="true">首页</span>
                        </a>
                    </li>
                    <c:if test="${priceInfo.hasPreviousPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/retail/price?page=${priceInfo.pageNum - 1}" aria-label="Previous">
                                <span aria-hidden="true"><i class="fa fa-backward"></i></span>
                            </a>
                        </li>
                    </c:if>

                    <c:forEach items="${priceInfo.navigatepageNums}" var="pageNums">
                        <c:if test="${pageNums == priceInfo.pageNum}">
                            <li class="active"><a href="${pageContext.request.contextPath}/admin/retail/price?page=${pageNums}">${pageNums}</a></li>
                        </c:if>
                        <c:if test="${pageNums != priceInfo.pageNum}">
                            <li><a href="${pageContext.request.contextPath}/admin/retail/price?page=${pageNums}">${pageNums}</a></li>
                        </c:if>
                    </c:forEach>

                    <c:if test="${priceInfo.hasNextPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/retail/price?page=${priceInfo.pageNum + 1}" aria-label="Next">
                                <span aria-hidden="true"><i class="fa fa-forward"></i></span>
                            </a>
                        </li>
                    </c:if>
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/retail/price?page=${priceInfo.pages}" aria-label="Next">
                            <span aria-hidden="true">末页</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div id="path" style="display: none;">${pageContext.request.contextPath}</div>
<!-- jQuery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/templatemo-script.js"></script>
<!-- Templatemo Script -->
<script>
    $(document).ready(function () {
        // Content widget with background image
        var imageUrl = $('img.content-bg-img').attr('src');
        $('.templatemo-content-img-bg').css('background-image', 'url(' + imageUrl + ')');
        $('img.content-bg-img').hide();

        /*$("a").click(function () {
            $(this).addClass("active");
        });*/
    });

</script>
</body>
</html>
