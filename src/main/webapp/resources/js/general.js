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