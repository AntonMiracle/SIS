$(document).ready(function() {	
	//general
	
	//login page
	$('#imgbg').fadeIn(1500);
	$('.loginInput').fadeIn(500);
	setTimeout("$('.menuLogin').show();", 1000);
	//home page
	setTimeout("$('.menuHome').show();", 1000);	
	
	
	
	$.ajaxSetup({		
		async: false,
		beforeSend: function(){
			console.log('BEFORE SEND');
			loadingScreen();				
		},
		complete : function(){
			console.log('COMPLETE SEND');
			loadingScreen();	
		},		
	});	
	
	var loadingScreenActive = false;
	function loadingScreen() {
		if (loadingScreenActive) {
			$('#loadDiv').hide();
			loadingScreenActive = false;
		} else {
			$('#loadDiv').show();
			loadingScreenActive = true;
		}
	}
	
	
	
	$('.menuLogin #info').click(function() {
		makeFirstAfterClick('.menuLogin #info');
		loadingScreen();
		if(isInfoContentActive){
			hideProjectInformationContent();
		}else{
			showProjectInformationContent();
		}
		setTimeout(function () {
			loadingScreen();
		},3000);
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
		
	/* clients menu */
	$('.menuHome #clients').click(function() {
		makeFirstAfterClick('.menuHome #clients');
		if(isHomeClientsMenuActive){
			hideHomeClientsMenu();			
		}else{
			showHomeClientsMenu();			
		}
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
	/* workers menu*/
	$('.menuHome #workers').click(function() {
		makeFirstAfterClick('.menuHome #workers');
		if(isHomeWorkersMenuActive){
			hideHomeWorkersMenu();	
		}else{
			showHomeWorkersMenu();		
		}
	});	
	/*home menu username*/
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
		console.log('create table');
//		loadingScreen();
		clientsUsers = getClients();
		$('#clientsTableList').empty();
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
//		loadingScreen();
		console.log('finish create table');
		};
		
//		var loadingScreenActive = false;
//		function loadingScreen(){
//			console.log('loading screen function - 1');
//			if(loadingScreenActive){
//				console.log('loading screen function - 2');
//				$('#loadDiv').hide();
//				console.log('loading screen function - 3');
//			}else{
//				console.log('loading screen function - 4');
//				$('#loadDiv').show();
//				console.log('loading screen function - 5');
//				loadingScreenActive = true;
//				console.log('loading screen function - 6');
//			}
//			console.log('loading screen function - 7');
//		}
	});