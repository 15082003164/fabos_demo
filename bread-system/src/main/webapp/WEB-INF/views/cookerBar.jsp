<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="templatemo-sidebar">
    <header class="templatemo-site-header">
        <div class="square"></div>
        <h1>厨房管理</h1>
    </header>
    <div class="mobile-menu-icon">
        <i class="fa fa-bars"></i>
    </div>
    <nav class="templatemo-left-nav">
        <ul>
            <li><a href="${pageContext.request.contextPath}/cooker/show"><i class="fa fa-database fa-fw"></i>面包管理</a></li>
            <li><a href="${pageContext.request.contextPath}/cooker/kinds/show"><i class="fa fa-database fa-fw"></i>材料管理</a></li>
            <li><a href="${pageContext.request.contextPath}/"><i class="fa fa-eject fa-fw"></i>返回主页</a></li>
            <li><a href="${pageContext.request.contextPath}/cooker/logout"><i class="fa fa-eject fa-fw"></i>退出系统</a></li>
        </ul>
    </nav>
</div>
