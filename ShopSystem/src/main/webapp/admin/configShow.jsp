<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考勤参数设置信息</title>
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
   document.info.action="Admin_listConfigs.action";
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
       alert("请至少选择一个要删除的考勤时间！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delConfigs.action?ids="+ids;
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
  document.info.action="Admin_listConfigs.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listConfigs.action";
  document.info.submit();
}
$(document).ready(function(){
	$("#saveConfig").bind("click",function(){
		//验证
		var num = /^\d+(\.\d+)?$/;
		var num2 = /^\d+$/;
		for(var i=1;i<=7;i++){
			if(!num.exec($("#config_value"+i).val())){
				alert("分数都必须为数字");
				return false;
			}
		}
		if(!num.exec($("#config_value8").val())){
			alert("禁考（旷课次数）必须为数字");
			return false;
		}
		
		var aQuery = $("#info").serialize();
		$.post('Admin_saveConfig.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						alert("更新成功");
					}else  if(responseObj.err.msg){
						 alert('失败：'+responseObj.err.msg);
					}else{
						 alert('失败：服务器异常！');
					}	
		 },'json');
	});
});

</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">考勤参数管理&gt;&gt;考勤参数设置</span>
</div>
<form name="info" id="info" action="Admin_listConfigs.action" method="post">
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">考勤参数设置</td>
    <td width="" align="right">
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle" style="height:50px">
     <td width="" align="center">出勤分数</td>
     <td width="" align="center">事假分数</td>
     <td width="" align="center">病假分数</td>
     <td width="" align="center">公假分数</td>
     <td width="" align="center">迟到分数</td>
     <td width="" align="center">早退分数</td>
     <td width="" align="center">旷课分数</td>
     <td width="" align="center">禁考（旷课次数）</td>
     <td width="" align="center">操作</td>
   </tr>
   <c:if test="${configs!=null &&  fn:length(configs)>0}">
   <c:forEach items="${configs}" var="config" varStatus="status">
   <tr class="list0" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center" style="background-color:green;height:30px">
     	<input type="hidden" name="config_id" id="config_id" value='${config.config_id}'/>
     	<input type="text"  name="config_cq" id="config_value1" Style="text-align:center;width:50px"
					 value="${config.config_cq}" />
     </td>
     <td width="" align="center" style="background-color:#ffe600">
     	<input type="text" name="config_sj" id="config_value2" Style="text-align:center;width:50px"
					 value="${config.config_sj}" />
     </td>
     <td width="" align="center" style="background-color:#f47920">
     	<input type="text" name="config_bj" id="config_value3" Style="text-align:center;width:50px"
					 value="${config.config_bj}" />
     </td>
     <td width="" align="center" style="background-color:#d71345">
     	<input type="text" name="config_gj" id="config_value4" Style="text-align:center;width:50px"
					 value="${config.config_gj}" />
     </td>
     <td width="" align="center" style="background-color:#840228">
     	<input type="text" name="config_cd" id="config_value5" Style="text-align:center;width:50px"
					 value="${config.config_cd}" />
     </td>
      <td width="" align="center" style="background-color:#840228">
     	<input type="text" name="config_zt" id="config_value6" Style="text-align:center;width:50px"
					 value="${config.config_zt}" />
     </td>
      <td width="" align="center" style="background-color:#840228">
     	<input type="text" name="config_kk" id="config_value7" Style="text-align:center;width:50px"
					 value="${config.config_kk}" />
     </td>
      <td width="" align="center" style="background-color:#840228">
     	<input type="text" name="config_jk" id="config_value8" Style="text-align:center;width:50px"
					 value="${config.config_jk}" />
     </td>
     <td width="100" align="center">
       <a href="javascript:void(0)" id="saveConfig">更新</a>
     </td>
   </tr> 
   </c:forEach>
   </c:if>
   <c:if test="${configs==null ||  fn:length(configs)==0}">
   <tr>
     <td height="60" colspan="10" align="center"><b>&lt;不存在考勤参数设置信息&gt;</b></td>
   </tr>
   </c:if>
</table>
</form> 
</body>
</html>