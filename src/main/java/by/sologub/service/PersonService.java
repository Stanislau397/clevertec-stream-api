package by.sologub.service;

import by.sologub.model.House;
import by.sologub.model.Person;

import java.util.List;

public class PersonService {

    public List<Person> getHospitalPeople(List<House> houses) {
        List<Person> hospitalPeople = houses.stream()
                .filter(house -> house.getBuildingType().equals("Hospital"))
                .flatMap(house -> house.getPersonList().stream())
                .toList();
        return hospitalPeople;
    }
}
