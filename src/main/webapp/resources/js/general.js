var rootUrl = location.protocol + '//' + location.host + '/';
function getElement(element){
	element = $(document).find(element);
	return element;
}
var isLoadingScreenActive = false;
function loadingScreen() {
	if (isLoadingScreenActive) {
		getElement('#loadDiv').fadeOut('fast');
		isLoadingScreenActive = false;
	} else {
		getElement('#loadDiv').fadeIn('fast');
		isLoadingScreenActive = true;
	}
}
function serverErrorScreen(){
	showElement(getElement('#serverError'));
	setTimeout(function () {
		hideElement(getElement('#serverError'));
	},5000);
}
function setTextInsideElement(element, text) {
	element = $(element);
	element.text(text);
};
function setElementValue(element, text) {
	element = $(element);
	element.val(text);
};
function setButtonTitle(buttonElement, text) {
	buttonElement = $(buttonElement);
	buttonElement.prop('title', text);
};
// show or hide function
function hideElement(element) {
	element = $(element);
	element.hide();
};

function showElement(element) {
	element = $(element);
	element.show();
};
// login UP content
var isUpContentActive = false;
function showUpContent() {
	setTextInsideElement('.menuLogin #up', 'BACK');
	setButtonTitle('.menuLogin #up', 'BACK');
	hideElement('.loginInput');
	hideElement('.menuLogin #info');
	hideElement('.loginInput td#loginTip');
	cleanLoginContentFields();
	cleanUpContentFields();
	showElement('.loginUpContent');
	isUpContentActive = true;
};
function hideUpContent() {
	setTextInsideElement($('.menuLogin #up'), 'UP');
	setButtonTitle('.menuLogin #up', 'registration');
	hideElement('.loginUpContent');
	hideLoginUpContentTips();
	showElement('.loginInput');
	showElement('.menuLogin #info');
	isUpContentActive = false;
};
function hideLoginUpContentTips() {
	hideElement('.loginUpInput #usernameTip');
	hideElement('.loginUpInput #passwordTip');
	hideElement('.loginUpInput #confirmTip');
	hideElement('.loginUpInput #nameTip');
	hideElement('.loginUpInput #phoneTip');
};
// Project information content
var isInfoContentActive = false;
function hideProjectInformationContent() {
	setTextInsideElement($('.menuLogin #info'), 'ABOUT');
	showElement('.menuLogin #up');
	showElement('.loginInput');
	hideElement('.loginProjectInfo');
	isInfoContentActive = false;
};
function showProjectInformationContent() {
	setTextInsideElement($('.menuLogin #info'), 'BACK');
	showElement('.loginProjectInfo');
	hideElement('.menuLogin #up');
	hideElement('.loginInput');
	hideElement('.loginInput td#loginTip');
	isInfoContentActive = true;
};
// home clients menu
var isHomeClientsMenuActive = false;
function showHomeClientsMenu() {
	setTextInsideElement('.menuHome .homeLogo', 'CLIENTS');
	setTextInsideElement('.menuHome #clients', 'BACK');
	setButtonTitle('.menuHome #clients', 'BACK');
	showElement('.menuHome #newClient');
	showElement('.menuHome #allClients');
	hideElement('.menuHome #proposals');
	hideElement('.menuHome #workers');
	isHomeClientsMenuActive = true;
};
function hideHomeClientsMenu() {
	setTextInsideElement('.menuHome .homeLogo', 'SIS');
	setButtonTitle('.menuHome #clients', 'CLIENTS');
	setTextInsideElement('.menuHome #clients', 'CLIENTS');
	hideElement('.menuHome #newClient');
	hideElement('.menuHome #allClients');
	showElement('.menuHome #proposals');
	showElement('.menuHome #workers');
	isHomeClientsMenuActive = false;
};
// home proposals menu
var isHomeProposalsMenuActive = false;
function showHomeProposalsMenu() {
	setTextInsideElement('.menuHome .homeLogo', 'PROPOSALS');
	setTextInsideElement('.menuHome #proposals', 'BACK');
	setButtonTitle('.menuHome #proposals', 'BACK');
	showElement('.menuHome #newProposal');
	showElement('.menuHome #allProposals');
	hideElement('.menuHome #clients');
	hideElement('.menuHome #workers');
	isHomeProposalsMenuActive = true;
};
function hideHomeProposalsMenu() {
	setTextInsideElement('.menuHome .homeLogo', 'SIS');
	setButtonTitle('.menuHome #proposals', 'PROPOSALS');
	setTextInsideElement('.menuHome #proposals', 'PROPOSALS');
	hideElement('.menuHome #newProposal');
	hideElement('.menuHome #allProposals');
	showElement('.menuHome #clients');
	showElement('.menuHome #workers');
	isHomeProposalsMenuActive = false;
};
// home workers menu
var isHomeWorkersMenuActive = false;
function showHomeWorkersMenu() {
	setTextInsideElement('.menuHome .homeLogo', 'WORKERS');
	setTextInsideElement('.menuHome #workers', 'BACK');
	setButtonTitle('.menuHome #workers', 'BACK');
	showElement('.menuHome #newWorker');
	showElement('.menuHome #allWorkers');
	showElement('.menuHome #tasks');
	hideElement('.menuHome #clients');
	hideElement('.menuHome #proposals');
	isHomeWorkersMenuActive = true;
};
function hideHomeWorkersMenu() {
	setTextInsideElement('.menuHome .homeLogo', 'SIS');
	setButtonTitle('.menuHome #workers', 'WORKERS');
	setTextInsideElement('.menuHome #workers', 'WORKERS');
	hideElement('.menuHome #newWorker');
	hideElement('.menuHome #allWorkers');
	hideElement('.menuHome #tasks');
	showElement('.menuHome #clients');
	showElement('.menuHome #proposals');
	isHomeWorkersMenuActive = false;
};
// username menu
var isUsernameMenuActive = false;
var usernameValue;
function hideUsernameMenu() {
	setButtonTitle('.menuHome #homeUsername', 'USER MENU');	
	hideElement('.menuHome #homeUsernameOut');
	isUsernameMenuActive = false;
};
function showUsernameMenu() {
	setButtonTitle('.menuHome #homeUsername', 'CLOSE USER MENU');	
	showElement('.menuHome #homeUsernameOut');
	isUsernameMenuActive = true;
};
//home main menu
function showHomeMainMenu(){
	showElement('.menuHome #clients');
	showElement('.menuHome #proposals');
	showElement('.menuHome #workers');
};
function hideHomeMainMenu(){
	hideHomeClientsMenu();
	hideHomeProposalsMenu();
	hideHomeWorkersMenu();
	hideElement('.menuHome #clients');
	hideElement('.menuHome #proposals');
	hideElement('.menuHome #workers');
};
// home user div
var isHomeCLientsProfileActive = false;
function showHomeCLientsProfile(){
	hideAllHomeTable();
	hideHomeMainMenu();
	showElement('.menuHome #exit_user_profile');
	showElement('.userProfile');
	setTextInsideElement('.menuHome .homeLogo', 'PROFILE');	
	isHomeCLientsProfileActive = true;
};
function hideHomeCLientsProfile(){
	setTextInsideElement('.menuHome .homeLogo', 'SIS');
	hideElement('.menuHome #exit_user_profile');
	hideElement('.userProfile');
	showHomeMainMenu();
	isHomeCLientsProfileActive = false;
};

// hide all home tables
function hideAllHomeTable() {
	// clients area
	hideElement('#clientsTableList');
};
// home all clients table
function showAllClientsTable() {
	showElement('#clientsTableList');
};

// clean function
function cleanUpContentFields() {
	setElementValue('.loginUpInput #username', '');
	setElementValue('.loginUpInput #password', '');
	setElementValue('.loginUpInput #confirm', '');
	setElementValue('.loginUpInput #name', '');
	setElementValue('.loginUpInput #surname', '');
	setElementValue('.loginUpInput #phone', '');
	setElementValue('.loginUpInput #mail', '');
};
function cleanLoginContentFields() {
	setElementValue('.loginInput input#username', '');
	setElementValue('.loginInput input#password', '');
};
// other
function makeFirstAfterClick(element){	
	 getElement(element).blur();	
	 hideAllHomeTable();
	 if(isUsernameMenuActive){
		 hideUsernameMenu(); 
	 }			
};

