$(document).ready(function() {	
	//general
	$.ajaxSetup({
		error : function(){
			serverErrorScreen();
		},
		beforeSend: function(){
			loadingScreen();				
		},
		complete : function(){
			loadingScreen();	
		},
//		timeout : 3000,
	});	
	//login page
	$('#imgbg').fadeIn(1500);
	$('.loginInput').fadeIn(500);
	setTimeout("$('.menuLogin').show();", 1000);
	$('.menuLogin #info').click(function() {
		makeFirstAfterClick('.menuLogin #info');
		if(isInfoContentActive){
			hideProjectInformationContent();
		}else{
			showProjectInformationContent();
		}		
	});	
	$('.menuLogin #up').click(function(){	
		makeFirstAfterClick('.menuLogin #up');
		if(isUpContentActive){
			hideUpContent();			
		}else{
			showUpContent();		
		};		
	});
	$('.loginIn #in').click(function(){	
		makeFirstAfterClick('.loginIn #in');
		var result;
		var username = $('.loginInput input#username');
		var password = $('.loginInput input#password');
		var login = {
				username : username.val(),
				password : confirm.val(),
		};
		$.ajax({
			url : rootUrl + 'rest/check/authentication/',
			type : 'POST',
			data : JSON.stringify(login),
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			success : function(data){
				console.log('DATA');
				console.log(data);
				result = data;
				if(result){
					hideElement($('.loginInput td#loginTip'));
					return true;			
				}else{
					setTextInsideElement($('.loginInput td#loginTip'),'login or password error');
					showElement($('.loginInput td#loginTip'));
					cleanLoginContentFields();
					return false;
				};
			},
			timeout : 100,
		});	
		//setTimeout(function(){
//			if(result){
//				hideElement($('.loginInput td#loginTip'));
//				return true;			
//			}else{
//				setTextInsideElement($('.loginInput td#loginTip'),'login or password error');
//				showElement($('.loginInput td#loginTip'));
//				cleanLoginContentFields();
//				return false;
//			};
		//}, 110)
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
		var client = {
				username : username.val(),
				confirmPassword : confirm.val(),
				password : password.val(),
				name : name.val(),
				surname : surname.val(),
				phone : phone.val(),
				mail : mail.val(),
			};
			$.ajax({
				url : rootUrl + 'rest/user/new/client',
				type : 'POST',
				data : JSON.stringify(client),
				contentType : 'application/json; charset=utf-8',
				dataType : 'json',
				success : function(data){
					client = data;
					if(client.isSave){
						hideUpContent();
					}else{
						if(client.username.length === 0){
							setTextInsideElement(usernameTip, 'choose another username');
							showElement(usernameTip);
						}else{
							hideElement(usernameTip);
						}
						if(client.password.length === 0){
							setTextInsideElement(passwordTip, 'pto short or long');
							showElement(passwordTip);
						}else{
							hideElement(passwordTip);
						}
						if(client.confirmPassword.length === 0){
							setTextInsideElement(confirmTip, 'not equals with password');
							showElement(confirmTip);
						}else{
							hideElement(confirmTip);
						}
						if(client.phone.length === 0){
							setTextInsideElement(phoneTip, 'to short or long');
							showElement(phoneTip);
						}else{
							hideElement(phoneTip);
						}
						if(client.name.length === 0){
							setTextInsideElement(nameTip, 'to short or long');
							showElement(nameTip);
						}else{
							hideElement(nameTip);
						}
					}
				}				
			});	
	});	
	//home page
	setTimeout("$('.menuHome').show();", 1000);	
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
//	function getAndReturnData(relativeUrl){
//		var result;
//		$.getJSON( rootUrl + relativeUrl, function( data ) {	      
//			result = data;			
//			});		
//		return result;
//	};	
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
		
	function updateClientsTable(){
		console.log('create table');
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
		console.log('finish create table');
		};		
	});