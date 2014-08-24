<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	Person manager 111 222
	
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>

<sj:head jqueryui="true" jquerytheme="redmond" />

<s:url var="remoteurl" action="listAllJson" namespace="/personx" />
 <sjg:grid
     id="gridtable"
     caption="Customer Examples"
     dataType="json"
     href="%{remoteurl}"
     pager="true"
     gridModel="persons"
     rowList="10,15,20"
     rowNum="10"
     navigator="true" navigatorRefresh="true" navigatorAdd="false" 
     navigatorEdit="false" navigatorDelete="false" navigatorSearch="false"
     viewrecords="true" 
     rownumbers="true" >
     <sjg:gridColumn name="id" index="id" title="ID" formatter="integer" sortable="false"/>
     <sjg:gridColumn name="lastName" index="lastName" title="Last Name" sortable="true"/>
     <sjg:gridColumn name="firstName" index="firstName" title="First Name" sortable="false"/>
 </sjg:grid>
kkkk
</body>
</html>