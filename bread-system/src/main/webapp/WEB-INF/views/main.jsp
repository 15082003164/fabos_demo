<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>面包店商城</title>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/css/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/sort.js"></script>
    <script src="${pageContext.request.contextPath}/js/holder.js"></script>

    <script>

    </script>
    <style type="text/css">
        .button
        {
            clear:both;
            margin:10px auto;
            text-align:center;
            font-size: 20px;
            padding:10px 0;
            line-height:25px;
            color:#666;
            border-top:#ddd 1px solid;
        }
        .button a
        {
            margin:0 7px;
            color:#666;
        }
        .button a:hover
        {
            color:#000;
            text-decoration:none;
        }
    </style>
</head>
<body>
<div id="main" class="container">

    <div id="header">
        <%@ include file="header.jsp" %>

        <!-- 旋转图 -->
        <div class="header-bottom">
            <div class="sort">
                <div class="sort-channel">
                    <ul class="sort-channel-list list-group">
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/category?cate=面包">软欧</a>
                            <div class="sort-detail">
                                <dl class="dl-hor">
                                    <dt>
                                        <a href="${pageContext.request.contextPath}/category?cate=奶酪软欧">奶酪面包</a>
                                    </dt>
                                    <dd>
                                         <a href="${pageContext.request.contextPath}/category?cate=牛奶软欧">牛奶面包</a>
                                    </dd>
                                </dl>
                            </div>
                        </li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/category?cate=法式">法式</a>
                            <div class="sort-detail">
                                <dl class="dl-hor">
                                    <dt>
                                        <a href="${pageContext.request.contextPath}/category?cate=切片法式">切片面包</a>
                                    </dt>
<%--                                    <dd>--%>
<%--                                        <a href="${pageContext.request.contextPath}/category?cate=洗发水">洗发水</a> <a--%>
<%--                                            href="${pageContext.request.contextPath}/category?cate=沐浴露">沐浴露</a> <a--%>
<%--                                            href="${pageContext.request.contextPath}/category?cate=洗面奶">洗面奶</a> <a--%>
<%--                                            href="${pageContext.request.contextPath}/category?cate=洗手液">洗手液</a>--%>
<%--                                    </dd>--%>
                                </dl>
                            </div>
                        </li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/category?cate=花式">花式</a>
                            <div class="sort-detail">
                                <dl class="dl-hor">
                                    <dt>
                                        <a href="${pageContext.request.contextPath}/category?cate=牛角花式">牛角面包</a>
                                    </dt>
                                    <dd>
                                        <a href="${pageContext.request.contextPath}/category?cate=毛毛虫花式">毛毛虫面包</a>
                                    </dd>
                                </dl>
<%--                                <dl class="dl-hor">--%>
<%--                                    <dt>--%>
<%--                                        <a href="${pageContext.request.contextPath}/category?cate=考研">考研</a>--%>
<%--                                    </dt>--%>
<%--                                    <dd>--%>
<%--                                        <a href="${pageContext.request.contextPath}/category?cate=计算机">计算机</a> <a--%>
<%--                                            href="${pageContext.request.contextPath}/category?cate=自动化">自动化</a> <a--%>
<%--                                            href="${pageContext.request.contextPath}/category?cate=金融">金融</a> <a--%>
<%--                                            href="${pageContext.request.contextPath}/category?cate=其他">其他</a>--%>
<%--                                    </dd>--%>
<%--                                </dl>--%>
                            </div>
                        </li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/category?cate=中式">中式</a>
                            <div class="sort-detail">
                                <dl class="dl-hor">
                                    <dt>
                                        <a  href="${pageContext.request.contextPath}/category?cate=菠萝中式">菠萝面包</a>
                                    </dt>
                                    <dd>
                                        <a  href="${pageContext.request.contextPath}/category?cate=花生酱中式">花生酱面包</a> <a
                                            href="${pageContext.request.contextPath}/category?cate=奶黄中式"> 奶黄面包</a> <a
                                            href="${pageContext.request.contextPath}/category?cate= 葡萄干中式"> 葡萄干面包</a>
                                    </dd>
                                </dl>
                            </div>
                        </li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/category?cate=美式">美式</a>
                            <div class="sort-detail">
                                <dl class="dl-hor">
                                    <dt>
                                        <a href="${pageContext.request.contextPath}/category?cate=全麦美式">全麦面包</a>
                                    </dt>
<%--                                    <dd>--%>
<%--                                        <a  href="${pageContext.request.contextPath}/category?cate=腰带">腰带</a> <a--%>
<%--                                            href="${pageContext.request.contextPath}/category?cate=皮带">皮带</a> <a--%>
<%--                                            href="${pageContext.request.contextPath}/category?cate=帽子">帽子</a> <a--%>
<%--                                            href="${pageContext.request.contextPath}/category?cate=围巾">围巾</a> <a--%>
<%--                                            href="${pageContext.request.contextPath}/category?cate=手套">手套</a>--%>
<%--                                    </dd>--%>
                                </dl>
                            </div>
                        </li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/category?cate=法国">法国</a>
                            <div class="sort-detail">
                                <dl class="dl-hor">
                                    <dt>
                                        <a href="${pageContext.request.contextPath}/category?cate=法棍法国">法棍面包</a>
                                    </dt>

                                </dl>
                            </div>
                        </li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/category?cate=日式">日式</a>
                            <div class="sort-detail">
                                <dl class="dl-hor">
                                    <dt>
                                        <a href="${pageContext.request.contextPath}/category?cate=酵母日式">酵母面包</a>
                                    </dt>
                                </dl>
                            </div>
                        </li>
                    </ul>
                </div>

            </div>
            <div id="mycarousel" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                    <div class="item active">
                        <img src="${pageContext.request.contextPath}/image/4.jpg" alt="">
                    </div>

                    <div class="item">
                        <img src="${pageContext.request.contextPath}/image/3.jpg" alt="">
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/image/5.jpg" alt="">
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/image/6.jpg" alt="">
                    </div>
                </div>

                <ol class="carousel-indicators">
                    <li data-target="#mycarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#mycarousel" data-slide-to="1"></li>
                    <li data-target="#mycarousel" data-slide-to="2"></li>
                    <li data-target="#mycarousel" data-slide-to="3"></li>
                </ol>

                <a class="left carousel-control" href="#mycarousel" role="button"
                   data-slide="prev" style="display: none;"> <span
                        class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a> <a class="right carousel-control" href="#mycarousel" role="button"
                        data-slide="next" style="display: none;"> <span
                    class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
            </div>
            <div class="clear-float"></div>
        </div>
    </div>
    <div class="content">

        <c:if test="${!empty roGoods}">
            <div class="module">
                <div class="hd">
                    <h2>软欧</h2>
                    <hr>
                </div>

                <div class="bd">
                    <div class="data">
                        <ul>
                            <c:forEach items="${roGoods}" var="goods">
                                <li class="data-item-li">
                                    <div class="to-big">
                                        <a href="${pageContext.request.contextPath}/detail?goodsid=${goods.goodsid}"><img src="${pageContext.request.contextPath}/pictures/${goods.imagePaths[0].path}" alt=""
                                                        width="200" height="200"/>
                                        </a>
                                    </div>
                                    <p class="text-right">
                                        <a href="${pageContext.request.contextPath}/detail?goodsid=${goods.goodsid}">${goods.goodsname}</a>
                                    </p>
                                    <div class="text-right">
                                        <b>￥${goods.price}</b>
                                    </div>
                                </li>
                            </c:forEach>


                            <div class="clear-float" style="clear: both;"></div>
                        </ul>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${!empty fsGoods}">
            <div class="module">
                <div class="hd">
                    <h2>法式</h2>
                    <hr>
                </div>

                <div class="bd">
                    <div class="data">
                        <ul>
                            <c:forEach items="${fsGoods}" var="goods">
                                <li class="data-item-li">
                                    <div class="to-big">
                                        <a href="${pageContext.request.contextPath}/detail?goodsid=${goods.goodsid}"><img src="${pageContext.request.contextPath}/pictures/${goods.imagePaths[0].path}" alt=""
                                                                                                                          width="200" height="200"/>
                                        </a>
                                    </div>
                                    <p class="text-right">
                                        <a href="${pageContext.request.contextPath}/detail?goodsid=${goods.goodsid}">${goods.goodsname}</a>
                                    </p>
                                    <div class="text-right">
                                        <b>￥${goods.price}</b>
                                    </div>
                                </li>
                            </c:forEach>


                            <div class="clear-float" style="clear: both;"></div>
                        </ul>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${!empty hsGoods}">
            <div class="module">
                <div class="hd">
                    <h2>花式</h2>
                    <hr>
                </div>

                <div class="bd">
                    <div class="data">
                        <ul>
                            <c:forEach items="${hsGoods}" var="goods">
                                <li class="data-item-li">
                                    <div class="to-big">
                                        <a href="${pageContext.request.contextPath}/detail?goodsid=${goods.goodsid}"><img src="${pageContext.request.contextPath}/pictures/${goods.imagePaths[0].path}" alt=""
                                                                                                                          width="200" height="200"/>
                                        </a>
                                    </div>
                                    <p class="text-right">
                                        <a href="${pageContext.request.contextPath}/detail?goodsid=${goods.goodsid}">${goods.goodsname}</a>
                                    </p>
                                    <div class="text-right">
                                        <b>￥${goods.price}</b>
                                    </div>
                                </li>
                            </c:forEach>


                            <div class="clear-float" style="clear: both;"></div>
                        </ul>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${!empty zsGoods}">
            <div class="module">
                <div class="hd">
                    <h2>中式</h2>
                    <hr>
                </div>

                <div class="bd">
                    <div class="data">
                        <ul>
                            <c:forEach items="${zsGoods}" var="goods">
                                <li class="data-item-li">
                                    <div class="to-big">
                                        <a href="${pageContext.request.contextPath}/detail?goodsid=${goods.goodsid}"><img src="${pageContext.request.contextPath}/pictures/${goods.imagePaths[0].path}" alt=""
                                                                                                                          width="200" height="200"/>
                                        </a>
                                    </div>
                                    <p class="text-right">
                                        <a href="${pageContext.request.contextPath}/detail?goodsid=${goods.goodsid}">${goods.goodsname}</a>
                                    </p>
                                    <div class="text-right">
                                        <b>￥${goods.price}</b>
                                    </div>
                                </li>
                            </c:forEach>


                            <div class="clear-float" style="clear: both;"></div>
                        </ul>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${!empty msGoods}">
            <div class="module">
                <div class="hd">
                    <h2>美式</h2>
                    <hr>
                </div>

                <div class="bd">
                    <div class="data">
                        <ul>
                            <c:forEach items="${msGoods}" var="goods">
                                <li class="data-item-li">
                                    <div class="to-big">
                                        <a href="${pageContext.request.contextPath}/detail?goodsid=${goods.goodsid}"><img src="${pageContext.request.contextPath}/pictures/${goods.imagePaths[0].path}" alt=""
                                                                                                                          width="200" height="200"/>
                                        </a>
                                    </div>
                                    <p class="text-right">
                                        <a href="${pageContext.request.contextPath}/detail?goodsid=${goods.goodsid}">${goods.goodsname}</a>
                                    </p>
                                    <div class="text-right">
                                        <b>￥${goods.price}</b>
                                    </div>
                                </li>
                            </c:forEach>


                            <div class="clear-float" style="clear: both;"></div>
                        </ul>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${!empty fgGoods}">
            <div class="module">
                <div class="hd">
                    <h2>法国</h2>
                    <hr>
                </div>

                <div class="bd">
                    <div class="data">
                        <ul>
                            <c:forEach items="${fgGoods}" var="goods">
                                <li class="data-item-li">
                                    <div class="to-big">
                                        <a href="${pageContext.request.contextPath}/detail?goodsid=${goods.goodsid}"><img src="${pageContext.request.contextPath}/pictures/${goods.imagePaths[0].path}" alt=""
                                                                                                                          width="200" height="200"/>
                                        </a>
                                    </div>
                                    <p class="text-right">
                                        <a href="${pageContext.request.contextPath}/detail?goodsid=${goods.goodsid}">${goods.goodsname}</a>
                                    </p>
                                    <div class="text-right">
                                        <b>￥${goods.price}</b>
                                    </div>
                                </li>
                            </c:forEach>


                            <div class="clear-float" style="clear: both;"></div>
                        </ul>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${!empty rsGoods}">
            <div class="module">
                <div class="hd">
                    <h2>日式</h2>
                    <hr>
                </div>

                <div class="bd">
                    <div class="data">
                        <ul>
                            <c:forEach items="${rsGoods}" var="goods">
                                <li class="data-item-li">
                                    <div class="to-big">
                                        <a href="${pageContext.request.contextPath}/detail?goodsid=${goods.goodsid}"><img src="${pageContext.request.contextPath}/pictures/${goods.imagePaths[0].path}" alt=""
                                                                                                                          width="200" height="200"/>
                                        </a>
                                    </div>
                                    <p class="text-right">
                                        <a href="${pageContext.request.contextPath}/detail?goodsid=${goods.goodsid}">${goods.goodsname}</a>
                                    </p>
                                    <div class="text-right">
                                        <b>￥${goods.price}</b>
                                    </div>
                                </li>
                            </c:forEach>


                            <div class="clear-float" style="clear: both;"></div>
                        </ul>
                    </div>
                </div>
            </div>
        </c:if>



    </div>
</div>

<div class="button">版权归本人所有</div>
</body>
</html>


