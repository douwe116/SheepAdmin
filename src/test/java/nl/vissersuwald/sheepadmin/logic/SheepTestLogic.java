package nl.vissersuwald.sheepadmin.logic;

import nl.vissersuwald.sheepadmin.models.farming.Birth;
import nl.vissersuwald.sheepadmin.models.farming.Sheep;

import java.time.ZoneId;
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

    public static Birth createNewBirth(Sheep mother, Date dateOfBirth, Long ewes, Long rams) {
        Birth birth = new Birth(mother, dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).getYear());
        birth.setDateOfBirth(dateOfBirth);
        birth.setEwes(1L);
        birth.setRams(2L);
        return birth;
    }

}
