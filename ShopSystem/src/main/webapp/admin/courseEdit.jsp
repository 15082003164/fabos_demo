<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${course!=null && course.course_id!=0?'编辑':'添加'}课程信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 
	 var num = /^\d+(\.\d+)?$/;
	 $("#addBtn").bind('click',function(){
		if($("#paramsCourse\\.course_name").val()==''){
			alert('课程名称不能为空');
			return;
		}
		$("#paramsCourse\\.course_id").val(0);
		$("#info").attr('action','Admin_addCourse.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
	 	if($("#paramsCourse\\.course_name").val()==''){
			alert('课程名称不能为空');
			return;
		}
		$("#info").attr('action','Admin_saveCourse.action').submit();
		 
	});
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">课程管理&gt;&gt;${course!=null && course.course_id!=0?'编辑':'添加'}课程</span>
</div>
<form id="info" name="info" action="Admin_addCourse.action" method="post">   
<input type="hidden" id="paramsCourse.course_id" name="course_id" value="${course.course_id}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">${course!=null && course.course_id!=0?'编辑':'添加'}课程</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td height="1" bgcolor="#8f8f8f"></td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 课程名称：</td>
          <td width="65%">
          	<input type="text" name="course_name" id="paramsCourse.course_name" value="${course.course_name}"/>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"> 课程描述：</td>
          <td>
          	<textarea name="course_note" id="paramsCourse.course_note" Style="width:300px;height:80px;">${course.course_note}</textarea>
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
          	<c:if test="${course!=null && course.course_id!=0}">
          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
          	</c:if>
          	<c:if test="${course==null || course.course_id==0}">
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