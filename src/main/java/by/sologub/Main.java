package by.sologub;

import by.sologub.model.Animal;
import by.sologub.model.Car;
import by.sologub.model.Flower;
import by.sologub.model.House;
import by.sologub.model.Person;
import by.sologub.service.CarService;
import by.sologub.service.FlowerService;
import by.sologub.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static by.sologub.validator.FlowerValidator.isVaseMadeOfAluminum;
import static by.sologub.validator.FlowerValidator.isVaseMadeOfGlass;
import static by.sologub.validator.FlowerValidator.isVaseMadeOfSteel;
import static by.sologub.validator.FlowerValidator.nameContainsC;
import static by.sologub.validator.FlowerValidator.nameStartsWithS;
import static by.sologub.validator.PersonValidator.isAgeGreaterOrEqual18;
import static by.sologub.validator.PersonValidator.isAgeLessOrEqual27;
import static by.sologub.validator.PersonValidator.isAgelessThan18;
import static by.sologub.validator.PersonValidator.isRetiredMan;
import static by.sologub.validator.PersonValidator.isRetiredWomen;
import static by.sologub.validator.PersonValidator.others;

public class Main {
    public static void main(String[] args) throws IOException {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
    }

    private static void task1() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() >= 10 && animal.getAge() <= 20)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .skip(7 * 2)
                .limit(7)
                .forEach(System.out::println);
    }

    private static void task2() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> "Japanese".equals(animal.getOrigin()))
                .peek(animal -> animal.setBread(animal.getBread().toUpperCase()))
                .filter(animal -> "Female".equals(animal.getGender()))
                .map(Animal::getBread)
                .forEach(System.out::println);
    }

    private static void task3() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() > 30)
                .map(Animal::getOrigin)
                .filter(origin -> origin.startsWith("A"))
                .distinct()
                .forEach(System.out::println);
    }

    private static void task4() throws IOException {
        List<Animal> animals = Util.getAnimals();
        long femaleAnimals = animals.stream()
                .filter(animal -> "Female".equals(animal.getGender()))
                .count();
        System.out.println(femaleAnimals);
    }

    private static void task5() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean anyHungarians = animals.stream()
                .filter(animal -> animal.getAge() >= 20 && animal.getAge() <= 30)
                .anyMatch(animal -> "Hungarian".equals(animal.getOrigin()));
        System.out.println(anyHungarians);
    }

    private static void task6() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean malesAndFemales = animals.stream()
                .allMatch(animal -> "Female".equals(animal.getGender())
                        || "Male".equals(animal.getGender()));
        System.out.println(malesAndFemales);
    }

    private static void task7() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean isOceania = animals.stream()
                .noneMatch(animal -> "Oceania".equals(animal.getOrigin()));
        System.out.println(isOceania);
    }

    private static void task8() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .forEach(System.out::println);
    }

    private static void task9() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .min(Comparator.comparingInt(chars -> chars.length))
                .ifPresent(shortestChar -> System.out.println(shortestChar.length));
    }

    private static void task10() throws IOException {
        List<Animal> animals = Util.getAnimals();
        long animalsAge = animals.stream()
                .mapToInt(Animal::getAge)
                .sum();
        System.out.println(animalsAge);
    }

    private static void task11() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> "Indonesian".equals(animal.getOrigin()))
                .mapToInt(Animal::getAge)
                .average()
                .ifPresent(System.out::println);
    }

    private static void task12() throws IOException {
        List<Person> people = Util.getPersons();
        people.stream()
                .filter(person -> "Male".equals(person.getGender()))
                .filter(person -> isAgeGreaterOrEqual18.or(isAgeLessOrEqual27).test(person))
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    private static void task13() throws IOException {
        List<House> houses = new ArrayList<>(Util.getHouses());
        List<Person> people = new ArrayList<>();

        houses.stream()
                .filter(house -> house.getBuildingType().equals("Hospital"))
                .flatMap(house -> house.getPersonList().stream())
                .forEach(people::add);

        houses.stream()
                .filter(house -> !house.getBuildingType().equals("Hospital"))
                .flatMap(house -> house.getPersonList().stream())
                .filter(isAgelessThan18.or(isRetiredWomen).or(isRetiredMan))
                .forEach(people::add);

        houses.stream()
                .filter(house -> !house.getBuildingType().equals("Hospital"))
                .flatMap(house -> house.getPersonList().stream())
                .filter(others)
                .forEach(people::add);

        people.stream()
                .limit(500)
                .forEach(System.out::println);
    }

    private static void task14() throws IOException {
        List<Car> cars = new ArrayList<>(Util.getCars());
        CarService carService = new CarService();

        Map<String, List<Car>> carsMap = new LinkedHashMap<>();
        carsMap.put("Turkmenistan", carService.findTurkmenistanCars(cars));
        carsMap.put("Uzbekistan", carService.findUzbekistanCars(cars));
        carsMap.put("Kazakhstan", carService.findKazakhstanCars(cars));
        carsMap.put("Kyrgyzstan", carService.findKyrgyzstanCars(cars));
        carsMap.put("Russian", carService.findRussianCars(cars));
        carsMap.put("Mongolia", carService.findMongolianCars(cars));

        Map<String, Double> transportationCost = carsMap.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        map -> map.getValue()
                                .stream()
                                .mapToDouble(carService::calculateTransportationCost)
                                .sum(),
                        (v1, v2) -> v1,
                        LinkedHashMap::new));

        double profit = carsMap.values()
                .stream()
                .flatMap(List::stream)
                .mapToDouble(carService::calculateProfit)
                .sum();

        System.out.println("Transportation cost for each country: ");
        transportationCost.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println("Profit made: " + profit);
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();
        FlowerService flowerService = new FlowerService();
        double flowersMaintenanceCost = flowers.stream()
                .sorted(Comparator.comparing(Flower::getOrigin).reversed()
                        .thenComparingInt(Flower::getPrice)
                        .thenComparing(Comparator.comparingDouble(Flower::getWaterConsumptionPerDay).reversed()))
                .filter(nameStartsWithS.and(nameContainsC))
                .filter(Flower::isShadePreferred)
                .filter(isVaseMadeOfGlass.or(isVaseMadeOfAluminum).or(isVaseMadeOfSteel))
                .mapToDouble(flowerService::calculateTotalCostOfPlantsMaintenance)
                .sum();
        System.out.println(flowersMaintenanceCost);
    }
}