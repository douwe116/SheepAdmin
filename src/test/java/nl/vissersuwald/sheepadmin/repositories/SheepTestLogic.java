package nl.vissersuwald.sheepadmin.repositories;

import nl.vissersuwald.sheepadmin.models.farming.Sheep;

import java.util.Date;

public class SheepTestLogic {

    public static Sheep createNewSheep(String sheepName) {
        Date dateOfBirth = new Date();
        return createNewSheep(sheepName, dateOfBirth);
    }

    public static Sheep createNewSheep(String sheepName, Date dateOfBirth) {
        Sheep sheep = new Sheep();
        sheep.setName(sheepName);
        sheep.setGender("F");
        sheep.setYearOfBirth(dateOfBirth.getYear());
        sheep.setDateOfBirth(dateOfBirth);
        return sheep;
    }
}
