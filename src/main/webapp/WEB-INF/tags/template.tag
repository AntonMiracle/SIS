<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="title"%>
<%@attribute name="head"%>
<%@attribute name="content" fragment="true"%>
<c:url value="/resources/js/jquery-3.1.1.js" var="jqUrl" />
<c:url value="/resources/js/main.js" var="mainJsUrl" />
<c:url value="/resources/css/style.css" var="cssUrl" />
<c:url value="/resources/css/tabicon.png" var="tabIconUrl" />
<c:url value="https://fonts.googleapis.com/css?family=Monoton|Varela+Round" var="fontUrl" />


<!DOCTYPE html>
<html>
<head>
	<title>${title}</title>
	<link rel="icon" type="image/png" href="${tabIconUrl}" />
	<link type="text/css; UTF-8" rel="stylesheet" href="${cssUrl}" />
	<link type="text/css; UTF-8" rel="stylesheet" href="${fontUrl}" />
	<script type="text/javascript" src="${jqUrl}"></script>
	<script type="text/javascript" src="${mainJsUrl}"></script>
	<jsp:invoke fragment="head" />
</head>
<body>
	<!-- ============== -->
	<!-- background img -->
	<!-- ============== -->	
	<img src="resources/css/bodybg.jpg" id="imgbg">
	
	<!-- =============== -->
	<!-- general content -->
	<!-- =============== -->	
	<jsp:invoke fragment="content" />
</body>
</html>