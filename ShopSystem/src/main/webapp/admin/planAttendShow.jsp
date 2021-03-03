<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程点名信息</title>
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
   document.info.action="Admin_listPlansAttend.action";
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
       alert("请至少选择一个要删除的课程点名！");
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
  document.info.action="Admin_listPlansAttend.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listPlansAttend.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">任课功能模块&gt;&gt;课程安排查询</span>
</div>
<form name="info" id="info" action="Admin_listPlansAttend.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${paperUtil.pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">选择课程安排进行点名</td>
    <td width="" align="right">
            班级：
      <select name="dept_id" id="dept_id" Class="selectstyle" Style="width:155px">
      	  <option value="0">请选择</option> 
	      <c:forEach items="${depts}" var="dept">
	      <option ${dept.dept_id==paramsPlan.dept_id?'selected':''} value="${dept.dept_id}">${dept.dept_name}</option> 
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
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
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
     <td width="" align="center">${status.index+1}</td>
     <td width="" align="center">${plan.plan_weekDesc}</td>
     <td width="" align="center">${plan.plan_lessonDesc}</td>
     <td width="" align="center">${plan.dept_name}</td>
     <td width="" align="center">${plan.real_name}</td>  
     <td width="" align="center">${plan.course_name}&nbsp;</td>  
     <td width="" align="center">
       <img src="images/edit.png"/>&nbsp;<a href="Admin_addAttendShow.action?plan_id=${plan.plan_id}">点名</a>
     </td>
   </tr> 
   </c:forEach>
   </c:if>
   <c:if test="${plans==null ||  fn:length(plans)==0}">
   <tr>
     <td height="60" colspan="13" align="center"><b>&lt;不存在课程安排信息&gt;</b></td>
   </tr>
  </c:if>
</table>
</form> 
</body>
</html>