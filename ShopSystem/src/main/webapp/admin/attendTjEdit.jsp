<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程点名</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 
	 var num = /^\d+(\.\d+)?$/;
	 $("#addBtn1,#addBtn2").bind('click',function(){
		if($("#attend_dmrq").val()==''){
			alert('点名日期不能为空');
			return;
		}
		if($("#attend_dmsj").val()==''){
			alert('点名时间不能为空');
			return;
		}
		$("#attend_status").val(1);
		if($(this).attr("id")=="addBtn2"){
			if(confirm('一旦保存并提交，将不能再次修改，确认保存并提交吗!?')){
				$("#attend_status").val(2);
			}else{
				return;
			}
		}
		var aQuery = $("#info").serialize();  
		$.post('Admin_saveAttendTj.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						window.location.href="Admin_listAttendsTj.action";
					}else  if(responseObj.err.msg){
						 alert('失败：'+responseObj.err.msg);
					}else{
						 alert('失败：服务器异常！');
					}	
		},'json');
		 
	 });
	 
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">任课功能模块&gt;&gt;课程点名</span>
</div>
<form id="info" name="info" action="Admin_addAttend.action" method="post">   
<input type="hidden" id="plan_id" name="plan_id" value="${plan.plan_id}" /> 
<input type="hidden" id="attend_status" name="attend_status" value="1" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">课程点名</TD>
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
          <td width="150" align="right" style="padding-right:5px">班级：</td>
          <td width="*">
          	${plan.dept_name}
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px">课程：</td>
          <td>
          	${plan.plan_weekDesc}　${plan.plan_lessonDesc}　${plan.course_name}
          </td>
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 点名日期：</td>
          <td>
          	<input type="text" name="attend_dmrq" id="attend_dmrq" value="${attend.attend_dmrq}" 
          			onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
          </td>
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 点名时间：</td>
          <td>
          	<input type="text" name="attend_dmsj" id="attend_dmsj" value="${attend.attend_dmsj}" 
          			onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm'})"/>
          </td>
        </tr> 
        <tr>
          <td colspan="2" style="padding-left:5px;background-color:#288CC8;color:white"> 考勤情况</td>
        </tr>
        <tr>
          <td colspan="2"  style="padding:5px"> 
          <table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
		   <tr class="listtitle">
		     <td width="" align="center">序号</td>
		     <td width="" align="center">学生</td>
		     <td width="" align="center">考勤状态</td>
		   </tr>
		   <c:if test="${attends!=null &&  fn:length(attends)>0}">
		   <c:forEach items="${attends}" var="user" varStatus="status">
		   <tr class="list0"> 
		     <td width="" align="center">${status.index+1}</td>
		     <td width="" align="center">${user.real_name}</td>  
		     <td width="" align="center">
				<input type="hidden" name="user_ids" value="${user.user_id}" /> 
		     	<select name="attend_flags" style="width:100px;text-align:center">
				      <c:forTokens items="出勤、事假、病假、公假、迟到、早退、旷课"  delims="、" var="item" varStatus="status">
				      <option ${status.index+1==user.attend_flag?'selected':''} value="${status.index+1}">${item}</option> 
				      </c:forTokens>
				</select>
		     </td> 
		   </tr> 
		   </c:forEach>
		   </c:if>
		   <c:if test="${attends==null ||  fn:length(attends)==0}">
		   <tr>
		     <td height="60" colspan="13" align="center"><b>&lt;不存在学生信息&gt;</b></td>
		   </tr>
		  </c:if>
		  </table>
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
          	<input type="button" id="addBtn1" Class="btnstyle" value="保 存"/>　 
          	<input type="button" id="addBtn2" Class="btnstyle" value="保存并提交"/> 
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>