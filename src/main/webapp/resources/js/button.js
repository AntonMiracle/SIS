$(document).ready(function() {	
	/*====================*/
	/*=    login menu    =*/
	/*====================*/
	$('.menuLogin #up').mouseenter(function() {
		switchElementTextValueAndSize('.menuLogin #up', 'false', 'UP')		
	});
	$('.menuLogin #up').mouseleave(function() {
		switchElementTextValueAndSize('.menuLogin #up', 'false', 'U')
	});	
	$('.menuLogin #info').mouseenter(function() {
		switchElementTextValueAndSize('.menuLogin #info', 'false', 'info')		
	});
	$('.menuLogin #info').mouseleave(function() {
		switchElementTextValueAndSize('.menuLogin #info', 'false', 'i')
	});
	/*===================*/
	/*=    home menu    =*/
	/*===================*/
	//clients
	$('.menuHome #clients').mouseenter(function() {
		switchElementTextValueAndSize('.menuHome #clients', '20', 'CLIENTS');
	});
	$('.menuHome #clients').mouseleave(function() {
		switchElementTextValueAndSize('.menuHome #clients', '25', 'C');
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

	//proposals
	$('.menuHome #proposals').mouseenter(function() {
		switchElementTextValueAndSize('.menuHome #proposals', '20', 'PROPOSALS');
	});
	$('.menuHome #proposals').mouseleave(function() {
		switchElementTextValueAndSize('.menuHome #proposals', '25', 'P');
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

	//workers
	$('.menuHome #workers').mouseenter(function() {
		switchElementTextValueAndSize('.menuHome #workers', '20', 'WORKERS');
	});
	$('.menuHome #workers').mouseleave(function() {
		switchElementTextValueAndSize('.menuHome #workers', '25', 'W');
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

	//user menu
	$('.menuHome #homeUsernameOut').mouseenter(function() {
		switchElementTextValueAndSize('.menuHome #homeUsernameOut', '20', 'OUT');
	});
	$('.menuHome #homeUsernameOut').mouseleave(function() {
		switchElementTextValueAndSize('.menuHome #homeUsernameOut', '25', 'O');
	});
	$('.menuHome #homeUsername').mouseenter(function() {
		switchElementTextValueAndSize('.menuHome #homeUsername', '20', usernameValue);
	});
	$('.menuHome #homeUsername').mouseleave(function() {
		switchElementTextValueAndSize('.menuHome #homeUsername', '25', '|||');
	});
	
	//home user profile
	$('.menuHome #exit_user_profile').mouseenter(function() {
		switchElementTextValueAndSize('.menuHome #exit_user_profile', '20', 'SIS');
	});
	$('.menuHome #exit_user_profile').mouseleave(function() {
		switchElementTextValueAndSize('.menuHome #exit_user_profile', '25', 'B');
	});
	$('.menuHome #edit_user_profile').mouseenter(function() {
		switchElementTextValueAndSize('.menuHome #edit_user_profile', '20', 'EDIT');
	});
	$('.menuHome #edit_user_profile').mouseleave(function() {
		switchElementTextValueAndSize('.menuHome #edit_user_profile', '25', 'E');
	});
	$('.menuHome #cars_user_profile').mouseenter(function() {
		switchElementTextValueAndSize('.menuHome #cars_user_profile', '20', 'CARS');
	});
	$('.menuHome #cars_user_profile').mouseleave(function() {
		switchElementTextValueAndSize('.menuHome #cars_user_profile', '25', 'C');
	});
	$('.menuHome #proposals_user_profile').mouseenter(function() {
		switchElementTextValueAndSize('.menuHome #proposals_user_profile', '20', 'PROPOSALS');
	});
	$('.menuHome #proposals_user_profile').mouseleave(function() {
		switchElementTextValueAndSize('.menuHome #proposals_user_profile', '25', 'P');
	});
});