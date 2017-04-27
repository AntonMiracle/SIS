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

<t:template title="SIS">
	<jsp:attribute name="content">
		
		<!-- ========== -->
		<!-- login menu -->
		<!-- ========== -->
		<div class="menuLogin">
			<button class="button-scale" id="up" title="registration">UP</button>
			<button class="button-scale" id="info" title="project information">i</button>
		</div>
				
		<!-- ============= -->
		<!-- login content -->
		<!-- ============= -->
		<form:form action="${loginUrl}" method="post">		
			<div class="loginContent">
				<table class=loginInput>
					<tr>
						<td colspan="2" class="loginLogo">SIS</td>
					</tr>			
					<tr>
						<td> </td><td id="loginTip"/>
					</tr>
					<tr>
						<td>LOGIN</td><td><input id="username" type="text" name="j_username"></td>
					</tr>
					<tr>
						<td>PASSWORD</td><td><input id="password" type="password" name="j_password"></td>
					</tr>
					<tr>
						<td colspan="2" class="loginIn"><button class="button-scale" id="in">IN</button>						
					</tr>
				</table>				
			</div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />		
		</form:form>
		<!-- ================ -->
		<!-- login up content -->
		<!-- ================ -->
		<div class="loginUpContent">		
			<table class="loginUpInput">
				<tr>
					<td colspan="4" id="title">SIS ACCOUNT</td>
				</tr>
				<tr>
					<td rowspan="7" id="button"><button class="button-scale" id="up" title="finish registration">UP</button></td>
					<td>USERNAME</td>					
					<td><input id="username" type="text" placeholder="required (4-15)"></td>
					<td id="usernameTip"/>
				</tr>
				<tr>
					<td>PASSWORD</td>
					<td><input id="password" type="password" placeholder="required (4-15)"></td>
					<td id="passwordTip" />
				</tr>
				<tr>
					<td>CONFIRM</td>
					<td><input id="confirm" type="password" placeholder="required (4-15)"></td>
					<td id="confirmTip" />
				</tr>
				<tr>
					<td>NAME</td>
					<td><input id="name" type="text" placeholder="required (4-15)"></td>
					<td id="nameTip" />					
				</tr>
				<tr>
					<td>SURNAME</td>
					<td><input id="surname" type="text"></td>
					<td id="surnameTip" />					
				</tr>
				<tr>
					<td>PHONE</td>
					<td><input id="phone" type="text" placeholder="required (4-15)"></td>					
					<td id="phoneTip" />															
				</tr>
				<tr>
					<td>MAIL</td>
					<td><input id="mail" type="text"></td>					
					<td id="mailTip" />															
				</tr>
			</table>
		</div>
		
		<!-- ============ -->
		<!-- project info -->
		<!-- ============ -->	
		<div class="loginProjectInfo">Service Interaction System information nee post here</div>
			
		<!-- ============ -->
		<!-- login footer -->
		<!-- ============ -->	
		<div class="loginFooter">Created by Bondarenko A.M<br>b.anton.m.1986@gmail.com</div>
	</jsp:attribute>
</t:template>