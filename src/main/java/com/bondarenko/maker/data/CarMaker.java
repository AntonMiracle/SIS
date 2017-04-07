package com.bondarenko.maker.data;

import java.util.Random;

import com.bondarenko.model.Car;

public class CarMaker {

	public Car[] generateCars(int quantity) {
		Car[] cars = new Car[quantity];
		String[] numbers = generateNumber(quantity);
		for (int i = 0; i < cars.length; i++) {
			Car car = new Car();
			car.setNumber(numbers[i]);
			car.setModel(generateModel());
			car.setMark(generateMark(car.getModel()));
			car.setDescription(generateDescription());
			cars[i] = car;
		}
		return cars;
	}

	private String[] generateNumber(int quantity) {
		Random rnd = new Random();
		String[] numbers = new String[quantity];
		for (int i = 0; i < numbers.length; i++) {
			String number = String.valueOf(i);
			while (true) {
				if (number.length() == String.valueOf(quantity).length()) {
					break;
				}
				number = "0" + number;
			}
			number = "" + (char) (65 + rnd.nextInt(26)) + (char) (65 + rnd.nextInt(26)) + String.valueOf(rnd.nextInt(10))
					+ String.valueOf(rnd.nextInt(10)) + number + (char) (65 + rnd.nextInt(26))
					+ (char) (65 + rnd.nextInt(26));
			numbers[i] = number;
		}
		return numbers;
	}

	private String generateModel() {
		String[] models = new String[]{"BMW","VOLVO","TOYOTA","Mercedes","Honda"};
		return models[new Random().nextInt(models.length)];
	}

	private String generateMark(String model) {
		String[] marks = new String[]{};
		switch (model) {
			case "BMW": {
				marks = new String[]{"1 SERIES","2 SERIES","3 SERIES","3 SERIES GT","4 SERIES","5 SERIES","5 SERIES GT",
						"6 SERIES","7 SERIES","I8","M1","M3","M4","M6","M6 GRAN COUPE","X1","X3","X4","X5","X5 M","X6",
						"X6 M","Z4"};
				break;
			}
			case "VOLVO": {
				marks = new String[]{"C30","C70","S40","S60","S60 CROSS COUNTRY","S80","S90","V40 CROSS COUNTRY","V50",
						"V60","V60 CROSS COUNTRY","V70","V90 CROSS COUNTRY","XC60","XC70","XC90"};
				break;
			}
			case "TOYOTA": {
				marks = new String[]{"2000GT","4Runner","Allex","Allion","Alphard","Altezza","Altezza Wagon","Aristo",
						"Aurion","Avalon","Avensis","Avensis Wagon","Aygo","Auris","bB","Blade","Belta","Blizzard",
						"Brevis"};
				break;
			}
			case "Mercedes": {
				marks = new String[]{"W168","W169","W203","W204","W215","W216","W210","W211","W212","X164","W163",
						"W164","W220","W221","R170","R171","R230","SLR-McLare","722 Edition","SLS AMG"};
				break;
			}
			case "Honda": {
				marks = new String[]{"Crossroad","CR-V","CR-Z","Edix","Element","Elysion","EV Plus","FCX","Gienia",
						"Fit","Fit EV","Fit Shuttle","Freed","FR-V","HR-V","HSC","Insight","Integra","Jazz","L700/P700",
						"Lagreat"};
				break;
			}
			default: {
				marks = new String[]{""};
				break;
			}

		}

		return marks[new Random().nextInt(marks.length)];
	}

	private String generateDescription() {
		String[] descriptions = new String[]{"The cars we drive say a lot about us.",
				"We aren't addicted to oil, but our cars are.",
				"Self-driving cars are the natural extension of active safety and obviously something we should do.",
				"Older cars tend to drive like older cars. That is not for me.",
				"When it comes to cars, only two varieties of people are possible - cowards and fools.",
				"I love vintage cars because you can do so much more to them.",
				"Electric cars are not going to take the market by storm, but it's going to be a gradual improvement.",
				"Fast cars are my only vice.","A racing car is an animal with a thousand adjustments.",
				"Electric cars aren't pollution-free; they have to get their energy from somewhere. ",
				"Cars bring me sheer joy.","Fast cars like Porsches and Ferraris - they are things of beauty.",
				"I couldn't find the sports car of my dreams, so I built it myself.",
				"A car is like a mother-in-law - if you let it, it will rule your life.",
				"Flying cars are not a very efficient way to move things from one point to another.",""};
		return descriptions[new Random().nextInt(descriptions.length)];
	}
}
