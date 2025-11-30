package nl.vissersuwald.sheepadmin.repositories;

import nl.vissersuwald.sheepadmin.models.farming.FosterReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FosterReferenceRepositoryTest {

    @Autowired
    private FosterReferenceRepository fosterReferenceRepository;

    @Test
    void testSaveAndFindFosterReference() {
        FosterReference fr = new FosterReference();
        fr.setYearOfBirth(2024L);
        fr.setFosterMotherId(5L);

        FosterReference saved = fosterReferenceRepository.save(fr);
        Optional<FosterReference> found = fosterReferenceRepository.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals(2024L, found.get().getYearOfBirth());
        assertEquals(5L, found.get().getMotherId());
    }
}
