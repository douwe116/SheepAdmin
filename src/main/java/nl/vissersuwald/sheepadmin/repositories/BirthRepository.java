package nl.vissersuwald.sheepadmin.repositories;

import nl.vissersuwald.sheepadmin.models.Birth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BirthRepository extends JpaRepository<Birth, Long> {

    //Birth findByMotherIdAndYearOfBirth(Long motherId, Long year);
    //List<Birth> findByMotherId(long motherId);
}
