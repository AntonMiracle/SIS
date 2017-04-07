package com.bondarenko.maker.data;

import java.util.Random;

import com.bondarenko.model.UserInformation;

public class UserInformationMaker {
	
	public UserInformation[] generateUserInformation(int quantity){			
		UserInformation[] uis = new UserInformation[quantity];
		String[] phones = generatePhones(quantity);
		for(int i = 0; i < uis.length; i++){
			UserInformation ui = new UserInformation();
			ui.setName(generateName());
			if(i%11 != 0){
				ui.setSurname("");
			}else{
				ui.setSurname(generateSurname());
			}			
			ui.setPhone(phones[i]);
			ui.setMail(generateMail(ui.getName(), ui.getSurname()));
			uis[i] = ui;
		}
		return uis;
	}

	private String generateName() {
		String[] names = new String[]{"Michael","Emma","Joshua","Madison","Matthew","Olivia","Ethan","Hannah","Andrew","Abigail",
				"Daniel","Isabella","William","Ashley","Joseph","Samantha","Christopher","Elizabeth","Anthony","Alexis","Ryan","Sarah"};
		return 	names[new Random().nextInt(names.length)];
	}

	private String generateSurname() {
		String[] surnames = new String[]{
				"Abramson","Hoggarth","Adamson","Holiday","Adderiy","Holmes","Addington","Howard","Adrian","Jacobson",
				"Albertson","James","Aldridge","Jeff","Allford","Jenkin","Alsopp","Jerome","Anderson","Johnson",
				"Andrews","Jones","Archibald","Keat"}; 
		return 	surnames[new Random().nextInt(surnames.length)];
	}
	
	
	private String[] generatePhones(int quantity){	
		Random rnd = new Random();
		String[] phones = new String[quantity];		
		for(int i = 0; i < phones.length; i++){
			String phone = String.valueOf(i); 			
			while(true){
				if(phone.length() == String.valueOf(quantity).length()){
					break;
				}
				phone = "0" + phone;
			}
			phone = String.valueOf(rnd.nextInt(10)) + String.valueOf(rnd.nextInt(10))  + String.valueOf(rnd.nextInt(10)) + phone; 			
			phones[i] = phone;
		}
		return phones;
	}
	
	private String generateMail(String name, String surname){
		if(surname != null && surname.length() >0 && name != null && name.length() > 0){
			return name + "@" + surname + ".com";
		}else{
			if(surname != null && surname.length() >0){
				return surname + "@" + surname + ".com";
			}else{
				return name + "@" + name + ".com";
			}
		}
	}
}
