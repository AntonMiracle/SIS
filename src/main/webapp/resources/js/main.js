$(document).ready(function() {	
	/*======================*/
	/*=    global setup    =*/
	/*======================*/
	$.ajaxSetup({		
		async: false,
		beforeSend: function(){
			console.log('BEFORE SEND');
			$('.loadingDiv').show();
		},
		complete : function(){
			console.log('COMPLETE SEND');
			$('.loadingDiv').hide();
		},		
	});	
	/*=========================*/
	/*=    global variable    =*/
	/*=========================*/
	var loginContentFlag = true;	
	var isUpContentActive = false;
	var isInfoContentActive = false;
	var isHomeClientsMenuActive = false;
	var isHomeProposalsMenuActive = false;
	var isHomeWorkersMenuActive = false;
	var loginButtonShow = 1000;	
	var loginBackgroundShow = 1500;	
	var rootUrl = location.protocol + '//' + location.host + '/';	
	var clientsUsers;
	
	/*====================*/
	/*=    login page    =*/
	/*====================*/
	//fadein login bacground picture
	$('#imgbg').fadeIn(loginBackgroundShow);
	
	//set visible to other login page element
	$('.loginInput').fadeIn(500);
	setTimeout("$('.menuLogin').show();", loginButtonShow);
	setTimeout("$('.menuHome').show();", loginButtonShow);
	
	//login project info	
	$('.menuLogin #info').mouseenter(function() {
		switchElementTextValueAndSize('.menuLogin #info', 'false', 'info')		
	});
	$('.menuLogin #info').mouseleave(function() {
		switchElementTextValueAndSize('.menuLogin #info', 'false', 'i')
	});
	$('.menuLogin #info').click(function() {
		makeFirstAfterClick('.menuLogin #info');
		if(isInfoContentActive){
			hideProjectInformationContent();
		}else{
			showProjectInformationContent();
		}
	});	
	
	//check input username & password
	$('.loginIn #in').click(function(){	
		makeFirstAfterClick('.loginIn #in');
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
	$('.menuLogin #up').mouseenter(function() {
		switchElementTextValueAndSize('.menuLogin #up', 'false', 'UP')		
	});
	$('.menuLogin #up').mouseleave(function() {
		switchElementTextValueAndSize('.menuLogin #up', 'false', 'U')
	});	
	
	$('.menuLogin #up').click(function(){	
		makeFirstAfterClick('.menuLogin #up');
		if(isUpContentActive){
			hideUpContent();			
		}else{
			showUpContent();		
		};		
	});
	
	$('.loginUpInput #up').click(function(){
		makeFirstAfterClick('.loginUpInput #up');
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

	/*===================*/
	/*=    home page    =*/
	/*===================*/
	/*=== home menu ==*/
	$('.menuHome #clients').mouseenter(function() {
		switchElementTextValueAndSize('.menuHome #clients', '20', 'CLIENTS'); 
	});
	$('.menuHome #clients').mouseleave(function() {
		switchElementTextValueAndSize('.menuHome #clients', '25', 'C');
	});	
	$('.menuHome #proposals').mouseenter(function() {
		switchElementTextValueAndSize('.menuHome #proposals', '19', 'PROPOSALS');
	});
	$('.menuHome #proposals').mouseleave(function() {
		switchElementTextValueAndSize('.menuHome #proposals', '25', 'P');	
	});	
	$('.menuHome #workers').mouseenter(function() {
		switchElementTextValueAndSize('.menuHome #workers', '20', 'WORKERS');
	});
	$('.menuHome #workers').mouseleave(function() {
		switchElementTextValueAndSize('.menuHome #workers', '25', 'W');
	});
	
	/* clients menu */
	$('.menuHome #clients').click(function() {
		makeFirstAfterClick('.menuHome #clients');
		if(isHomeClientsMenuActive){
			hideHomeClientsMenu();			
		}else{
			showHomeClientsMenu();			
		}
	});	
	$('.menuHome #newClient').mouseenter(function() {
		switchElementTextValueAndSize('.menuHome #newClient', '20', 'NEW');
	});
	$('.menuHome #newClient').mouseleave(function() {
		switchElementTextValueAndSize('.menuHome #newClient', '25', 'N');
	});	
	$('.menuHome #allClients').mouseenter(function() {
		switchElementTextValueAndSize('.menuHome #allClients', '20', 'ALL');
	});
	$('.menuHome #allClients').mouseleave(function() {
		switchElementTextValueAndSize('.menuHome #allClients', '25', 'A');
	});
	
	/* proposals menu*/
	$('.menuHome #proposals').click(function() {
		makeFirstAfterClick('.menuHome #proposals');
		if(isHomeProposalsMenuActive){
			hideHomeProposalsMenu();		
		}else{
			showHomeProposalsMenu();		
		}
	});		
	$('.menuHome #newProposal').mouseenter(function() {
		switchElementTextValueAndSize('.menuHome #newProposal', '20', 'NEW');
	});
	$('.menuHome #newProposal').mouseleave(function() {
		switchElementTextValueAndSize('.menuHome #newProposal', '25', 'N');
	});	
	$('.menuHome #allProposals').mouseenter(function() {
		switchElementTextValueAndSize('.menuHome #allProposals', '20', 'ALL');
	});
	$('.menuHome #allProposals').mouseleave(function() {
		switchElementTextValueAndSize('.menuHome #allProposals', '25', 'A');
	});	
	
	/* workers menu*/
	$('.menuHome #workers').click(function() {
		makeFirstAfterClick('.menuHome #workers');
		if(isHomeWorkersMenuActive){
			hideHomeWorkersMenu();	
		}else{
			showHomeWorkersMenu();		
		}
	});	
	$('.menuHome #newWorker').mouseenter(function() {
		switchElementTextValueAndSize('.menuHome #newWorker', '20', 'NEW');		
	});
	$('.menuHome #newWorker').mouseleave(function() {
		switchElementTextValueAndSize('.menuHome #newWorker', '25', 'N');
	});	
	$('.menuHome #allWorkers').mouseenter(function() {
		switchElementTextValueAndSize('.menuHome #allWorkers', '20', 'ALL');		
	});
	$('.menuHome #allWorkers').mouseleave(function() {
		switchElementTextValueAndSize('.menuHome #allWorkers', '25', 'A');
	});	
	$('.menuHome #tasks').mouseenter(function() {
		switchElementTextValueAndSize('.menuHome #tasks', '20', 'TASKS');		
	});
	$('.menuHome #tasks').mouseleave(function() {
		switchElementTextValueAndSize('.menuHome #tasks', '25', 'T');
	});	
	/*home menu username area*/
	$('.menuHome #out').mouseenter(function() {
		switchElementTextValueAndSize('.menuHome #out', '20', 'OUT');
	});
	$('.menuHome #out').mouseleave(function() {
		switchElementTextValueAndSize('.menuHome #out', '25', 'O');
	});	
	/*home menu username*/
	var isUsernameMenuActive = false;
	var usernameValue;
	$('.menuHome #homeUsername').click(function() {
		if(usernameValue === undefined){
			usernameValue = $('.menuHome #homeUsername').text();
		}
		if(isUsernameMenuActive){
			hideUsernameMenu();
		}else{
			makeFirstAfterClick('.menuHome #homeUsername');
			showUsernameMenu();
		}		
	});	
	
	/* clients*/
	var clientsRowId;
	$('.menuHome #allClients').click(function(){
		hideAllHomeTable();
		showAllClientsTable();
		updateClientsTable();
	});
	$('#clientsTableList').on('click', '.clientsRow', function() {		
		clientsRowId = $(this).attr('id');		
		hideAllHomeTable();	
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
		setTextInsideElement($('.menuLogin #up'),'BACK');
		hideElement($('.loginInput'));
		hideElement($('.menuLogin #info'));
		hideElement($('.loginInput td#loginTip'));
		cleanLoginContentFields();
		cleanUpContentFields();
		showElement($('.loginUpContent'));
		isUpContentActive = true;
	};
	
	function hideUpContent(){
		if($('.menuLogin #up').text() === 'BACK'){
			setTextInsideElement($('.menuLogin #up'),'UP');
		};
		if($('.menuLogin #up').text() === 'B'){
			setTextInsideElement($('.menuLogin #up'),'U');
		};				
		setButtonTitle(	$('.menuLogin #up'), 'registration');
		hideElement($('.loginUpContent'));		
		hideLoginUpContentTips();
		showElement($('.loginInput'));
		showElement($('.menuLogin #info'));	
		isUpContentActive = false;
	};	
	
	//Project information content
	function hideProjectInformationContent(){
		setTextInsideElement($('.menuLogin #info'),'i');		
		showElement($('.menuLogin #up'));	
		showElement($('.loginInput'));
		hideElement($('.loginProjectInfo'));
		isInfoContentActive = false;
	};
	
	function showProjectInformationContent(){
		setTextInsideElement($('.menuLogin #info'),'BACK');
		showElement($('.loginProjectInfo'));
		hideElement($('.menuLogin #up'));
		hideElement($('.loginInput'));
		hideElement($('.loginInput td#loginTip'));	
		isInfoContentActive = true;
	};
	//home clients menu
	function showHomeClientsMenu(){
		setTextInsideElement($('.menuHome .homeLogo'),'CLIENTS');
		setTextInsideElement($('.menuHome #clients'),'BACK');
		setButtonTitle($('.menuHome #clients'), 'BACK');
		showElement($('.menuHome #newClient'));
		showElement($('.menuHome #allClients'));		
		hideElement($('.menuHome #proposals'));
		hideElement($('.menuHome #workers'));		
		isHomeClientsMenuActive = true;
	};
	function hideHomeClientsMenu(){
		setTextInsideElement($('.menuHome .homeLogo'),'SIS');
		setButtonTitle($('.menuHome #clients'), 'CLIENTS');
		setTextInsideElement($('.menuHome #clients'),'CLIENTS');
		hideElement($('.menuHome #newClient'));
		hideElement($('.menuHome #allClients'));	
		showElement($('.menuHome #proposals'));
		showElement($('.menuHome #workers'));		
		isHomeClientsMenuActive = false;		
	};
	//home proposals menu
	function showHomeProposalsMenu(){
		setTextInsideElement($('.menuHome .homeLogo'),'PROPOSALS');
		setTextInsideElement($('.menuHome #proposals'),'BACK');
		setButtonTitle($('.menuHome #proposals'), 'BACK');
		showElement($('.menuHome #newProposal'));
		showElement($('.menuHome #allProposals'));		
		hideElement($('.menuHome #clients'));
		hideElement($('.menuHome #workers'));		
		isHomeProposalsMenuActive = true;
	};
	function hideHomeProposalsMenu(){
		setTextInsideElement($('.menuHome .homeLogo'),'SIS');
		setButtonTitle($('.menuHome #proposals'), 'PROPOSALS');
		setTextInsideElement($('.menuHome #proposals'),'PROPOSALS');
		hideElement($('.menuHome #newProposal'));
		hideElement($('.menuHome #allProposals'));	
		showElement($('.menuHome #clients'));
		showElement($('.menuHome #workers'));		
		isHomeProposalsMenuActive = false;		
	};
	//home workers menu
	function showHomeWorkersMenu(){
		setTextInsideElement($('.menuHome .homeLogo'),'WORKERS');
		setTextInsideElement($('.menuHome #workers'),'BACK');
		setButtonTitle($('.menuHome #workers'), 'BACK');
		showElement($('.menuHome #newWorker'));
		showElement($('.menuHome #allWorkers'));		
		showElement($('.menuHome #tasks'));		
		hideElement($('.menuHome #clients'));
		hideElement($('.menuHome #proposals'));		
		isHomeWorkersMenuActive = true;
	};
	function hideHomeWorkersMenu(){
		setTextInsideElement($('.menuHome .homeLogo'),'SIS');
		setButtonTitle($('.menuHome #workers'), 'WORKERS');
		setTextInsideElement($('.menuHome #workers'),'WORKERS');
		hideElement($('.menuHome #newWorker'));
		hideElement($('.menuHome #allWorkers'));	
		hideElement($('.menuHome #tasks'));	
		showElement($('.menuHome #clients'));
		showElement($('.menuHome #proposals'));		
		isHomeWorkersMenuActive = false;
	};
	//username menu
	function hideUsernameMenu(){
		setTextInsideElement($('.menuHome .homeLogo'),'SIS');
		setButtonTitle($('.menuHome #homeUsername'), 'USERNAME');
		setTextInsideElement($('.menuHome #homeUsername'),usernameValue);
		hideElement($('.menuHome #homeUsernameOut'));		
		isUsernameMenuActive = false;
	};
	function showUsernameMenu(){
		setTextInsideElement($('.menuHome .homeLogo'),usernameValue);
		setButtonTitle($('.menuHome #homeUsername'), 'CLOSE USERNAME MENU');
		setTextInsideElement($('.menuHome #homeUsername'),'CLOSE');
		showElement($('.menuHome #homeUsernameOut'));
		isUsernameMenuActive = true;
	};
	// hide all home tables
	function hideAllHomeTable(){
		//clients area		
		hideElement($('#clientsTableList'));
	};
	//home all clients table	
	function showAllClientsTable(){
		showElement($('#clientsTableList'));
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
			dataType : 'json'
		});
	};
	
	function getAndReturnData(relativeUrl){
		var result;			
		$.getJSON( rootUrl + relativeUrl, function( data ) {	      
			result = data;			
			});	
		return result;
	};
	
	/*============================*/
	/*=    home menu function    =*/
	/*============================*/
	function switchElementTextValueAndSize(element, fontSize, text){	
		element = $(element);
		if((element.text() === 'B') || (element.text() === 'BACK')){
			if((element.text() === 'B')){
				setTextInsideElement($(element),'BACK');
			}else{
				setTextInsideElement($(element),'B');
			}
		}else{
			if(fontSize !== 'false'){
				element.css('font-size',fontSize + 'px');
			};
			setTextInsideElement($(element),text);
		};
	};	
	
	/*========================*/
	/*=    other function    =*/
	/*========================*/
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
	
	function getClients(){
		return getAndReturnData('rest/clients');
	};
	function makeFirstAfterClick(element){	
		 $(element).blur();	
		 hideAllHomeTable();
		 if(isUsernameMenuActive){
			 hideUsernameMenu(); 
		 }			
	};
	
	function updateClientsTable(){
		clientsUsers = getClients();
//		$('#clientsTableList').empty();
		$('#clientsTableList').append('<tr id="allClientsTableTitle">'
				+'<td>NAME</td>'
				+'<td>SURNAME</td>'
				+'<td>PHONE</td>'
				+'<td>MAIL</td>'
				+'<td>CREATE</td>'
			+'</tr>'				
		);	
		$.each(clientsUsers, function(){
			$('#clientsTableList').append('<tr class=clientsRow id=' + this.id + '>'
					+'<td>' + this.name + '</td>'
					+'<td>' + this.surname + '</td>'
					+'<td>' + this.phone + '</td>'
					+'<td>' + this.mail + '</td>'
					+'<td>' + this.createDate + '</td>'
					+'</tr>'
				);
		});
		};
	});