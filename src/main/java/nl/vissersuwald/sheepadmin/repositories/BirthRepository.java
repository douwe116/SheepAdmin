package nl.vissersuwald.sheepadmin.repositories;

import nl.vissersuwald.sheepadmin.models.farming.Birth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthRepository extends JpaRepository<Birth, Long> {

    //Birth findByMotherIdAndYearOfBirth(Long motherId, Long year);
    //List<Birth> findByMotherId(long motherId);
}
