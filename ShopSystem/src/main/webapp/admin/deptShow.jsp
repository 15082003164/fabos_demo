<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>班级信息</title>
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
   document.info.action="Admin_listDepts.action";
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
       alert("请至少选择一个要删除的班级！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delDepts.action?ids="+ids;
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
  document.info.action="Admin_listDepts.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listDepts.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">班级管理&gt;&gt;班级查询</span>
</div>
<form name="info" id="info" action="Admin_listDepts.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${paperUtil.pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">班级列表</td>
    <td width="" align="right">
            班级名称：
      <input type="text" id="paramsDept.dept_name" name="dept_name" 
      		value="${paramsDept.dept_name}" class="inputstyle" Style=""/>&nbsp;
            辅导员：
      <input type="text" id="paramsDept.real_name" name="real_name" 
      		value="${paramsDept.real_name}" class="inputstyle" Style=""/>&nbsp;&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
      <input type="button" value="删除" onclick="del();" class="btnstyle"/>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
     <td width="40" align="center">序号</td>
     <td width="" align="center">班级名称</td>
     <td width="300" align="center">班级描述</td>
     <td width="" align="center">辅导员</td>
     <td width="" align="center">操作</td>
   </tr>
   <c:if test="${depts!=null &&  fn:length(depts)>0}">
   <c:forEach items="${depts}" var="dept" varStatus="status">
   <tr class="list0" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center"><input type="checkbox" name="chkid" value="${dept.dept_id}" style="vertical-align:text-bottom;"/></td>
     <td width="" align="center">${status.index+paramsDept.start+1}</td>
     <td width="" align="center">${dept.dept_name}</td>
     <td width="" align="center">${dept.dept_note}&nbsp;</td>
     <td width="" align="center">${dept.real_name}&nbsp;</td>
     <td width="" align="center">
       <img src="images/edit.png"/>&nbsp;<a href="Admin_editDept.action?dept_id=${dept.dept_id}">编辑</a>
     </td>
   </tr> 
   </c:forEach>
   </c:if>
   <c:if test="${depts==null ||  fn:length(depts)==0}">
   <tr>
     <td height="60" colspan="13" align="center"><b>&lt;不存在班级信息&gt;</b></td>
   </tr>
  </c:if>
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>