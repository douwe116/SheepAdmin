package nl.vissersuwald.sheepadmin.repositories;

import nl.vissersuwald.sheepadmin.logic.SheepTestLogic;
import nl.vissersuwald.sheepadmin.models.farming.Birth;
import nl.vissersuwald.sheepadmin.models.farming.Sheep;
import nl.vissersuwald.sheepadmin.repositories.farming.BirthRepository;
import nl.vissersuwald.sheepadmin.repositories.farming.SheepRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class BirthRepositoryTest {

    @Autowired
    private BirthRepository birthRepository;

    @Autowired
    private SheepRepository sheepRepository;

    @Test
    void testSaveBirthWithRelation() {
        Date dateOfBirth =  new Date();
        Sheep mother = SheepTestLogic.createNewSheep("mother");
        Sheep savedMother = sheepRepository.save(mother);

        Birth birth = SheepTestLogic.createNewBirth(savedMother,dateOfBirth,1L,2L);
        Birth savedBirth = birthRepository.save(birth);

        Optional<Birth> found = birthRepository.findById(savedBirth.getBirthId());

        assertTrue(found.isPresent());
        assertThat(found.get().getBirthId().getMotherId()).isEqualTo(savedMother.getId());
        assertThat(found.get().getBirthId().getYearOfBirth()).isEqualTo(dateOfBirth.getYear());
        assertThat(found.get().getEwes()).isEqualTo(1L);
        assertThat(found.get().getRams()).isEqualTo(2L);
    }

    @Test
    void testFindBirthsByMother() {
        Date dateOfBirth1 =  new Date(124, 3, 31);
        Date dateOfBirth2 =  new Date(125, 3, 31);
        Sheep mother = SheepTestLogic.createNewSheep("mother");
        Sheep savedMother = sheepRepository.save(mother);

        Birth birth1 = SheepTestLogic.createNewBirth(savedMother,dateOfBirth1,1L,2L);
        Birth birth2 = SheepTestLogic.createNewBirth(savedMother,dateOfBirth2,2L,null);
        birthRepository.save(birth1);
        birthRepository.save(birth2);

        List<Birth> births1 = birthRepository.findByMother(savedMother);

        assertThat(births1.size()).isEqualTo(2);
        assertThat(births1.stream().map(b -> b.getDateOfBirth()).toList())
                .containsExactlyElementsOf(List.of(dateOfBirth1, dateOfBirth2));

        List<Birth> births2 = birthRepository.findById_YearOfBirth(2025);

        assertThat(births2.size()).isEqualTo(1);
        assertThat(births2.stream().map(b -> b.getDateOfBirth()).toList())
                .containsExactlyElementsOf(List.of(dateOfBirth2));
    }

    @Test
    void testFindByCompositeKeyParts() {
        Date dateOfBirth =  new Date();
        Sheep mother = SheepTestLogic.createNewSheep("mother");
        Sheep savedMother = sheepRepository.save(mother);

        Birth birth = SheepTestLogic.createNewBirth(savedMother,dateOfBirth,null,2L);
        Birth savedBirth = birthRepository.save(birth);

        Optional<Birth> found = birthRepository.findById_MotherIdAndId_YearOfBirth(mother.getId(), dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).getYear());
        assertTrue(found.isPresent());
    }

}
