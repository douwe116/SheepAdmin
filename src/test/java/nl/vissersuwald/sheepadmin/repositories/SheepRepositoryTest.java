package nl.vissersuwald.sheepadmin.repositories;

import nl.vissersuwald.sheepadmin.models.farming.Sheep;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SheepRepositoryTest {

    @Autowired
    private SheepRepository sheepRepository;

    @Test
    void testSaveAndFindSheep() {
        String sheepName = "Test Sheep";
        Sheep sheep = SheepTestLogic.createNewSheep(sheepName);

        Sheep saved = sheepRepository.save(sheep);
        Optional<Sheep> found = sheepRepository.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals(sheepName, found.get().getName());
    }

}
