package by.kachan.service;

import by.kachan.model.Flower;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FlowerService {

    public Double calculateTotalCostOfPlantsMaintenance(Flower flower) {
        LocalDate inFiveYears = LocalDate.now().plusYears(5);
        long daysOfFiveYears = ChronoUnit.DAYS.between(LocalDate.now(), inFiveYears);
        return daysOfFiveYears * ((flower.getWaterConsumptionPerDay() / 1000) * 1.39) + flower.getPrice();
    }
}
