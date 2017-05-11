<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<c:url value="/j_spring_security_check" var="loginUrl" />  
<c:url var="logoutUrl" value="/logout"/>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.username" var="username" />
</sec:authorize>

<t:template title="HOME">
	<jsp:attribute name="head">
	
	</jsp:attribute>
	<jsp:attribute name="content">		
		<!-- ========= -->
		<!-- home menu -->
		<!-- ========= -->
		<div class="menuHome">	
			<sec:authorize access="hasAnyRole('ROLE_MANAGER', 'ROLE_BOSS', 'ROLE_CLIENT')">	
				<div class="homeLogo">SIS</div>
				<button class="button-scale" id="homeUsername" title="USERNAME">Username</button>
			</sec:authorize>
			<sec:authorize access="hasAnyRole('ROLE_MANAGER', 'ROLE_BOSS', 'ROLE_CLIENT')">						
				<form:form action="${logoutUrl}" method="POST">	
				<button class="button-scale" id="homeUsernameOut" title="LOG OUT">OUT</button>
					<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>			
				</form:form>				
			</sec:authorize>
			<sec:authorize access="hasAnyRole('ROLE_MANAGER', 'ROLE_BOSS', 'ROLE_CLIENT')">								
				<button class="button-scale" id="clients" title="CLIENTS">C</button>	
				<button class="button-scale" id="newClient" title="NEW CLIENT">N</button>	
				<button class="button-scale" id="allClients" title="ALL CLIENT">A</button>		
				<button class="button-scale" id="proposals" title="PROPOSALS">P</button>
				<button class="button-scale" id="newProposal" title="NEW PROPOSAL">N</button>	
				<button class="button-scale" id="allProposals" title="ALL PROPOSALS">A</button>		
				<button class="button-scale" id="workers" title="WORKERS">W</button>		
				<button class="button-scale" id="newWorker" title="NEW WORKER">N</button>	
				<button class="button-scale" id="allWorkers" title="ALL WORKERS">A</button>	
				<button class="button-scale" id="tasks" title="TASKS">T</button>
			</sec:authorize>
		</div>
		
		<!-- ==================== -->
		<!-- home clients content -->
		<!-- ==================== -->
		<table id="clientsTableList">				
					
		</table>		
		
	</jsp:attribute>
</t:template>