<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考勤记录信息</title>
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
   document.info.action="Admin_listAttendsSelf.action";
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
       alert("请至少选择一个要删除的考勤记录！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delAttends.action?ids="+ids;
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
  document.info.action="Admin_listAttendsSelf.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listAttendsSelf.action";
  document.info.submit();
}
function exportUsers()
{
   document.info.action="exportAttendsSelf.action";
   document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">考勤记录管理&gt;&gt;考勤记录查询</span>
</div>
<form name="info" id="info" action="Admin_listAttendsSelf.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${paperUtil.pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">考勤记录列表</td>
    <td width="" align="right">
            姓名：
      <input type="text" id="real_name" name="real_name" value="${paramsAttend.real_name}" class="inputstyle" Style="width:100px"/>&nbsp;
            班级：
      <select name="dept_id" id="dept_id" Class="selectstyle" Style="width:100px">
      	  <option value="0">请选择</option> 
	      <c:forEach items="${depts}" var="dept">
	      <option ${dept.dept_id==paramsAttend.dept_id?'selected':''} value="${dept.dept_id}">${dept.dept_name}</option> 
	      </c:forEach>
	  </select>&nbsp;
	   课程：
      <select name="course_id" id="course_id" Class="selectstyle" Style="width:100px">
      	  <option value="0">请选择</option> 
	      <c:forEach items="${courses}" var="course">
	      <option ${course.course_id==paramsAttend.course_id?'selected':''} value="${course.course_id}">${course.course_name}</option> 
	      </c:forEach>
	  </select>&nbsp;
	   考勤状态：
      <select name="attend_flag" id="attend_flag" Class="selectstyle" Style="width:100px">
      	  <option value="0">请选择</option> 
	      <c:forTokens items="出勤、事假、病假、公假、迟到、早退、旷课"  delims="、" var="item" varStatus="status">
	      <option ${status.index+1==paramsAttend.attend_flag?'selected':''} value="${status.index+1}">${item}</option> 
	      </c:forTokens>
	  </select>&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
      <!-- <input type="button" value="导出" onclick="exportUsers();" class="btnstyle"/> -->
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="" align="center">序号</td>
     <td width="" align="center">点名日期</td>
     <td width="" align="center">点名时间</td>
     <td width="" align="center">班级</td>
     <td width="" align="center">学生</td>
     <td width="" align="center">课程</td>
     <td width="" align="center">考勤状态</td>
   </tr>
   <c:if test="${attends!=null &&  fn:length(attends)>0}">
   <c:forEach items="${attends}" var="attend" varStatus="status">
   <tr class="list0" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center">${status.index+1+paramsAttend.start}</td>
     <td width="" align="center">${attend.attend_dmrq}</td>
     <td width="" align="center">${attend.attend_dmsj}</td>
     <td width="" align="center">${attend.dept_name}</td>
     <td width="" align="center">${attend.real_name}</td>  
     <td width="" align="center">${attend.course_name}&nbsp;</td> 
     <td width="" align="center">${attend.attend_flagDesc}</td> 
   </tr> 
   </c:forEach>
   </c:if>
   <c:if test="${attends==null ||  fn:length(attends)==0}">
   <tr>
     <td height="60" colspan="13" align="center"><b>&lt;不存在考勤记录信息&gt;</b></td>
   </tr>
  </c:if>
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>