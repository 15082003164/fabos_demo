<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的课表周视图</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
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
   document.info.action="Admin_listPlansSelf.action";
   document.info.submit();
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
  document.info.action="Admin_listPlansSelf.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listPlansSelf.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">课表信息查询&gt;&gt;我的课表周视图</span>
</div>
<form name="info" id="info" action="Admin_listPlansSelf.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${paperUtil.pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">我的课表周视图</td>
    <td width="" align="right">
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td align="center" width="60"></td>
     <td align="center" width="60"></td>
     <td width="" align="center">星期一</td>
     <td width="" align="center">星期二</td>
     <td width="" align="center">星期三</td>
     <td width="" align="center">星期四</td>
     <td width="" align="center">星期五</td>
     <td width="" align="center">星期六</td>
     <td width="" align="center">星期日</td>
   </tr>
   <c:if test="${plans!=null &&  fn:length(plans)>0}">
   <c:forEach items="${plans}" var="plan" varStatus="status">
   <tr class="list0" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
    <c:if test="${status.index==0}">
     <td align="center" rowspan="4">上午</td>
     </c:if>
     <c:if test="${status.index==2}">
     <td align="center" rowspan="4">下午</td>
     </c:if>
     <td width="" align="center">${plan.plan_lessonDesc2}&nbsp;</td>
     <td width="" align="center" rowspan="2">${fn:replace(plan.week1_lesson,'，','<br/>')}&nbsp;</td>
     <td width="" align="center" rowspan="2">${fn:replace(plan.week2_lesson,'，','<br/>')}&nbsp;</td>
     <td width="" align="center" rowspan="2">${fn:replace(plan.week3_lesson,'，','<br/>')}&nbsp;</td>
     <td width="" align="center" rowspan="2">${fn:replace(plan.week4_lesson,'，','<br/>')}&nbsp;</td>
     <td width="" align="center" rowspan="2">${fn:replace(plan.week5_lesson,'，','<br/>')}&nbsp;</td>
     <td width="" align="center" rowspan="2">${fn:replace(plan.week6_lesson,'，','<br/>')}&nbsp;</td>
     <td width="" align="center" rowspan="2">${fn:replace(plan.week7_lesson,'，','<br/>')}&nbsp;</td>
   </tr> 
   <tr class="list0" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center">${plan.plan_lessonDesc3}&nbsp;</td>
   </tr> 
   </c:forEach>
   </c:if>
   <c:if test="${plans==null ||  fn:length(plans)==0}">
   <tr>
     <td height="60" colspan="13" align="center"><b>&lt;不存在课表安排信息&gt;</b></td>
   </tr>
  </c:if>
   
</table>
</form> 
</body>
</html>