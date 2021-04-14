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
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/templatemo-style.css" rel="stylesheet">

    <!-- JS -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
    <!-- jQuery -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/templatemo-script.js"></script>
    <!-- Templatemo Script -->
    <style>
        .head-div {
            font-size: 18px;
        }

        .goods-table thead {
            background-color: #fbffff;
        }

        .white-text {
            color: #404040;
        }
    </style>
</head>
<body>
<!-- Left column -->
<div class="templatemo-flex-row">
    <jsp:include page="sidebar.jsp"/>
    <!-- Main content -->
    <div class="templatemo-content col-1 light-gray-bg">
        <div class="templatemo-content-container">


                <div class="templatemo-flex-row flex-content-row">
                    <div class="col-1">
                        <div class="panel panel-default margin-10">
                            <div class="panel-body">
                                <div>
                                    <div class="order-info margin-bottom-10">
                                        <div class="head-div">销售情况统计</div>
                                        <div>
                                            <table id="orderinfo" class="table table-striped table-bordered templatemo-user-table goods-table">
                                                <thead>
                                                <tr>
                                                    <td><a href="" class="white-text templatemo-sort-by">商品ID<span
                                                            class="caret"></span></a></td>
                                                    <td><a href="" class="white-text templatemo-sort-by">商品名<span class="caret"></span></a>
                                                    </td>
                                                    <td><a href="" class="white-text templatemo-sort-by">数量<span
                                                            class="caret"></span></a></td>
                                                    <td><a href="" class="white-text templatemo-sort-by">时间<span
                                                            class="caret"></span></a></td>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach items="${pageInfo.list}" var="saleInfo">
                                                <tr>
                                                    <td>${saleInfo.goods_id}</td>
                                                    <td>${saleInfo.goods_name}</td>
                                                    <td>${saleInfo.goods_num}</td>
                                                    <td>${saleInfo.buy_time}</td>
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
                </div>

            <div class="pagination-wrap" id="page-div-nav">
                <div class="page-info" id="page-info-area">
                    当前第${pageInfo.pageNum}页，总共${pageInfo.pages}页，总共${pageInfo.total}记录
                </div>
                <ul class="pagination">
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/order/sale?page=1" aria-label="Next">
                            <span aria-hidden="true">首页</span>
                        </a>
                    </li>

                    <c:if test="${pageInfo.hasPreviousPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/order/sale?page=${pageInfo.pageNum - 1}" aria-label="Previous">
                                <span aria-hidden="true"><i class="fa fa-backward"></i></span>
                            </a>
                        </li>
                    </c:if>

                    <c:forEach items="${pageInfo.navigatepageNums}" var="pageNums">
                        <c:if test="${pageNums == pageInfo.pageNum}">
                            <li class="active"><a href="${pageContext.request.contextPath}/admin/order/sale?page=${pageNums}">${pageNums}</a></li>
                        </c:if>
                        <c:if test="${pageNums != pageInfo.pageNum}">
                            <li><a href="${pageContext.request.contextPath}/admin/order/sale?page=${pageNums}">${pageNums}</a></li>
                        </c:if>
                    </c:forEach>

                    <c:if test="${pageInfo.hasNextPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/order/sale?page=${pageInfo.pageNum + 1}" aria-label="Next">
                                <span aria-hidden="true"><i class="fa fa-forward"></i></span>
                            </a>
                        </li>
                    </c:if>
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/order/sale?page=${pageInfo.pages}" aria-label="Next">
                            <span aria-hidden="true">末页</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
