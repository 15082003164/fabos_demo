<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教学安排信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
var tempClassName="";
function tr_mouseover(obj) 
{ 
	tempClassName=obj.className;
	obj.className="list_mouseover";
}
function tr_mouseout(obj)      
{ 
	obj.className=tempClassName;
}      
function CheckAll(obj) 
{
	var checks=document.getElementsByName("chkid");
    for (var i=0;i<checks.length;i++)
	{
	    var e = checks[i];
	    e.checked = obj.checked;
	}
    
}


function serch()
{
   document.info.action="Admin_listPlans.action";
   document.getElementById("pageNo").value=1;
   document.info.submit();
}
function del()
{
	var checks=document.getElementsByName("chkid");
    var ids="";
	for (var i=0;i<checks.length;i++)
    {
        var e = checks[i];
		if(e.checked==true)
		{
		  if(ids=="")
		  {
		    ids=ids+e.value;
		  }
		  else
		  {
		    ids=ids+","+e.value;
		  }
		}
    }
    if(ids=="")
    {
       alert("请至少选择一个要删除的教学安排！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delPlans.action?ids="+ids;
       document.info.submit();
    }
    
}
function GoPage()
{
  var pagenum=document.getElementById("goPage").value;
  var patten=/^\d+$/;
  if(!patten.exec(pagenum))
  {
    alert("页码必须为大于0的数字");
    return false;
  }
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listPlans.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listPlans.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">教学安排信息管理&gt;&gt;教学安排查询</span>
</div>
<form name="info" id="info" action="Admin_listPlans.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${paperUtil.pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">教学安排列表</td>
    <td width="" align="right">
            班级：
      <select name="dept_id" id="dept_id" Class="selectstyle" Style="width:155px">
      	  <option value="0">请选择</option> 
	      <c:forEach items="${depts}" var="dept">
	      <option ${dept.dept_id==paramsPlan.dept_id?'selected':''} value="${dept.dept_id}">${dept.dept_name}</option> 
	      </c:forEach>
	  </select>&nbsp;
	  教师：
      <select name="user_id" id="user_id" Class="selectstyle" Style="width:155px">
      	  <option value="0">请选择</option> 
	      <c:forEach items="${users}" var="user">
	      <option ${user.user_id==paramsPlan.user_id?'selected':''} value="${user.user_id}">${user.real_name}</option> 
	      </c:forEach>
	  </select>&nbsp;
	   课程：
       <select name="course_id" id="course_id" Class="selectstyle" Style="width:155px">
      	  <option value="0">请选择</option> 
	      <c:forEach items="${courses}" var="course">
	      <option ${course.course_id==paramsPlan.course_id?'selected':''} value="${course.course_id}">${course.course_name}</option> 
	      </c:forEach>
	  </select>&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
      <input type="button" value="删除" onclick="del();" class="btnstyle"/>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
     <td width="" align="center">序号</td>
     <td width="" align="center">星期几</td>
     <td width="" align="center">第几节课</td>
     <td width="" align="center">班级</td>
     <td width="" align="center">教师</td>
     <td width="" align="center">课程</td>
     <td width="" align="center">操作</td>
   </tr>
   <c:if test="${plans!=null &&  fn:length(plans)>0}">
   <c:forEach items="${plans}" var="plan" varStatus="status">
   <tr class="list0" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center"><input type="checkbox" name="chkid" value="${plan.plan_id}" style="vertical-align:text-bottom;"/></td>
     <td width="" align="center">${status.index+1+paramsPlan.start}</td>
     <td width="" align="center">${plan.plan_weekDesc}</td>
     <td width="" align="center">${plan.plan_lessonDesc}</td>
     <td width="" align="center">${plan.dept_name}</td>
     <td width="" align="center">${plan.real_name}</td>  
     <td width="" align="center">${plan.course_name}&nbsp;</td>  
     <td width="" align="center">
       <img src="images/edit.png"/>&nbsp;<a href="Admin_editPlan.action?plan_id=${plan.plan_id}">编辑</a>
     </td>
   </tr> 
   </c:forEach>
   </c:if>
   <c:if test="${plans==null ||  fn:length(plans)==0}">
   <tr>
     <td height="60" colspan="13" align="center"><b>&lt;不存在教学安排信息&gt;</b></td>
   </tr>
  </c:if>
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>