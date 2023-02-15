package by.kachan.validator;

import by.kachan.model.Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Predicate;

public class PersonValidator {

    private PersonValidator() {

    }

    public static final Predicate<Person> isAgelessThan18 = person -> Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() < 18;

    public static final Predicate<Person> isRetiredWomen = person -> person.getGender().equals("Female")
            && Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() >= 58;

    public static final Predicate<Person> isRetiredMan = person -> person.getGender().equals("Male")
            && Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() >= 63;

    public static final Predicate<Person> others = person -> Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() >= 18
            && Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() < 58;

    public static final Predicate<Person> isAgeGreaterOrEqual18 = person -> Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() >= 18;

    public static final Predicate<Person> isAgeLessOrEqual27 = person -> Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() <= 27;
}
