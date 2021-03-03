<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${plan!=null && plan.plan_id!=0?'编辑':'添加'}教学安排信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 var num = /^\d+$/;
	 $("#addBtn").bind('click',function(){
		if($("#dept_id").val()=='0'){
			alert('班级不能为空');
			return;
		}
		if($("#user_id").val()=='0'){
			alert('教师不能为空');
			return;
		}
		if($("#course_id").val()=='0'){
			alert('课程不能为空');
			return;
		}
		$("#plan_id").val(0);
		$("#info").attr('action','Admin_addPlan.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		if($("#dept_id").val()=='0'){
			alert('班级不能为空');
			return;
		}
		if($("#user_id").val()=='0'){
			alert('教师不能为空');
			return;
		}
		if($("#course_id").val()=='0'){
			alert('课程不能为空');
			return;
		}
		$("#info").attr('action','Admin_savePlan.action').submit();
		 
	 });
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">教学安排管理&gt;&gt;${plan!=null && plan.plan_id!=0?'编辑':'添加'}教学安排信息</span>
</div>
<form id="info" name="info" action="Admin_addPlan.action" method="post">   
<input type="hidden"  id="plan_id" name="plan_id" value="${plan.plan_id}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">${plan!=null && plan.plan_id!=0?'编辑':'添加'}教学安排信息</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td height="1" bgcolor="#8f8f8f"></td>
   </tr>
   <tr>
     <td>
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 星期几：</td>
          <td width="65%" >
              <select name="plan_week" id="plan_week" Class="selectstyle" Style="width:155px">
			      <option ${1==plan.plan_week?'selected':''} value="1">星期一</option> 
			      <option ${2==plan.plan_week?'selected':''} value="2">星期二</option> 
			      <option ${3==plan.plan_week?'selected':''} value="3">星期三</option> 
			      <option ${4==plan.plan_week?'selected':''} value="4">星期四</option> 
			      <option ${5==plan.plan_week?'selected':''} value="5">星期五</option> 
			  </select>
          </td>
        </tr>  
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 第几节课：</td>
          <td>
            <select name="plan_lesson" id="plan_lesson" Class="selectstyle"  Style="width:155px">
			      <option ${1==plan.plan_lesson?'selected':''} value="1">第1、2节课</option> 
			      <option ${2==plan.plan_lesson?'selected':''} value="2">第3、4节课</option> 
			      <option ${3==plan.plan_lesson?'selected':''} value="3">第5、6节课</option> 
			      <option ${4==plan.plan_lesson?'selected':''} value="4">第7、8节课</option> 
			  </select>
          </td>
        </tr>  
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 班级：</td>
          <td>
             <select name="dept_id" id="dept_id" Class="selectstyle" Style="width:155px">
		      	  <option value="0">请选择</option> 
			      <c:forEach items="${depts}" var="dept">
			      <option ${dept.dept_id==plan.dept_id?'selected':''} value="${dept.dept_id}">${dept.dept_name}</option> 
			      </c:forEach>
			  </select>
          </td>
        </tr>    
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 教师：</td>
          <td>
              <select name="user_id" id="user_id" Class="selectstyle" Style="width:155px">
		      	  <option value="0">请选择</option> 
			      <c:forEach items="${users}" var="user">
			      <option ${user.user_id==plan.user_id?'selected':''} value="${user.user_id}">${user.real_name}</option> 
			      </c:forEach>
			  </select>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 课程：</td>
          <td>
              <select name="course_id" id="course_id" Class="selectstyle" Style="width:155px">
		      	  <option value="0">请选择</option> 
			      <c:forEach items="${courses}" var="course">
			      <option ${course.course_id==plan.course_id?'selected':''} value="${course.course_id}">${course.course_name}</option> 
			      </c:forEach>
			  </select>
          </td>
        </tr>   
     </table>
     </td>
   </tr>  
   <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30">
          	<c:if test="${plan!=null && plan.plan_id!=0}">
          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
          	</c:if>
          	<c:if test="${plan==null || plan.plan_id==0}">
          	<input type="button" id="addBtn" Class="btnstyle" value="添 加" />
          	</c:if>
            &nbsp;<label style="color:red">${tip}</label>
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>