package com.bondarenko.dao;

import java.util.List;

import com.bondarenko.model.Car;

public interface CarDao {
	public Car save(Car car) throws RuntimeException;

	public boolean delete(Car car) throws RuntimeException;

	public Car update(Car car) throws RuntimeException;

	public List<Car> getAll() throws RuntimeException;

	public List<Car> getByUserId(Long id) throws RuntimeException;

	public Car getById(Long id) throws RuntimeException;

	public Car getByNumber(String number) throws RuntimeException;

	public List<Car> getByModel(String model) throws RuntimeException;

	public List<Car> getByMark(String mark) throws RuntimeException;

	public boolean isNumberUnique(String number) throws RuntimeException;

}
