<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<c:url value="/j_spring_security_check" var="loginUrl" />
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.username" var="username" />
</sec:authorize>

<t:template title="HOME">
	<jsp:attribute name="head">
		
	</jsp:attribute>
	<jsp:attribute name="content">
		
	</jsp:attribute>
</t:template>