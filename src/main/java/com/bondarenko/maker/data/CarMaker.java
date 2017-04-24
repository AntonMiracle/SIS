package com.bondarenko.maker.data;

import java.util.Random;
import java.util.Set;

import com.bondarenko.model.Car;
import com.bondarenko.model.User;
import com.bondarenko.service.CarService;
import com.bondarenko.service.UserService;

public class CarMaker {

	public void generateExampleData(int maxCarsByClient, CarService cs, UserService us) {
		Set<User> users = us.getUsers();
		int i = 0;
		for (User user : users) {
			int numberOfCars = new Random().nextInt(maxCarsByClient) + 1;
			for (int j = 0; j < numberOfCars; j++) {
				if (us.isHasRole(user.getId(), RoleMaker.ROLE_CLIENT)) {
					Car car = new Car();
					car.setUser(user);
					car.setNumber("number" + String.valueOf(i) + String.valueOf(j));
					car.setMark("mark" + String.valueOf(i + j));
					car.setModel("model" + String.valueOf(i + j));
					car.setDescription("description + description");
					car = cs.save(car);
					System.out.println("User ID : " + user.getId());
					System.out.println("Car ID : " + car.getId());
				}
			}
			i++;
		}
	}
}
