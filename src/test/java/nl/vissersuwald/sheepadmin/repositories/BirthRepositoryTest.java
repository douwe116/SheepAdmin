package nl.vissersuwald.sheepadmin.repositories;

import nl.vissersuwald.sheepadmin.models.farming.Birth;
import nl.vissersuwald.sheepadmin.models.farming.Sheep;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BirthRepositoryTest {

    @Autowired
    private BirthRepository birthRepository;

    @Autowired
    private SheepRepository sheepRepository;

    @Test
    void testSaveBirthWithRelation() {
        Sheep mother = SheepTestLogic.createNewSheep("mother");

        Birth birth = new Birth();
        birth.setDateOfBirth(new Date());
        birth.setMotherId(mother.getId());

        Birth saved = birthRepository.save(birth);
        Optional<Birth> found = birthRepository.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals(mother.getId(), found.get().getMotherId());
    }
}
