function switchElementTextValueAndSize(element, fontSize, text) {
	element = $(element);
	if ((element.text() === 'B') || (element.text() === 'BACK')) {
		if ((element.text() === 'B')) {
			setTextInsideElement(element, 'BACK');
		} else {
			setTextInsideElement(element, 'B');
		}
	} else {
		if (fontSize !== 'false') {
			element.css('font-size', fontSize + 'px');
		}
		;
		setTextInsideElement(element, text);
	}
	;
};
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
/* ====================== */
/* = show/hide function = */
/* ====================== */
function hideElement(element) {
	element = $(element);
	element.hide();
};

function showElement(element) {
	element = $(element);
	element.show();
};

function hideLoginUpContentTips() {
	hideElement($('.loginUpInput #usernameTip'));
	hideElement($('.loginUpInput #passwordTip'));
	hideElement($('.loginUpInput #confirmTip'));
	hideElement($('.loginUpInput #nameTip'));
	hideElement($('.loginUpInput #phoneTip'));
};
// login UP content
function showUpContent() {
	setTextInsideElement($('.menuLogin #up'), 'BACK');
	hideElement($('.loginInput'));
	hideElement($('.menuLogin #info'));
	hideElement($('.loginInput td#loginTip'));
	cleanLoginContentFields();
	cleanUpContentFields();
	showElement($('.loginUpContent'));
	isUpContentActive = true;
};

function hideUpContent() {
	if ($('.menuLogin #up').text() === 'BACK') {
		setTextInsideElement($('.menuLogin #up'), 'UP');
	}
	;
	if ($('.menuLogin #up').text() === 'B') {
		setTextInsideElement($('.menuLogin #up'), 'U');
	}
	;
	setButtonTitle($('.menuLogin #up'), 'registration');
	hideElement($('.loginUpContent'));
	hideLoginUpContentTips();
	showElement($('.loginInput'));
	showElement($('.menuLogin #info'));
	isUpContentActive = false;
};

// Project information content
function hideProjectInformationContent() {
	setTextInsideElement($('.menuLogin #info'), 'i');
	showElement($('.menuLogin #up'));
	showElement($('.loginInput'));
	hideElement($('.loginProjectInfo'));
	isInfoContentActive = false;
};

function showProjectInformationContent() {
	setTextInsideElement($('.menuLogin #info'), 'BACK');
	showElement($('.loginProjectInfo'));
	hideElement($('.menuLogin #up'));
	hideElement($('.loginInput'));
	hideElement($('.loginInput td#loginTip'));
	isInfoContentActive = true;
};
// home clients menu
function showHomeClientsMenu() {
	setTextInsideElement($('.menuHome .homeLogo'), 'CLIENTS');
	setTextInsideElement($('.menuHome #clients'), 'BACK');
	setButtonTitle($('.menuHome #clients'), 'BACK');
	showElement($('.menuHome #newClient'));
	showElement($('.menuHome #allClients'));
	hideElement($('.menuHome #proposals'));
	hideElement($('.menuHome #workers'));
	isHomeClientsMenuActive = true;
};
function hideHomeClientsMenu() {
	setTextInsideElement($('.menuHome .homeLogo'), 'SIS');
	setButtonTitle($('.menuHome #clients'), 'CLIENTS');
	setTextInsideElement($('.menuHome #clients'), 'CLIENTS');
	hideElement($('.menuHome #newClient'));
	hideElement($('.menuHome #allClients'));
	showElement($('.menuHome #proposals'));
	showElement($('.menuHome #workers'));
	isHomeClientsMenuActive = false;
};
// home proposals menu
function showHomeProposalsMenu() {
	setTextInsideElement($('.menuHome .homeLogo'), 'PROPOSALS');
	setTextInsideElement($('.menuHome #proposals'), 'BACK');
	setButtonTitle($('.menuHome #proposals'), 'BACK');
	showElement($('.menuHome #newProposal'));
	showElement($('.menuHome #allProposals'));
	hideElement($('.menuHome #clients'));
	hideElement($('.menuHome #workers'));
	isHomeProposalsMenuActive = true;
};
function hideHomeProposalsMenu() {
	setTextInsideElement($('.menuHome .homeLogo'), 'SIS');
	setButtonTitle($('.menuHome #proposals'), 'PROPOSALS');
	setTextInsideElement($('.menuHome #proposals'), 'PROPOSALS');
	hideElement($('.menuHome #newProposal'));
	hideElement($('.menuHome #allProposals'));
	showElement($('.menuHome #clients'));
	showElement($('.menuHome #workers'));
	isHomeProposalsMenuActive = false;
};
// home workers menu
function showHomeWorkersMenu() {
	setTextInsideElement($('.menuHome .homeLogo'), 'WORKERS');
	setTextInsideElement($('.menuHome #workers'), 'BACK');
	setButtonTitle($('.menuHome #workers'), 'BACK');
	showElement($('.menuHome #newWorker'));
	showElement($('.menuHome #allWorkers'));
	showElement($('.menuHome #tasks'));
	hideElement($('.menuHome #clients'));
	hideElement($('.menuHome #proposals'));
	isHomeWorkersMenuActive = true;
};
function hideHomeWorkersMenu() {
	setTextInsideElement($('.menuHome .homeLogo'), 'SIS');
	setButtonTitle($('.menuHome #workers'), 'WORKERS');
	setTextInsideElement($('.menuHome #workers'), 'WORKERS');
	hideElement($('.menuHome #newWorker'));
	hideElement($('.menuHome #allWorkers'));
	hideElement($('.menuHome #tasks'));
	showElement($('.menuHome #clients'));
	showElement($('.menuHome #proposals'));
	isHomeWorkersMenuActive = false;
};
// username menu
function hideUsernameMenu() {
	setTextInsideElement($('.menuHome .homeLogo'), 'SIS');
	setButtonTitle($('.menuHome #homeUsername'), 'USERNAME');
	setTextInsideElement($('.menuHome #homeUsername'), usernameValue);
	hideElement($('.menuHome #homeUsernameOut'));
	isUsernameMenuActive = false;
};
function showUsernameMenu() {
	setTextInsideElement($('.menuHome .homeLogo'), usernameValue);
	setButtonTitle($('.menuHome #homeUsername'), 'CLOSE USERNAME MENU');
	setTextInsideElement($('.menuHome #homeUsername'), 'CLOSE');
	showElement($('.menuHome #homeUsernameOut'));
	isUsernameMenuActive = true;
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
/* ================== */
/* = clean function = */
/* ================== */
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

