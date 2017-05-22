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
		timeout : 30000,
	});	
	//login page
	$('#imgbg').fadeIn("slow");
	$('.loginInput').fadeIn("slow");	
	$('.menuLogin').fadeIn("slow");
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
		var result = false;
		var username = $('.loginInput input#username');
		var password = $('.loginInput input#password');
		var login = {
				username : username.val(),
				password : password.val(),
		};
		$.ajax({
			async : false,
			url : rootUrl + 'rest/check/authentication/',
			type : 'POST',
			data : JSON.stringify(login),
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			success : function(data){
				result = data;
				if(result){
					hideElement($('.loginInput td#loginTip'));
				}else{
					setTextInsideElement($('.loginInput td#loginTip'),'login or password error');
					showElement($('.loginInput td#loginTip'));
					cleanLoginContentFields();
				};
			},
		});
		return result;
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
						return true;
					}else{
						if(client.username.length === 0){
							setTextInsideElement(usernameTip, 'incorrect username');
							showElement(usernameTip);
						}else{
							hideElement(usernameTip);
						}
						if(client.password.length === 0){
							setTextInsideElement(passwordTip, 'need 4-16 symbols');
							showElement(passwordTip);
						}else{
							hideElement(passwordTip);
						}
						if(client.confirmPassword.length === 0){
							setTextInsideElement(confirmTip, 'wrong confirm');
							showElement(confirmTip);
						}else{
							hideElement(confirmTip);
						}
						if(client.phone.length === 0){
							setTextInsideElement(phoneTip, 'need 4-16 symbols');
							showElement(phoneTip);
						}else{
							hideElement(phoneTip);
						}
						if(client.name.length === 0){
							setTextInsideElement(nameTip, 'need 4-16 symbols');
							showElement(nameTip);
						}else{
							hideElement(nameTip);
						}
					}
				}				
			});	
	});	
	//home page
	setTimeout(function(){
		$('.menuHome').show()
	}, 1000);	
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
	if(usernameValue === undefined){
		usernameValue = $('.menuHome #homeUsername').text();
		setTextInsideElement('.menuHome #homeUsername', '|||');
	}
	$('.menuHome #homeUsername').click(function() {
		if(isUsernameMenuActive){
			hideUsernameMenu();
		}else{				
			$('.menuHome #homeUsername').blur();
			showUsernameMenu();
		}		
	});		
	/* clients*/	
	$('.menuHome #allClients').click(function(){
		makeFirstAfterClick('.loginIn #in');
		hideAllHomeTable();		
		//updateClientsTable();	
		var clientsUsers;		
		$.ajax({			
			url : rootUrl + 'rest/clients',
			type : 'GET',			
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			success : function(data){
				console.log('create table');
				clientsUsers=data;
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
				showAllClientsTable();
			},
		});	
	});
	var clientsRowId;
	$('#clientsTableList').on('click', '.clientsRow', function() {	
		makeFirstAfterClick('.clientsRow');
		clientsRowId = $(this).attr('id');		
		//updateClientsTable();	
		var clientsUsers;		
		$.ajax({			
			url : rootUrl + '/rest/user/' + clientsRowId,
			type : 'GET',			
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			success : function(data){				
				$('.userProfile').empty();
				$('.userProfile').append('<tr>'
						+'<td id="button"><button class="button-scale"  id="0" title="EDIT">e</button></td>'
						+'<td id="profileHead">' + data.name + '</td>'
						+'<td id="profileHead">' + data.surname + '</td>'
						+'<td id="profileHead">' + data.phone + '</td>'
						+'<td id="profileHead">' + data.mail + '</td>'
						+'<td id="profileHead">' + data.createDate + '</td>'
					+'</tr>'				
				);
				$('.userProfile').append('<tr>'
						+'<td id="button"><button class="button-scale" id="0" title="ADD CAR">+</button></td>'
						+'<td colspan="5" id="profileHead">CARS</td>'						
					+'</tr>'				
				);	
				$.each(data.cars, function(){
					$('.userProfile').append('<tr>'
							+'<td id="button"><button class="button-scale" id="' + this.id + '" title="REMOVE CAR">-</button></td>'
							+'<td>' + this.number + '</td>'
							+'<td>' + this.model + '</td>'
							+'<td>' + this.mark + '</td>'
							+'<td>' + this.description + '</td>'
							+'<td>' + this.createDate + '</td>'
							+'</tr>'
						);
				});						
				$('.userProfile').append('<tr>'
						+'<td id="button"><button class="button-scale" id="0" title="ADD PROPOSAL">+</button></td>'
						+'<td colspan="5" id="profileHead">PROPOSALS</td>'						
						+'</tr>'				
				);	
				$.each(data.proposals, function(){	
						var trClass;
						if(this.status === 'open'){
							trClass ='openStatus';
						};
						if(this.status === 'closed'){
							trClass ='closedStatus';
						};
						if(this.status === 'accepted'){
							trClass ='acceptedStatus';
						};
						$('.userProfile').append('<tr class="'+trClass+'">'
							+'<td id="button"><button class="button-scale" id="' + this.id + '" title="REMOVE CAR">-</button></td>'
							+'<td>' + this.status + '</td>'
							+'<td>' + this.carNumber + '</td>'
							+'<td colspan="2">' + this.description + '</td>'
							+'<td>' + this.createDate + '</td>'
							+'</tr>'
					);
				});						
				showHomeCLientsProfile();
			},
		});	
	});	
	
	$('.menuHome #exit_user_profile').click(function() {
		makeFirstAfterClick('.menuHome #exit_user_profile');
		hideHomeCLientsProfile();
	});
	
	});