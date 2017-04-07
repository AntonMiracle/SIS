$(document).ready(function() {	
	/*======================*/
	/*=    global setup    =*/
	/*======================*/
	$.ajaxSetup({
		async: false
		});
	/*=========================*/
	/*=    global variable    =*/
	/*=========================*/
	var loginContentFlag = true;	
	var isUpContentActive = false;
	var loginButtonShow = 1000;	
	var loginBackgroundShow = 1500;	
	var rootUrl = location.protocol + '//' + location.host + '/';	
	
	/*====================*/
	/*=    login page    =*/
	/*====================*/
	//fadein login bacground picture
	$('#imgbg').fadeIn(loginBackgroundShow);
	
	//set visible to other login page element
	$('.loginInput').fadeIn(500);
	$('.loginLogo').fadeIn(500);	
	setTimeout("$('.loginIn').show();", loginButtonShow);
	setTimeout("$('.menuLogin').show();", loginButtonShow);
	
	//login project info, set/unset visible on mouseenter/mouseleave 
	$('.menuLogin #info').mouseleave(function() {
		hideProjectInformationContent();
	});	
	$('.menuLogin #info').mouseenter(function() {
		showProjectInformationContent();
	});
	
	//check input username & password
	$('.loginIn #in').click(function(){	
		if(compareUsernameAndPassword($('.loginInput input#username').val(),$('.loginInput input#password').val())){
			hideElement($('.loginInput td#loginTip'));
			return true;			
		}else{
			setTextInsideElement($('.loginInput td#loginTip'),'login or password error');
			showElement($('.loginInput td#loginTip'));
			cleanLoginContentFields();
			return false;
		};
	});
	
	//login UP
	$('.menuLogin #up').click(function(){		
		if(isUpContentActive){
			hideUpContent();
			isUpContentActive=false;
		}else{
			showUpContent();
			isUpContentActive = true;			
		};		
	});
	
	$('.loginUpInput #up').click(function(){
		var username = $('.loginUpInput #username');
		var password = $('.loginUpInput #password');
		var confirm = $('.loginUpInput #confirm');
		var name = $('.loginUpInput #name');
		var surname = $('.loginUpInput #surname');
		var phone = $('.loginUpInput #phone');		
		var mail = $('.loginUpInput #mail');		
		var usernameTip = $('.loginUpInput #usernameTip');
		var passwordTip = $('.loginUpInput #passwordTip');
		var confirmTip = $('.loginUpInput #confirmTip');
		var nameTip = $('.loginUpInput #nameTip');		
		var phoneTip = $('.loginUpInput #phoneTip');		
		var count = 0;
		var error = 'incorrect field';
		var isUsernameFree = getAndReturnData('rest/check/usernamefree/' + username.val());
		var isPhoneFree = getAndReturnData('rest/check/phonefree/' + phone.val());
		count = count + checkUniqueField(username.val(), usernameTip, error, isUsernameFree);
		count = count + checkInputData(password.val(), passwordTip, error);		
		count = count + checkInputData(confirm.val(), confirmTip, error);		
		count = count + checkInputData(name.val(), nameTip, error);
		count = count + checkUniqueField(phone.val(), phoneTip, error, isPhoneFree);
		if(password.val() != confirm.val()){
			setTextInsideElement(confirmTip, error);
			showElement(confirmTip);
			return false;
		};
		if(count === 0 && password.val() === confirm.val()){
			var client = {
			username : username.val(),
			password : password.val(),
			name : name.val(),
			surname : surname.val(),
			phone : phone.val(),
			mail : mail.val(),
			};			
			saveData('rest/user/new', 'POST', client);
			hideUpContent();
			return true;
		}else{
			return false;
		};			
	});
	
	/*============================*/
	/*=    show/hide function    =*/
	/*============================*/
	function hideElement(element) {
		element.hide();
	};
	
	function showElement(element) {
		element.show();
	};
	
	function hideLoginUpContentTips(){
		hideElement($('.loginUpInput #usernameTip'));
		hideElement($('.loginUpInput #passwordTip'));
		hideElement($('.loginUpInput #confirmTip'));
		hideElement($('.loginUpInput #nameTip'));
		hideElement($('.loginUpInput #phoneTip'));
	};
	//login UP content
	function showUpContent(){		
		setTextInsideElement($('.menuLogin #up'),'B');
		setButtonTitle(	$('.menuLogin #up'), 'back to login');
		hideElement($('.loginInput'));
		hideElement($('.loginLogo'));
		hideElement($('.loginIn'));
		hideElement($('.menuLogin #info'));
		hideElement($('.loginInput td#loginTip'));
		cleanLoginContentFields();
		cleanUpContentFields();
		showElement($('.loginUpContent'));
	};
	
	function hideUpContent(){
		setTextInsideElement($('.menuLogin #up'),'UP');
		setButtonTitle(	$('.menuLogin #up'), 'registration');
		hideElement($('.loginUpContent'));		
		hideLoginUpContentTips();
		showElement($('.loginInput'));
		showElement($('.loginLogo'));
		showElement($('.loginIn'));
		showElement($('.menuLogin #info'));		
	};	
	
	//Project information content
	function hideProjectInformationContent(){
		showElement($('.loginIn'));
		showElement($('.loginInput'));
		hideElement($('.loginProjectInfo'));		
	};
	
	function showProjectInformationContent(){
		showElement($('.loginProjectInfo'));
		hideElement($('.loginInput'));
		hideElement($('.loginIn'));
		hideElement($('.loginInput td#loginTip'));	
	};
	
	/*========================*/
	/*=    clean function    =*/
	/*========================*/	
	function cleanUpContentFields(){
		setElementValue($('.loginUpInput #username'), '');
		setElementValue($('.loginUpInput #password'), '');
		setElementValue($('.loginUpInput #confirm'), '');
		setElementValue($('.loginUpInput #name'), '');
		setElementValue($('.loginUpInput #surname'), '');
		setElementValue($('.loginUpInput #phone'), '');
		setElementValue($('.loginUpInput #mail'), '');
	};
	function cleanLoginContentFields(){
		setElementValue($('.loginInput input#username'), '');
		setElementValue($('.loginInput input#password'), '');
	};
	
	/*=======================*/
	/*=    ajax function    =*/
	/*=======================*/
	function saveData(relativeUrl, method, data) {	
		$.ajax({
			url : rootUrl + relativeUrl,
			type : method,
			data : JSON.stringify(data),
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			async : false,
			success : function() {
				console.log('SAVE DATA COMPLETE');
			},
			error : function() {
				console.log('SAVE DATA ERROR');
			}
		});
	};
	
	function getAndReturnData(relativeUrl){
		var result;
		$.getJSON( rootUrl + relativeUrl, function( data ) {
			result = data;
			});	
		return result;
	};
	
	/*=========================*/
	/*=    other function    =*/
	/*=========================*/
	function checkInputValuelength(value) {
		if (value && value.trim().length > 3 && value.trim().length < 17) {
			return true;
		} else {
			return false;
		};
	};
	
	function setTextInsideElement(element, text) {
		element.text(text);		
	};
	
	function setElementValue(element, text) {
		element.val(text);		
	};
	
	function setButtonTitle(buttonElement, text) {
		buttonElement.prop('title', text);		
	};
	
	function checkInputData(value,tipElement, error){
		if(checkInputValuelength(value)){
			hideElement(tipElement);
			return 0;
		}else{
			setTextInsideElement(tipElement, error);
			showElement(tipElement);
			return 1;
		};
	};
	
	function checkUniqueField(value,tipElement, error, check){	
		if(checkInputValuelength(value)){		
			if(check){
				hideElement(tipElement);
				return 0;
			}else{
				setTextInsideElement(tipElement, error);
				showElement(tipElement);
				return 1;
			};		
		}else{
			setTextInsideElement(tipElement, error);
			showElement(tipElement);
			return 1;
		};	
	};
	
	function compareUsernameAndPassword(username, password){
		return getAndReturnData('rest/check/authentication/' + username + '/' + password);
	};
	
});
