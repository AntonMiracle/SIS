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
				<button class="button-scale" id="homeUsername" title="USER MENU">${username}</button>				
				<form:form action="${logoutUrl}" method="POST">	
				<button class="button-scale" id="homeUsernameOut" title="LOG OUT">OUT</button>
					<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>			
				</form:form>							
				<button class="button-scale" id="clients" title="CLIENTS">CLIENTS</button>	
				<button class="button-scale" id="newClient" title="NEW CLIENT">NEW</button>	
				<button class="button-scale" id="allClients" title="ALL CLIENT">ALL</button>		
				<button class="button-scale" id="proposals" title="PROPOSALS">PROPOSALS</button>
				<button class="button-scale" id="newProposal" title="NEW PROPOSAL">NEW</button>	
				<button class="button-scale" id="allProposals" title="ALL PROPOSALS">ALL</button>		
				<button class="button-scale" id="workers" title="WORKERS">WORKERS</button>		
				<button class="button-scale" id="newWorker" title="NEW WORKER">NEW</button>	
				<button class="button-scale" id="allWorkers" title="ALL WORKERS">ALL</button>	
				<button class="button-scale" id="tasks" title="TASKS">TASKS</button>
				<button class="button-scale" id="exit_user_profile" title="BACK TO SIS">SIS</button>
				<button class="button-scale" id="edit_user_profile" title="EDIT PROFILE">EDIT</button>
				<button class="button-scale" id="cars_user_profile" title="USER CARS">CARS</button>
				<button class="button-scale" id="proposals_user_profile" title="USER PROPOSALS">PROPOSALS</button>
				
				
			</sec:authorize>
		</div>
		
		<!-- ==================== -->
		<!-- home clients content -->
		<!-- ==================== -->
		<table id="clientsTableList">				
					
		</table>		
		
	</jsp:attribute>
</t:template>