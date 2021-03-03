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

<div class="modal fade" id="updateRetailConfig" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">修改分销配置</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="update-form" name="update-form" method="post">
                    <div class="form-group">
                        <label for="retail_first_percent" class="col-sm-2 control-label">一级分销比</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="retail_first_percent" id="retail_first_percent">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="retail_second_percent" class="col-sm-2 control-label">二级分销比</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="retail_second_percent" id="retail_second_percent">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="retail_third_percent" class="col-sm-2 control-label">三级分销比</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="retail_third_percent" id="retail_third_percent">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="price_config" class="col-sm-2 control-label">最低提现额</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="price_config" name="price_config">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="saveRetailConfig" >保存</button>
            </div>
        </div>
    </div>
</div>

<!-- Left column -->
<div class="templatemo-flex-row">
    <jsp:include page="sidebar.jsp"/>
    <!-- Main content -->
    <div class="templatemo-content col-1 light-gray-bg">
        <div class="templatemo-top-nav-container">
            <div class="row">
                <nav class="templatemo-top-nav col-lg-12 col-md-12">
                    <ul class="text-uppercase">
                        <li><a href="${pageContext.request.contextPath}/admin/retail/show" class="active">分销配置</a></li>

                    </ul>
                </nav>
            </div>
        </div>
        <div class="templatemo-content-container">
            <div class="templatemo-content-widget no-padding">
                <div class="panel panel-default table-responsive">
                    <table id="retailinfo" class="table table-striped table-bordered templatemo-user-table">
                        <thead>
                        <tr>
                            <td><a href="" class="white-text templatemo-sort-by">id<span class="caret"></span></a></td>
                            <td><a href="" class="white-text templatemo-sort-by">一级分销比<span class="caret"></span></a></td>
                            <td><a href="" class="white-text templatemo-sort-by">二级分销比<span class="caret"></span></a></td>
                            <td><a href="" class="white-text templatemo-sort-by">三级分销比<span class="caret"></span></a></td>
                            <td><a href="" class="white-text templatemo-sort-by">最低提现额<span class="caret"></span></a></td>
                            <td>操作</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pageInfo.list}" var="retailConfig" varStatus="num">
                            <tr>
                                <td id="idVal">${retailConfig.id}</td>
                                <td id="retail_first_percentVal">${retailConfig.retail_first_percent}</td>
                                <td id="retail_second_percentVal">${retailConfig.retail_second_percent}</td>
                                <td id="retail_third_percentVal">${retailConfig.retail_third_percent}</td>
                                <td id="price_configVal">${retailConfig.price_config}</td>
                                <td><button style="color: red" id="changeRetailConfig">修改</button></td>
<%--                               <td> <input type="button" value="弹出固定窗口" onClick="javascript:window.open('#','','scrollbars=yes,width=600,height=200')" ></td>--%>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>
<div id="path" style="display: none;">${pageContext.request.contextPath}</div>
<!-- jQuery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/retail.js"></script>
<script src="https://cdn.bootcss.com/material-design-lite/1.3.0/material.min.js"></script>
</body>
</html>
